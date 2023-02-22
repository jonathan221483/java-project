import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AuthIIN extends Account implements Accounts{
    private String iin;
    public AuthIIN(String login, String password, String iin) {
        super(login, password);
        this.iin = iin;
    }

    public AuthIIN() {}
    @Override
    public boolean checker(String login, String pass2) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(pass2.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b: bytes){
                builder.append(String.format("%02X", b));
            }
            return (getLogin().equals(login) && getPassword().equals(builder.toString())) || (getPassword().equals(builder.toString()) && getIIN().equals(login));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String getIIN() {
        return iin;
    }
    public void setIIN(String iin) {
        this.iin = iin;
    }
}
