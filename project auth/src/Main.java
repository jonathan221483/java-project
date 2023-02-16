import org.mindrot.jbcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static AuthIIN func(database db, Connection connection){
        int i = 0;
        Scanner s = new Scanner(System.in);
        System.out.println("Login first, then password ");
        while (i != 4) {
            AuthIIN ans = db.login(connection, s.next(), s.next());
            if (ans != null) {
                return ans;
            } else {
                System.out.println("Error");
                System.out.println("You have " + (3 - i) + " attempts left");
                i++;
            }
        }
        return null;
    }
    public static void main(String[] args)  {
        database db = new database();
        Connection connection = db.connection_psql("Authentification", "admin", "a2bf9c79");
        //db.update_password(connection);
        Scanner sc = new Scanner(System.in);
        Send s = new Send();
        AuthIIN user = func(db, connection);
        if(user != null){
            user.setCode(String.valueOf((int)(100000 + Math.random() * 899999)));
            s.emailsender(s.emailsettings(), user);
            System.out.println("Enter code from email");
            if(sc.nextLine().equals(user.getCode())) {
                System.out.println("Login succesfull");
            }
            else{
                System.out.println("Wrong code");
            }
        }
    }
}
