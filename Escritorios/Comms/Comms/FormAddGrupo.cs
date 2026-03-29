using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Comms
{
    public partial class FormAddGrupo : Form
    {
        private readonly List<Usuario> seleccionados = new List<Usuario>();

        public FormAddGrupo()
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

        private void btAgregar_Click(object sender, EventArgs e)
        {
            if (listResultados.SelectedIndex < 0)

                return;

            var usuarios = listResultados.Tag as List<Usuario>;
            if (usuarios == null)

                return;

            Usuario u = usuarios[listResultados.SelectedIndex];
            foreach (Usuario ya in seleccionados)
            {
                if (ya.id == u.id)

                    return;
            }

            seleccionados.Add(u);
            listSeleccionados.Items.Add(u.nombre);
        }

        private void btQuitar_Click(object sender, EventArgs e)
        {
            int idx = listSeleccionados.SelectedIndex;
            if (idx < 0)

                return;

            seleccionados.RemoveAt(idx);
            listSeleccionados.Items.RemoveAt(idx);
        }

        private async void btCrear_Click(object sender, EventArgs e)
        {
            string nombre = etNombre.Text.Trim();
            if (string.IsNullOrEmpty(nombre))
            {
                lblError.Text = "El nombre no puede estar vacío.";

                return;
            }

            if (seleccionados.Count == 0)
            {
                lblError.Text = "Añade al menos un integrante.";

                return;
            }

            btCrear.Enabled = false;
            List<int> ids = new List<int>();
            foreach (Usuario u in seleccionados)
                ids.Add(u.id);

            bool ok = await ApiRest.crearGrupo(nombre, ids);
            if (ok)
            {
                MessageBox.Show($"Grupo '{nombre}' creado.", "Comms");
                this.Close();
            }
            else
            {
                lblError.Text = "Error al crear el grupo.";
                btCrear.Enabled = true;
            }
        }

        private void btVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
