using System;
using System.Windows.Forms;

namespace Comms
{
    public partial class FormRegistro : Form
    {
        public FormRegistro()
        {
            InitializeComponent();
        }

        private async void btCrearCuenta_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(etNewName.Text) || string.IsNullOrWhiteSpace(etNewPass.Text) || string.IsNullOrWhiteSpace(etRepPass.Text))
            {
                lblError.Text = "Rellena todos los campos.";
                return;
            }
            if (etNewPass.Text != etRepPass.Text)
            {
                lblError.Text = "Las contraseñas no coinciden.";
                return;
            }

            btCrearCuenta.Enabled = false;
            btCrearCuenta.Text = "Cargando...";

            bool libre = await ApiRest.nombreLibre(etNewName.Text.Trim());
            if (!libre)
            {
                lblError.Text = "El nombre de usuario ya existe.";
                btCrearCuenta.Enabled = true;
                btCrearCuenta.Text = "Crear cuenta";
                return;
            }

            bool ok = await ApiRest.registrar(etNewName.Text.Trim(), etNewPass.Text);
            if (ok)
            {
                MessageBox.Show("¡Cuenta creada con éxito!", "Comms", MessageBoxButtons.OK, MessageBoxIcon.Information);
                this.Close();
            }
            else
            {
                lblError.Text = "Error al crear la cuenta.";
                btCrearCuenta.Enabled = true;
                btCrearCuenta.Text = "Crear cuenta";
            }
        }

        private void btCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
