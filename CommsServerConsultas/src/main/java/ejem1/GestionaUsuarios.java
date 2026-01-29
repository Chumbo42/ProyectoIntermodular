package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
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

    private static final String URL = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String USER = "root";
    private static final String PASS = "";


    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");


        } catch (Exception e) {
            return null;
        }

            
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("Select * from intermodular.usuarios");
        ) {
            while (rs.next()) {
                lista.add(new Usuario(rs.getInt("id"),rs.getString("nombre"), rs.getString("correo"),rs.getString("contraseña"), rs.getBytes("foto")));
                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
          
        return lista;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response obtenerTodos(){
       
        GenericEntity<List<Usuario>>entity = new GenericEntity<List<Usuario>>(getUsuarios()) {};
        Response response = Response.ok(entity).build();
        
        return response;  
        
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/login")
    public Response ver(@QueryParam("nombre") String nombre,@QueryParam("contra") String contra) {
        
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
   

}
