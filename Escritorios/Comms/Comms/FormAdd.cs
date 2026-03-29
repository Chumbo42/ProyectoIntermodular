using System.Windows.Forms;

namespace Comms
{
    public partial class FormAdd : Form
    {
        public FormAdd()
        {
            InitializeComponent();
        }

        private void btPrivado_Click(object sender, System.EventArgs e)
        {
            this.Hide();
            new FormAddPrivado().ShowDialog();
            this.Close();
        }

        private void btGrupo_Click(object sender, System.EventArgs e)
        {
            this.Hide();
            new FormAddGrupo().ShowDialog();
            this.Close();
        }

        private void btVolver_Click(object sender, System.EventArgs e)
        {
            this.Close();
        }
    }
}
