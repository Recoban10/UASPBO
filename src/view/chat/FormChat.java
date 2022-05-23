package view.chat;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import jdbc.chat.ChatJdbc;
import jdbc.chat.ChatJdbcImplement;
import jdbc.chat.SaveChatRequest;
import jdbc.chat.UsernameRequest;
import jdbc.chat.UsernameResponse;
import jdbc.user.SaveUserResponse;
import jdbc.user.UserJdbc;
import jdbc.user.UserJdbcImplement;
import jdbc.user.UserResponse;

// Old Password dan New Password
public class FormChat extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    private final UserJdbc userJdbc;
    private final ChatJdbc chatJdbc;
    private static UserResponse user;
    private Boolean clickTableUser;
    private DefaultTableModel defaultTableModelUser;

    public FormChat(UserResponse user) {
        FormChat.user = user;
        initComponents();
        userJdbc = new UserJdbcImplement();
        chatJdbc = new ChatJdbcImplement();
        initTable();
        loadComboBoxUser();
        showChat();
        txtAreaMessages.setEditable(false);
    }
    
    private void showChat(){
        UsernameRequest request = new UsernameRequest();
        request.setUsername(user.getUsername());
        request.setUsernameTarget(jComboBoxUser.getSelectedItem().toString());
        List<UsernameResponse> responses = chatJdbc.selectAll(request);
        StringBuilder sb = new StringBuilder();
        for (UsernameResponse response : responses) {
            sb.append(response.getSender());
            sb.append(" : ");
            sb.append(response.getContent());
            sb.append("\n");
        }
        txtAreaMessages.setText(sb.toString());
    }
    
    private void initTable() {
        defaultTableModelUser = new DefaultTableModel();
        defaultTableModelUser.addColumn("No");
        defaultTableModelUser.addColumn("User Name");
        defaultTableModelUser.addColumn("Note");
        jTableUser.setModel(defaultTableModelUser);
    }
    
    private void loadTableUser() {
        defaultTableModelUser.getDataVector().removeAllElements();
        defaultTableModelUser.fireTableDataChanged();
        List<SaveUserResponse> responses = userJdbc.selectAllUser("%" + jTextFieldSearchUser.getText() + "%", user.getUsername());
        Object[] objects = new Object[3];
        for (SaveUserResponse response : responses) {
            objects[0] = response.getId();
            objects[1] = response.getName();
            objects[2] = response.getNote();
            defaultTableModelUser.addRow(objects);
        }
        clickTableUser = false;
    }

    private void loadComboBoxUser() {
        List<SaveUserResponse> responses = userJdbc.selectAllUser("%" + jTextFieldSearchUser.getText() + "%", user.getUsername());
        for (SaveUserResponse response : responses) {
            jComboBoxUser.addItem(response.getUsername());
        }
    }

    private void empty() {
        jTextFieldText.setText("");
    }

    private void performSave() {
        if (!jTextFieldText.getText().isEmpty()) {
            SaveChatRequest request = new SaveChatRequest();
            request.setContent(jTextFieldText.getText());
            request.setDate(new Timestamp(System.currentTimeMillis()));
            request.setUsername(user.getUsername());
            request.setUsernameTarget(jComboBoxUser.getSelectedItem().toString());
            chatJdbc.insert(request);
            empty();
            showChat();
        } else {
            empty();
            JOptionPane.showMessageDialog(null, "Text not empty", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void openDialog(JDialog dialog, ActionEvent evt) {
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor((Component) evt.getSource()));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogUser = new javax.swing.JDialog();
        jPanelTableUser = new javax.swing.JPanel();
        jScrollPaneUser = new javax.swing.JScrollPane();
        jTableUser = new javax.swing.JTable();
        jLabelSearchUser = new javax.swing.JLabel();
        jTextFieldSearchUser = new javax.swing.JTextField();
        jPanelExecutionUser = new javax.swing.JPanel();
        jButtonSelectUser = new javax.swing.JButton();
        jButtonCloseUser = new javax.swing.JButton();
        jPanelUser = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMessages = new javax.swing.JTextArea();
        jPanelItemUser = new javax.swing.JPanel();
        jButtonUser = new javax.swing.JButton();
        jLabelUser = new javax.swing.JLabel();
        jComboBoxUser = new javax.swing.JComboBox<>();
        jPanelExecution = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jTextFieldText = new javax.swing.JTextField();

        jDialogUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDialogUser.setSize(new java.awt.Dimension(720, 480));
        jDialogUser.setType(java.awt.Window.Type.POPUP);

        jPanelTableUser.setBorder(javax.swing.BorderFactory.createTitledBorder("User Table"));

        jScrollPaneUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jTableUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jTableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUserMouseClicked(evt);
            }
        });
        jScrollPaneUser.setViewportView(jTableUser);

        jLabelSearchUser.setForeground(new java.awt.Color(169, 224, 49));
        jLabelSearchUser.setText("Search");

        jTextFieldSearchUser.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSearchUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jTextFieldSearchUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchUserKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelTableUserLayout = new javax.swing.GroupLayout(jPanelTableUser);
        jPanelTableUser.setLayout(jPanelTableUserLayout);
        jPanelTableUserLayout.setHorizontalGroup(
            jPanelTableUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTableUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneUser, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addGroup(jPanelTableUserLayout.createSequentialGroup()
                        .addComponent(jLabelSearchUser)
                        .addGap(7, 7, 7)
                        .addComponent(jTextFieldSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelTableUserLayout.setVerticalGroup(
            jPanelTableUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTableUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearchUser)
                    .addComponent(jTextFieldSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneUser, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelExecutionUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Execution"));

        jButtonSelectUser.setBackground(new java.awt.Color(169, 224, 49));
        jButtonSelectUser.setForeground(new java.awt.Color(0, 0, 0));
        jButtonSelectUser.setText("Select");
        jButtonSelectUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonSelectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectUserActionPerformed(evt);
            }
        });

        jButtonCloseUser.setBackground(new java.awt.Color(169, 224, 49));
        jButtonCloseUser.setForeground(new java.awt.Color(0, 0, 0));
        jButtonCloseUser.setText("Close");
        jButtonCloseUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCloseUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelExecutionUserLayout = new javax.swing.GroupLayout(jPanelExecutionUser);
        jPanelExecutionUser.setLayout(jPanelExecutionUserLayout);
        jPanelExecutionUserLayout.setHorizontalGroup(
            jPanelExecutionUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExecutionUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSelectUser, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCloseUser, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelExecutionUserLayout.setVerticalGroup(
            jPanelExecutionUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExecutionUserLayout.createSequentialGroup()
                .addGroup(jPanelExecutionUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSelectUser)
                    .addComponent(jButtonCloseUser))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogUserLayout = new javax.swing.GroupLayout(jDialogUser.getContentPane());
        jDialogUser.getContentPane().setLayout(jDialogUserLayout);
        jDialogUserLayout.setHorizontalGroup(
            jDialogUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelExecutionUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanelTableUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogUserLayout.setVerticalGroup(
            jDialogUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTableUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelExecutionUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBackground(new java.awt.Color(0, 51, 51));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Password");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanelUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Chatting"));

        txtAreaMessages.setColumns(20);
        txtAreaMessages.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        txtAreaMessages.setRows(5);
        jScrollPane1.setViewportView(txtAreaMessages);

        jPanelItemUser.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));

        jButtonUser.setBackground(new java.awt.Color(169, 224, 49));
        jButtonUser.setForeground(new java.awt.Color(0, 0, 0));
        jButtonUser.setText("Search User");
        jButtonUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUserActionPerformed(evt);
            }
        });

        jLabelUser.setForeground(new java.awt.Color(169, 224, 49));
        jLabelUser.setText("User");

        jComboBoxUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jComboBoxUser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxUserItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelItemUserLayout = new javax.swing.GroupLayout(jPanelItemUser);
        jPanelItemUser.setLayout(jPanelItemUserLayout);
        jPanelItemUserLayout.setHorizontalGroup(
            jPanelItemUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelItemUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelItemUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelItemUserLayout.createSequentialGroup()
                        .addComponent(jLabelUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelItemUserLayout.setVerticalGroup(
            jPanelItemUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelItemUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelItemUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser)
                    .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelUserLayout = new javax.swing.GroupLayout(jPanelUser);
        jPanelUser.setLayout(jPanelUserLayout);
        jPanelUserLayout.setHorizontalGroup(
            jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addComponent(jPanelItemUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelUserLayout.setVerticalGroup(
            jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelItemUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelExecution.setBorder(javax.swing.BorderFactory.createTitledBorder("Send"));

        jButtonSave.setBackground(new java.awt.Color(169, 224, 49));
        jButtonSave.setForeground(new java.awt.Color(0, 0, 0));
        jButtonSave.setText("Send");
        jButtonSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jTextFieldText.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        javax.swing.GroupLayout jPanelExecutionLayout = new javax.swing.GroupLayout(jPanelExecution);
        jPanelExecution.setLayout(jPanelExecutionLayout);
        jPanelExecutionLayout.setHorizontalGroup(
            jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelExecutionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelExecutionLayout.setVerticalGroup(
            jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExecutionLayout.createSequentialGroup()
                .addGroup(jPanelExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jTextFieldText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
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
                .addComponent(jPanelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelExecution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        performSave();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUserActionPerformed
        openDialog(jDialogUser, evt);
        loadTableUser();
    }//GEN-LAST:event_jButtonUserActionPerformed

    private void jComboBoxUserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxUserItemStateChanged
        showChat();
    }//GEN-LAST:event_jComboBoxUserItemStateChanged

    private void jTableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUserMouseClicked
        clickTableUser = true;
    }//GEN-LAST:event_jTableUserMouseClicked

    private void jTextFieldSearchUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchUserKeyReleased
        loadTableUser();
    }//GEN-LAST:event_jTextFieldSearchUserKeyReleased

    private void jButtonSelectUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectUserActionPerformed
        if (clickTableUser) {
            jComboBoxUser.setSelectedItem(defaultTableModelUser.getValueAt(jTableUser.getSelectedRow(), 0).toString());
            jDialogUser.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Select table must click table", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSelectUserActionPerformed

    private void jButtonCloseUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseUserActionPerformed
        jDialogUser.dispose();
    }//GEN-LAST:event_jButtonCloseUserActionPerformed
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormChat(user).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCloseUser;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSelectUser;
    private javax.swing.JButton jButtonUser;
    private javax.swing.JComboBox<String> jComboBoxUser;
    private javax.swing.JDialog jDialogUser;
    private javax.swing.JLabel jLabelSearchUser;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanelExecution;
    private javax.swing.JPanel jPanelExecutionUser;
    private javax.swing.JPanel jPanelItemUser;
    private javax.swing.JPanel jPanelTableUser;
    private javax.swing.JPanel jPanelUser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneUser;
    private javax.swing.JTable jTableUser;
    private javax.swing.JTextField jTextFieldSearchUser;
    private javax.swing.JTextField jTextFieldText;
    private javax.swing.JTextArea txtAreaMessages;
    // End of variables declaration//GEN-END:variables

}
