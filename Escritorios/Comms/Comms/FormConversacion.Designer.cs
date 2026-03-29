namespace Comms
{
    partial class FormConversacion
    {
        private System.ComponentModel.IContainer components = null;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.barraNav = new System.Windows.Forms.Panel();
            this.btVolver = new System.Windows.Forms.Button();
            this.lblNombre = new System.Windows.Forms.Label();
            this.rtbMensajes = new System.Windows.Forms.RichTextBox();
            this.barraInferior = new System.Windows.Forms.Panel();
            this.btEnviar = new System.Windows.Forms.Button();
            this.etMensaje = new System.Windows.Forms.TextBox();
            this.barraNav.SuspendLayout();
            this.barraInferior.SuspendLayout();
            this.SuspendLayout();


            this.barraNav.BackColor = System.Drawing.Color.FromArgb(15, 15, 15);
            this.barraNav.Controls.Add(this.lblNombre);
            this.barraNav.Controls.Add(this.btVolver);
            this.barraNav.Dock = System.Windows.Forms.DockStyle.Top;

            this.barraNav.Name = "barraNav";
            this.barraNav.Size = new System.Drawing.Size(440, 55);
            this.barraNav.TabIndex = 0;


            this.btVolver.BackColor = System.Drawing.Color.Transparent;
            this.btVolver.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btVolver.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btVolver.Font = new System.Drawing.Font("Segoe UI", 13F);
            this.btVolver.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.btVolver.Text = "<";
            this.btVolver.FlatAppearance.BorderSize = 0;
            this.btVolver.Click += new System.EventHandler(this.btVolver_Click);

            this.btVolver.Location = new System.Drawing.Point(5, 10);
            this.btVolver.Name = "btVolver";
            this.btVolver.Size = new System.Drawing.Size(35, 35);
            this.btVolver.TabIndex = 0;


            this.lblNombre.AutoEllipsis = true;
            this.lblNombre.Font = new System.Drawing.Font("Segoe UI", 13F, System.Drawing.FontStyle.Bold);
            this.lblNombre.ForeColor = System.Drawing.Color.White;
            this.lblNombre.TabStop = false;
            this.lblNombre.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;

            this.lblNombre.Location = new System.Drawing.Point(45, 10);
            this.lblNombre.Name = "lblNombre";
            this.lblNombre.Size = new System.Drawing.Size(380, 35);
            this.lblNombre.TabIndex = 1;


            this.rtbMensajes.BackColor = System.Drawing.Color.Black;
            this.rtbMensajes.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.rtbMensajes.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rtbMensajes.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.rtbMensajes.ForeColor = System.Drawing.Color.White;
            this.rtbMensajes.ReadOnly = true;
            this.rtbMensajes.ScrollBars = System.Windows.Forms.RichTextBoxScrollBars.Vertical;

            this.rtbMensajes.Name = "rtbMensajes";
            this.rtbMensajes.TabIndex = 1;


            this.barraInferior.BackColor = System.Drawing.Color.FromArgb(15, 15, 15);
            this.barraInferior.Controls.Add(this.etMensaje);
            this.barraInferior.Controls.Add(this.btEnviar);
            this.barraInferior.Dock = System.Windows.Forms.DockStyle.Bottom;

            this.barraInferior.Name = "barraInferior";
            this.barraInferior.Size = new System.Drawing.Size(440, 55);
            this.barraInferior.TabIndex = 2;


            this.etMensaje.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etMensaje.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etMensaje.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etMensaje.ForeColor = System.Drawing.Color.White;

            this.etMensaje.Location = new System.Drawing.Point(10, 13);
            this.etMensaje.Name = "etMensaje";
            this.etMensaje.Size = new System.Drawing.Size(330, 27);
            this.etMensaje.TabIndex = 0;


            this.btEnviar.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btEnviar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btEnviar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btEnviar.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btEnviar.ForeColor = System.Drawing.Color.White;
            this.btEnviar.Text = "Enviar";
            this.btEnviar.FlatAppearance.BorderSize = 0;
            this.btEnviar.Click += new System.EventHandler(this.btEnviar_Click);

            this.btEnviar.Location = new System.Drawing.Point(350, 10);
            this.btEnviar.Name = "btEnviar";
            this.btEnviar.Size = new System.Drawing.Size(80, 35);
            this.btEnviar.TabIndex = 1;


            this.AcceptButton = this.btEnviar;
            this.CancelButton = this.btVolver;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Black;
            this.ClientSize = new System.Drawing.Size(440, 641);
            this.Controls.Add(this.rtbMensajes);
            this.Controls.Add(this.barraInferior);
            this.Controls.Add(this.barraNav);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FormConversacion";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Comms";
            this.Load += new System.EventHandler(this.FormConversacion_Load);
            this.barraNav.ResumeLayout(false);
            this.barraInferior.ResumeLayout(false);
            this.barraInferior.PerformLayout();
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.Panel barraNav;
        private System.Windows.Forms.Button btVolver;
        private System.Windows.Forms.Label lblNombre;
        private System.Windows.Forms.RichTextBox rtbMensajes;
        private System.Windows.Forms.Panel barraInferior;
        private System.Windows.Forms.TextBox etMensaje;
        private System.Windows.Forms.Button btEnviar;
    }
}
