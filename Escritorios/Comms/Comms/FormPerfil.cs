using System;
using System.Windows.Forms;

namespace Comms
{
    public partial class FormPerfil : Form
    {
        public FormPerfil()
        {
            InitializeComponent();
            etNombre.Text = Sesion.usuario_nombre;
            etCorreo.Text = Sesion.usuario_correo;
            etContra.Text = Sesion.usuario_contra;
        }

        private async void btGuardar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(etNombre.Text))
            {
                lblError.Text = "El nombre no puede estar vacío.";
                lblExito.Text = "";
                return;
            }

            bool ok = await ApiRest.editarCuenta(
                Sesion.usuario_id,
                etNombre.Text.Trim(),
                etCorreo.Text.Trim(),
                etContra.Text
            );

            if (ok)
            {
                Sesion.usuario_nombre = etNombre.Text.Trim();
                Sesion.usuario_correo = etCorreo.Text.Trim();
                Sesion.usuario_contra = etContra.Text;
                lblError.Text = "";
                lblExito.Text = "Cambios guardados.";
            }
            else
            {
                lblError.Text = "Error al guardar los cambios.";
                lblExito.Text = "";
            }
        }

        private async void btBorrar_Click(object sender, EventArgs e)
        {
            DialogResult confirm = MessageBox.Show(
                "¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer.",
                "Eliminar cuenta",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning
            );

            if (confirm == DialogResult.Yes)
            {
                bool ok = await ApiRest.borrarCuenta(Sesion.usuario_nombre, Sesion.usuario_contra);
                if (ok)
                {
                    Sesion.usuario_id = 0;
                    Sesion.usuario_nombre = "";
                    Sesion.usuario_correo = "";
                    Sesion.usuario_contra = "";
                    MessageBox.Show("Cuenta eliminada.", "Comms");
                    Application.Exit();
                }
                else
                {
                    lblError.Text = "Error al eliminar la cuenta.";
                }
            }
        }

        private void btCerrarSesion_Click(object sender, EventArgs e)
        {
            Sesion.usuario_id = 0;
            Sesion.usuario_nombre = "";
            Sesion.usuario_correo = "";
            Sesion.usuario_contra = "";
            this.DialogResult = System.Windows.Forms.DialogResult.Abort;
            this.Close();
        }

        private void btVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
