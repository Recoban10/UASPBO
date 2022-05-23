package view.user;

import bcrypt.UpdatableBCrypt;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdbc.user.SaveUserRequest;
import jdbc.user.SaveUserResponse;
import jdbc.user.UserJdbc;
import jdbc.user.UserJdbcImplement;

public class FormAddUser extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    private final UserJdbc userJdbc;
    private Boolean clickTable;
    private DefaultTableModel defaultTableModel;
    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
    private String[] mutableHash = new String[1];
    private Function<String, Boolean> update = hash -> {
        mutableHash[0] = hash;
        return true;
    };

    public FormAddUser() {
        initComponents();
        userJdbc = new UserJdbcImplement();
        initTable();
        loadTable();
    }

    public static String hash(String password) {
        return bcrypt.hash(password);
    }

    public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
    }

    private void initTable() {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("No");
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("User Name");
        defaultTableModel.addColumn("Role");
        defaultTableModel.addColumn("Note");
        defaultTableModel.addColumn("Date Time");
        defaultTableModel.addColumn("Status");
        jTableUser.setModel(defaultTableModel);
    }

    private void loadTable() {
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();
        List<SaveUserResponse> responses = userJdbc.selectAll("%" + jTextFieldSearch.getText() + "%");
        Object[] objects = new Object[7];
        for (SaveUserResponse response : responses) {
            objects[0] = response.getId();
            objects[1] = response.getName();
            objects[2] = response.getUsername();
            objects[3] = response.getRole();
            objects[4] = response.getNote();
            objects[5] = response.getDate();
            objects[6] = response.getStatus();
            defaultTableModel.addRow(objects);
        }
        clickTable = false;
    }

    private void clickTable() {
        jTextFieldName.setText(defaultTableModel.getValueAt(jTableUser.getSelectedRow(), 1).toString());
        jTextFieldUsername.setText(defaultTableModel.getValueAt(jTableUser.getSelectedRow(), 2).toString());
        jComboBoxLevel.setSelectedItem(defaultTableModel.getValueAt(jTableUser.getSelectedRow(), 3).toString());
        jTextFieldNote.setText(defaultTableModel.getValueAt(jTableUser.getSelectedRow(), 4).toString());
        jComboBoxStatus.setSelectedItem(defaultTableModel.getValueAt(jTableUser.getSelectedRow(), 6).toString());
        clickTable = true;
    }

    private void empty() {
        jTextFieldName.setText("");
        jTextFieldUsername.setText("");
        jTextFieldNote.setText("");
        jComboBoxLevel.setSelectedIndex(0);
        jComboBoxStatus.setSelectedIndex(0);
        jTextFieldSearch.setText("");
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
                                request.setRole(jComboBoxLevel.getSelectedItem().toString());
                                request.setStatus(jComboBoxStatus.getSelectedItem().toString());
                                request.setUsername(jTextFieldUsername.getText());
                                userJdbc.insert(request);
                                loadTable();
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

    private void performUpdate() {
        if (clickTable) {
            if (!jTextFieldName.getText().isEmpty()) {
                if (!jTextFieldUsername.getText().isEmpty()) {
                    if (!jPasswordFieldPassword.getText().isEmpty()) {
                        if (!jPasswordFieldConfirmPassword.getText().isEmpty()) {
                            if (jPasswordFieldConfirmPassword.getText().equals(jPasswordFieldConfirmPassword.getText())) {
                                SaveUserRequest request = new SaveUserRequest();
                                request.setDate(new Timestamp(System.currentTimeMillis()));
                                request.setId(Long.parseLong(defaultTableModel.getValueAt(jTableUser.getSelectedRow(), 0).toString()));
                                request.setName(jTextFieldName.getText());
                                request.setNote(jTextFieldNote.getText());
                                String hashPw = bcrypt.hash(jPasswordFieldPassword.getText());
                                request.setPassword(hashPw);
                                request.setRole(jComboBoxLevel.getSelectedItem().toString());
                                request.setStatus(jComboBoxStatus.getSelectedItem().toString());
                                request.setUsername(jTextFieldUsername.getText());
                                userJdbc.update(request);
                                loadTable();
                                empty();
                                JOptionPane.showMessageDialog(null, "Successfully update data", "Success", JOptionPane.INFORMATION_MESSAGE);
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
        } else {
            JOptionPane.showMessageDialog(null, "Delete or edit must click table", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void performDelete() {
        if (clickTable) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to delete data by id " + defaultTableModel.getValueAt(jTableUser.getSelectedRow(), 0).toString() + " ?", "Warning", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                userJdbc.delete(Long.parseLong(defaultTableModel.getValueAt(jTableUser.getSelectedRow(), 0).toString()));
                loadTable();
                empty();
                JOptionPane.showMessageDialog(null, "Successfully delete data", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Delete or edit must click table", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelUser = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelUsername = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jLabelLevel = new javax.swing.JLabel();
        jComboBoxLevel = new javax.swing.JComboBox<>();
        jLabelStatus = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jTextFieldNote = new javax.swing.JTextField();
        jLabelNote = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jPasswordFieldConfirmPassword = new javax.swing.JPasswordField();
        jLabelConfirmPassword = new javax.swing.JLabel();
        jPanelExecution = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jPanelTable = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jScrollPaneTable = new javax.swing.JScrollPane();
        jTableUser = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 51, 51));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User");
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanelUser.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));

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

        jLabelLevel.setForeground(new java.awt.Color(169, 224, 49));
        jLabelLevel.setText("Level");

        jComboBoxLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        jComboBoxLevel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

        jLabelStatus.setForeground(new java.awt.Color(169, 224, 49));
        jLabelStatus.setText("Status");

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ON", "OFF" }));
        jComboBoxStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

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
                    .addComponent(jLabelLevel)
                    .addComponent(jLabelName)
                    .addComponent(jLabelPassword)
                    .addComponent(jLabelNote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUserLayout.createSequentialGroup()
                        .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldName)
                            .addComponent(jPasswordFieldPassword)
                            .addComponent(jComboBoxLevel, 0, 447, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelStatus)
                            .addComponent(jLabelConfirmPassword)
                            .addComponent(jLabelUsername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxStatus, 0, 428, Short.MAX_VALUE)
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
                    .addComponent(jLabelLevel)
                    .addComponent(jComboBoxLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNote)
                    .addComponent(jTextFieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelExecution.setBorder(javax.swing.BorderFactory.createTitledBorder("Execution"));

        jButtonSave.setBackground(new java.awt.Color(169, 224, 49));
        jButtonSave.setText("Save");
        jButtonSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonEdit.setBackground(new java.awt.Color(169, 224, 49));
        jButtonEdit.setText("Edit");
        jButtonEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(169, 224, 49));
        jButtonDelete.setText("Delete");
        jButtonDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonReset.setBackground(new java.awt.Color(169, 224, 49));
        jButtonReset.setText("Reset");
        jButtonReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
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
                .addComponent(jButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jButtonEdit)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonReset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelTable.setBorder(javax.swing.BorderFactory.createTitledBorder("User Table"));

        jLabelSearch.setForeground(new java.awt.Color(169, 224, 49));
        jLabelSearch.setText("Search");

        jTextFieldSearch.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });

        jScrollPaneTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49)));

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
        jScrollPaneTable.setViewportView(jTableUser);

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addGroup(jPanelTableLayout.createSequentialGroup()
                        .addComponent(jLabelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSearch)))
                .addContainerGap())
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearch)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelExecution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelExecution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        performSave();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        performUpdate();
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        performDelete();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        empty();
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        loadTable();
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jTableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUserMouseClicked
        clickTable();
    }//GEN-LAST:event_jTableUserMouseClicked
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormAddUser().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox<String> jComboBoxLevel;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabelConfirmPassword;
    private javax.swing.JLabel jLabelLevel;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanelExecution;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JPanel jPanelUser;
    private javax.swing.JPasswordField jPasswordFieldConfirmPassword;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JTable jTableUser;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldNote;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

}
