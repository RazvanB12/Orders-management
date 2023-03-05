package start;

import presentation.*;
import java.sql.SQLException;

/**
 * Clasa Main realizeaza crearea aplicatiei prin intermediul metodei main
 */

public class Main {
    public static void main(String[] args) throws SQLException {
        MainView mainView = new MainView();
        ClientView clientView = new ClientView();
        ProductView productView = new ProductView();
        OrderView orderView = new OrderView();
        Controller controller = new Controller(mainView, clientView, productView, orderView);
    }
}