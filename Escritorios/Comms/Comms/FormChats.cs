using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Comms
{
    public partial class FormChats : Form
    {
        public FormChats()
        {
            InitializeComponent();
        }

        private async void FormChats_Load(object sender, EventArgs e)
        {
            lblUsuario.Text = Sesion.usuario_nombre;
            await CargarChats();
        }

        private async Task CargarChats()
        {
            List<Chat> chats = await ApiRest.obtenerChats(Sesion.usuario_id);
            panelChats.Controls.Clear();

            if (chats.Count == 0)
            {
                System.Windows.Forms.Label lblVacio = new System.Windows.Forms.Label();
                lblVacio.Text = "No tienes chats todavía.";
                lblVacio.ForeColor = System.Drawing.Color.FromArgb(150, 150, 150);
                lblVacio.Font = new System.Drawing.Font("Segoe UI", 12F);
                lblVacio.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
                lblVacio.Dock = DockStyle.Fill;
                panelChats.Controls.Add(lblVacio);
                return;
            }

            panelChats.SuspendLayout();
            int y = 0;
            foreach (Chat chat in chats)
            {
                Celda celda = new Celda();
                celda.Nombre = chat.nombre;
                celda.Privado = chat.privado;
                celda.IdChat = chat.id;
                celda.Location = new System.Drawing.Point(0, y);
                celda.Width = panelChats.ClientSize.Width;
                celda.Height = 70;
                panelChats.Controls.Add(celda);
                y += 75;
            }
            panelChats.AutoScrollMinSize = new System.Drawing.Size(0, y);
            panelChats.ResumeLayout();
        }

        private void btPerfil_Click(object sender, EventArgs e)
        {
            new FormPerfil().ShowDialog();
            lblUsuario.Text = Sesion.usuario_nombre;
        }
    }
}
