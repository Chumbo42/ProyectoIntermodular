<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        DB::table('usuarios')->insert(
            [
                'nombre'=>'iagodoval',
                'correo'=>'iagodv@correo.com',
                'contraseña'=>'ourensecampeon',
                'foto'=> file_get_contents(public_path("media\\img\\iagodoval.jpg")),
                'created_at'=>now(),
                'updated_at'=>now()
            ]
        );
        DB::table('usuarios')->insert(
            [
                'nombre'=>'skyhll',
                'correo'=>'skyhill@correo.com',
                'contraseña'=>'bandetinder',
                'foto'=> file_get_contents(public_path("media\\img\\skyhill.jpg")),
                'created_at'=>now(),
                'updated_at'=>now()
            ]
        );
        DB::table('usuarios')->insert(
            [
                'nombre'=>'lugonpa',
                'correo'=>'lugonpa@correo.com',
                'contraseña'=>'fernando33',
                'foto'=> file_get_contents(public_path("media\\img\\lugonpa.jpg")),
                'created_at'=>now(),
                'updated_at'=>now()
            ]
        );

        DB::table('grupos')->insert(
            [
                'nombre'=>'vivaschat',
                'descripcion'=>'colegiovivas',
                'foto'=> file_get_contents(public_path("media\\img\\vivas.jpg")),
                'created_at'=>now(),
                'updated_at'=>now()
            ]
        );
        DB::table('actividades')->insert(
            [
                'fecha'=>now(),
                'tipo'=>'1',
                'contenido'=> "",
                'id_usuario'=>'1',
                'id_grupo'=>'1',
                'created_at'=>now(),
                'updated_at'=>now()
            ]
        );
        DB::table('mensajes')->insert(
            [
                'contenido'=> "Yo no los hice",
                'fecha'=>now(),
                'id_usuario'=>'2',
                'id_grupo'=>'1',
                'created_at'=>now(),
                'updated_at'=>now()
            ]
        );
        DB::table('usuarios-actividades')->insert(
            [
                'id_usuario'=>'3',
                'id_actividad'=>'1',
                'datos'=>'<select>true</select>',
                'created_at'=>now(),
                'updated_at'=>now()
            ]
        );
        DB::table('usuarios-actividades')->insert(
            [
                'id_usuario'=>'2',
                'id_actividad'=>'1',
                'datos'=>'<select>false</select>',
                'created_at'=>now(),
                'updated_at'=>now()
            ]
        );


    }
}
