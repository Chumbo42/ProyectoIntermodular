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
                var contenido = new FormUrlEncodedContent(new[]
                {
                    new KeyValuePair<string, string>("nombre", nombre),
                    new KeyValuePair<string, string>("contraseña", contra)
                });
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
    }
}
