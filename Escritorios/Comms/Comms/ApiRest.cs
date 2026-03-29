using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Script.Serialization;

namespace Comms
{
    public static class ApiRest
    {
        private static readonly HttpClient cliente = new HttpClient { Timeout = TimeSpan.FromSeconds(10) };
        private static readonly JavaScriptSerializer json = new JavaScriptSerializer();
        private const string BASE_URL = "http://192.168.1.209:8080/CommsServerConsultas/rest/usuarios";

        public static async Task<Dictionary<string, object>> iniciarSesion(string nombre, string contra)
        {
            try
            {
                string url = $"{BASE_URL}/login?nombre={Uri.EscapeDataString(nombre)}&contra={Uri.EscapeDataString(contra)}";
                string resp = await cliente.GetStringAsync(url);
                return json.Deserialize<Dictionary<string, object>>(resp);
            }
            catch { return null; }
        }

        public static async Task<bool> registrar(string nombre, string contra)
        {
            try
            {
                string body = json.Serialize(new Dictionary<string, string>
                {
                    { "nombre", nombre },
                    { "contraseña", contra }
                });
                var contenido = new System.Net.Http.StringContent(body, System.Text.Encoding.UTF8, "application/json");
                var resp = await cliente.PostAsync($"{BASE_URL}/registrar", contenido);
                return resp.IsSuccessStatusCode;
            }
            catch { return false; }
        }

        public static async Task<bool> nombreLibre(string nombre)
        {
            try
            {
                string url = $"{BASE_URL}/nombres?nombre={Uri.EscapeDataString(nombre)}";
                var resp = await cliente.GetAsync(url);
                return resp.StatusCode == System.Net.HttpStatusCode.NotFound;
            }
            catch { return false; }
        }

        public static async Task<List<Chat>> obtenerChats(int id)
        {
            try
            {
                string url = $"{BASE_URL}/chats?id={id}";
                string resp = await cliente.GetStringAsync(url);
                var lista = json.Deserialize<List<Dictionary<string, object>>>(resp);
                var chats = new List<Chat>();
                foreach (var d in lista)
                {
                    chats.Add(new Chat
                    {
                        id = Convert.ToInt32(d["id"]),
                        nombre = d["nombre"].ToString(),
                        privado = Convert.ToBoolean(d["privado"])
                    });
                }
                return chats;
            }
            catch { return new List<Chat>(); }
        }

        public static async Task<bool> editarCuenta(int id, string nombre, string correo, string contra)
        {
            try
            {
                string url = $"{BASE_URL}/editar?id={id}&nombre={Uri.EscapeDataString(nombre)}&correo={Uri.EscapeDataString(correo)}&contra={Uri.EscapeDataString(contra)}";
                await cliente.GetStringAsync(url);
                return true;
            }
            catch { return false; }
        }

        public static async Task<bool> borrarCuenta(string nombre, string contra)
        {
            try
            {
                string url = $"{BASE_URL}/borrar?nombre={Uri.EscapeDataString(nombre)}&contra={Uri.EscapeDataString(contra)}";
                await cliente.GetStringAsync(url);
                return true;
            }
            catch { return false; }
        }

        public static async Task<List<Mensaje>> obtenerMensajes(int idChat, bool privado, int idUsuario)
        {
            try
            {
                string url = privado
                    ? $"{BASE_URL}/mensajespriv?idc={idChat}&idu={idUsuario}"
                    : $"{BASE_URL}/mensajes?id={idChat}";
                string resp = await cliente.GetStringAsync(url);
                var lista = json.Deserialize<List<Dictionary<string, object>>>(resp);
                var mensajes = new List<Mensaje>();
                foreach (var d in lista)
                {
                    mensajes.Add(new Mensaje
                    {
                        id = Convert.ToInt32(d["id"]),
                        autor = Convert.ToInt32(d["autor"]),
                        contenido = d["contenido"].ToString(),
                        fecha = Convert.ToDateTime(d["fecha"])
                    });
                }

                return mensajes;
            }
            catch { return new List<Mensaje>(); }
        }

        public static async Task<bool> enviarMensaje(int idChat, bool privado, int autor, string contenido)
        {
            try
            {
                string fecha = DateTime.Now.ToString("yyyy-MM-dd'T'HH:mm:ss.fff");
                string url = $"{BASE_URL}/enviar?conversacion={idChat}&privado={privado.ToString().ToLower()}&autor={autor}";
                string body = json.Serialize(new Dictionary<string, string>
                {
                    { "contenido", contenido },
                    { "fecha", fecha }
                });
                var contenidoHttp = new System.Net.Http.StringContent(body, System.Text.Encoding.UTF8, "application/json");
                var resp = await cliente.PostAsync(url, contenidoHttp);

                return resp.IsSuccessStatusCode;
            }
            catch { return false; }
        }

        public static async Task<List<Usuario>> buscarUsuarios(string nombre, int idUsuario)
        {
            try
            {
                string url = $"{BASE_URL}/pornombre?nombre={Uri.EscapeDataString(nombre)}&id={idUsuario}";
                string resp = await cliente.GetStringAsync(url);
                var lista = json.Deserialize<List<Dictionary<string, object>>>(resp);
                var usuarios = new List<Usuario>();
                foreach (var d in lista)
                {
                    usuarios.Add(new Usuario
                    {
                        id = Convert.ToInt32(d["id"]),
                        nombre = d["nombre"].ToString()
                    });
                }

                return usuarios;
            }
            catch { return new List<Usuario>(); }
        }

        public static async Task<bool> crearPrivado(int idA, int idB)
        {
            try
            {
                await cliente.GetStringAsync($"{BASE_URL}/crearmd?ua={idA}&ub={idB}");
                return true;
            }
            catch { return false; }
        }

        public static async Task<bool> crearGrupo(string nombre, List<int> integrantes)
        {
            try
            {
                string resp = await cliente.GetStringAsync($"{BASE_URL}/creargr?nombre={Uri.EscapeDataString(nombre)}");
                int idGrupo = Convert.ToInt32(resp.Trim());
                await cliente.GetStringAsync($"{BASE_URL}/addUsuarioGrupo?idGrupo={idGrupo}&idUsuario={Sesion.usuario_id}");
                foreach (int idU in integrantes)
                    await cliente.GetStringAsync($"{BASE_URL}/addUsuarioGrupo?idGrupo={idGrupo}&idUsuario={idU}");

                return true;
            }
            catch { return false; }
        }

        public static async Task<string> getNombreUsuario(int id)
        {
            try
            {
                return await cliente.GetStringAsync($"{BASE_URL}/nombreusuario?id={id}");
            }
            catch { return id.ToString(); }
        }
    }
}
