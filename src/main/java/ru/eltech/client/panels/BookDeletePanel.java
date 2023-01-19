package ru.eltech.client.panels;

import ru.eltech.client.api.BookRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BookDeletePanel extends JPanel {
    public BookDeletePanel(BookRouter router, BookTablePanel bookTable) {
        JLabel idLabel = new JLabel("Введите id");
        JTextField idText = new JTextField(25);

        JButton delete = new JButton("Удалить");

        delete.addActionListener(new BookDeleteListener(router, bookTable, idText));

        setLayout(new GridLayout(3,1));

        add(idLabel);
        add(idText);

        add(delete);
    }

    private record BookDeleteListener(BookRouter router,
                                        BookTablePanel bookTable,
                                        JTextField idText)
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int id = 0;
            if (idText.getText().trim().length() > 0) {
                id = Integer.parseInt(idText.getText().trim());
            }

            try {
                router.deleteBook(id);
                bookTable.getBtModel().deleteRecord(id);
                bookTable.update();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            finally {
                idText.setText("");
            }
        }
    }
}
