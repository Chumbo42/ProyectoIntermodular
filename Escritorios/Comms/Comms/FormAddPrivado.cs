using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Comms
{
    public partial class FormAddPrivado : Form
    {
        public FormAddPrivado()
        {
            InitializeComponent();
        }

        private async void btBuscar_Click(object sender, EventArgs e)
        {
            string nombre = etBuscar.Text.Trim();
            if (string.IsNullOrEmpty(nombre))

                return;

            List<Usuario> usuarios = await ApiRest.buscarUsuarios(nombre, Sesion.usuario_id);
            listResultados.Items.Clear();
            foreach (Usuario u in usuarios)
                listResultados.Items.Add(u.nombre);

            listResultados.Tag = usuarios;
        }

        private async void btAnadir_Click(object sender, EventArgs e)
        {
            if (listResultados.SelectedIndex < 0)

                return;

            var usuarios = listResultados.Tag as List<Usuario>;
            if (usuarios == null)

                return;

            Usuario seleccionado = usuarios[listResultados.SelectedIndex];
            btAnadir.Enabled = false;
            bool ok = await ApiRest.crearPrivado(Sesion.usuario_id, seleccionado.id);
            if (ok)
            {
                MessageBox.Show($"Chat con {seleccionado.nombre} creado.", "Comms");
                this.Close();
            }
            else
            {
                lblError.Text = "Error al crear el chat.";
                btAnadir.Enabled = true;
            }
        }

        private void btVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
