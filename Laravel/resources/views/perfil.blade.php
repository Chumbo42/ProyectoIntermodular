@extends('layouts.app')

@section('content')
<div class="caja-perfil">
    <div class="foto-perfil foto-perfil-grande">
        @if(session('usuario_foto'))
            <img src="data:image/{{ str_starts_with(session('usuario_foto'), 'iVBOR') ? 'png' : 'jpeg' }};base64,{{ session('usuario_foto') }}" alt="{{ session('usuario_nombre') }}">
        @else
            {{ mb_substr(session('usuario_nombre'), 0, 1) }}
        @endif
    </div>
    <h2>{{ session('usuario_nombre') }}</h2>
    <p class="texto-secundario">{{ session('usuario_correo') ?: 'Sin correo registrado' }}</p>

    @if(session('mensaje'))
        <div class="alerta alerta-exito">{{ session('mensaje') }}</div>
    @endif

    <div class="boton-grupo-vertical">
        <a href="{{ route('perfil.editar') }}" class="boton-primario">Editar cuenta</a>
        <form method="POST" action="{{ route('perfil.borrar') }}" onsubmit="return confirm('¿Seguro que quieres eliminar tu cuenta? Esta acción no se puede deshacer.')">
            @csrf
            <button type="submit" class="boton-peligro">Eliminar cuenta</button>
        </form>
        <form method="POST" action="{{ route('cerrarSesion') }}">
            @csrf
            <button type="submit" class="boton-secundario">Cerrar sesión</button>
        </form>
    </div>
</div>
@endsection
