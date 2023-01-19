package ru.eltech;

import ru.eltech.client.Client;
import ru.eltech.client.api.BookRouter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BookRouter bookRouter =new BookRouter();

        Client client = new Client(bookRouter);
        client.run();
    }
}