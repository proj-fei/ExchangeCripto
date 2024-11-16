package view;

import controller.ControllerAdmin;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Administrador;
import model.Investidor;

public class AdminHubFrame extends javax.swing.JFrame {

    
    public AdminHubFrame(Administrador adm) {
        initComponents();
        this.ca = new ControllerAdmin(this, adm);
    }

    public JButton getjBtnConta() {
        return jBtnConta;
    }

    public JButton getjBtnExcluirInvestidor() {
        return jBtnExcluirInvestidor;
    }

    public JButton getjBtnExcluirMoeda() {
        return jBtnExcluirMoeda;
    }

    public JButton getjBtnExtrato2() {
        return jBtnExtrato2;
    }

    public JButton getjBtnLogout() {
        return jBtnLogout;
    }

    public JButton getjBtnNewInvestidor() {
        return jBtnNewInvestidor;
    }

    public JButton getjBtnNewMoeda() {
        return jBtnNewMoeda;
    }

    public JButton getjBtnSobre() {
        return jBtnSobre;
    }

    public JButton getjBtnUpdateMoeda() {
        return jBtnUpdateMoeda;
    }

    public JTable getjTableMoedas() {
        return jTableMoedas;
    }

    public JTable getjTableUsers() {
        return jTableUsers;
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel16 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelWelcome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        jBtnNewInvestidor = new javax.swing.JButton();
        jBtnExtrato2 = new javax.swing.JButton();
        jBtnExcluirInvestidor = new javax.swing.JButton();
        jLabelWelcome2 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableMoedas = new javax.swing.JTable();
        jBtnExcluirMoeda = new javax.swing.JButton();
        jBtnUpdateMoeda = new javax.swing.JButton();
        jBtnNewMoeda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jBtnConta = new javax.swing.JButton();
        jBtnSobre = new javax.swing.JButton();
        jBtnLogout = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaCoin - Hub Admin");
        setBackground(new java.awt.Color(36, 25, 19));

        jPanel16.setBackground(new java.awt.Color(225, 214, 154));
        jPanel16.setForeground(new java.awt.Color(36, 25, 19));

        jSeparator3.setForeground(new java.awt.Color(36, 25, 19));

        jLabelWelcome.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabelWelcome.setForeground(new java.awt.Color(36, 25, 19));
        jLabelWelcome.setText("Investidores");

        jScrollPane1.setBackground(new java.awt.Color(36, 25, 24));

        jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CPF", "Nome", "Saldo (R$)", "BTC", "ETH", "XRP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUsers.setGridColor(new java.awt.Color(204, 204, 204));
        jTableUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsers);

        jBtnNewInvestidor.setBackground(new java.awt.Color(36, 25, 19));
        jBtnNewInvestidor.setForeground(new java.awt.Color(255, 255, 255));
        jBtnNewInvestidor.setText("Novo Investidor");
        jBtnNewInvestidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNewInvestidorActionPerformed(evt);
            }
        });

        jBtnExtrato2.setBackground(new java.awt.Color(36, 25, 19));
        jBtnExtrato2.setForeground(new java.awt.Color(255, 255, 255));
        jBtnExtrato2.setText("Consultar Extrato");
        jBtnExtrato2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExtrato2ActionPerformed(evt);
            }
        });

        jBtnExcluirInvestidor.setBackground(new java.awt.Color(153, 0, 0));
        jBtnExcluirInvestidor.setForeground(new java.awt.Color(255, 255, 255));
        jBtnExcluirInvestidor.setText("Excluir");
        jBtnExcluirInvestidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirInvestidorActionPerformed(evt);
            }
        });

        jLabelWelcome2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabelWelcome2.setForeground(new java.awt.Color(36, 25, 19));
        jLabelWelcome2.setText("Cripto Moedas");

        jSeparator6.setForeground(new java.awt.Color(36, 25, 19));

        jScrollPane3.setBackground(new java.awt.Color(36, 25, 24));

        jTableMoedas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Moeda", "Sigla", "Cotação", "Taxa de Venda", "Taxa de Compra", "Qtd. Comprada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMoedas.setGridColor(new java.awt.Color(204, 204, 204));
        jTableMoedas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMoedasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableMoedas);

        jBtnExcluirMoeda.setBackground(new java.awt.Color(153, 0, 0));
        jBtnExcluirMoeda.setForeground(new java.awt.Color(255, 255, 255));
        jBtnExcluirMoeda.setText("Excluir");
        jBtnExcluirMoeda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirMoedaActionPerformed(evt);
            }
        });

        jBtnUpdateMoeda.setBackground(new java.awt.Color(36, 25, 19));
        jBtnUpdateMoeda.setForeground(new java.awt.Color(255, 255, 255));
        jBtnUpdateMoeda.setText("Editar Moeda");
        jBtnUpdateMoeda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUpdateMoedaActionPerformed(evt);
            }
        });

        jBtnNewMoeda.setBackground(new java.awt.Color(36, 25, 19));
        jBtnNewMoeda.setForeground(new java.awt.Color(255, 255, 255));
        jBtnNewMoeda.setText("Nova Moeda");
        jBtnNewMoeda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNewMoedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .addComponent(jLabelWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelWelcome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jBtnExcluirInvestidor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnExtrato2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnNewInvestidor))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jBtnExcluirMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnUpdateMoeda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnNewMoeda)))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabelWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnNewInvestidor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnExtrato2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnExcluirInvestidor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelWelcome2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnNewMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnUpdateMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnExcluirMoeda))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(36, 25, 19));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logoMarromSmall.png"))); // NOI18N

        jBtnConta.setBackground(new java.awt.Color(36, 25, 19));
        jBtnConta.setForeground(new java.awt.Color(255, 255, 255));
        jBtnConta.setText("Conta");
        jBtnConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnContaActionPerformed(evt);
            }
        });

        jBtnSobre.setBackground(new java.awt.Color(36, 25, 19));
        jBtnSobre.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSobre.setText("Sobre");
        jBtnSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSobreActionPerformed(evt);
            }
        });

        jBtnLogout.setBackground(new java.awt.Color(153, 0, 0));
        jBtnLogout.setForeground(new java.awt.Color(255, 255, 255));
        jBtnLogout.setText("Sair");
        jBtnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLogoutActionPerformed(evt);
            }
        });

        jSeparator4.setBackground(new java.awt.Color(36, 25, 19));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSobre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnConta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnConta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSobre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnLogout)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnContaActionPerformed
        ca.conta();
    }//GEN-LAST:event_jBtnContaActionPerformed

    private void jBtnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLogoutActionPerformed
        ca.logout();
    }//GEN-LAST:event_jBtnLogoutActionPerformed

    private void jBtnSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSobreActionPerformed
        ca.aboutUs();
    }//GEN-LAST:event_jBtnSobreActionPerformed

    private void jBtnNewInvestidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewInvestidorActionPerformed
        ca.createUserScreen();
    }//GEN-LAST:event_jBtnNewInvestidorActionPerformed

    private void jBtnExtrato2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExtrato2ActionPerformed
        ca.getExtratoByIndex();
    }//GEN-LAST:event_jBtnExtrato2ActionPerformed

    private void jBtnExcluirInvestidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirInvestidorActionPerformed
        ca.deleteUserByIndex();
    }//GEN-LAST:event_jBtnExcluirInvestidorActionPerformed

    private void jBtnExcluirMoedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirMoedaActionPerformed
        ca.deleteMoeda();
    }//GEN-LAST:event_jBtnExcluirMoedaActionPerformed

    private void jBtnUpdateMoedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpdateMoedaActionPerformed
        ca.updateMoedaScreen();
    }//GEN-LAST:event_jBtnUpdateMoedaActionPerformed

    private void jBtnNewMoedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewMoedaActionPerformed
        ca.createMoedaScreen();
    }//GEN-LAST:event_jBtnNewMoedaActionPerformed

    private void jTableUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsersMouseClicked
        ca.UserMouseCLicked(evt);
    }//GEN-LAST:event_jTableUsersMouseClicked

    private void jTableMoedasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMoedasMouseClicked
        ca.UserMouseCLickedCoin(evt);
    }//GEN-LAST:event_jTableMoedasMouseClicked

    private ControllerAdmin ca;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConta;
    private javax.swing.JButton jBtnExcluirInvestidor;
    private javax.swing.JButton jBtnExcluirMoeda;
    private javax.swing.JButton jBtnExtrato2;
    private javax.swing.JButton jBtnLogout;
    private javax.swing.JButton jBtnNewInvestidor;
    private javax.swing.JButton jBtnNewMoeda;
    private javax.swing.JButton jBtnSobre;
    private javax.swing.JButton jBtnUpdateMoeda;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabelWelcome;
    private javax.swing.JLabel jLabelWelcome2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTableMoedas;
    private javax.swing.JTable jTableUsers;
    // End of variables declaration//GEN-END:variables
}
