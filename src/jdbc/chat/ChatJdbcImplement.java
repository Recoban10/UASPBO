package jdbc.chat;

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
public class ChatJdbcImplement implements ChatJdbc {

//    MENGGUNAKAN PreparedStatement UNTUK MENCEGAH SQL INJECTION
    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(ChatJdbcImplement.class);

    public ChatJdbcImplement() {
        connection = Conn.getConnection();
    }

//    MENAMPILKAN ISI CHAT
    @Override
    public List<UsernameResponse> selectAll(UsernameRequest request) {
        List<UsernameResponse> response = new ArrayList<>();
        try {
            sql = "\n"
                    + "select\n"
                    + "	(select name from user where username = message.username) sender,\n"
                    + "	(select name from user where username = message.target_username) reciver,\n"
                    + "	message.content\n"
                    + "from\n"
                    + "	message\n"
                    + "left join user on\n"
                    + "	message.username = user.id\n"
                    + "where\n"
                    + "	message.username in(?, ?)\n"
                    + "	and message.target_username in(?, ?)\n"
                    + "order by\n"
                    + "	message.date;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getUsername());
            preparedStatement.setString(2, request.getUsernameTarget());
            preparedStatement.setString(3, request.getUsername());
            preparedStatement.setString(4, request.getUsernameTarget());
            logger.info(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UsernameResponse userResponse = new UsernameResponse();
                userResponse.setContent(resultSet.getString("content"));
                userResponse.setReciver(resultSet.getString("reciver"));
                userResponse.setSender(resultSet.getString("sender"));;
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

//    MENGIRIM CHAT
    @Override
    public void insert(SaveChatRequest request) {
        try {
            sql = "\n"
                    + "INSERT INTO message\n"
                    + "(username, target_username, `date`, content)\n"
                    + "VALUES(?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getUsername());
            preparedStatement.setString(2, request.getUsernameTarget());
            preparedStatement.setTimestamp(3, request.getDate());
            preparedStatement.setString(4, request.getContent());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
