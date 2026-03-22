<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Services\ServicioApiUsuario;

class ChatController extends Controller
{
    private $servicio;

    public function __construct(ServicioApiUsuario $servicio)
    {
        $this->servicio = $servicio;
    }

    private function verificarSesion()
    {
        if (!session('usuario_id')) {
            return redirect()->route('iniciarSesion');
        }
        return null;
    }

    public function inicio()
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $chats = $this->servicio->obtenerChats(session('usuario_id')) ?? [];
        return view('chats', compact('chats'));
    }

    public function mostrar(Request $request, $id)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $privado = $request->query('privado', 0);
        $nombreChat = $request->query('nombre', '');
        $usuarioId = session('usuario_id');

        if ($privado) {
            $mensajes = $this->servicio->obtenerMensajesPrivado($id, $usuarioId) ?? [];
        } else {
            $mensajes = $this->servicio->obtenerMensajes($id) ?? [];
        }

        $cacheNombres = [];
        foreach ($mensajes as $mensaje) {
            $autorId = $mensaje->autor;
            if (!isset($cacheNombres[$autorId])) {
                $cacheNombres[$autorId] = trim($this->servicio->obtenerNombreUsuario($autorId));
            }
        }

        return view('conversacion', compact('mensajes', 'id', 'privado', 'nombreChat', 'cacheNombres'));
    }

    public function enviar(Request $request, $id)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $privado = $request->input('privado', 0);
        $contenido = $request->input('contenido');
        $autor = session('usuario_id');
        $fecha = date('Y-m-d\TH:i:s.000');

        $this->servicio->enviarMensaje($id, $privado, $autor, $contenido, $fecha);

        return redirect()->route('chats.mostrar', ['id' => $id, 'privado' => $privado, 'nombre' => $request->input('nombreChat', '')]);
    }

    public function mostrarAgregar()
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;
        return view('add');
    }

    public function mostrarAgregarPrivado(Request $request)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $resultados = [];
        $busqueda = $request->query('buscar', '');

        if ($busqueda !== '') {
            $resultados = $this->servicio->buscarUsuarios($busqueda, session('usuario_id')) ?? [];
        }

        return view('add_privado', compact('resultados', 'busqueda'));
    }

    public function confirmarAgregarPrivado($idUsuario)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $nombreUsuario = request()->query('nombre', '');
        return view('confirmar_add_privado', compact('idUsuario', 'nombreUsuario'));
    }

    public function crearPrivado(Request $request)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $this->servicio->crearPrivado(session('usuario_id'), $request->input('idUsuario'));
        return redirect()->route('chats.inicio')->with('mensaje', 'Chat privado creado');
    }

    public function mostrarAgregarGrupo(Request $request)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $resultados = [];
        $busqueda = $request->query('buscar', '');

        if ($busqueda !== '') {
            $resultados = $this->servicio->buscarUsuarios($busqueda, session('usuario_id')) ?? [];
        }

        $seleccionados = session('grupo_seleccionados', []);
        return view('add_grupo', compact('resultados', 'busqueda', 'seleccionados'));
    }

    public function agregarAGrupo(Request $request)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $seleccionados = session('grupo_seleccionados', []);
        $id = $request->input('idUsuario');
        $nombre = $request->input('nombreUsuario');

        $seleccionados[$id] = $nombre;
        session(['grupo_seleccionados' => $seleccionados]);

        return redirect()->route('chats.agregar.grupo', ['buscar' => $request->input('busqueda', '')]);
    }

    public function quitarDeGrupo(Request $request)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $seleccionados = session('grupo_seleccionados', []);
        $id = $request->input('idUsuario');
        unset($seleccionados[$id]);
        session(['grupo_seleccionados' => $seleccionados]);

        return redirect()->route('chats.agregar.grupo');
    }

    public function confirmarAgregarGrupo()
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $seleccionados = session('grupo_seleccionados', []);
        return view('confirmar_add_grupo', compact('seleccionados'));
    }

    public function crearGrupo(Request $request)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $nombreGrupo = $request->input('nombre_grupo');
        $seleccionados = session('grupo_seleccionados', []);

        $idGrupo = trim($this->servicio->crearGrupo($nombreGrupo));

        if (is_numeric($idGrupo)) {
            $this->servicio->agregarUsuarioGrupo($idGrupo, session('usuario_id'));
            foreach ($seleccionados as $idUsuario => $nombreUsuario) {
                $this->servicio->agregarUsuarioGrupo($idGrupo, $idUsuario);
            }
        }

        session()->forget('grupo_seleccionados');
        return redirect()->route('chats.inicio')->with('mensaje', 'Grupo creado correctamente');
    }
}
