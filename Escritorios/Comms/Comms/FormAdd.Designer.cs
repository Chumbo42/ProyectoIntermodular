namespace Comms
{
    partial class FormAdd
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
            this.lblTitulo = new System.Windows.Forms.Label();
            this.btPrivado = new System.Windows.Forms.Button();
            this.btGrupo = new System.Windows.Forms.Button();
            this.btVolver = new System.Windows.Forms.Button();
            this.SuspendLayout();


            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.White;
            this.lblTitulo.TabStop = false;
            this.lblTitulo.Text = "Nuevo chat";
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;

            this.lblTitulo.Location = new System.Drawing.Point(0, 50);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(320, 40);
            this.lblTitulo.TabIndex = 0;


            this.btPrivado.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btPrivado.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btPrivado.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btPrivado.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.btPrivado.ForeColor = System.Drawing.Color.White;
            this.btPrivado.Text = "Chat privado";
            this.btPrivado.FlatAppearance.BorderSize = 0;
            this.btPrivado.Click += new System.EventHandler(this.btPrivado_Click);

            this.btPrivado.Location = new System.Drawing.Point(60, 130);
            this.btPrivado.Name = "btPrivado";
            this.btPrivado.Size = new System.Drawing.Size(200, 50);
            this.btPrivado.TabIndex = 1;


            this.btGrupo.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btGrupo.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btGrupo.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btGrupo.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.btGrupo.ForeColor = System.Drawing.Color.White;
            this.btGrupo.Text = "Crear grupo";
            this.btGrupo.FlatAppearance.BorderSize = 0;
            this.btGrupo.Click += new System.EventHandler(this.btGrupo_Click);

            this.btGrupo.Location = new System.Drawing.Point(60, 200);
            this.btGrupo.Name = "btGrupo";
            this.btGrupo.Size = new System.Drawing.Size(200, 50);
            this.btGrupo.TabIndex = 2;


            this.btVolver.BackColor = System.Drawing.Color.Transparent;
            this.btVolver.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btVolver.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btVolver.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btVolver.ForeColor = System.Drawing.Color.FromArgb(150, 150, 150);
            this.btVolver.Text = "Volver";
            this.btVolver.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(80, 80, 80);
            this.btVolver.Click += new System.EventHandler(this.btVolver_Click);

            this.btVolver.Location = new System.Drawing.Point(60, 270);
            this.btVolver.Name = "btVolver";
            this.btVolver.Size = new System.Drawing.Size(200, 38);
            this.btVolver.TabIndex = 3;


            this.CancelButton = this.btVolver;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Black;
            this.ClientSize = new System.Drawing.Size(320, 350);
            this.Controls.Add(this.btVolver);
            this.Controls.Add(this.btGrupo);
            this.Controls.Add(this.btPrivado);
            this.Controls.Add(this.lblTitulo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FormAdd";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Comms";
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Button btPrivado;
        private System.Windows.Forms.Button btGrupo;
        private System.Windows.Forms.Button btVolver;
    }
}
