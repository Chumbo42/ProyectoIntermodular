namespace Comms
{
    partial class FormAddGrupo
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
            this.etNombre = new System.Windows.Forms.TextBox();
            this.lbl2 = new System.Windows.Forms.Label();
            this.etBuscar = new System.Windows.Forms.TextBox();
            this.btBuscar = new System.Windows.Forms.Button();
            this.listResultados = new System.Windows.Forms.ListBox();
            this.btAgregar = new System.Windows.Forms.Button();
            this.lbl3 = new System.Windows.Forms.Label();
            this.listSeleccionados = new System.Windows.Forms.ListBox();
            this.btQuitar = new System.Windows.Forms.Button();
            this.lblError = new System.Windows.Forms.Label();
            this.btCrear = new System.Windows.Forms.Button();
            this.btVolver = new System.Windows.Forms.Button();
            this.SuspendLayout();


            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.White;
            this.lblTitulo.TabStop = false;
            this.lblTitulo.Text = "Crear grupo";
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;

            this.lblTitulo.Location = new System.Drawing.Point(0, 20);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(400, 40);
            this.lblTitulo.TabIndex = 0;


            this.lbl1.AutoSize = true;
            this.lbl1.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl1.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl1.TabStop = false;
            this.lbl1.Text = "Nombre del grupo";

            this.lbl1.Location = new System.Drawing.Point(30, 80);
            this.lbl1.Name = "lbl1";
            this.lbl1.TabIndex = 1;


            this.etNombre.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etNombre.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etNombre.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etNombre.ForeColor = System.Drawing.Color.White;

            this.etNombre.Location = new System.Drawing.Point(30, 100);
            this.etNombre.Name = "etNombre";
            this.etNombre.Size = new System.Drawing.Size(340, 27);
            this.etNombre.TabIndex = 2;


            this.lbl2.AutoSize = true;
            this.lbl2.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl2.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl2.TabStop = false;
            this.lbl2.Text = "Buscar usuario";

            this.lbl2.Location = new System.Drawing.Point(30, 148);
            this.lbl2.Name = "lbl2";
            this.lbl2.TabIndex = 3;


            this.etBuscar.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etBuscar.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etBuscar.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etBuscar.ForeColor = System.Drawing.Color.White;

            this.etBuscar.Location = new System.Drawing.Point(30, 168);
            this.etBuscar.Name = "etBuscar";
            this.etBuscar.Size = new System.Drawing.Size(230, 27);
            this.etBuscar.TabIndex = 4;


            this.btBuscar.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btBuscar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btBuscar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btBuscar.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btBuscar.ForeColor = System.Drawing.Color.White;
            this.btBuscar.Text = "Buscar";
            this.btBuscar.FlatAppearance.BorderSize = 0;
            this.btBuscar.Click += new System.EventHandler(this.btBuscar_Click);

            this.btBuscar.Location = new System.Drawing.Point(270, 166);
            this.btBuscar.Name = "btBuscar";
            this.btBuscar.Size = new System.Drawing.Size(100, 30);
            this.btBuscar.TabIndex = 5;


            this.listResultados.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.listResultados.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.listResultados.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.listResultados.ForeColor = System.Drawing.Color.White;

            this.listResultados.Location = new System.Drawing.Point(30, 208);
            this.listResultados.Name = "listResultados";
            this.listResultados.Size = new System.Drawing.Size(250, 90);
            this.listResultados.TabIndex = 6;


            this.btAgregar.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btAgregar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btAgregar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btAgregar.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btAgregar.ForeColor = System.Drawing.Color.White;
            this.btAgregar.Text = "Agregar >";
            this.btAgregar.FlatAppearance.BorderSize = 0;
            this.btAgregar.Click += new System.EventHandler(this.btAgregar_Click);

            this.btAgregar.Location = new System.Drawing.Point(290, 225);
            this.btAgregar.Name = "btAgregar";
            this.btAgregar.Size = new System.Drawing.Size(80, 30);
            this.btAgregar.TabIndex = 7;


            this.lbl3.AutoSize = true;
            this.lbl3.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl3.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl3.TabStop = false;
            this.lbl3.Text = "Integrantes";

            this.lbl3.Location = new System.Drawing.Point(30, 315);
            this.lbl3.Name = "lbl3";
            this.lbl3.TabIndex = 8;


            this.listSeleccionados.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.listSeleccionados.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.listSeleccionados.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.listSeleccionados.ForeColor = System.Drawing.Color.White;

            this.listSeleccionados.Location = new System.Drawing.Point(30, 335);
            this.listSeleccionados.Name = "listSeleccionados";
            this.listSeleccionados.Size = new System.Drawing.Size(250, 90);
            this.listSeleccionados.TabIndex = 9;


            this.btQuitar.BackColor = System.Drawing.Color.Transparent;
            this.btQuitar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btQuitar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btQuitar.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btQuitar.ForeColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.btQuitar.Text = "< Quitar";
            this.btQuitar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.btQuitar.Click += new System.EventHandler(this.btQuitar_Click);

            this.btQuitar.Location = new System.Drawing.Point(290, 352);
            this.btQuitar.Name = "btQuitar";
            this.btQuitar.Size = new System.Drawing.Size(80, 30);
            this.btQuitar.TabIndex = 10;


            this.lblError.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblError.ForeColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.lblError.TabStop = false;

            this.lblError.Location = new System.Drawing.Point(30, 438);
            this.lblError.Name = "lblError";
            this.lblError.Size = new System.Drawing.Size(340, 20);
            this.lblError.TabIndex = 11;


            this.btCrear.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btCrear.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btCrear.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btCrear.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btCrear.ForeColor = System.Drawing.Color.White;
            this.btCrear.Text = "Crear grupo";
            this.btCrear.FlatAppearance.BorderSize = 0;
            this.btCrear.Click += new System.EventHandler(this.btCrear_Click);

            this.btCrear.Location = new System.Drawing.Point(30, 465);
            this.btCrear.Name = "btCrear";
            this.btCrear.Size = new System.Drawing.Size(340, 38);
            this.btCrear.TabIndex = 12;


            this.btVolver.BackColor = System.Drawing.Color.Transparent;
            this.btVolver.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btVolver.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btVolver.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btVolver.ForeColor = System.Drawing.Color.FromArgb(150, 150, 150);
            this.btVolver.Text = "Volver";
            this.btVolver.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(80, 80, 80);
            this.btVolver.Click += new System.EventHandler(this.btVolver_Click);

            this.btVolver.Location = new System.Drawing.Point(30, 515);
            this.btVolver.Name = "btVolver";
            this.btVolver.Size = new System.Drawing.Size(340, 38);
            this.btVolver.TabIndex = 13;


            this.AcceptButton = this.btCrear;
            this.CancelButton = this.btVolver;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Black;
            this.ClientSize = new System.Drawing.Size(400, 595);
            this.Controls.Add(this.btVolver);
            this.Controls.Add(this.btCrear);
            this.Controls.Add(this.lblError);
            this.Controls.Add(this.btQuitar);
            this.Controls.Add(this.listSeleccionados);
            this.Controls.Add(this.lbl3);
            this.Controls.Add(this.btAgregar);
            this.Controls.Add(this.listResultados);
            this.Controls.Add(this.btBuscar);
            this.Controls.Add(this.etBuscar);
            this.Controls.Add(this.lbl2);
            this.Controls.Add(this.etNombre);
            this.Controls.Add(this.lbl1);
            this.Controls.Add(this.lblTitulo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FormAddGrupo";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Comms";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lbl1;
        private System.Windows.Forms.TextBox etNombre;
        private System.Windows.Forms.Label lbl2;
        private System.Windows.Forms.TextBox etBuscar;
        private System.Windows.Forms.Button btBuscar;
        private System.Windows.Forms.ListBox listResultados;
        private System.Windows.Forms.Button btAgregar;
        private System.Windows.Forms.Label lbl3;
        private System.Windows.Forms.ListBox listSeleccionados;
        private System.Windows.Forms.Button btQuitar;
        private System.Windows.Forms.Label lblError;
        private System.Windows.Forms.Button btCrear;
        private System.Windows.Forms.Button btVolver;
    }
}
