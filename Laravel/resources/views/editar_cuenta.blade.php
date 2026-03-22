@extends('layouts.app')

@section('content')
<div class="caja-central">
    <h1>Editar cuenta</h1>
    @if(session('error'))
        <p class="alerta alerta-error">{{ session('error') }}</p>
    @endif
    <form method="POST" action="{{ route('perfil.editar.post') }}">
        @csrf
        <table class="tabla-formulario">
            <tr>
                <td><label for="nombre">Nombre de usuario</label></td>
                <td><input type="text" id="nombre" name="nombre" value="{{ session('usuario_nombre') }}" required></td>
            </tr>
            <tr>
                <td><label for="correo">Correo</label></td>
                <td><input type="email" id="correo" name="correo" value="{{ session('usuario_correo') }}"></td>
            </tr>
            <tr>
                <td><label for="contra">Nueva contraseña</label></td>
                <td><input type="password" id="contra" name="contra" value="{{ session('usuario_contra') }}" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">Guardar cambios</button>
                    <a href="{{ route('perfil') }}">Cancelar</a>
                </td>
            </tr>
        </table>
    </form>
</div>
@endsection
