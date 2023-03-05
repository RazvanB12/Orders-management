package presentation;

import dao.ClientDAO;
import model.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Fereastra de tip ClientView este utlizata pentru lucrul asupra datelor din tabela Client
 * Fereastra contine 3 butoane (Insert, Delete, Edit) si tabela de clienti
 */

public class ClientView extends JFrame {
    private JPanel panel;
    private JButton insertButton, editButton, deleteButton;
    private JTable table;
    private JTextField idField, nameField, ageField;
    private JLabel idLabel, nameLabel, ageLabel;
    private JScrollPane scroll;

    public ClientView(){
        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(390, 500);
        this.setTitle("CLIENT WINDOW");

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

        ageLabel = new JLabel("AGE");
        ageLabel.setBounds(300, 20, 50, 25);
        ageLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        ageField = new JTextField();
        ageField.setBounds(260, 50, 100,25);
        ageField.setFont(new Font("Tahoma", Font.BOLD, 15));

        insertButton = new JButton("INSERT");
        insertButton.setBounds(140, 80, 100, 25);
        insertButton.setFont(new Font("Tahoma", Font.BOLD, 15));

        editButton = new JButton("EDIT");
        editButton.setBounds(140, 110, 100, 25);
        editButton.setFont(new Font("Tahoma", Font.BOLD, 15));

        deleteButton = new JButton("DELETE");
        deleteButton.setBounds(140, 140, 100, 25);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));

        String[][] data = new String[15][3];

        ClientDAO client = new ClientDAO();
        List<Client> clients = client.findAll();
        int i=0;
        for (Client c : clients){
            data[i][0] = Integer.toString(c.getId());
            data[i][1] = c.getName();
            data[i][2] = Integer.toString(c.getAge());
            i++;
        }

        String[] columnNames = { "ID", "NAME", "AGE" };
        table = new JTable(data, columnNames);
        scroll = new JScrollPane(table);
        scroll.setBounds(30, 200, 320, 240);

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(insertButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(scroll);

        this.add(panel);
    }

    public void addClientInsertButtonListener(ActionListener listener) {insertButton.addActionListener(listener);}

    public void addClientDeleteButtonListener(ActionListener listener) {deleteButton.addActionListener(listener);}

    public void addClientEditButtonListener(ActionListener listener) {editButton.addActionListener(listener);}

    public JTextField getClientIdField() {return idField;}

    public JTextField getClientNameField() {return nameField;}

    public JTextField getClientAgeField() {return ageField;}
}
