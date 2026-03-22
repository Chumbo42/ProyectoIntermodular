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
        <h1 class="titulo-autenticacion">Vamos a crearte una cuenta</h1>

        @if(session('error'))
            <p class="alerta alerta-error">{{ session('error') }}</p>
        @endif

        <form method="POST" action="{{ route('registro.post') }}" class="formulario-autenticacion">
            @csrf
            <div class="campo-autenticacion">
                <label for="nombre">Nombre de usuario</label>
                <input type="text" id="nombre" name="nombre" required autocomplete="off">
            </div>
            <div class="campo-autenticacion">
                <label for="contra">Contraseña</label>
                <input type="password" id="contra" name="contra" required>
            </div>
            <div class="campo-autenticacion">
                <label for="contra2">Repetir Contraseña</label>
                <input type="password" id="contra2" name="contra2" required>
            </div>
            <div class="botones-autenticacion">
                <a href="{{ route('iniciarSesion') }}" class="boton-autenticacion">Cancelar</a>
                <button type="submit" class="boton-autenticacion boton-autenticacion-primario">Crear cuenta</button>
            </div>
        </form>
    </div>
</body>
</html>
