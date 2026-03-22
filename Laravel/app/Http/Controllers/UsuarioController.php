<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Services\ServicioApiUsuario;

class UsuarioController extends Controller
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

    public function perfil()
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;
        return view('perfil');
    }

    public function mostrarEditar()
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;
        return view('editar_cuenta');
    }

    public function editar(Request $request)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $id = session('usuario_id');
        $nombre = $request->input('nombre');
        $correo = $request->input('correo');
        $contra = $request->input('contra');

        $this->servicio->editarCuenta($id, $nombre, $correo, $contra);

        session([
            'usuario_nombre' => $nombre,
            'usuario_correo' => $correo,
            'usuario_contra' => $contra,
        ]);

        return redirect()->route('perfil')->with('mensaje', 'Cuenta actualizada correctamente');
    }

    public function borrar(Request $request)
    {
        if ($redireccion = $this->verificarSesion()) return $redireccion;

        $nombre = session('usuario_nombre');
        $contra = session('usuario_contra');

        $this->servicio->borrarCuenta($nombre, $contra);
        $request->session()->flush();

        return redirect()->route('iniciarSesion')->with('mensaje', 'Cuenta eliminada');
    }
}
