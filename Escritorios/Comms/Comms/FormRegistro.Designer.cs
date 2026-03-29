namespace Comms
{
    partial class FormRegistro
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
            this.etNewName = new System.Windows.Forms.TextBox();
            this.lbl2 = new System.Windows.Forms.Label();
            this.etNewPass = new System.Windows.Forms.TextBox();
            this.lbl3 = new System.Windows.Forms.Label();
            this.etRepPass = new System.Windows.Forms.TextBox();
            this.lblError = new System.Windows.Forms.Label();
            this.btCrearCuenta = new System.Windows.Forms.Button();
            this.btCancelar = new System.Windows.Forms.Button();
            this.SuspendLayout();


            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.White;
            this.lblTitulo.TabStop = false;
            this.lblTitulo.Text = "Vamos a crearte una cuenta";
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;

            this.lblTitulo.Location = new System.Drawing.Point(20, 50);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(340, 50);
            this.lblTitulo.TabIndex = 0;


            this.lbl1.AutoSize = true;
            this.lbl1.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl1.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl1.TabStop = false;
            this.lbl1.Text = "Nombre de usuario";

            this.lbl1.Location = new System.Drawing.Point(60, 130);
            this.lbl1.Name = "lbl1";
            this.lbl1.TabIndex = 1;


            this.etNewName.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etNewName.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etNewName.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etNewName.ForeColor = System.Drawing.Color.White;

            this.etNewName.Location = new System.Drawing.Point(60, 150);
            this.etNewName.Name = "etNewName";
            this.etNewName.Size = new System.Drawing.Size(260, 27);
            this.etNewName.TabIndex = 2;


            this.lbl2.AutoSize = true;
            this.lbl2.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl2.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl2.TabStop = false;
            this.lbl2.Text = "Contraseña";

            this.lbl2.Location = new System.Drawing.Point(60, 195);
            this.lbl2.Name = "lbl2";
            this.lbl2.TabIndex = 3;


            this.etNewPass.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etNewPass.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etNewPass.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etNewPass.ForeColor = System.Drawing.Color.White;
            this.etNewPass.UseSystemPasswordChar = true;

            this.etNewPass.Location = new System.Drawing.Point(60, 215);
            this.etNewPass.Name = "etNewPass";
            this.etNewPass.Size = new System.Drawing.Size(260, 27);
            this.etNewPass.TabIndex = 4;


            this.lbl3.AutoSize = true;
            this.lbl3.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lbl3.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lbl3.TabStop = false;
            this.lbl3.Text = "Repetir contraseña";

            this.lbl3.Location = new System.Drawing.Point(60, 260);
            this.lbl3.Name = "lbl3";
            this.lbl3.TabIndex = 5;


            this.etRepPass.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.etRepPass.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.etRepPass.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.etRepPass.ForeColor = System.Drawing.Color.White;
            this.etRepPass.UseSystemPasswordChar = true;

            this.etRepPass.Location = new System.Drawing.Point(60, 280);
            this.etRepPass.Name = "etRepPass";
            this.etRepPass.Size = new System.Drawing.Size(260, 27);
            this.etRepPass.TabIndex = 6;


            this.lblError.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblError.ForeColor = System.Drawing.Color.FromArgb(220, 80, 80);
            this.lblError.TabStop = false;

            this.lblError.Location = new System.Drawing.Point(60, 320);
            this.lblError.Name = "lblError";
            this.lblError.Size = new System.Drawing.Size(260, 20);
            this.lblError.TabIndex = 7;


            this.btCrearCuenta.BackColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.btCrearCuenta.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btCrearCuenta.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btCrearCuenta.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btCrearCuenta.ForeColor = System.Drawing.Color.White;
            this.btCrearCuenta.Text = "Crear cuenta";
            this.btCrearCuenta.FlatAppearance.BorderSize = 0;
            this.btCrearCuenta.Click += new System.EventHandler(this.btCrearCuenta_Click);

            this.btCrearCuenta.Location = new System.Drawing.Point(60, 350);
            this.btCrearCuenta.Name = "btCrearCuenta";
            this.btCrearCuenta.Size = new System.Drawing.Size(260, 38);
            this.btCrearCuenta.TabIndex = 8;


            this.btCancelar.BackColor = System.Drawing.Color.Transparent;
            this.btCancelar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btCancelar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btCancelar.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btCancelar.ForeColor = System.Drawing.Color.FromArgb(150, 150, 150);
            this.btCancelar.Text = "Cancelar";
            this.btCancelar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(80, 80, 80);
            this.btCancelar.Click += new System.EventHandler(this.btCancelar_Click);

            this.btCancelar.Location = new System.Drawing.Point(60, 400);
            this.btCancelar.Name = "btCancelar";
            this.btCancelar.Size = new System.Drawing.Size(260, 38);
            this.btCancelar.TabIndex = 9;


            this.AcceptButton = this.btCrearCuenta;
            this.CancelButton = this.btCancelar;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Black;
            this.ClientSize = new System.Drawing.Size(384, 481);
            this.Controls.Add(this.btCancelar);
            this.Controls.Add(this.btCrearCuenta);
            this.Controls.Add(this.lblError);
            this.Controls.Add(this.etRepPass);
            this.Controls.Add(this.lbl3);
            this.Controls.Add(this.etNewPass);
            this.Controls.Add(this.lbl2);
            this.Controls.Add(this.etNewName);
            this.Controls.Add(this.lbl1);
            this.Controls.Add(this.lblTitulo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FormRegistro";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Crear cuenta - Comms";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lbl1;
        private System.Windows.Forms.TextBox etNewName;
        private System.Windows.Forms.Label lbl2;
        private System.Windows.Forms.TextBox etNewPass;
        private System.Windows.Forms.Label lbl3;
        private System.Windows.Forms.TextBox etRepPass;
        private System.Windows.Forms.Label lblError;
        private System.Windows.Forms.Button btCrearCuenta;
        private System.Windows.Forms.Button btCancelar;
    }
}
