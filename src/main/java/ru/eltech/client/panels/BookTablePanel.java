package ru.eltech.client.panels;

import ru.eltech.client.api.BookRouter;
import ru.eltech.client.model.Book;
import ru.eltech.client.tableModel.BookTableModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BookTablePanel extends JPanel{
    private final BookRouter router;
    private final BookTableModel btModel;
    private final JScrollPane scrollPane;
    private final JTable bookTable;

    public BookTablePanel(BookRouter router) throws IOException {
        this.router = router;

        btModel = new BookTableModel(router);
        bookTable = new JTable(btModel);
        scrollPane = new JScrollPane(bookTable);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);

        update();
    }

    public BookTableModel getBtModel() {
        return btModel;
    }

    public void update() throws IOException {
        try {
            List<Book> books =  router.getAllBooks();

            if (books.isEmpty()) {
                return;
            }

            var data = books.stream()
                    .map(Book::toString)
                    .map(record -> record.split(",")).toList();

            btModel.clearData();
            btModel.fireTableDataChanged();

            btModel.setBooksRecords(data);
            btModel.fireTableDataChanged();
        } catch (IOException ex) {
            System.out.println("update table error");
        }
    }
}
