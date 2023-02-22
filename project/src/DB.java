import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import org.mindrot.jbcrypt.BCrypt;
public class DB implements Inter{
    public DB(){}

    @Override
    public Connection connection_psql(String dbname, String user, String pass)
    {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+ dbname,user,pass);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
    public void update_password(Connection conn)
    {
        Statement statement;
        ResultSet res = null;
        try
        {
            statement = conn.createStatement();
            res = statement.executeQuery(String.format("select * from %s", "users"));
            while(res.next()) {
                statement = conn.createStatement();
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                String str = res.getString("password");
                byte[] bytes = md5.digest(str.getBytes());
                StringBuilder builder = new StringBuilder();
                for (byte b: bytes){
                    builder.append(String.format("%02X", b));
                }
                String query = String.format("update %s set password ='%s' where login = '%s'", "users", builder.toString(), res.getString("login"));
                statement.executeUpdate(query);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    @Override
    public AuthIIN login(Connection connection, String login, String password){
        Statement statement;
        ResultSet res = null;
        Send s = new Send();
        try{
            String query=String.format("select * from %s", "users");
            statement = connection.createStatement();
            res = statement.executeQuery(query);
            while(res.next()){
                AuthIIN user = new AuthIIN(res.getString("login"), res.getString("password"), res.getString("iin"));
                if (user.checker(login, password))
                {
                    user.checkpass();
                    return user;
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
