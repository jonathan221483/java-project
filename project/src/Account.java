public class Account implements Accounts{
    private String login;
    private String password;
    private String code;
    public Account(String login, String password){
        this.login = login;
        this.password = password;
    }
    public Account(String code){
        this.code = code;
    }
    public Account() {}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }

    public void checkpass(){
        int up = 0, low = 0, digit = 0;
        for(int i = 0; i < this.password.length(); i++) {
            if (Character.isUpperCase(this.password.charAt(i))) {
                up++;
            } else if (Character.isLowerCase(this.password.charAt(i))) {
                low++;
            } else if (Character.isDigit(this.password.charAt(i))) {
                digit++;
            }
        }
        if(up == 0 || low == 0 || digit == 0){
            System.out.println("Your password is weak, we recommend you to change it");
        }
        if(this.password.length() < 6){
            System.out.println("Your password is too short, we recommend you to change it");
        }
    }
    @Override
    public boolean checker(String name, String password) {
        return (getLogin().equals(login) && getPassword().equals(password));
    }
}

