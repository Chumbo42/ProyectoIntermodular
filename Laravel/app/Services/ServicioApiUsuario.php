<?php

namespace App\Services;

use Illuminate\Support\Facades\Http;

class ServicioApiUsuario
{
    private $urlBase;

    public function __construct()
    {
        $this->urlBase = 'http://127.0.0.1:8080/CommsServerConsultas/rest/usuarios';
    }

    public function iniciarSesion($nombre, $contra)
    {
        $respuesta = Http::get($this->urlBase . '/login', [
            'nombre' => $nombre,
            'contra' => $contra,
        ]);
        return json_decode($respuesta->body());
    }

    public function registrar($nombre, $contra)
    {
        $respuesta = Http::post($this->urlBase . '/registrar', [
            'nombre' => $nombre,
            'contraseña' => $contra,
        ]);
        return $respuesta->status();
    }

    public function nombreLibre($nombre)
    {
        $respuesta = Http::get($this->urlBase . '/nombres', [
            'nombre' => $nombre,
        ]);
        return $respuesta->status() === 404;
    }

    public function obtenerChats($id)
    {
        $respuesta = Http::get($this->urlBase . '/chats', ['id' => $id]);
        return json_decode($respuesta->body());
    }

    public function obtenerMensajes($id)
    {
        $respuesta = Http::get($this->urlBase . '/mensajes', ['id' => $id]);
        return json_decode($respuesta->body());
    }

    public function obtenerMensajesPrivado($idc, $idu)
    {
        $respuesta = Http::get($this->urlBase . '/mensajespriv', [
            'idc' => $idc,
            'idu' => $idu,
        ]);
        return json_decode($respuesta->body());
    }

    public function enviarMensaje($conversacion, $privado, $autor, $contenido, $fecha)
    {
        $respuesta = Http::withHeaders(['Content-Type' => 'application/json'])
            ->post($this->urlBase . '/enviar?' . http_build_query([
                'conversacion' => $conversacion,
                'privado' => $privado,
                'autor' => $autor,
            ]), [
                'contenido' => $contenido,
                'fecha' => $fecha,
            ]);
        return $respuesta->status();
    }

    public function buscarUsuarios($nombre, $id)
    {
        $respuesta = Http::get($this->urlBase . '/pornombre', [
            'nombre' => $nombre,
            'id' => $id,
        ]);
        return json_decode($respuesta->body());
    }

    public function crearPrivado($ua, $ub)
    {
        $respuesta = Http::get($this->urlBase . '/crearmd', [
            'ua' => $ua,
            'ub' => $ub,
        ]);
        return $respuesta->status();
    }

    public function crearGrupo($nombre)
    {
        $respuesta = Http::get($this->urlBase . '/creargr', ['nombre' => $nombre]);
        return $respuesta->body();
    }

    public function agregarUsuarioGrupo($idGrupo, $idUsuario)
    {
        $respuesta = Http::get($this->urlBase . '/addUsuarioGrupo', [
            'idGrupo' => $idGrupo,
            'idUsuario' => $idUsuario,
        ]);
        return $respuesta->status();
    }

    public function borrarCuenta($nombre, $contra)
    {
        $respuesta = Http::get($this->urlBase . '/borrar', [
            'nombre' => $nombre,
            'contra' => $contra,
        ]);
        return $respuesta->status();
    }

    public function obtenerNombreUsuario($id)
    {
        $respuesta = Http::get($this->urlBase . '/nombreusuario', ['id' => $id]);
        return $respuesta->body();
    }

    public function editarCuenta($id, $nombre, $correo, $contra)
    {
        $respuesta = Http::get($this->urlBase . '/editar', [
            'id' => $id,
            'nombre' => $nombre,
            'correo' => $correo,
            'contra' => $contra,
        ]);
        return $respuesta->status();
    }
}
