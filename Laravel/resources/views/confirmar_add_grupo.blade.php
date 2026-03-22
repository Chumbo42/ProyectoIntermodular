@extends('layouts.app')

@section('content')
<div class="caja-confirmacion">
    <h2>Nuevo grupo</h2>

    <h3>Integrantes</h3>
    <div class="lista-seleccionados">
        <div class="seleccionado-item">
            <div class="foto-perfil">{{ mb_substr(session('usuario_nombre'), 0, 1) }}</div>
            <span>{{ session('usuario_nombre') }} (tú)</span>
        </div>
        @foreach($seleccionados as $id => $nombre)
            <div class="seleccionado-item">
                <div class="foto-perfil">{{ mb_substr($nombre, 0, 1) }}</div>
                <span>{{ $nombre }}</span>
            </div>
        @endforeach
    </div>

    <form method="POST" action="{{ route('chats.crear.grupo') }}">
        @csrf
        <div class="formulario-nombre-grupo">
            <label for="nombre_grupo">Nombre del grupo</label>
            <input type="text" id="nombre_grupo" name="nombre_grupo" placeholder="Nombre del grupo" required>
        </div>
        <div class="boton-grupo">
            <button type="submit" class="boton-primario">Crear grupo</button>
            <a href="{{ route('chats.agregar.grupo') }}" class="boton-secundario">Cancelar</a>
        </div>
    </form>
</div>
@endsection
