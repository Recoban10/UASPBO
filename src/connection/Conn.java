package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

//MEMBUAT KONEKSI KE DATABASE
public class Conn {

    private static Connection koneksi;
    private static final Properties prop = new Properties();
    private static final Logger logger = Logger.getLogger(Conn.class);
    private static String url;
    private static String user;
    private static String password;

    public static Connection getConnection() {

        if (koneksi == null) {
            try {
                try {
                    url = "jdbc:mysql://" + getPropValues("SERVER") + "/" + getPropValues("DATABASE");
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                try {
                    user = getPropValues("USERNAME");
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                try {
                    password = getPropValues("PASSWORD");
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }

                DriverManager.registerDriver(new com.mysql.jdbc.Driver());

                koneksi = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return koneksi;

    }

    public static String getPropValues(String value) throws IOException {
        String result = new String();
        String configFile = "config.properties";
        try {
            InputStream inputStream = new FileInputStream(configFile);
            prop.load(inputStream);
            result = prop.getProperty(value);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}
