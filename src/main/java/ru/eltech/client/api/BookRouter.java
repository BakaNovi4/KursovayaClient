package ru.eltech.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.eltech.client.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookRouter {
    private final String BOOK_URL = "http://localhost:4000/book";

    public BookRouter() {}

    public List<Book> getAllBooks() throws IOException {
        URL url = new URL(BOOK_URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            List<Book> books = mapper.readValue(response.toString(), new TypeReference<List<Book>>(){});

            return books;
        } else {
            System.out.println("GET request error");
            return null;
        }
    }

    public Book createBook(String title, String author, int price) throws IOException {
        URL url = new URL(BOOK_URL);

        //create params
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("title", title);
        params.put("author", author);
        params.put("price", price);

        //add params to request
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        //add headers to request
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        connection.setDoOutput(true);
        connection.getOutputStream().write(postDataBytes);

        //get response
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            Book book = mapper.readValue(response.toString(), new TypeReference<Book>(){});

            return book;
        } else {
            System.out.println("POST request error");
            return new Book();
        }
    }

    public Book updateBook(int id, String title, String author, int price) throws IOException {
        URL url = new URL(BOOK_BY_ID_URL(id));

        //create params
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("title", title);
        params.put("author", author);
        params.put("price", price);

        //add params to request
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        //add headers to request
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        connection.setDoOutput(true);
        connection.getOutputStream().write(postDataBytes);

        //get response
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            Book book = mapper.readValue(response.toString(), new TypeReference<Book>(){});

            return book;
        } else {
            System.out.println("POST request error");
            return new Book();
        }
    }

    public void deleteBook(int id) throws IOException {
        URL url = new URL(BOOK_DELETE_URL(id));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("success");
        } else {
            System.out.println("DELETE request error");
        }
    }

    private String BOOK_BY_ID_URL(int id) { return BOOK_URL + "/" + id; }

    private String BOOK_DELETE_URL(int id) { return BOOK_URL + "/delete/" + id; }
}
