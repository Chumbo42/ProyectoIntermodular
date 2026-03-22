@extends('layouts.app')

@section('content')
<div class="conversacion-header">
    <a href="{{ route('chats.inicio') }}" class="boton-volver">&#8592; Volver</a>
    <h2>{{ $nombreChat }}</h2>
</div>

<div class="mensajes-lista">
    @if(empty($mensajes))
        <p class="texto-secundario">Sin mensajes aún.</p>
    @else
        @foreach($mensajes as $mensaje)
            @if($mensaje->autor == session('usuario_id'))
                <div class="mensaje mensaje-propio">
                    <span class="mensaje-contenido">{{ $mensaje->contenido }}</span>
                </div>
            @else
                <div class="mensaje mensaje-otro">
                    @if(!$privado)
                        <span class="mensaje-autor">{{ $cacheNombres[$mensaje->autor] ?? $mensaje->autor }}</span>
                    @endif
                    <span class="mensaje-contenido">{{ $mensaje->contenido }}</span>
                </div>
            @endif
        @endforeach
    @endif
</div>

<div class="formulario-enviar">
    <form method="POST" action="{{ route('chats.enviar', $id) }}">
        @csrf
        <input type="hidden" name="privado" value="{{ $privado }}">
        <input type="hidden" name="nombreChat" value="{{ $nombreChat }}">
        <input type="text" name="contenido" placeholder="Escribe un mensaje..." required autocomplete="off">
        <button type="submit">Enviar</button>
    </form>
</div>
@endsection
