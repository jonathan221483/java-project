public interface Checking {
    default boolean checker(String name, String password) {
        return false;
    }
}
