<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Services\ServicioApiUsuario;

class AutenticacionController extends Controller
{
    private $servicio;

    public function __construct(ServicioApiUsuario $servicio)
    {
        $this->servicio = $servicio;
    }

    public function mostrarLogin()
    {
        return view('login');
    }

    public function iniciarSesion(Request $request)
    {
        $nombre = $request->input('nombre');
        $contra = $request->input('contra');

        $usuario = $this->servicio->iniciarSesion($nombre, $contra);

        if ($usuario && isset($usuario->id)) {
            session([
                'usuario_id' => $usuario->id,
                'usuario_nombre' => $usuario->nombre,
                'usuario_correo' => $usuario->correo ?? '',
                'usuario_contra' => $contra,
                'usuario_foto' => $usuario->foto ?? '',
            ]);
            return redirect()->route('chats.inicio');
        }

        return redirect()->route('iniciarSesion')->with('error', 'Credenciales incorrectas');
    }

    public function mostrarRegistro()
    {
        return view('registro');
    }

    public function registrar(Request $request)
    {
        $nombre = $request->input('nombre');
        $contra = $request->input('contra');
        $contra2 = $request->input('contra2');

        if ($contra !== $contra2) {
            return redirect()->route('registro')->with('error', 'Las contraseñas no coinciden');
        }

        $disponible = $this->servicio->nombreLibre($nombre);
        if (trim($disponible) !== $nombre) {
            return redirect()->route('registro')->with('error', 'El nombre de usuario ya está en uso');
        }

        $this->servicio->registrar($nombre, $contra);
        return redirect()->route('iniciarSesion')->with('mensaje', 'Cuenta creada correctamente');
    }

    public function cerrarSesion(Request $request)
    {
        $request->session()->flush();
        return redirect()->route('iniciarSesion');
    }
}
