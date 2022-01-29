import java.util.HashMap;

public interface MenuStrategy {
    UserAccountType getAccountType();
    HashMap<String, String> getAccountHolderInformation();
    String getAccountMenuOptions(int index);
    String getAccountMenuOptionsConsola(int index);
    void nextMenuOption(int index);
    void previousMenuOption(int index);
}
