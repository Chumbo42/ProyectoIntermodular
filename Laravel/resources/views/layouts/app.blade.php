<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Comms</title>
    <link rel="stylesheet" href="{{ asset('css/styles.css') }}">
</head>
<body>
    <nav class="barra-nav">
        <div class="barra-nav-marca">
            <img src="{{ asset('images/comms_logo.png') }}" alt="Comms" class="barra-nav-logo">
            <span>Comms</span>
        </div>
        <div class="barra-nav-enlaces">
            <a href="{{ route('chats.inicio') }}">Chats</a>
            <a href="{{ route('perfil') }}">{{ session('usuario_nombre') }}</a>
            <form method="POST" action="{{ route('cerrarSesion') }}" class="formulario-linea">
                @csrf
                <button type="submit">Salir</button>
            </form>
        </div>
    </nav>
    <main class="contenido-principal">
        @if(session('mensaje'))
            <div class="alerta alerta-exito">{{ session('mensaje') }}</div>
        @endif
        @if(session('error'))
            <div class="alerta alerta-error">{{ session('error') }}</div>
        @endif
        @yield('content')
    </main>
</body>
</html>
