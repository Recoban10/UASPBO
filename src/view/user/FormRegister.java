package view.user;

import bcrypt.UpdatableBCrypt;
import java.awt.Color;
import java.io.File;
import jdbc.user.SaveUserRequest;
import jdbc.user.UserJdbc;
import jdbc.user.UserJdbcImplement;
import java.sql.Timestamp;
import java.util.function.Function;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.PropertyConfigurator;

public class FormRegister extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private final UserJdbc userJdbc;
    private DefaultTableModel defaultTableModel;
    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
    private String[] mutableHash = new String[1];
    private Function<String, Boolean> update = hash -> {
        mutableHash[0] = hash;
        return true;
    };

    public FormRegister() {
        initComponents();
        userJdbc = new UserJdbcImplement();
    }

    public static String hash(String password) {
        return bcrypt.hash(password);
    }

    public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
    }

    private void empty() {
        jTextFieldName.setText("");
        jTextFieldUsername.setText("");
        jTextFieldNote.setText("");
        jPasswordFieldPassword.setText("");
        jPasswordFieldConfirmPassword.setText("");
    }

    private void performSave() {
        if (!jTextFieldName.getText().isEmpty()) {
            if (!jTextFieldUsername.getText().isEmpty()) {
                if (!jPasswordFieldPassword.getText().isEmpty()) {
                    if (!jPasswordFieldConfirmPassword.getText().isEmpty()) {
                        if (jPasswordFieldPassword.getText().equals(jPasswordFieldConfirmPassword.getText())) {
                            if (!userJdbc.selectUserExist(jTextFieldUsername.getText())) {
                                SaveUserRequest request = new SaveUserRequest();
                                request.setDate(new Timestamp(System.currentTimeMillis()));
                                request.setId(0L);
                                request.setName(jTextFieldName.getText());
                                request.setNote(jTextFieldNote.getText());
                                String hashPw = bcrypt.hash(jPasswordFieldPassword.getText());
                                request.setPassword(hashPw);
                                request.setRole("User");
                                request.setStatus("ON");
                                request.setUsername(jTextFieldUsername.getText());
                                userJdbc.insert(request);
                                empty();
                                JOptionPane.showMessageDialog(null, "Successfully save data", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Username Exist", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Password not same", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Confirm Password not empty", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Password not empty", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username not empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Name not empty", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void perLogin() {
        this.setVisible(false);
        FormLogin formLogin = new FormLogin();
        formLogin.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelUser = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelUsername = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldNote = new javax.swing.JTextField();
        jLabelNote = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jPasswordFieldConfirmPassword = new javax.swing.JPasswordField();
        jLabelConfirmPassword = new javax.swing.JLabel();
        jPanelExecution = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User");
        setBackground(new java.awt.Color(0, 51, 51));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanelUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Registration User"));

        jLabelName.setForeground(new java.awt.Color(169, 224, 49));
        jLabelName.setText("Name");

        jTextFieldName.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelUsername.setForeground(new java.awt.Color(169, 224, 49));
        jLabelUsername.setText("Username");

        jTextFieldUsername.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelPassword.setForeground(new java.awt.Color(169, 224, 49));
        jLabelPassword.setText("Password");

        jTextFieldNote.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelNote.setForeground(new java.awt.Color(169, 224, 49));
        jLabelNote.setText("Note");

        jPasswordFieldPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jPasswordFieldConfirmPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelConfirmPassword.setForeground(new java.awt.Color(169, 224, 49));
        jLabelConfirmPassword.setText("Confirm Password");

        javax.swing.GroupLayout jPanelUserLayout = new javax.swing.GroupLayout(jPanelUser);
        jPanelUser.setLayout(jPanelUserLayout);
        jPanelUserLayout.setHorizontalGroup(
            jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName)
                    .addComponent(jLabelPassword)
                    .addComponent(jLabelNote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUserLayout.createSequentialGroup()
                        .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldName)
                            .addComponent(jPasswordFieldPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelConfirmPassword)
                            .addComponent(jLabelUsername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordFieldConfirmPassword)
                            .addComponent(jTextFieldUsername, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jTextFieldNote)))
        );
        jPanelUserLayout.setVerticalGroup(
            jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUsername)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelConfirmPassword)
                    .addComponent(jPasswordFieldConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNote)
                    .addComponent(jTextFieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelExecution.setBorder(javax.swing.BorderFactory.createTitledBorder("Execution"));

        jButtonSave.setBackground(new java.awt.Color(169, 224, 49));
        jButtonSave.setText("Register");
        jButtonSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonLogin.setBackground(new java.awt.Color(169, 224, 49));
        jButtonLogin.setText("Back to login");
        jButtonLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelExecutionLayout = new javax.swing.GroupLayout(jPanelExecution);
        jPanelExecution.setLayout(jPanelExecutionLayout);
        jPanelExecutionLayout.setHorizontalGroup(
            jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExecutionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelExecutionLayout.setVerticalGroup(
            jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExecutionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelExecution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelExecution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        performSave();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        perLogin();
    }//GEN-LAST:event_jButtonLoginActionPerformed
    public static void main(String args[]) {
        String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/log/log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);

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
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FormRegister().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabelConfirmPassword;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanelExecution;
    private javax.swing.JPanel jPanelUser;
    private javax.swing.JPasswordField jPasswordFieldConfirmPassword;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldNote;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

}
