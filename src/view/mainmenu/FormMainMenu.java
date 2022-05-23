package view.mainmenu;

import java.awt.Color;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import jdbc.user.UserJdbc;
import jdbc.user.UserJdbcImplement;
import jdbc.user.UserResponse;
import org.apache.log4j.PropertyConfigurator;
import view.changepassword.FormChangePassword;
import view.chat.FormChat;
import view.user.FormAddUser;
import view.user.FormLogin;

public class FormMainMenu extends javax.swing.JFrame {

    private final UserJdbc userJdbc;
    private final UserResponse user;

    public FormMainMenu() {
        initComponents();
        userJdbc = new UserJdbcImplement();
        user = userJdbc.selectUsername("2");
        jLabelFooter.setText("Welcome " + user.getName());
        checkRole();
    }

    public FormMainMenu(String username) {
        initComponents();
        userJdbc = new UserJdbcImplement();
        user = userJdbc.selectUsername(username);
        jLabelFooter.setText("Welcome " + user.getName());
        checkRole();
    }

    private void checkRole() {
        if (user.getRole().equals("User")) {
            jMenuItemUserAdmin.setVisible(false);
        } 
//        else {
//            jMenuChat.setVisible(false);
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanelFooter = new javax.swing.JPanel();
        jLabelFooter = new javax.swing.JLabel();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenuChat = new javax.swing.JMenu();
        jMenuItemChat = new javax.swing.JMenuItem();
        jMenuSetting = new javax.swing.JMenu();
        jMenuItemUserAdmin = new javax.swing.JMenuItem();
        jMenuItemChangePassword = new javax.swing.JMenuItem();
        jMenuItemLogOut = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(21, 25, 28));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jDesktopPane1.setLayout(new javax.swing.BoxLayout(jDesktopPane1, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        jLabelFooter.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFooter.setText("Wellcome");

        javax.swing.GroupLayout jPanelFooterLayout = new javax.swing.GroupLayout(jPanelFooter);
        jPanelFooter.setLayout(jPanelFooterLayout);
        jPanelFooterLayout.setHorizontalGroup(
            jPanelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFooterLayout.createSequentialGroup()
                .addComponent(jLabelFooter, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelFooterLayout.setVerticalGroup(
            jPanelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenuChat.setText("Chat");

        jMenuItemChat.setText("Chat");
        jMenuItemChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemChatActionPerformed(evt);
            }
        });
        jMenuChat.add(jMenuItemChat);

        jMenuBarMain.add(jMenuChat);

        jMenuSetting.setText("Setting");

        jMenuItemUserAdmin.setText("User");
        jMenuItemUserAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUserAdminActionPerformed(evt);
            }
        });
        jMenuSetting.add(jMenuItemUserAdmin);

        jMenuItemChangePassword.setText("Change Password");
        jMenuItemChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemChangePasswordActionPerformed(evt);
            }
        });
        jMenuSetting.add(jMenuItemChangePassword);

        jMenuItemLogOut.setText("Log Out");
        jMenuItemLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogOutActionPerformed(evt);
            }
        });
        jMenuSetting.add(jMenuItemLogOut);

        jMenuBarMain.add(jMenuSetting);

        setJMenuBar(jMenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelFooter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 299, Short.MAX_VALUE)
                    .addComponent(jPanelFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogOutActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Log out ?", "Warning", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            FormLogin formLogin = new FormLogin();
            formLogin.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jMenuItemLogOutActionPerformed

    private void jMenuItemUserAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUserAdminActionPerformed
        FormMainMenu.jDesktopPane1.removeAll();
        FormMainMenu.jDesktopPane1.updateUI();
        FormAddUser formAddUser = new FormAddUser();
        FormMainMenu.jDesktopPane1.add(formAddUser);
        formAddUser.setVisible(true);
    }//GEN-LAST:event_jMenuItemUserAdminActionPerformed

    private void jMenuItemChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemChangePasswordActionPerformed
        FormMainMenu.jDesktopPane1.removeAll();
        FormMainMenu.jDesktopPane1.updateUI();
        FormChangePassword formChangePassword = new FormChangePassword(user);
        FormMainMenu.jDesktopPane1.add(formChangePassword);
        formChangePassword.setVisible(true);
    }//GEN-LAST:event_jMenuItemChangePasswordActionPerformed

    private void jMenuItemChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemChatActionPerformed
        FormMainMenu.jDesktopPane1.removeAll();
        FormMainMenu.jDesktopPane1.updateUI();
        FormChat formChat = new FormChat(user);
        FormMainMenu.jDesktopPane1.add(formChat);
        formChat.setVisible(true);
    }//GEN-LAST:event_jMenuItemChatActionPerformed

    public static void main(String[] args) {
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
            new FormMainMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabelFooter;
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenu jMenuChat;
    private javax.swing.JMenuItem jMenuItemChangePassword;
    private javax.swing.JMenuItem jMenuItemChat;
    private javax.swing.JMenuItem jMenuItemLogOut;
    private javax.swing.JMenuItem jMenuItemUserAdmin;
    private javax.swing.JMenu jMenuSetting;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelFooter;
    // End of variables declaration//GEN-END:variables
}
