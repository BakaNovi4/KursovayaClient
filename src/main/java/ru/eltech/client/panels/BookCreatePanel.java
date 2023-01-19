package ru.eltech.client.panels;

import ru.eltech.client.api.BookRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BookCreatePanel extends JPanel {
    public BookCreatePanel(BookRouter router, BookTablePanel bookTable) {
        JLabel titleLabel = new JLabel("Введите название");
        JTextField titleText = new JTextField(25);
        JLabel authorLabel = new JLabel("Введите имя автора");
        JTextField authorText = new JTextField(25);
        JLabel priceLabel = new JLabel("Введите стоимость");
        JTextField priceText = new JTextField(25);

        JButton send = new JButton("Добавить");
        send.addActionListener(new BookCreateListener(router, bookTable, titleText, authorText, priceText));

        setLayout(new GridLayout(7,1));

        add(titleLabel);
        add(titleText);

        add(authorLabel);
        add(authorText);

        add(priceLabel);
        add(priceText);

        add(send);
    }

    private record BookCreateListener(BookRouter router,
                                     BookTablePanel bookTable,
                                     JTextField titleText,
                                     JTextField authorText,
                                     JTextField priceText)
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String title = titleText.getText().trim();
            String author = authorText.getText().trim();

            int price = 0;
            if (priceText.getText().trim().length() > 0) {
                price = Integer.parseInt(priceText.getText().trim());
            }

            try {
                bookTable.getBtModel().addRecord(router.createBook(title, author, price));
                bookTable.update();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            finally {
                titleText.setText("");
                authorText.setText("");
                priceText.setText("");
            }
        }
    }
}
