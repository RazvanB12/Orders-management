package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Fereastra de tip MainView este utlizata pentru a face trimiterea la una din cele 3 ferestre principale de lucru
 * Fereastra contine 3 butoane (Client, Product, Orders) in functie de fereastra spre care se doreste redirectionarea
 */

public class MainView extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JButton clientButton, productButton, orderButton;

    public MainView(){
        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setTitle("SELECT OPERATION WINDOW");

        title = new JLabel("PERFORM OPERATIONS ON");
        title.setBounds(5, 30, 290, 25);
        title.setFont(new Font("Tahoma", Font.BOLD, 20));

        clientButton = new JButton("CLIENTS");
        clientButton.setBounds(70, 80, 150, 40);
        clientButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        productButton = new JButton("PRODUCTS");
        productButton.setBounds(70, 130, 150, 40);
        productButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        orderButton = new JButton("ORDERS");
        orderButton.setBounds(70, 180, 150, 40);
        orderButton.setFont(new Font("Tahoma", Font.BOLD, 20));

        panel.add(title);
        panel.add(clientButton);
        panel.add(productButton);
        panel.add(orderButton);

        this.add(panel);
    }

    public void addClientButtonListener(ActionListener listener) {
        clientButton.addActionListener(listener);
    }

    public void addProductButtonListener(ActionListener listener) {
        productButton.addActionListener(listener);
    }

    public void addOrderButtonListener(ActionListener listener) { orderButton.addActionListener(listener); }
}
