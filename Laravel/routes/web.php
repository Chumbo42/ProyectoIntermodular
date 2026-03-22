<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AutenticacionController;
use App\Http\Controllers\ChatController;
use App\Http\Controllers\UsuarioController;

Route::get('/', function () {
    return redirect()->route('iniciarSesion');
});

Route::get('/login', [AutenticacionController::class, 'mostrarLogin'])->name('iniciarSesion');
Route::post('/login', [AutenticacionController::class, 'iniciarSesion'])->name('iniciarSesion.post');
Route::get('/registro', [AutenticacionController::class, 'mostrarRegistro'])->name('registro');
Route::post('/registro', [AutenticacionController::class, 'registrar'])->name('registro.post');
Route::post('/logout', [AutenticacionController::class, 'cerrarSesion'])->name('cerrarSesion');

Route::get('/chats', [ChatController::class, 'inicio'])->name('chats.inicio');
Route::get('/chats/add', [ChatController::class, 'mostrarAgregar'])->name('chats.agregar');
Route::get('/chats/add/privado', [ChatController::class, 'mostrarAgregarPrivado'])->name('chats.agregar.privado');
Route::get('/chats/add/grupo', [ChatController::class, 'mostrarAgregarGrupo'])->name('chats.agregar.grupo');
Route::post('/chats/add/grupo/agregar', [ChatController::class, 'agregarAGrupo'])->name('chats.grupo.agregar');
Route::post('/chats/add/grupo/quitar', [ChatController::class, 'quitarDeGrupo'])->name('chats.grupo.quitar');
Route::get('/chats/confirmar-grupo', [ChatController::class, 'confirmarAgregarGrupo'])->name('chats.confirmar.grupo');
Route::post('/chats/crear-grupo', [ChatController::class, 'crearGrupo'])->name('chats.crear.grupo');
Route::get('/chats/confirmar-privado/{idUsuario}', [ChatController::class, 'confirmarAgregarPrivado'])->name('chats.confirmar.privado');
Route::post('/chats/crear-privado', [ChatController::class, 'crearPrivado'])->name('chats.crear.privado');
Route::get('/chats/{id}', [ChatController::class, 'mostrar'])->name('chats.mostrar');
Route::post('/chats/{id}/enviar', [ChatController::class, 'enviar'])->name('chats.enviar');

Route::get('/perfil', [UsuarioController::class, 'perfil'])->name('perfil');
Route::get('/perfil/editar', [UsuarioController::class, 'mostrarEditar'])->name('perfil.editar');
Route::post('/perfil/editar', [UsuarioController::class, 'editar'])->name('perfil.editar.post');
Route::post('/perfil/borrar', [UsuarioController::class, 'borrar'])->name('perfil.borrar');
