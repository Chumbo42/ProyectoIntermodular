@extends('layouts.app')

@section('content')
<div class="caja-confirmacion">
    <div class="foto-perfil foto-perfil-grande">{{ mb_substr($nombreUsuario, 0, 1) }}</div>
    <h2>¿Iniciar chat con <strong>{{ $nombreUsuario }}</strong>?</h2>
    <div class="boton-grupo">
        <form method="POST" action="{{ route('chats.crear.privado') }}">
            @csrf
            <input type="hidden" name="idUsuario" value="{{ $idUsuario }}">
            <button type="submit" class="boton-primario">Confirmar</button>
        </form>
        <a href="{{ route('chats.agregar.privado') }}" class="boton-secundario">Cancelar</a>
    </div>
</div>
@endsection
