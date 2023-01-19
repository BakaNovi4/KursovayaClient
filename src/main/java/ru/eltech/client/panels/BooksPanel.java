package ru.eltech.client.panels;

import ru.eltech.client.api.BookRouter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BooksPanel extends JPanel {

    public BooksPanel(BookRouter router) throws IOException {
        setLayout(new BorderLayout());

        JPanel commandPanel = new JPanel();;

        commandPanel.setLayout(new GridLayout(3,1));

        BookTablePanel bookTablePanel = new BookTablePanel(router);

        commandPanel.add(new BookCreatePanel(router, bookTablePanel));
        commandPanel.add(new BookUpdatePanel(router, bookTablePanel));
        commandPanel.add(new BookDeletePanel(router, bookTablePanel));

        add(commandPanel, BorderLayout.LINE_START);
        add(bookTablePanel, BorderLayout.CENTER);
    }
}
