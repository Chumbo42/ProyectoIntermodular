@extends('layouts.app')

@section('content')
<div class="cabecera-pagina">
    <a href="{{ route('chats.agregar') }}" class="boton-volver">&#8592; Volver</a>
    <h1>Crear grupo</h1>
</div>

@if(!empty($seleccionados))
    <div class="seleccionados">
        <h3>Seleccionados ({{ count($seleccionados) }})</h3>
        <div class="lista-seleccionados">
            @foreach($seleccionados as $id => $nombre)
                <div class="seleccionado-item">
                    <span>{{ $nombre }}</span>
                    <form method="POST" action="{{ route('chats.grupo.quitar') }}" class="formulario-linea">
                        @csrf
                        <input type="hidden" name="idUsuario" value="{{ $id }}">
                        <button type="submit" class="boton-quitar">&#10005;</button>
                    </form>
                </div>
            @endforeach
        </div>
        <a href="{{ route('chats.confirmar.grupo') }}" class="boton-primario">Siguiente</a>
    </div>
@endif

<form method="GET" action="{{ route('chats.agregar.grupo') }}" class="formulario-buscar">
    <input type="text" name="buscar" value="{{ $busqueda }}" placeholder="Buscar usuario..." required>
    <button type="submit">Buscar</button>
</form>

@if(!empty($resultados))
    <div class="lista-usuarios">
        @foreach($resultados as $usuario)
            @if(!isset($seleccionados[$usuario->id]))
                <div class="elemento-usuario">
                    <div class="foto-perfil">
                        @if(!empty($usuario->foto))
                            <img src="data:image/{{ str_starts_with($usuario->foto, 'iVBOR') ? 'png' : 'jpeg' }};base64,{{ $usuario->foto }}" alt="{{ $usuario->nombre }}">
                        @else
                            {{ mb_substr($usuario->nombre, 0, 1) }}
                        @endif
                    </div>
                    <span class="nombre-usuario">{{ $usuario->nombre }}</span>
                    <form method="POST" action="{{ route('chats.grupo.agregar') }}" class="formulario-linea">
                        @csrf
                        <input type="hidden" name="idUsuario" value="{{ $usuario->id }}">
                        <input type="hidden" name="nombreUsuario" value="{{ $usuario->nombre }}">
                        <input type="hidden" name="busqueda" value="{{ $busqueda }}">
                        <button type="submit" class="boton-primario">Añadir</button>
                    </form>
                </div>
            @endif
        @endforeach
    </div>
@elseif($busqueda !== '')
    <p class="texto-secundario">Sin resultados para "{{ $busqueda }}".</p>
@endif
@endsection
