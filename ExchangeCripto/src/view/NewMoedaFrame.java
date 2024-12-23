package view;

import controller.ControllerAdmin;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewMoedaFrame extends javax.swing.JFrame {

    public NewMoedaFrame(ControllerAdmin ca, String label) {
        initComponents();
        this.ca = ca;
        jLabelName.setText(label);
        if(label.equalsIgnoreCase("Nova Moeda")){
            jBtnCadastrar.setText("Criar");
        }else{
            jBtnCadastrar.setText("Atualizar");
        }
        
        this.label = label;
    }

    
    public JTextField getjTxtCoinCotacao() {
        return jTxtCoinCotacao;
    }

    public JTextField getjTxtCoinName() {
        return jTxtCoinName;
    }

    public JTextField getjTxtCoinSigla() {
        return jTxtCoinSigla;
    }

    public JTextField getjTxtCoinTxC() {
        return jTxtCoinTxC;
    }

    public JTextField getjTxtCoinTxV() {
        return jTxtCoinTxV;
    }

    public JLabel getjLabelName() {
        return jLabelName;
    }

    public JButton getjBtnCadastrar() {
        return jBtnCadastrar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jTxtCoinName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jBtnCadastrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTxtCoinSigla = new javax.swing.JTextField();
        jTxtCoinCotacao = new javax.swing.JTextField();
        jTxtCoinTxV = new javax.swing.JTextField();
        jTxtCoinTxC = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(36, 25, 19));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logoMarromSmall.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(225, 214, 154));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 60));

        jLabelName.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelName.setText("Nova Moeda");
        jLabelName.setPreferredSize(new java.awt.Dimension(140, 60));

        jTxtCoinName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTxtCoinName.setBorder(null);
        jTxtCoinName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCoinNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Silga:");
        jLabel4.setPreferredSize(new java.awt.Dimension(140, 60));

        jBtnCadastrar.setBackground(new java.awt.Color(36, 25, 19));
        jBtnCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jBtnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCadastrar.setText("Criar");
        jBtnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCadastrarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Cotação:");
        jLabel5.setPreferredSize(new java.awt.Dimension(140, 60));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Taxa de Venda:");
        jLabel6.setPreferredSize(new java.awt.Dimension(140, 60));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Taxa de Compra:");
        jLabel7.setPreferredSize(new java.awt.Dimension(140, 60));

        jTxtCoinSigla.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTxtCoinSigla.setBorder(null);
        jTxtCoinSigla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCoinSiglaActionPerformed(evt);
            }
        });

        jTxtCoinCotacao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTxtCoinCotacao.setBorder(null);
        jTxtCoinCotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCoinCotacaoActionPerformed(evt);
            }
        });

        jTxtCoinTxV.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTxtCoinTxV.setBorder(null);
        jTxtCoinTxV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCoinTxVActionPerformed(evt);
            }
        });

        jTxtCoinTxC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTxtCoinTxC.setBorder(null);
        jTxtCoinTxC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCoinTxCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtCoinName, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtCoinSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtCoinCotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtCoinTxC, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtCoinTxV, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jBtnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtCoinName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtCoinSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtCoinCotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtCoinTxV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtCoinTxC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtCoinNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCoinNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCoinNameActionPerformed

    private void jBtnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadastrarActionPerformed
        if(label.equalsIgnoreCase("Nova Moeda")){
            ca.createMoeda();
        }else{
            ca.updateMoeda();
        }
        
    }//GEN-LAST:event_jBtnCadastrarActionPerformed

    private void jTxtCoinSiglaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCoinSiglaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCoinSiglaActionPerformed

    private void jTxtCoinCotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCoinCotacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCoinCotacaoActionPerformed

    private void jTxtCoinTxVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCoinTxVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCoinTxVActionPerformed

    private void jTxtCoinTxCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCoinTxCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCoinTxCActionPerformed

    private ControllerAdmin ca;
    private String label;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCadastrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTxtCoinCotacao;
    private javax.swing.JTextField jTxtCoinName;
    private javax.swing.JTextField jTxtCoinSigla;
    private javax.swing.JTextField jTxtCoinTxC;
    private javax.swing.JTextField jTxtCoinTxV;
    // End of variables declaration//GEN-END:variables
}
