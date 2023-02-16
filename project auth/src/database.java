import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import org.mindrot.jbcrypt.BCrypt;
public class database implements Connector{
    public database(){}

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
    @Override
    public AuthIIN login(Connection connection, String login, String password){
        Statement statement;
        ResultSet res = null;
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
