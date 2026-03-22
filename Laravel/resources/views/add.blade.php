@extends('layouts.app')

@section('content')
<div class="caja-opciones">
    <h1>Nuevo chat</h1>
    <a href="{{ route('chats.agregar.privado') }}" class="boton-opcion">Chat privado</a>
    <a href="{{ route('chats.agregar.grupo') }}" class="boton-opcion">Crear grupo</a>
</div>
@endsection
