namespace Comms
{
    partial class FormAddPrivado
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
            this.lbl1 = new System.Windows.Forms.Label();
            this.etBuscar = new System.Windows.Forms.TextBox();
            this.btBuscar = new System.Windows.Forms.Button();
            this.listResultados = new System.Windows.Forms.ListBox();
            this.lblError = new System.Windows.Forms.Label();
            this.btAnadir = new System.Windows.Forms.Button();
            this.btVolver = new System.Windows.Forms.Button();
            this.SuspendLayout();


            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.White;
            this.lblTitulo.TabStop = false;
            this.lblTitulo.Text = "Chat privado";
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;

            this.lblTitulo.Location = new System.Drawing.Point(0, 30);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(360, 40);
            this.lblTitulo.TabIndex = 0;


            this.lbl1.AutoSize = true;
            this.lbl1.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl1.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl1.TabStop = false;
            this.lbl1.Text = "Nombre de usuario";

            this.lbl1.Location = new System.Drawing.Point(40, 95);
            this.lbl1.Name = "lbl1";
            this.lbl1.TabIndex = 1;


            this.etBuscar.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etBuscar.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etBuscar.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etBuscar.ForeColor = System.Drawing.Color.White;

            this.etBuscar.Location = new System.Drawing.Point(40, 115);
            this.etBuscar.Name = "etBuscar";
            this.etBuscar.Size = new System.Drawing.Size(200, 27);
            this.etBuscar.TabIndex = 2;


            this.btBuscar.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btBuscar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btBuscar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btBuscar.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btBuscar.ForeColor = System.Drawing.Color.White;
            this.btBuscar.Text = "Buscar";
            this.btBuscar.FlatAppearance.BorderSize = 0;
            this.btBuscar.Click += new System.EventHandler(this.btBuscar_Click);

            this.btBuscar.Location = new System.Drawing.Point(250, 113);
            this.btBuscar.Name = "btBuscar";
            this.btBuscar.Size = new System.Drawing.Size(70, 30);
            this.btBuscar.TabIndex = 3;


            this.listResultados.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.listResultados.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.listResultados.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.listResultados.ForeColor = System.Drawing.Color.White;

            this.listResultados.Location = new System.Drawing.Point(40, 160);
            this.listResultados.Name = "listResultados";
            this.listResultados.Size = new System.Drawing.Size(280, 130);
            this.listResultados.TabIndex = 4;


            this.lblError.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblError.ForeColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.lblError.TabStop = false;

            this.lblError.Location = new System.Drawing.Point(40, 300);
            this.lblError.Name = "lblError";
            this.lblError.Size = new System.Drawing.Size(280, 20);
            this.lblError.TabIndex = 5;


            this.btAnadir.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btAnadir.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btAnadir.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btAnadir.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btAnadir.ForeColor = System.Drawing.Color.White;
            this.btAnadir.Text = "Añadir";
            this.btAnadir.FlatAppearance.BorderSize = 0;
            this.btAnadir.Click += new System.EventHandler(this.btAnadir_Click);

            this.btAnadir.Location = new System.Drawing.Point(40, 330);
            this.btAnadir.Name = "btAnadir";
            this.btAnadir.Size = new System.Drawing.Size(280, 38);
            this.btAnadir.TabIndex = 6;


            this.btVolver.BackColor = System.Drawing.Color.Transparent;
            this.btVolver.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btVolver.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btVolver.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btVolver.ForeColor = System.Drawing.Color.FromArgb(150, 150, 150);
            this.btVolver.Text = "Volver";
            this.btVolver.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(80, 80, 80);
            this.btVolver.Click += new System.EventHandler(this.btVolver_Click);

            this.btVolver.Location = new System.Drawing.Point(40, 380);
            this.btVolver.Name = "btVolver";
            this.btVolver.Size = new System.Drawing.Size(280, 38);
            this.btVolver.TabIndex = 7;


            this.AcceptButton = this.btBuscar;
            this.CancelButton = this.btVolver;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Black;
            this.ClientSize = new System.Drawing.Size(360, 460);
            this.Controls.Add(this.btVolver);
            this.Controls.Add(this.btAnadir);
            this.Controls.Add(this.lblError);
            this.Controls.Add(this.listResultados);
            this.Controls.Add(this.btBuscar);
            this.Controls.Add(this.etBuscar);
            this.Controls.Add(this.lbl1);
            this.Controls.Add(this.lblTitulo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FormAddPrivado";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Comms";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lbl1;
        private System.Windows.Forms.TextBox etBuscar;
        private System.Windows.Forms.Button btBuscar;
        private System.Windows.Forms.ListBox listResultados;
        private System.Windows.Forms.Label lblError;
        private System.Windows.Forms.Button btAnadir;
        private System.Windows.Forms.Button btVolver;
    }
}
