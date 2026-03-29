namespace Comms
{
    partial class Celda
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
            this.lblNombre = new System.Windows.Forms.Label();
            this.lblTipo = new System.Windows.Forms.Label();
            this.SuspendLayout();


            this.lblNombre.AutoEllipsis = true;
            this.lblNombre.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold);
            this.lblNombre.ForeColor = System.Drawing.Color.White;
            this.lblNombre.TabStop = false;

            this.lblNombre.Location = new System.Drawing.Point(75, 12);
            this.lblNombre.Name = "lblNombre";
            this.lblNombre.Size = new System.Drawing.Size(200, 25);
            this.lblNombre.TabIndex = 0;


            this.lblTipo.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblTipo.ForeColor = System.Drawing.Color.FromArgb(150, 150, 150);
            this.lblTipo.TabStop = false;
            this.lblTipo.Text = "Privado";

            this.lblTipo.Location = new System.Drawing.Point(75, 40);
            this.lblTipo.Name = "lblTipo";
            this.lblTipo.Size = new System.Drawing.Size(100, 18);
            this.lblTipo.TabIndex = 1;


            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(26, 26, 26);
            this.Controls.Add(this.lblTipo);
            this.Controls.Add(this.lblNombre);
            this.Cursor = System.Windows.Forms.Cursors.Hand;

            this.Name = "Celda";
            this.Size = new System.Drawing.Size(300, 70);
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.Label lblNombre;
        private System.Windows.Forms.Label lblTipo;
    }
}
