package view;

import controller.ControllerForm;
import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Investidor;

public class FormCriptoFrame extends javax.swing.JFrame {

    public FormCriptoFrame(String title, String subTitle, String btnText, Investidor user, HubFrame hb) {
        initComponents();
        this.cf = new ControllerForm(this, title, user, hb);
        this.setTitle("Formulário " + title);
        this.jLabelTitle.setText(title);
        this.jLabelSubTitle.setText(subTitle);
        this.jBtn.setText(btnText);
    }

    public JLabel getjLabelSubTitle() {
        return jLabelSubTitle;
    }

    public JLabel getjLabelTitle() {
        return jLabelTitle;
    }

    public JPasswordField getjPasswordInput() {
        return jPasswordInput;
    }

    public Choice getjSelectCriptos() {
        return jSelectCriptos;
    }

    public JTextField getjTxtInput() {
        return jTxtInput;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabelSubTitle = new javax.swing.JLabel();
        jTxtInput = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jPasswordInput = new javax.swing.JPasswordField();
        jSelectCriptos = new java.awt.Choice();
        jLabelSubTitle1 = new javax.swing.JLabel();
        jBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("JavaCoin - Formulário");

        jPanel1.setBackground(new java.awt.Color(36, 25, 19));

        jLabelTitle.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("Função");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(225, 214, 154));

        jLabelSubTitle.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSubTitle.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelSubTitle.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSubTitle.setText("Função:");

        jTxtInput.setBackground(new java.awt.Color(255, 255, 255));
        jTxtInput.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTxtInput.setPreferredSize(new java.awt.Dimension(65, 30));

        jLabelPassword.setBackground(new java.awt.Color(0, 0, 0));
        jLabelPassword.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelPassword.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPassword.setText("Senha:");

        jPasswordInput.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordInput.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPasswordInput.setForeground(new java.awt.Color(36, 25, 19));

        jLabelSubTitle1.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSubTitle1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelSubTitle1.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSubTitle1.setText("Moeda:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSelectCriptos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSubTitle)
                    .addComponent(jTxtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSubTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabelSubTitle)
                .addGap(2, 2, 2)
                .addComponent(jTxtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelSubTitle1)
                .addGap(1, 1, 1)
                .addComponent(jSelectCriptos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jBtn.setBackground(new java.awt.Color(255, 255, 255));
        jBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBtn.setForeground(new java.awt.Color(36, 25, 19));
        jBtn.setText("Funcão");
        jBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtn)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActionPerformed
        cf.action();
    }//GEN-LAST:event_jBtnActionPerformed

    ControllerForm cf;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelSubTitle;
    private javax.swing.JLabel jLabelSubTitle1;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordInput;
    private java.awt.Choice jSelectCriptos;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTxtInput;
    // End of variables declaration//GEN-END:variables
}
