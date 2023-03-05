package presentation;

import dao.ProductDAO;
import model.Product;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Fereastra de tip ProductView este utlizata pentru lucrul asupra datelor din tabela Product
 * Fereastra contine 3 butoane (Insert, Delete, Edit) si tabela de product
 */

public class ProductView extends JFrame {
    private JPanel panel;
    private JButton insertButton, editButton, deleteButton;
    private JTable table;
    private JTextField idField, nameField, quantityField, priceField;
    private JLabel idLabel, nameLabel, quantityLabel, priceLabel;

    public ProductView(){
        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(510, 500);
        this.setTitle("PRODUCT WINDOW");

        idLabel = new JLabel("ID");
        idLabel.setBounds(60, 20, 50, 25);
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        idField = new JTextField();
        idField.setBounds(20, 50, 100,25);
        idField.setFont(new Font("Tahoma", Font.BOLD, 15));

        nameLabel = new JLabel("NAME");
        nameLabel.setBounds(170, 20, 50, 25);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        nameField = new JTextField();
        nameField.setBounds(140, 50, 100,25);
        nameField.setFont(new Font("Tahoma", Font.BOLD, 15));

        quantityLabel = new JLabel("QUANTITY");
        quantityLabel.setBounds(270, 20, 100, 25);
        quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        quantityField = new JTextField();
        quantityField.setBounds(260, 50, 100,25);
        quantityField.setFont(new Font("Tahoma", Font.BOLD, 15));

        priceLabel = new JLabel("PRICE");
        priceLabel.setBounds(410, 20, 100, 25);
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        priceField = new JTextField();
        priceField.setBounds(380, 50, 100,25);
        priceField.setFont(new Font("Tahoma", Font.BOLD, 15));

        insertButton = new JButton("INSERT");
        insertButton.setBounds(200, 80, 100, 25);
        insertButton.setFont(new Font("Tahoma", Font.BOLD, 15));

        editButton = new JButton("EDIT");
        editButton.setBounds(200, 110, 100, 25);
        editButton.setFont(new Font("Tahoma", Font.BOLD, 15));

        deleteButton = new JButton("DELETE");
        deleteButton.setBounds(200, 140, 100, 25);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));

        String[][] data = new String[15][4];

        ProductDAO product = new ProductDAO();
        List<Product> products = product.findAll();
        int i=0;
        for (Product p : products){
            data[i][0] = Integer.toString(p.getId());
            data[i][1] = p.getName();
            data[i][2] = Integer.toString(p.getQuantity());
            data[i][3] = Integer.toString(p.getPrice());
            i++;
        }

        String[] columnNames = { "ID", "NAME", "QUANTITY", "PRICE" };
        table = new JTable(data, columnNames);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 200, 420, 240);

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(insertButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(scroll);

        this.add(panel);
    }

    public void addProductInsertButtonListener(ActionListener listener) {insertButton.addActionListener(listener);}

    public void addProductDeleteButtonListener(ActionListener listener) {deleteButton.addActionListener(listener);}

    public void addProductEditButtonListener(ActionListener listener) {editButton.addActionListener(listener);}

    public JTextField getProductIdField() {return idField;}

    public JTextField getProductNameField() {return nameField;}

    public JTextField getProductQuantityField() {return quantityField;}

    public JTextField getProductPriceField() {return priceField;}
}
