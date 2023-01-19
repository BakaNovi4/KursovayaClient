package ru.eltech.client.tableModel;

import ru.eltech.client.api.BookRouter;
import ru.eltech.client.model.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    private final int columnCount = 4;
    private List<String[]> bookRecords = new ArrayList<>();
    private final BookRouter router;

    public BookTableModel(BookRouter router){
        this.router = router;
    }

    @Override
    public int getRowCount() {
        return bookRecords.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return bookRecords.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 -> {return "id";}
            case 1 -> {return "title";}
            case 2 -> {return "author";}
            case 3 -> {return "price";}
        }

        return "";
    }

    public void addRecord(Book book) {
        bookRecords.add(book.toString().split(","));
    }

    public void clearData() {
        bookRecords.clear();
    }

    public void setBooksRecords(List<String[]> data) {
        bookRecords = new ArrayList<>(data);
    }

    public void updateRecord(Book book) {
        var record = bookRecords.stream()
                .filter(item -> String.valueOf(book.getId()).equals(item[0]))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        record[1] = book.getTitle();
        record[2] = book.getAuthor();
        record[3] = String.valueOf(book.getPrice());
    }

    public void deleteRecord(int id) {
        var record = bookRecords.stream()
                .filter(item -> String.valueOf(id).equals(item[0]))
                .findFirst()
                .orElseThrow();

        bookRecords.remove(record);
    }
}
