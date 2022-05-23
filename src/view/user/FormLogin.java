package view.user;

import bcrypt.UpdatableBCrypt;
import java.awt.Color;
import java.io.File;
import java.util.function.Function;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import jdbc.user.UserJdbc;
import jdbc.user.UserJdbcImplement;
import jdbc.user.UserResponse;
import org.apache.log4j.PropertyConfigurator;
import view.mainmenu.FormMainMenu;

public class FormLogin extends javax.swing.JFrame {

    private final UserJdbc userJdbc;
    private static final Logger LOG = Logger.getLogger(FormLogin.class.getName());
    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
    private String[] mutableHash;
    private final Function<String, Boolean> update;

    public FormLogin() {
        this.update = hash -> {
            mutableHash[0] = hash;
            return true;
        };
        this.mutableHash = new String[1];
        initComponents();
        userJdbc = new UserJdbcImplement();
    }

    private void perMenu(String username) {
        this.setVisible(false);
        FormMainMenu formMainMenu = new FormMainMenu(username);
        formMainMenu.setVisible(true);
    }
    
    private void perRegister() {
        this.setVisible(false);
        FormRegister formRegister = new FormRegister();
        formRegister.setVisible(true);
    }

    private void empty() {
        jTextFieldUsername.setText("");
        jPasswordFieldPassword.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextFieldUsername = new javax.swing.JTextField();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jLabelUsername = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jButtonLogin = new javax.swing.JButton();
        jLabelContent1 = new javax.swing.JLabel();
        jLabelTitle1 = new javax.swing.JLabel();
        jButtonRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jTextFieldUsername.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jTextFieldUsername.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jPasswordFieldPassword.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jPasswordFieldPassword.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordFieldPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(169, 224, 49));
        jLabelUsername.setText("Username");

        jLabelPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelPassword.setForeground(new java.awt.Color(169, 224, 49));
        jLabelPassword.setText("Password");

        jButtonLogin.setBackground(new java.awt.Color(152, 201, 45));
        jButtonLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogin.setText("Login");
        jButtonLogin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLogin.setFocusPainted(false);
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jLabelContent1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabelContent1.setForeground(new java.awt.Color(169, 224, 49));
        jLabelContent1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelContent1.setText("Chating Application");

        jLabelTitle1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabelTitle1.setForeground(new java.awt.Color(169, 224, 49));
        jLabelTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle1.setText("CHAT");

        jButtonRegister.setBackground(new java.awt.Color(152, 201, 45));
        jButtonRegister.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButtonRegister.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegister.setText("Create Account");
        jButtonRegister.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRegister.setFocusPainted(false);
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelContent1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addComponent(jLabelUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelContent1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPassword)
                .addGap(7, 7, 7)
                .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRegister)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        if (!jTextFieldUsername.getText().isEmpty()) {
            if (!jPasswordFieldPassword.getText().isEmpty()) {
                UserResponse userResponse = userJdbc.selectUsername(jTextFieldUsername.getText());
                if (userResponse != null) {
                    if (bcrypt.verifyAndUpdateHash(jPasswordFieldPassword.getText(), userResponse.getPassword(), update)) {
                        JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                        perMenu(userResponse.getUsername());
                    } else {
                        empty();
                        JOptionPane.showMessageDialog(null, "Username or password wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    empty();
                    JOptionPane.showMessageDialog(null, "Username not exist", "Warning", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                empty();
                JOptionPane.showMessageDialog(null, "Password not empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            empty();
            JOptionPane.showMessageDialog(null, "Username not empty", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegisterActionPerformed
        perRegister();
    }//GEN-LAST:event_jButtonRegisterActionPerformed

    public static void main(String[] args) {
        String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/log/log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
        
//        UIManager.put("control", new Color(0, 0, 0));
//        UIManager.put("info", new Color(0, 0, 0));
//        UIManager.put("nimbusBase", new Color(18, 30, 49));
//        UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
//        UIManager.put("nimbusDisabledText", new Color(0, 0, 0));
//        UIManager.put("nimbusFocus", new Color(115, 164, 209));
//        UIManager.put("nimbusGreen", new Color(176, 179, 50));
//        UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
//        UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
//        UIManager.put("nimbusOrange", new Color(191, 98, 4));
//        UIManager.put("nimbusRed", new Color(169, 46, 34));
//        UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
//        UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
//        UIManager.put("text", new Color(230, 230, 230));
        UIManager.put("control", new Color(128, 128, 128));
        UIManager.put("info", new Color(128, 128, 128));
        UIManager.put("nimbusBase", new Color(18, 30, 49));
        UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
        UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
        UIManager.put("nimbusFocus", new Color(115, 164, 209));
        UIManager.put("nimbusGreen", new Color(176, 179, 50));
        UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
        UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
        UIManager.put("nimbusOrange", new Color(191, 98, 4));
        UIManager.put("nimbusRed", new Color(169, 46, 34));
        UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
        UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
        UIManager.put("text", new Color(230, 230, 230));
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            LOG.severe(e.getMessage());
        }
        java.awt.EventQueue.invokeLater(() -> {
            new FormLogin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JLabel jLabelContent1;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelTitle1;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}
