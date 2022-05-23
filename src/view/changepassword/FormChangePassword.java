package view.changepassword;

import bcrypt.UpdatableBCrypt;
import java.sql.Timestamp;
import java.util.function.Function;
import javax.swing.JOptionPane;
import jdbc.user.ChangePasswordRequest;
import jdbc.user.SaveUserRequest;
import jdbc.user.UserJdbc;
import jdbc.user.UserJdbcImplement;
import jdbc.user.UserResponse;

// Old Password dan New Password
public class FormChangePassword extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    private final UserJdbc userJdbc;
    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
    private String[] mutableHash;
    private final Function<String, Boolean> update;

    public FormChangePassword() {
        initComponents();
        userJdbc = new UserJdbcImplement();
        jTextFieldName.setEditable(false);
        jTextFieldUsername.setEditable(false);
        jTextFieldNote.setEditable(false);
        this.update = hash -> {
            mutableHash[0] = hash;
            return true;
        };
        this.mutableHash = new String[1];
    }

    public FormChangePassword(UserResponse user) {
        initComponents();
        userJdbc = new UserJdbcImplement();
        jTextFieldName.setEditable(false);
        jTextFieldUsername.setEditable(false);
        jTextFieldNote.setEditable(false);
        jTextFieldName.setText(user.getName());
        jTextFieldUsername.setText(user.getUsername());
        jTextFieldNote.setText(user.getNote());
        this.update = hash -> {
            mutableHash[0] = hash;
            return true;
        };
        this.mutableHash = new String[1];
    }

    private void empty() {
        jPasswordFieldOldPassword.setText("");
        jPasswordFieldPassword.setText("");
        jPasswordFieldConfirmPassword.setText("");
    }

    private void performSave() {
        if (!jPasswordFieldPassword.getText().isEmpty()) {
            if (!jPasswordFieldConfirmPassword.getText().isEmpty()) {
                if (jPasswordFieldPassword.getText().equals(jPasswordFieldConfirmPassword.getText())) {
                    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
                    changePasswordRequest.setUsername(jTextFieldUsername.getText());
                    changePasswordRequest.setPassword(jPasswordFieldOldPassword.getText());
                    if (bcrypt.verifyAndUpdateHash(jPasswordFieldOldPassword.getText(), userJdbc.checkChangePassword(changePasswordRequest), update)) {
                        if (JOptionPane.showConfirmDialog(null, "Do you want to save new password ?", "Info", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                            SaveUserRequest request = new SaveUserRequest();
                            request.setDate(new Timestamp(System.currentTimeMillis()));
                            String hashPw = bcrypt.hash(jPasswordFieldPassword.getText());
                            request.setPassword(hashPw);
                            request.setUsername(jTextFieldUsername.getText());
                            userJdbc.changePassword(request);
                            empty();
                            JOptionPane.showMessageDialog(null, "Successfully save data", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        empty();
                        JOptionPane.showMessageDialog(null, "Old Password wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    empty();
                    JOptionPane.showMessageDialog(null, "Password not same", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                empty();
                JOptionPane.showMessageDialog(null, "Confirm Password not empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            empty();
            JOptionPane.showMessageDialog(null, "Password not empty", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelUser = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelUsername = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jTextFieldNote = new javax.swing.JTextField();
        jLabelNote = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jLabelConfirmPassword = new javax.swing.JLabel();
        jPasswordFieldConfirmPassword = new javax.swing.JPasswordField();
        jLabelNote1 = new javax.swing.JLabel();
        jLabelOldPassword = new javax.swing.JLabel();
        jPasswordFieldOldPassword = new javax.swing.JPasswordField();
        jPanelExecution = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 51, 51));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Password");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanelUser.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));

        jLabelName.setForeground(new java.awt.Color(169, 224, 49));
        jLabelName.setText("Name");

        jTextFieldName.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldName.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelUsername.setForeground(new java.awt.Color(169, 224, 49));
        jLabelUsername.setText("Username");

        jTextFieldUsername.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldUsername.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jTextFieldNote.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldNote.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelNote.setForeground(new java.awt.Color(169, 224, 49));
        jLabelNote.setText("Note");

        jLabelPassword.setForeground(new java.awt.Color(169, 224, 49));
        jLabelPassword.setText("Password");

        jPasswordFieldPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelConfirmPassword.setForeground(new java.awt.Color(169, 224, 49));
        jLabelConfirmPassword.setText("Confirm Password");

        jPasswordFieldConfirmPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelNote1.setForeground(new java.awt.Color(169, 224, 49));
        jLabelNote1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNote1.setText("Tell your admin for forgot password");

        jLabelOldPassword.setForeground(new java.awt.Color(169, 224, 49));
        jLabelOldPassword.setText("Old Password");

        jPasswordFieldOldPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        javax.swing.GroupLayout jPanelUserLayout = new javax.swing.GroupLayout(jPanelUser);
        jPanelUser.setLayout(jPanelUserLayout);
        jPanelUserLayout.setHorizontalGroup(
            jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUserLayout.createSequentialGroup()
                        .addComponent(jLabelNote1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelUserLayout.createSequentialGroup()
                        .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUsername)
                            .addComponent(jLabelName)
                            .addComponent(jLabelNote)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelConfirmPassword)
                            .addComponent(jLabelOldPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                            .addComponent(jTextFieldNote, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordFieldConfirmPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldName)
                            .addComponent(jPasswordFieldOldPassword))
                        .addGap(6, 6, 6))))
        );
        jPanelUserLayout.setVerticalGroup(
            jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsername)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOldPassword)
                    .addComponent(jPasswordFieldOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConfirmPassword)
                    .addComponent(jPasswordFieldConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNote)
                    .addComponent(jTextFieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNote1)
                .addContainerGap())
        );

        jPanelExecution.setBorder(javax.swing.BorderFactory.createTitledBorder("Execution"));

        jButtonSave.setBackground(new java.awt.Color(169, 224, 49));
        jButtonSave.setForeground(new java.awt.Color(0, 0, 0));
        jButtonSave.setText("Save");
        jButtonSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonReset.setBackground(new java.awt.Color(169, 224, 49));
        jButtonReset.setForeground(new java.awt.Color(0, 0, 0));
        jButtonReset.setText("Reset");
        jButtonReset.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelExecutionLayout = new javax.swing.GroupLayout(jPanelExecution);
        jPanelExecution.setLayout(jPanelExecutionLayout);
        jPanelExecutionLayout.setHorizontalGroup(
            jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExecutionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelExecutionLayout.setVerticalGroup(
            jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExecutionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonReset))
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

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        empty();
    }//GEN-LAST:event_jButtonResetActionPerformed
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormChangePassword().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabelConfirmPassword;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelNote1;
    private javax.swing.JLabel jLabelOldPassword;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanelExecution;
    private javax.swing.JPanel jPanelUser;
    private javax.swing.JPasswordField jPasswordFieldConfirmPassword;
    private javax.swing.JPasswordField jPasswordFieldOldPassword;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldNote;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

}
