using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace Comms
{
    [DefaultProperty("Nombre")]
    public partial class Celda : UserControl
    {
        private string nombre = "";
        private bool privado = true;
        private int idChat;

        private static readonly Color COLOR_ACCENT = Color.FromArgb(98, 77, 157);

        [Category("Apariencia")]
        [Description("Nombre del chat a mostrar en la celda")]
        public string Nombre
        {
            get { return nombre; }
            set { nombre = value; lblNombre.Text = value; Refresh(); }
        }

        [Category("Apariencia")]
        [Description("Indica si el chat es privado (true) o de grupo (false)")]
        public bool Privado
        {
            get { return privado; }
            set { privado = value; lblTipo.Text = value ? "Privado" : "Grupo"; }
        }

        [Category("Datos")]
        [Description("Identificador del chat en el servidor")]
        public int IdChat
        {
            get { return idChat; }
            set { idChat = value; }
        }

        public Celda()
        {
            InitializeComponent();
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            base.OnPaint(e);
            Graphics g = e.Graphics;
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;

            using (SolidBrush pincel = new SolidBrush(COLOR_ACCENT))
                g.FillEllipse(pincel, 10, 10, 50, 50);

            string inicial = nombre.Length > 0 ? nombre[0].ToString().ToUpper() : "?";
            using (Font fuente = new Font("Segoe UI", 18F, FontStyle.Bold))
            {
                SizeF sz = g.MeasureString(inicial, fuente);
                g.DrawString(inicial, fuente, Brushes.White,
                    10 + (50 - sz.Width) / 2,
                    10 + (50 - sz.Height) / 2);
            }

            using (Pen lapiz = new Pen(Color.FromArgb(40, 40, 40)))
                g.DrawLine(lapiz, 0, this.Height - 1, this.Width, this.Height - 1);
        }

        protected override void OnResize(EventArgs e)
        {
            base.OnResize(e);
            if (lblNombre != null)
            {
                lblNombre.Width = this.Width - 90;
                lblTipo.Width = this.Width - 90;
            }
        }
    }
}
