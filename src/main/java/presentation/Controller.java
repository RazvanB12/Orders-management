package presentation;

import dao.ClientDAO;
import dao.OrdersDAO;
import dao.ProductDAO;
import model.Client;
import model.Orders;
import model.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clasa Controller se ocupa cu principalele operatii realizate in cadrul ferestrelor interfetei grafice
 * Aceasta creeaza listenerele pentru toate butoanele
 */

public class Controller {
    private MainView mainView;
    private ClientView clientView;
    private ProductView productView;
    private OrderView orderView;

    public Controller(MainView mainView, ClientView clientView, ProductView productView, OrderView orderView) {
        this.mainView = mainView;
        this.clientView = clientView;
        this.productView = productView;
        this.orderView = orderView;

        mainView.setVisible(true);
        mainView.addClientButtonListener(new clientButtonListener());
        mainView.addProductButtonListener(new productButtonListener());
        mainView.addOrderButtonListener(new orderButtonListener());

        clientView.addClientInsertButtonListener(new clientInsertButtonListener());
        clientView.addClientDeleteButtonListener(new clientDeleteButtonListener());
        clientView.addClientEditButtonListener(new clientEditButtonListener());

        productView.addProductInsertButtonListener(new productInsertButtonListener());
        productView.addProductDeleteButtonListener(new productDeleteButtonListener());
        productView.addProductEditButtonListener(new productEditButtonListener());

        orderView.addBuyButtonListener(new buyButtonListener());
    }

    class clientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.this.mainView.setVisible(false);
            Controller.this.clientView.setVisible(true);
        }
    }

    class productButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.this.mainView.setVisible(false);
            Controller.this.productView.setVisible(true);
        }
    }

    class orderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.this.mainView.setVisible(false);
            Controller.this.orderView.setVisible(true);
        }
    }

    class clientInsertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = Controller.this.clientView.getClientNameField().getText();
            String age = Controller.this.clientView.getClientAgeField().getText();
            String command = "'" + name + "', " + age;
            ClientDAO clientDAO = new ClientDAO();
            Client client = clientDAO.insert(command);
            Controller.this.clientView.setVisible(false);
            Controller.this.clientView = new ClientView();
            Controller.this.clientView.setVisible(true);
            Controller.this.clientView.addClientInsertButtonListener(new clientInsertButtonListener());
            Controller.this.clientView.addClientDeleteButtonListener(new clientDeleteButtonListener());
            Controller.this.clientView.addClientEditButtonListener(new clientEditButtonListener());
        }
    }

    class clientDeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = Controller.this.clientView.getClientIdField().getText();
            int command = Integer.parseInt(id);
            ClientDAO clientDAO = new ClientDAO();
            clientDAO.delete(command);
            Controller.this.clientView.setVisible(false);
            Controller.this.clientView = new ClientView();
            Controller.this.clientView.setVisible(true);
            Controller.this.clientView.addClientInsertButtonListener(new clientInsertButtonListener());
            Controller.this.clientView.addClientDeleteButtonListener(new clientDeleteButtonListener());
            Controller.this.clientView.addClientEditButtonListener(new clientEditButtonListener());
        }
    }

    class clientEditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = Controller.this.clientView.getClientIdField().getText();
            String name = Controller.this.clientView.getClientNameField().getText();
            String age = Controller.this.clientView.getClientAgeField().getText();
            String command = name + ", " + age;
            int i = Integer.parseInt(id);
            ClientDAO clientDAO = new ClientDAO();
            Client client = clientDAO.update(i, command);
            Controller.this.clientView.setVisible(false);
            Controller.this.clientView = new ClientView();
            Controller.this.clientView.setVisible(true);
            Controller.this.clientView.addClientInsertButtonListener(new clientInsertButtonListener());
            Controller.this.clientView.addClientDeleteButtonListener(new clientDeleteButtonListener());
            Controller.this.clientView.addClientEditButtonListener(new clientEditButtonListener());
        }
    }

    class productInsertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = Controller.this.productView.getProductNameField().getText();
            String quantity = Controller.this.productView.getProductQuantityField().getText();
            String price = Controller.this.productView.getProductPriceField().getText();
            String command = "'" + name + "', " + quantity + ", " + price;
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.insert(command);
            Controller.this.productView.setVisible(false);
            Controller.this.productView = new ProductView();
            Controller.this.productView.setVisible(true);
            Controller.this.productView.addProductInsertButtonListener(new productInsertButtonListener());
            Controller.this.productView.addProductDeleteButtonListener(new productDeleteButtonListener());
            Controller.this.productView.addProductEditButtonListener(new productEditButtonListener());
        }
    }

    class productDeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = Controller.this.productView.getProductIdField().getText();
            int command = Integer.parseInt(id);
            ProductDAO productDAO = new ProductDAO();
            productDAO.delete(command);
            Controller.this.productView.setVisible(false);
            Controller.this.productView = new ProductView();
            Controller.this.productView.setVisible(true);
            Controller.this.productView.addProductInsertButtonListener(new productInsertButtonListener());
            Controller.this.productView.addProductDeleteButtonListener(new productDeleteButtonListener());
            Controller.this.productView.addProductEditButtonListener(new productEditButtonListener());
        }
    }

    class productEditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = Controller.this.productView.getProductIdField().getText();
            String name = Controller.this.productView.getProductNameField().getText();
            String quantity = Controller.this.productView.getProductQuantityField().getText();
            String price = Controller.this.productView.getProductPriceField().getText();
            int i = Integer.parseInt(id);
            String command = name + ", " + quantity + ", " + price;
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.update(i, command);
            Controller.this.productView.setVisible(false);
            Controller.this.productView = new ProductView();
            Controller.this.productView.setVisible(true);
            Controller.this.productView.addProductInsertButtonListener(new productInsertButtonListener());
            Controller.this.productView.addProductDeleteButtonListener(new productDeleteButtonListener());
            Controller.this.productView.addProductEditButtonListener(new productEditButtonListener());
        }
    }

    class buyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String idClient = Controller.this.orderView.getClientField().getText();
            String idProduct = Controller.this.orderView.getProductField().getText();
            String quantity = Controller.this.orderView.getQuantityField().getText();
            int idC = Integer.parseInt(idClient);
            int idP = Integer.parseInt(idProduct);
            int q = Integer.parseInt(quantity);
            ClientDAO clientDAO = new ClientDAO();
            Client client = clientDAO.findById(idC);
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.findById(idP);
            String reciept = null;
            String toWrite = readFromFile();

            if (product.getQuantity() == q){
                int total = product.getPrice() * q;
                String comm = idC + ", " + idP + ", " + q + ", " + total;
                OrdersDAO orderDAO = new OrdersDAO();
                Orders order = orderDAO.insert(comm);
                reciept = "Total to pay: " + String.valueOf(total);
                toWrite = toWrite + "\n\nRECIEPT\n" + "ClientID = " + idC + "\nProduct = " + product.getName() + "\nPrice = " + product.getPrice()
                        + "\nQuantity = " + q + "\nTotal = " + total;
                productDAO.delete(idP);
            }
            else if (product.getQuantity() > q){
                int total = product.getPrice() * q;
                toWrite = toWrite + "\n\nRECIEPT\n" + "ClientID = " + idC + "\nProduct = " + product.getName() + "\nPrice = " + product.getPrice()
                        + "\nQuantity = " + q + "\nTotal = " + total;
                String comm = idC + ", " + idP + ", " + q + ", " + total;
                OrdersDAO orderDAO = new OrdersDAO();
                Orders order = orderDAO.insert(comm);
                String command = product.getName() + ", " + q + ", " + product.getPrice();
                q = product.getQuantity() - q;
                reciept = "Total to pay: " + String.valueOf(total);
                product = productDAO.update(idP, command);
            }
            else {
                reciept = "Insufficient stock";
            }

            try {
                writeToFile(toWrite);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Controller.this.orderView.setVisible(false);
            Controller.this.orderView = new OrderView();
            Controller.this.orderView.setVisible(true);
            Controller.this.orderView.addBuyButtonListener(new buyButtonListener());
            Controller.this.orderView.getReciept().setText(reciept);
        }
    }

    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public String readFromFile(){
        String filePath = "reciept.txt";
        String content = null;
        try {
            content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void writeToFile(String string) throws IOException{
        FileWriter fileWriter = new FileWriter("reciept.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(string);
        printWriter.close();
    }
}
