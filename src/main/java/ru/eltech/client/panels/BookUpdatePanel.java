package ru.eltech.client.panels;

import ru.eltech.client.api.BookRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BookUpdatePanel extends JPanel {
    public BookUpdatePanel(BookRouter router, BookTablePanel bookTable) {
        JLabel idLabel = new JLabel("Введите id");
        JTextField idText = new JTextField(25);
        JLabel titleLabel = new JLabel("Введите название");
        JTextField titleText = new JTextField(25);
        JLabel authorLabel = new JLabel("Введите имя автора");
        JTextField authorText = new JTextField(25);
        JLabel priceLabel = new JLabel("Введите стоимость");
        JTextField priceText = new JTextField(25);

        JButton edit = new JButton("Изменить");
        edit.addActionListener(new BookUpdateListener(router, bookTable, idText, titleText, authorText, priceText));

        setLayout(new GridLayout(9,1));

        add(idLabel);
        add(idText);

        add(titleLabel);
        add(titleText);

        add(authorLabel);
        add(authorText);

        add(priceLabel);
        add(priceText);

        add(edit);
    }

    private record BookUpdateListener(   BookRouter router,
                                        BookTablePanel bookTable,
                                        JTextField idText,
                                        JTextField titleText,
                                        JTextField authorText,
                                        JTextField priceText)
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int id = 0;
            if (idText.getText().trim().length() > 0) {
                id = Integer.parseInt(idText.getText().trim());
            }

            String title = titleText.getText().trim();
            String author = authorText.getText().trim();

            int price = 0;
            if (priceText.getText().trim().length() > 0) {
                price = Integer.parseInt(priceText.getText().trim());
            }

            try {
                bookTable.getBtModel().updateRecord(router.updateBook(id, title, author, price));
                bookTable.update();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }
            finally {
                idText.setText("");
                titleText.setText("");
                authorText.setText("");
                priceText.setText("");
            }
        }
    }
}
