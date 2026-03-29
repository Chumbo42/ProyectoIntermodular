namespace Comms
{
    partial class FormLogin
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
            this.lblBienvenido = new System.Windows.Forms.Label();
            this.lblNombreLabel = new System.Windows.Forms.Label();
            this.etNombre = new System.Windows.Forms.TextBox();
            this.lblContraLabel = new System.Windows.Forms.Label();
            this.etContra = new System.Windows.Forms.TextBox();
            this.lblError = new System.Windows.Forms.Label();
            this.btLogin = new System.Windows.Forms.Button();
            this.btCrear = new System.Windows.Forms.Button();
            this.SuspendLayout();


            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 28F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.lblTitulo.TabStop = false;
            this.lblTitulo.Text = "Comms";
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;

            this.lblTitulo.Location = new System.Drawing.Point(0, 60);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(384, 50);
            this.lblTitulo.TabIndex = 0;


            this.lblBienvenido.Font = new System.Drawing.Font("Segoe UI", 13F);
            this.lblBienvenido.ForeColor = System.Drawing.Color.White;
            this.lblBienvenido.TabStop = false;
            this.lblBienvenido.Text = "¡Bienvenido a Comms!";
            this.lblBienvenido.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;

            this.lblBienvenido.Location = new System.Drawing.Point(0, 120);
            this.lblBienvenido.Name = "lblBienvenido";
            this.lblBienvenido.Size = new System.Drawing.Size(384, 30);
            this.lblBienvenido.TabIndex = 1;


            this.lblNombreLabel.AutoSize = true;
            this.lblNombreLabel.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblNombreLabel.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lblNombreLabel.TabStop = false;
            this.lblNombreLabel.Text = "Nombre de usuario";

            this.lblNombreLabel.Location = new System.Drawing.Point(60, 190);
            this.lblNombreLabel.Name = "lblNombreLabel";
            this.lblNombreLabel.TabIndex = 2;


            this.etNombre.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etNombre.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etNombre.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etNombre.ForeColor = System.Drawing.Color.White;

            this.etNombre.Location = new System.Drawing.Point(60, 210);
            this.etNombre.Name = "etNombre";
            this.etNombre.Size = new System.Drawing.Size(260, 27);
            this.etNombre.TabIndex = 3;


            this.lblContraLabel.AutoSize = true;
            this.lblContraLabel.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblContraLabel.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lblContraLabel.TabStop = false;
            this.lblContraLabel.Text = "Contraseña";

            this.lblContraLabel.Location = new System.Drawing.Point(60, 255);
            this.lblContraLabel.Name = "lblContraLabel";
            this.lblContraLabel.TabIndex = 4;


            this.etContra.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etContra.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etContra.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etContra.ForeColor = System.Drawing.Color.White;
            this.etContra.UseSystemPasswordChar = true;

            this.etContra.Location = new System.Drawing.Point(60, 275);
            this.etContra.Name = "etContra";
            this.etContra.Size = new System.Drawing.Size(260, 27);
            this.etContra.TabIndex = 5;


            this.lblError.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblError.ForeColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.lblError.TabStop = false;

            this.lblError.Location = new System.Drawing.Point(60, 315);
            this.lblError.Name = "lblError";
            this.lblError.Size = new System.Drawing.Size(260, 20);
            this.lblError.TabIndex = 6;


            this.btLogin.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btLogin.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btLogin.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btLogin.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btLogin.ForeColor = System.Drawing.Color.White;
            this.btLogin.Text = "Iniciar sesión";
            this.btLogin.FlatAppearance.BorderSize = 0;
            this.btLogin.Click += new System.EventHandler(this.btLogin_Click);

            this.btLogin.Location = new System.Drawing.Point(60, 345);
            this.btLogin.Name = "btLogin";
            this.btLogin.Size = new System.Drawing.Size(260, 38);
            this.btLogin.TabIndex = 7;


            this.btCrear.BackColor = System.Drawing.Color.Transparent;
            this.btCrear.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btCrear.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btCrear.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btCrear.ForeColor = System.Drawing.Color.FromArgb(150, 150, 150);
            this.btCrear.Text = "Crear cuenta";
            this.btCrear.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(80, 80, 80);
            this.btCrear.Click += new System.EventHandler(this.btCrear_Click);

            this.btCrear.Location = new System.Drawing.Point(60, 395);
            this.btCrear.Name = "btCrear";
            this.btCrear.Size = new System.Drawing.Size(260, 38);
            this.btCrear.TabIndex = 8;


            this.AcceptButton = this.btLogin;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Black;
            this.Controls.Add(this.btCrear);
            this.Controls.Add(this.btLogin);
            this.Controls.Add(this.lblError);
            this.Controls.Add(this.etContra);
            this.Controls.Add(this.lblContraLabel);
            this.Controls.Add(this.etNombre);
            this.Controls.Add(this.lblNombreLabel);
            this.Controls.Add(this.lblBienvenido);
            this.Controls.Add(this.lblTitulo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Comms";

            this.ClientSize = new System.Drawing.Size(384, 461);
            this.Name = "FormLogin";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lblBienvenido;
        private System.Windows.Forms.Label lblNombreLabel;
        private System.Windows.Forms.TextBox etNombre;
        private System.Windows.Forms.Label lblContraLabel;
        private System.Windows.Forms.TextBox etContra;
        private System.Windows.Forms.Label lblError;
        private System.Windows.Forms.Button btLogin;
        private System.Windows.Forms.Button btCrear;
    }
}
