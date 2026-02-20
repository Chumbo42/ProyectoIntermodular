package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
public class GestionaUsuarios {

    static Usuario u;

    @DefaultValue("valor por defecto")
    @QueryParam("valor")
    String text;

    private static final String URL = "jdbc:mariadb://localhost:3306/intermodular";
    private static final String USER = "root";
    private static final String PASS = "";

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (Exception e) {
            return null;
        }

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from intermodular.usuarios");) {
            while (rs.next()) {
                lista.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"),
                        rs.getString("contraseña"), rs.getBytes("foto")));

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return lista;
    }

    public ArrayList<Usuario> getUsuarios(String nombre) {
        ArrayList<Usuario> lista = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (Exception e) {
            return null;
        }

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from intermodular.usuarios WHERE nombre LIKE " + nombre + "%");) {
            while (rs.next()) {
                lista.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"),
                        rs.getString("contraseña"), rs.getBytes("foto")));

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return lista;
    }

    public int subirUsuario(String nombre, String contraseña) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (Exception e) {
            return 0;
        }

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                Statement st = conexion.createStatement();) {
            int filas = st.executeUpdate("Insert into intermodular.usuarios(nombre,contraseña) values (\'" + nombre
                    + "\',\'" + contraseña + "\')");
            return filas;

        } catch (Exception e) {

        }
        return 0;

    }

    public ArrayList<Chat> getChats(int id) {
        ArrayList<Chat> lista = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (Exception e) {
            return null;
        }

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(
                        "Select * from intermodular.grupos where id IN (SELECT id_grupo from intermodular.`usuarios-grupos` WHERE id_usuario = \'"
                                + id + "\')");) {
            while (rs.next()) {
                lista.add(new Grupo(rs.getInt("id"), rs.getString("nombre"), rs.getBytes("foto"),
                        rs.getString("descripcion")));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT DISTINCT u.* FROM intermodular.usuarios u JOIN intermodular.privados ON (privados.usuario1 = "
                                + id + " AND u.id = privados.usuario2) OR (privados.usuario2 = " + id
                                + " AND u.id = privados.usuario1)");) {
            while (rs.next()) {
                lista.add(new Chat(rs.getInt("id"), rs.getString("nombre"), rs.getBytes("foto"), true));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return lista;
    }

    public ArrayList<Mensaje> getMensajes(int id, Boolean privado) {
        ArrayList<Mensaje> lista = new ArrayList<>();

        try {

            Class.forName("org.mariadb.jdbc.Driver");

        } catch (Exception e) {
            return null;
        }

        String consulta;

        if (privado) {

            consulta = "SELECT m.* FROM intermodular.msgprivado m " +
                    "JOIN intermodular.privados p ON m.conversacion = p.id " +
                    "WHERE (p.usuario1 = " +
                    id +
                    " OR p.usuario2 = " +
                    id +
                    ")";
        } else {

            consulta = "SELECT * FROM intermodular.mensajes " +
                    "WHERE id_grupo = " +
                    id +
                    "";
        }

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(consulta)) {

            while (rs.next()) {
                lista.add(new Mensaje(
                        rs.getInt("id"),
                        rs.getString("contenido"),
                        rs.getDate("fecha"),
                        rs.getInt("autor")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public int subirMensaje(int idConversacion, int autor, Mensaje contenido, boolean privado) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            return 0;
        }

        String consulta;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = sdf.format(contenido.getFecha());

        if (privado) {

            consulta = "INSERT INTO intermodular.msgprivado (conversacion, autor, contenido, fecha) SELECT " +
                        "(SELECT id FROM intermodular.privados WHERE (usuario1 =" + autor + " AND usuario2 = "+ idConversacion +" ) OR (usuario1 = "+ idConversacion + " AND usuario2 = " + autor + ") LIMIT 1)," + 
                        autor + "," +
                        "'" + contenido.getContenido() + "', '" + 
                        fechaFormateada + "'";

                        
        }
        else{
            consulta = "INSERT INTO intermodular.mensajes (id_grupo, autor, contenido, fecha) " +
                    "VALUES (" + idConversacion + ", " + autor + ", '" + contenido.getContenido() + "', '" + fechaFormateada + "')" ;
        }
        System.out.println("Query ejecutada: " + consulta);
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                Statement st = conexion.createStatement()) {

            int filas = st.executeUpdate(consulta);
            return filas;

        } catch (Exception e) {
            System.err.println("Error al subir mensaje: " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response obtenerTodos() {

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(getUsuarios()) {
        };
        Response response = Response.ok(entity).build();

        return response;

    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/pornombre")
    public Response obtenerPorNombre(@QueryParam("nombre") String nombre) {

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(getUsuarios(nombre)) {
        };
        Response response = Response.ok(entity).build();

        return response;

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/login")
    public Response ver(@QueryParam("nombre") String nombre, @QueryParam("contra") String contra) {

        for (Usuario u : getUsuarios()) {
            if (u.getNombre().equals(nombre) && u.getContraseña().equals(contra)) {
                return Response.ok(u).build();
            }
        }
        return Response.status(Status.NOT_FOUND).entity("No se encontró").build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/nombres")
    public Response ver(@QueryParam("nombre") String nombre) {

        for (Usuario u : getUsuarios()) {
            if (u.getNombre().equals(nombre)) {
                return Response.ok(u.getNombre()).build();
            }
        }
        return Response.status(Status.NOT_FOUND).entity("No se encontró").build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/registrar")
    public Response guardar(Usuario u) {
        subirUsuario(u.getNombre(), u.getContraseña());
        return Response.ok(u).build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/chats")
    public Response ver(@QueryParam("id") int id) {

        ArrayList<Chat> chats = getChats(id);
        if (chats.isEmpty()) {
            return Response.status(Status.NOT_FOUND).entity("No se encontró").build();
        } else {
            return Response.ok(chats).build();
        }
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/mensajes")
    public Response obtenerMensajes(@QueryParam("id") int id, @QueryParam("privado") Boolean privado) {

        GenericEntity<List<Mensaje>> entity = new GenericEntity<List<Mensaje>>(getMensajes(id, privado)) {
        };
        Response response = Response.ok(entity).build();

        return response;

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/enviar")
    public Response subirMensaje(Mensaje contenido,
            @QueryParam("conversacion") int conversacion,
            @QueryParam("privado") boolean privado,
            @QueryParam("autor") int autor) {

        System.out.println( conversacion + autor);

        if (contenido == null) {
            System.out.println("Mensaje es NULL");
            return Response.status(Status.BAD_REQUEST)
                    .entity("Mensaje null").build();
        }
        int resultado = subirMensaje(conversacion, autor, contenido, privado);

        if (resultado > 0) {
            return Response.ok("Mensaje enviado correctamente").build();
        } else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error al enviar mensaje").build();
        }
    }

}
