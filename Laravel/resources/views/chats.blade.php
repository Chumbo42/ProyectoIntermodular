@extends('layouts.app')

@section('content')
<div class="cabecera-pagina">
    <h1>Chats</h1>
    <a href="{{ route('chats.agregar') }}" class="boton-primario">+ Nuevo chat</a>
</div>

@if(empty($chats))
    <p class="texto-secundario">No tienes chats aún.</p>
@else
    <div class="lista-chats">
        @foreach($chats as $chat)
            <a href="{{ route('chats.mostrar', ['id' => $chat->id, 'privado' => $chat->privado ? 1 : 0, 'nombre' => $chat->nombre]) }}" class="elemento-chat">
                <div class="foto-perfil">
                    @if(!empty($chat->foto))
                        <img src="data:image/{{ str_starts_with($chat->foto, 'iVBOR') ? 'png' : 'jpeg' }};base64,{{ $chat->foto }}" alt="{{ $chat->nombre }}">
                    @else
                        {{ mb_substr($chat->nombre, 0, 1) }}
                    @endif
                </div>
                <div class="datos-chat">
                    <span class="nombre-chat">{{ $chat->nombre }}</span>
                    <span class="tipo-chat">{{ $chat->privado ? 'Privado' : 'Grupo' }}</span>
                </div>
            </a>
        @endforeach
    </div>
@endif
@endsection
