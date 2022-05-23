package jdbc.user;

import connection.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author Khanza
 */
public class UserJdbcImplement implements UserJdbc {

//    MENGGUNAKAN PreparedStatement UNTUK MENCEGAH SQL INJECTION
    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(UserJdbcImplement.class);

    public UserJdbcImplement() {
        connection = Conn.getConnection();
    }

    //    MENAMPILKAN SEMUA USER
    @Override
    public List<SaveUserResponse> selectAll(String request) {
        List<SaveUserResponse> response = new ArrayList<>();
        try {
            sql = "\n"
                    + "select\n"
                    + "	*\n"
                    + "from\n"
                    + "	user\n"
                    + "where\n"
                    + "	name like ?\n"
                    + "	or role like ?\n"
                    + "	or note like ?\n"
                    + "	or status like ?\n"
                    + "order by\n"
                    + "	id desc;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request);
            preparedStatement.setString(2, request);
            preparedStatement.setString(3, request);
            preparedStatement.setString(4, request);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SaveUserResponse userResponse = new SaveUserResponse();
                userResponse.setDate(resultSet.getTimestamp("date"));
                userResponse.setId(resultSet.getLong("id"));
                userResponse.setName(resultSet.getString("name"));
                userResponse.setNote(resultSet.getString("note"));
                userResponse.setPassword(resultSet.getString("password"));
                userResponse.setRole(resultSet.getString("role"));
                userResponse.setStatus(resultSet.getString("status"));
                userResponse.setUsername(resultSet.getString("username"));
                response.add(userResponse);
            }
            resultSet.close();
            preparedStatement.close();
            return response;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
            return null;
        }
    }

    //    MENGIRIM PESAN
    @Override
    public void insert(SaveUserRequest request) {
        try {
            sql = "\n"
                    + "insert\n"
                    + "	into\n"
                    + "	user (username,\n"
                    + "	name,\n"
                    + "	password,\n"
                    + "	`role`,\n"
                    + "	note,\n"
                    + "	`date`,\n"
                    + "	status)\n"
                    + "values(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getUsername());
            preparedStatement.setString(2, request.getName());
            preparedStatement.setString(3, request.getPassword());
            preparedStatement.setString(4, request.getRole());
            preparedStatement.setString(5, request.getNote());
            preparedStatement.setTimestamp(6, request.getDate());
            preparedStatement.setString(7, request.getStatus());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //    MERUBAH USER
    @Override
    public void update(SaveUserRequest request) {
        try {
            sql = "\n"
                    + "update\n"
                    + "	user set\n"
                    + "	username =?,\n"
                    + "	name =?,\n"
                    + "	password =?,\n"
                    + "	`role` =?,\n"
                    + "	note =?,\n"
                    + "	`date` =?,\n"
                    + "	status =?\n"
                    + "where\n"
                    + "	id =?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getUsername());
            preparedStatement.setString(2, request.getName());
            preparedStatement.setString(3, request.getPassword());
            preparedStatement.setString(4, request.getRole());
            preparedStatement.setString(5, request.getNote());
            preparedStatement.setTimestamp(6, request.getDate());
            preparedStatement.setString(7, request.getStatus());
            preparedStatement.setLong(8, request.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage());
        }
    }

    //    MENGHAPUS USER
    @Override
    public void delete(Long request) {
        try {
            sql = "\n"
                    + "delete\n"
                    + "from\n"
                    + "	user\n"
                    + "where\n"
                    + "	id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.info(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //    MENGGANTI PASSWORD
    @Override
    public void changePassword(SaveUserRequest request) {
        try {
            sql = "\n"
                    + "update\n"
                    + "	user set\n"
                    + "	password =?,\n"
                    + "	date =?\n"
                    + "where\n"
                    + "	username =?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(2, request.getDate());
            preparedStatement.setString(3, request.getUsername());
            preparedStatement.setString(1, request.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage());
        }
    }

    //    CEK USER YANG AKTIF
    @Override
    public Boolean selectUserExist(String userName) {
        try {
            sql = "\n"
                    + "select\n"
                    + "	*\n"
                    + "from\n"
                    + "	user\n"
                    + "where\n"
                    + "	username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                return true;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage());
        }
        return false;
    }

    //    CEK PASSWORD
    @Override
    public String checkChangePassword(ChangePasswordRequest request) {
        try {
            sql = "\n"
                    + "select\n"
                    + "	*\n"
                    + "from\n"
                    + "	user\n"
                    + "where\n"
                    + "	username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getUsername());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage());
        }
        return null;
    }

    //    MENAMPILKAN USER YANG BISA DI CHAT
    @Override
    public List<SaveUserResponse> selectAllUser(String request, String username) {
        List<SaveUserResponse> response = new ArrayList<>();
        try {
            sql = "\n"
                    + "select\n"
                    + "	*\n"
                    + "from\n"
                    + "	user\n"
                    + "where\n"
                    + "	username != ?\n"
                    + "	and\n"
                    + "	(name like ?\n"
                    + "		or role like ?\n"
                    + "		or note like ?\n"
                    + "		or status like ?)\n"
                    + " and status = 'ON'"
                    + "order by\n"
                    + "	id desc;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, request);
            preparedStatement.setString(3, request);
            preparedStatement.setString(4, request);
            preparedStatement.setString(5, request);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SaveUserResponse userResponse = new SaveUserResponse();
                userResponse.setDate(resultSet.getTimestamp("date"));
                userResponse.setId(resultSet.getLong("id"));
                userResponse.setName(resultSet.getString("name"));
                userResponse.setNote(resultSet.getString("note"));
                userResponse.setPassword(resultSet.getString("password"));
                userResponse.setRole(resultSet.getString("role"));
                userResponse.setStatus(resultSet.getString("status"));
                userResponse.setUsername(resultSet.getString("username"));
                response.add(userResponse);
            }
            resultSet.close();
            preparedStatement.close();
            return response;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
            return null;
        }
    }

    //    SELECT USERNAME
    @Override
    public UserResponse selectUsername(String username) {
        UserResponse response = new UserResponse();
        try {
            sql = "\n"
                    + "select\n"
                    + "	*\n"
                    + "from\n"
                    + "	user\n"
                    + "where\n"
                    + "	username = ?\n"
                    + "	and status = 'ON';";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response.setId(resultSet.getLong("id"));
                response.setUsername(resultSet.getString("username"));
                response.setDate(resultSet.getTimestamp("date"));
                response.setName(resultSet.getString("name"));
                response.setNote(resultSet.getString("note"));
                response.setPassword(resultSet.getString("password"));
                response.setRole(resultSet.getString("role"));
                response.setStatus(resultSet.getBoolean("status"));
            } else {
                response = null;
            }
            resultSet.close();
            preparedStatement.close();
            return response;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
            return null;
        }

    }

}
