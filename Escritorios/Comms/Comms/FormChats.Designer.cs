namespace Comms
{
    partial class FormChats
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
            this.lblTitulo = new System.Windows.Forms.Label();
            this.lblUsuario = new System.Windows.Forms.Label();
            this.btPerfil = new System.Windows.Forms.Button();
            this.panelChats = new System.Windows.Forms.Panel();
            this.barraNav.SuspendLayout();
            this.SuspendLayout();


            this.barraNav.BackColor = System.Drawing.Color.FromArgb(15, 15, 15);
            this.barraNav.Controls.Add(this.btPerfil);
            this.barraNav.Controls.Add(this.lblUsuario);
            this.barraNav.Controls.Add(this.lblTitulo);
            this.barraNav.Dock = System.Windows.Forms.DockStyle.Top;

            this.barraNav.Name = "barraNav";
            this.barraNav.Size = new System.Drawing.Size(404, 55);
            this.barraNav.TabIndex = 0;


            this.lblTitulo.AutoSize = true;
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(98, 77, 157);
            this.lblTitulo.Text = "Comms";

            this.lblTitulo.Location = new System.Drawing.Point(15, 13);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.TabIndex = 0;


            this.lblUsuario.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblUsuario.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.lblUsuario.TextAlign = System.Drawing.ContentAlignment.MiddleRight;

            this.lblUsuario.Location = new System.Drawing.Point(130, 13);
            this.lblUsuario.Name = "lblUsuario";
            this.lblUsuario.Size = new System.Drawing.Size(200, 30);
            this.lblUsuario.TabIndex = 1;


            this.btPerfil.BackColor = System.Drawing.Color.Transparent;
            this.btPerfil.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btPerfil.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btPerfil.Font = new System.Drawing.Font("Segoe UI", 14F);
            this.btPerfil.ForeColor = System.Drawing.Color.FromArgb(180, 180, 180);
            this.btPerfil.Text = "#";
            this.btPerfil.FlatAppearance.BorderSize = 0;
            this.btPerfil.Click += new System.EventHandler(this.btPerfil_Click);

            this.btPerfil.Location = new System.Drawing.Point(358, 10);
            this.btPerfil.Name = "btPerfil";
            this.btPerfil.Size = new System.Drawing.Size(40, 35);
            this.btPerfil.TabIndex = 2;


            this.panelChats.AutoScroll = true;
            this.panelChats.BackColor = System.Drawing.Color.Black;
            this.panelChats.Dock = System.Windows.Forms.DockStyle.Fill;

            this.panelChats.Name = "panelChats";
            this.panelChats.TabIndex = 1;


            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Black;
            this.ClientSize = new System.Drawing.Size(404, 641);
            this.Controls.Add(this.panelChats);
            this.Controls.Add(this.barraNav);
            this.Name = "FormChats";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Comms";
            this.Load += new System.EventHandler(this.FormChats_Load);
            this.barraNav.ResumeLayout(false);
            this.barraNav.PerformLayout();
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.Panel barraNav;
        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lblUsuario;
        private System.Windows.Forms.Button btPerfil;
        private System.Windows.Forms.Panel panelChats;
    }
}
