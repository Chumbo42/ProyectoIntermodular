using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace Comms
{
    public partial class FormLogin : Form
    {
        public FormLogin()
        {
            InitializeComponent();
        }

        private async void btLogin_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(etNombre.Text) || string.IsNullOrWhiteSpace(etContra.Text))
            {
                lblError.Text = "Rellena todos los campos.";
                return;
            }

            btLogin.Enabled = false;
            btLogin.Text = "Cargando...";

            var usuario = await ApiRest.iniciarSesion(etNombre.Text.Trim(), etContra.Text);

            if (usuario != null)
            {
                Sesion.usuario_id = Convert.ToInt32(usuario["id"]);
                Sesion.usuario_nombre = usuario["nombre"].ToString();
                Sesion.usuario_correo = usuario.ContainsKey("correo") ? usuario["correo"]?.ToString() ?? "" : "";
                Sesion.usuario_contra = etContra.Text;

                this.Hide();
                new FormChats().ShowDialog();
                this.Close();
            }
            else
            {
                lblError.Text = "Credenciales incorrectas.";
                btLogin.Enabled = true;
                btLogin.Text = "Iniciar sesión";
            }
        }

        private void btCrear_Click(object sender, EventArgs e)
        {
            this.Hide();
            new FormRegistro().ShowDialog();
            this.Show();
        }
    }
}
