import java.sql.Connection;

public interface Inter {
    default Connection connection_psql(String dbname, String user, String pass)
    {
        return null;
    }
    default AuthIIN login(Connection connection, String login, String password){
        return null;
    }
}
