public interface Accounts {
    default boolean checker(String name, String password) {
        return false;
    }
}
