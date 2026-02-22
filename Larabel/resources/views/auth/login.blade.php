<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>¡Bienvenido a comms!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #000;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-card {
            background-color: #1a1a1a;
            border: 1px solid #333;
            padding: 40px;
            width: 100%;
            max-width: 480px;
        }

        .login-card h1 {
            color: #fff;
            font-size: 1.6rem;
            font-weight: bold;
            text-align: center;
            margin-bottom: 32px;
        }

        .form-label {
            color: #ccc;
            font-size: 0.9rem;
        }

        .form-control {
            background-color: transparent;
            border: none;
            border-bottom: 1px solid #555;
            border-radius: 0;
            color: #fff;
            padding-left: 0;
        }

        .form-control:focus {
            background-color: transparent;
            border-bottom-color: #fff;
            box-shadow: none;
            color: #fff;
        }

        .form-control::placeholder {
            color: #444;
        }

        .btn-outline-light {
            border-radius: 0;
            letter-spacing: 0.05em;
            font-size: 0.85rem;
            padding: 10px 0;
        }

        .btn-outline-light:hover {
            background-color: #fff;
            color: #000;
        }

        .alert {
            border-radius: 0;
            font-size: 0.85rem;
        }
    </style>
</head>
<body>

    <div class="login-card">
        <h1>¡Bienvenido a comms!</h1>

        {{-- Mensajes de error --}}
        @if ($errors->any())
            <div class="alert alert-danger">
                {{ $errors->first() }}
            </div>
        @endif

        @if (session('status'))
            <div class="alert alert-success">
                {{ session('status') }}
            </div>
        @endif

        <form method="POST" action="{{ route('login') }}">
            @csrf

            <div class="mb-4">
                <label for="username" class="form-label">Nombre de usuario:</label>
                <input
                    type="text"
                    class="form-control @error('email') is-invalid @enderror"
                    id="username"
                    name="email"
                    value="{{ old('email') }}"
                    required
                    autofocus
                >
            </div>

            <div class="mb-4">
                <label for="password" class="form-label">Contraseña:</label>
                <input
                    type="password"
                    class="form-control @error('password') is-invalid @enderror"
                    id="password"
                    name="password"
                    required
                >
            </div>

            <div class="d-flex gap-3 mt-4">
                <a href="{{ route('register') }}" class="btn btn-outline-light w-50">
                    CREAR CUENTA
                </a>
                <button type="submit" class="btn btn-outline-light w-50">
                    INICIAR SESIÓN
                </button>
            </div>

        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>