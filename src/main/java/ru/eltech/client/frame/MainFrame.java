package ru.eltech.client.frame;

import ru.eltech.client.api.BookRouter;
import ru.eltech.client.panels.BooksPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private final BookRouter bookRouter;

    public MainFrame(BookRouter bookRouter) throws HeadlessException, IOException {
        super("Books shop");
        this.bookRouter = bookRouter;

        add(new BooksPanel(bookRouter), BorderLayout.CENTER);
    }
}
