@extends('layouts.app')

@section('content')
<div class="cabecera-pagina">
    <a href="{{ route('chats.agregar') }}" class="boton-volver">&#8592; Volver</a>
    <h1>Chat privado</h1>
</div>

<form method="GET" action="{{ route('chats.agregar.privado') }}" class="formulario-buscar">
    <input type="text" name="buscar" value="{{ $busqueda }}" placeholder="Buscar usuario..." required>
    <button type="submit">Buscar</button>
</form>

@if(!empty($resultados))
    <div class="lista-usuarios">
        @foreach($resultados as $usuario)
            <div class="elemento-usuario">
                <div class="foto-perfil">
                    @if(!empty($usuario->foto))
                        <img src="data:image/{{ str_starts_with($usuario->foto, 'iVBOR') ? 'png' : 'jpeg' }};base64,{{ $usuario->foto }}" alt="{{ $usuario->nombre }}">
                    @else
                        {{ mb_substr($usuario->nombre, 0, 1) }}
                    @endif
                </div>
                <span class="nombre-usuario">{{ $usuario->nombre }}</span>
                <a href="{{ route('chats.confirmar.privado', ['idUsuario' => $usuario->id, 'nombre' => $usuario->nombre]) }}" class="boton-primario">Añadir</a>
            </div>
        @endforeach
    </div>
@elseif($busqueda !== '')
    <p class="texto-secundario">Sin resultados para "{{ $busqueda }}".</p>
@endif
@endsection
