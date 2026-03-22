<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Comms</title>
    <link rel="stylesheet" href="{{ asset('css/styles.css') }}">
</head>
<body class="fondo-autenticacion">
    <div class="contenedor-autenticacion">
        <h1 class="titulo-autenticacion">¡Bienvenido a Comms!</h1>

        <div class="envoltorio-logo">
            <img src="{{ asset('images/comms_logo.png') }}" alt="Comms">
        </div>

        @if(session('error'))
            <p class="alerta alerta-error">{{ session('error') }}</p>
        @endif
        @if(session('mensaje'))
            <p class="alerta alerta-exito">{{ session('mensaje') }}</p>
        @endif

        <form method="POST" action="{{ route('iniciarSesion.post') }}" class="formulario-autenticacion">
            @csrf
            <div class="campo-autenticacion">
                <label for="nombre">Nombre de usuario</label>
                <input type="text" id="nombre" name="nombre" required autocomplete="off">
            </div>
            <div class="campo-autenticacion">
                <label for="contra">Contraseña</label>
                <input type="password" id="contra" name="contra" required>
            </div>
            <div class="botones-autenticacion">
                <a href="{{ route('registro') }}" class="boton-autenticacion">Crear cuenta</a>
                <button type="submit" class="boton-autenticacion boton-autenticacion-primario">Iniciar sesión</button>
            </div>
        </form>
    </div>
</body>
</html>
