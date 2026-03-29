using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Comms
{
    public partial class FormConversacion : Form
    {
        private readonly int chatId;
        private readonly string chatNombre;
        private readonly bool esPrivado;
        private readonly Dictionary<int, string> nombres = new Dictionary<int, string>();

        public FormConversacion(int chatId, string chatNombre, bool esPrivado)
        {
            InitializeComponent();
            this.chatId = chatId;
            this.chatNombre = chatNombre;
            this.esPrivado = esPrivado;
            lblNombre.Text = chatNombre;
        }

        private async void FormConversacion_Load(object sender, EventArgs e)
        {
            await CargarMensajes();
        }

        private async Task CargarMensajes()
        {
            List<Mensaje> mensajes = await ApiRest.obtenerMensajes(chatId, esPrivado, Sesion.usuario_id);
            foreach (Mensaje m in mensajes)
            {
                if (m.autor != Sesion.usuario_id && !nombres.ContainsKey(m.autor))
                    nombres[m.autor] = await ApiRest.getNombreUsuario(m.autor);
            }

            rtbMensajes.Clear();
            foreach (Mensaje m in mensajes)
            {
                string remitente = m.autor == Sesion.usuario_id ? "Yo" : nombres[m.autor];
                rtbMensajes.AppendText($"[{m.fecha:HH:mm}] {remitente}: {m.contenido}\n");
            }

            rtbMensajes.ScrollToCaret();
        }

        private async void btEnviar_Click(object sender, EventArgs e)
        {
            string texto = etMensaje.Text.Trim();
            if (string.IsNullOrEmpty(texto))

                return;

            etMensaje.Text = "";
            btEnviar.Enabled = false;
            await ApiRest.enviarMensaje(chatId, esPrivado, Sesion.usuario_id, texto);
            await CargarMensajes();
            btEnviar.Enabled = true;
        }

        private void btVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
