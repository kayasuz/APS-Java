//<editor-fold defaultstate="collapsed" desc="The MIT License">
/*
 * The MIT License
 *
 * Copyright 2021 Gabriel Pavan de Moura, João da Silva Nodari, 
 * João Guilherme Aragão and Luciana Balsaneli Scabini.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *///</editor-fold>
package view;

/**
 *
 * @authors kayas, joaon, lucib, ?
 */
public class AdminViewHome extends javax.swing.JFrame {

    /**
     * Creates new form MenuView
     */
    public AdminViewHome() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SptMenu = new javax.swing.JSeparator();
        PainelMenu = new javax.swing.JPanel();
        LbLogo = new javax.swing.JLabel();
        BtnSair = new javax.swing.JButton();
        TgBtnHome = new javax.swing.JToggleButton();
        TgBtnLivros = new javax.swing.JToggleButton();
        TgBtnUsuarios = new javax.swing.JToggleButton();
        TgBtnEmprestimos = new javax.swing.JToggleButton();
        PainelHome = new javax.swing.JPanel();
        LbTitulo = new javax.swing.JLabel();
        LbBoasVindas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca JJLG - Administrador");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        SptMenu.setForeground(new java.awt.Color(0, 0, 0));
        SptMenu.setOrientation(javax.swing.SwingConstants.VERTICAL);

        PainelMenu.setBackground(new java.awt.Color(204, 204, 204));

        LbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/JJLG-icon.jpeg"))); // NOI18N
        LbLogo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BtnSair.setBackground(new java.awt.Color(255, 255, 255));
        BtnSair.setText("Sair");
        BtnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSair.setOpaque(false);
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });

        TgBtnHome.setBackground(new java.awt.Color(255, 255, 255));
        TgBtnHome.setText("Home");
        TgBtnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TgBtnHome.setEnabled(false);

        TgBtnLivros.setBackground(new java.awt.Color(255, 255, 255));
        TgBtnLivros.setText("Gerenciar livros");
        TgBtnLivros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TgBtnLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TgBtnLivrosActionPerformed(evt);
            }
        });

        TgBtnUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        TgBtnUsuarios.setText("Gerenciar usuários");
        TgBtnUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TgBtnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TgBtnUsuariosActionPerformed(evt);
            }
        });

        TgBtnEmprestimos.setBackground(new java.awt.Color(255, 255, 255));
        TgBtnEmprestimos.setText("Gerenciar empréstimos");
        TgBtnEmprestimos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TgBtnEmprestimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TgBtnEmprestimosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelMenuLayout = new javax.swing.GroupLayout(PainelMenu);
        PainelMenu.setLayout(PainelMenuLayout);
        PainelMenuLayout.setHorizontalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LbLogo)
                    .addComponent(BtnSair)
                    .addComponent(TgBtnHome)
                    .addComponent(TgBtnLivros)
                    .addComponent(TgBtnUsuarios)
                    .addComponent(TgBtnEmprestimos))
                .addGap(50, 50, 50))
        );

        PainelMenuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtnSair, TgBtnEmprestimos, TgBtnHome, TgBtnLivros, TgBtnUsuarios});

        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelMenuLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(LbLogo)
                .addGap(18, 18, 18)
                .addComponent(TgBtnHome)
                .addGap(18, 18, 18)
                .addComponent(TgBtnLivros)
                .addGap(18, 18, 18)
                .addComponent(TgBtnUsuarios)
                .addGap(18, 18, 18)
                .addComponent(TgBtnEmprestimos)
                .addGap(18, 18, 18)
                .addComponent(BtnSair)
                .addGap(33, 33, 33))
        );

        PainelMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BtnSair, TgBtnEmprestimos, TgBtnHome, TgBtnLivros, TgBtnUsuarios});

        PainelHome.setBackground(new java.awt.Color(255, 255, 255));

        LbTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LbTitulo.setText("Painel de Controle do Administrador");

        LbBoasVindas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LbBoasVindas.setText("Seja bem-vindo");

        javax.swing.GroupLayout PainelHomeLayout = new javax.swing.GroupLayout(PainelHome);
        PainelHome.setLayout(PainelHomeLayout);
        PainelHomeLayout.setHorizontalGroup(
            PainelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelHomeLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(PainelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LbBoasVindas)
                    .addComponent(LbTitulo))
                .addGap(110, 110, 110))
        );
        PainelHomeLayout.setVerticalGroup(
            PainelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelHomeLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(LbTitulo)
                .addGap(18, 18, 18)
                .addComponent(LbBoasVindas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(SptMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PainelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SptMenu, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(PainelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TgBtnLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TgBtnLivrosActionPerformed
        new AdminViewLivros().setVisible(true);
        dispose();
    }//GEN-LAST:event_TgBtnLivrosActionPerformed

    private void TgBtnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TgBtnUsuariosActionPerformed
        new AdminViewUsuarios().setVisible(true);
        dispose();
    }//GEN-LAST:event_TgBtnUsuariosActionPerformed

    private void TgBtnEmprestimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TgBtnEmprestimosActionPerformed
        new AdminViewEmprestimos().setVisible(true);
        dispose();
    }//GEN-LAST:event_TgBtnEmprestimosActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }//</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AdminViewHome().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSair;
    private javax.swing.JLabel LbBoasVindas;
    private javax.swing.JLabel LbLogo;
    private javax.swing.JLabel LbTitulo;
    private javax.swing.JPanel PainelHome;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JSeparator SptMenu;
    private javax.swing.JToggleButton TgBtnEmprestimos;
    private javax.swing.JToggleButton TgBtnHome;
    private javax.swing.JToggleButton TgBtnLivros;
    private javax.swing.JToggleButton TgBtnUsuarios;
    // End of variables declaration//GEN-END:variables
}
