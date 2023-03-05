package presentation;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Fereastra de tip OrderView este utlizata pentru a crea comenzi noi
 * Fereastra contine un buton (Buy) si tabelele de tip Client si Produs
 */

public class OrderView extends JFrame{
    private JPanel panel;
    private JTable clientTable, productTable;
    private JLabel clientLabel, productLabel, quantityLabel;
    private JTextField clientField, productField, quantityField, reciept;
    private JButton buy;

    public OrderView(){
        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(380, 500);
        this.setTitle("ORDER WINDOW");

        String[][] clientData = new String[15][3];
        ClientDAO client = new ClientDAO();
        List<Client> clients = client.findAll();
        int i=0;
        for (Client c : clients){
            clientData[i][0] = Integer.toString(c.getId());
            clientData[i][1] = c.getName();
            clientData[i][2] = Integer.toString(c.getAge());
            i++;
        }
        String[] columnNames = { "ID", "NAME", "AGE" };
        clientTable = new JTable(clientData, columnNames);
        JScrollPane clientScroll = new JScrollPane(clientTable);
        clientScroll.setBounds(30, 30, 300, 100);


        String[][] productData = new String[15][4];
        ProductDAO product = new ProductDAO();
        List<Product> products = product.findAll();
        i=0;
        for (Product p : products){
            productData[i][0] = Integer.toString(p.getId());
            productData[i][1] = p.getName();
            productData[i][2] = Integer.toString(p.getQuantity());
            productData[i][3] = Integer.toString(p.getPrice());
            i++;
        }
        String[] columnNames1 = { "ID", "NAME", "QUANTITY", "PRICE" };
        productTable = new JTable(productData, columnNames1);
        JScrollPane productScroll = new JScrollPane(productTable);
        productScroll.setBounds(30, 160, 300, 100);

        clientLabel = new JLabel("CLIENT ID");
        clientLabel.setBounds(80, 290, 100, 25);
        clientLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        clientField = new JTextField();
        clientField.setBounds(190, 290, 100,25);
        clientField.setFont(new Font("Tahoma", Font.BOLD, 15));

        productLabel = new JLabel("PRODUCT ID");
        productLabel.setBounds(80, 320, 100, 25);
        productLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        productField = new JTextField();
        productField.setBounds(190, 320, 100,25);
        productField.setFont(new Font("Tahoma", Font.BOLD, 15));

        quantityLabel = new JLabel("QUANTITY");
        quantityLabel.setBounds(80, 350, 100, 25);
        quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        quantityField = new JTextField();
        quantityField.setBounds(190, 350, 100,25);
        quantityField.setFont(new Font("Tahoma", Font.BOLD, 15));

        buy = new JButton("BUY");
        buy.setBounds(140, 380, 100, 25);
        buy.setFont(new Font("Tahoma", Font.BOLD, 15));

        reciept = new JTextField();
        reciept.setBounds(80, 410, 210,25);
        reciept.setFont(new Font("Tahoma", Font.BOLD, 15));
        reciept.setEditable(false);

        panel.add(clientScroll);
        panel.add(productScroll);
        panel.add(clientLabel);
        panel.add(clientField);
        panel.add(productLabel);
        panel.add(productField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(buy);
        panel.add(reciept);

        this.add(panel);
    }

    public JTextField getClientField() {return clientField;}

    public JTextField getProductField() {return productField;}

    public JTextField getQuantityField() {return quantityField;}

    public void addBuyButtonListener(ActionListener listener) {buy.addActionListener(listener);}

    public JTextField getReciept() {return reciept;}
}
