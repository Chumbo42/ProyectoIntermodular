namespace Comms
{
    partial class FormPerfil
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
            this.etCorreo = new System.Windows.Forms.TextBox();
            this.lbl3 = new System.Windows.Forms.Label();
            this.etContra = new System.Windows.Forms.TextBox();
            this.lblError = new System.Windows.Forms.Label();
            this.lblExito = new System.Windows.Forms.Label();
            this.btGuardar = new System.Windows.Forms.Button();
            this.btBorrar = new System.Windows.Forms.Button();
            this.btCerrarSesion = new System.Windows.Forms.Button();
            this.btVolver = new System.Windows.Forms.Button();
            this.SuspendLayout();


            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.White;
            this.lblTitulo.TabStop = false;
            this.lblTitulo.Text = "Mi perfil";
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;

            this.lblTitulo.Location = new System.Drawing.Point(0, 40);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(384, 40);
            this.lblTitulo.TabIndex = 0;


            this.lbl1.AutoSize = true;
            this.lbl1.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl1.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl1.TabStop = false;
            this.lbl1.Text = "Nombre de usuario";

            this.lbl1.Location = new System.Drawing.Point(60, 110);
            this.lbl1.Name = "lbl1";
            this.lbl1.TabIndex = 1;


            this.etNombre.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etNombre.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etNombre.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etNombre.ForeColor = System.Drawing.Color.White;

            this.etNombre.Location = new System.Drawing.Point(60, 130);
            this.etNombre.Name = "etNombre";
            this.etNombre.Size = new System.Drawing.Size(260, 27);
            this.etNombre.TabIndex = 2;


            this.lbl2.AutoSize = true;
            this.lbl2.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl2.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl2.TabStop = false;
            this.lbl2.Text = "Correo electrónico";

            this.lbl2.Location = new System.Drawing.Point(60, 175);
            this.lbl2.Name = "lbl2";
            this.lbl2.TabIndex = 3;


            this.etCorreo.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etCorreo.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etCorreo.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etCorreo.ForeColor = System.Drawing.Color.White;

            this.etCorreo.Location = new System.Drawing.Point(60, 195);
            this.etCorreo.Name = "etCorreo";
            this.etCorreo.Size = new System.Drawing.Size(260, 27);
            this.etCorreo.TabIndex = 4;


            this.lbl3.AutoSize = true;
            this.lbl3.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl3.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl3.TabStop = false;
            this.lbl3.Text = "Contraseña";

            this.lbl3.Location = new System.Drawing.Point(60, 240);
            this.lbl3.Name = "lbl3";
            this.lbl3.TabIndex = 5;


            this.etContra.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etContra.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etContra.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etContra.ForeColor = System.Drawing.Color.White;
            this.etContra.UseSystemPasswordChar = true;

            this.etContra.Location = new System.Drawing.Point(60, 260);
            this.etContra.Name = "etContra";
            this.etContra.Size = new System.Drawing.Size(260, 27);
            this.etContra.TabIndex = 6;


            this.lblError.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblError.ForeColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.lblError.TabStop = false;

            this.lblError.Location = new System.Drawing.Point(60, 305);
            this.lblError.Name = "lblError";
            this.lblError.Size = new System.Drawing.Size(260, 20);
            this.lblError.TabIndex = 7;


            this.lblExito.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblExito.ForeColor = System.Drawing.Color.FromArgb(80, 180, 80);
            this.lblExito.TabStop = false;

            this.lblExito.Location = new System.Drawing.Point(60, 305);
            this.lblExito.Name = "lblExito";
            this.lblExito.Size = new System.Drawing.Size(260, 20);
            this.lblExito.TabIndex = 8;


            this.btGuardar.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btGuardar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btGuardar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btGuardar.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btGuardar.ForeColor = System.Drawing.Color.White;
            this.btGuardar.Text = "Guardar cambios";
            this.btGuardar.FlatAppearance.BorderSize = 0;
            this.btGuardar.Click += new System.EventHandler(this.btGuardar_Click);

            this.btGuardar.Location = new System.Drawing.Point(60, 335);
            this.btGuardar.Name = "btGuardar";
            this.btGuardar.Size = new System.Drawing.Size(260, 38);
            this.btGuardar.TabIndex = 9;


            this.btBorrar.BackColor = System.Drawing.Color.Transparent;
            this.btBorrar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btBorrar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btBorrar.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btBorrar.ForeColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.btBorrar.Text = "Eliminar cuenta";
            this.btBorrar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.btBorrar.Click += new System.EventHandler(this.btBorrar_Click);

            this.btBorrar.Location = new System.Drawing.Point(60, 385);
            this.btBorrar.Name = "btBorrar";
            this.btBorrar.Size = new System.Drawing.Size(260, 38);
            this.btBorrar.TabIndex = 10;


            this.btCerrarSesion.BackColor = System.Drawing.Color.Transparent;
            this.btCerrarSesion.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btCerrarSesion.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btCerrarSesion.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btCerrarSesion.ForeColor = System.Drawing.Color.FromArgb(200, 140, 50);
            this.btCerrarSesion.Text = "Cerrar sesión";
            this.btCerrarSesion.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(200, 140, 50);
            this.btCerrarSesion.Click += new System.EventHandler(this.btCerrarSesion_Click);

            this.btCerrarSesion.Location = new System.Drawing.Point(60, 435);
            this.btCerrarSesion.Name = "btCerrarSesion";
            this.btCerrarSesion.Size = new System.Drawing.Size(260, 38);
            this.btCerrarSesion.TabIndex = 11;


            this.btVolver.BackColor = System.Drawing.Color.Transparent;
            this.btVolver.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btVolver.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btVolver.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btVolver.ForeColor = System.Drawing.Color.FromArgb(150, 150, 150);
            this.btVolver.Text = "Volver";
            this.btVolver.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(80, 80, 80);
            this.btVolver.Click += new System.EventHandler(this.btVolver_Click);

            this.btVolver.Location = new System.Drawing.Point(60, 485);
            this.btVolver.Name = "btVolver";
            this.btVolver.Size = new System.Drawing.Size(260, 38);
            this.btVolver.TabIndex = 12;


            this.AcceptButton = this.btGuardar;
            this.CancelButton = this.btVolver;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Black;
            this.ClientSize = new System.Drawing.Size(384, 565);
            this.Controls.Add(this.btVolver);
            this.Controls.Add(this.btCerrarSesion);
            this.Controls.Add(this.btBorrar);
            this.Controls.Add(this.btGuardar);
            this.Controls.Add(this.lblExito);
            this.Controls.Add(this.lblError);
            this.Controls.Add(this.etContra);
            this.Controls.Add(this.lbl3);
            this.Controls.Add(this.etCorreo);
            this.Controls.Add(this.lbl2);
            this.Controls.Add(this.etNombre);
            this.Controls.Add(this.lbl1);
            this.Controls.Add(this.lblTitulo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FormPerfil";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Perfil - Comms";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lbl1;
        private System.Windows.Forms.TextBox etNombre;
        private System.Windows.Forms.Label lbl2;
        private System.Windows.Forms.TextBox etCorreo;
        private System.Windows.Forms.Label lbl3;
        private System.Windows.Forms.TextBox etContra;
        private System.Windows.Forms.Label lblError;
        private System.Windows.Forms.Label lblExito;
        private System.Windows.Forms.Button btGuardar;
        private System.Windows.Forms.Button btBorrar;
        private System.Windows.Forms.Button btCerrarSesion;
        private System.Windows.Forms.Button btVolver;
    }
}
