package com.example.piromsurang.ebookk.data;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

import static com.example.piromsurang.ebookk.BookPresenter.DOWNLOADBOOK_CODE;
import static com.example.piromsurang.ebookk.BookPresenter.DOWNLOADIMAGE_CODE;
import static com.example.piromsurang.ebookk.BookPresenter.PROMOTION_CODE;

/**
 * Created by Piromsurang on 4/21/2017 AD.
 */

public class RealBookRepository extends Observable implements Repository, Observer {

    private ArrayList<Book> books;
    private ArrayList<Book> searchedBooks;
    private ArrayList<Bitmap> bitmaps;
    private ArrayList<PromotionBook> promotionBooks;
    private static RealBookRepository instance;

    public RealBookRepository() {
        books = new ArrayList<>();
        searchedBooks = new ArrayList<>();
        bitmaps = new ArrayList<>();
        promotionBooks = new ArrayList<>();
    }

    public ArrayList<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public static RealBookRepository getInstance() {
        if(instance == null) {
            instance = new RealBookRepository();
        }
        return instance;
    }

    public ArrayList<PromotionBook> getPromotionBooks() {
        return promotionBooks;
    }

    public ArrayList<Book> getBookList() {
        return books;
    }

    @Override
    public void loadData() {
        BookFetcherTask task = new BookFetcherTask();
        task.execute();
    }

    @Override
    public ArrayList<Book> searchByTitle(String t) {
        searchedBooks.clear();
        for (Book b : books) {
            if( b.getTitle().contains(t)) {
                searchedBooks.add(b);
            }
        }

        Collections.sort(searchedBooks, new Comparator<Book>() {
            @Override
            public int compare(Book one, Book other) {
                return one.getTitle().compareTo(other.getTitle());
            }
        });

        return searchedBooks;
    }

    public int getPosition(Book b) {
        int position = -1;
        for(int i = 0 ; i < books.size() ; i++ ) {
            if(books.get(i).getTitle().contains(b.getTitle())) {
                position = i;
                break;
            }
        }
        return position;
    }

    @Override
    public ArrayList<Book> searchByPublishedYear(String t) {
        //books.stream().filter().sorted().toArray(); Net suggested for new android version

        searchedBooks.clear();
        for(Book b : books) {
            if( b.getPub_year().substring(0, t.length()).equalsIgnoreCase(t)) {
                searchedBooks.add(b);
            }
        }

        Collections.sort(searchedBooks, new Comparator<Book>() {
            @Override
            public int compare(Book one, Book other) {
                return one.getPub_year().compareTo(other.getPub_year());
            }
        });
        return searchedBooks;
    }

    @Override
    public Book getBookFromId(String id) {
        for(Book b : books) {
            if(b.getId().contains(id)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        bitmaps.add(getPosition(getBookFromId(arg.toString())), getBookFromId(arg.toString()).getBitmap());
        System.out.println(bitmaps.size() + "    " + books.size());
        if(bitmaps.size() == books.size()) {
            setChanged();
            notifyObservers(DOWNLOADIMAGE_CODE);
        }
    }

    public void loadPromotionBooks() {
        PromotionBookFetcher fetcher = new PromotionBookFetcher();
        fetcher.execute();
    }

    public class BookFetcherTask extends AsyncTask<Void, Void, ArrayList<Book> > {

        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            String bookListJsonStr = loadBookJson();
            if (bookListJsonStr == null) {
                return null;
            }
            ArrayList<Book> results = new ArrayList<>();
            try {
                JSONArray dataArray = new JSONArray(bookListJsonStr);

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject bookJson = dataArray.getJSONObject(i);
                    Book book = new Book(bookJson.getDouble("price"),
                            bookJson.getString("img_url"), bookJson.getString("id"),
                            bookJson.getString("title"), bookJson.getString("pub_year"));
                    results.add(book);
                }
            } catch (JSONException e) {
                return null;
            }
            return results;
        }

        private String loadBookJson() {
            String result = "";
            try {
                URL dataUrl = new URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");
                URLConnection conn = dataUrl.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader((
                        conn.getInputStream()
                )));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                }
                return result;
            } catch (IOException e) {
                return result;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Book> results) {
            if( results != null ) {
                books.clear();
                for (Book t : results) {
                    t.addObserver(RealBookRepository.getInstance());
                    books.add(t);
                }
                setChanged();
                notifyObservers(DOWNLOADBOOK_CODE);
                loadPromotionBooks();
            }
        }
    }

    public class PromotionBookFetcher extends AsyncTask<Void, Void, ArrayList<PromotionBook> > {

        @Override
        protected ArrayList<PromotionBook> doInBackground(Void... params) {
            String bookListJsonStr = loadBookJson();
            if (bookListJsonStr == null) {
                return null;
            }
            ArrayList<PromotionBook> results = new ArrayList<>();
            try {
                JSONArray dataArray = new JSONArray(bookListJsonStr);

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject bookJson = dataArray.getJSONObject(i);

                    PromotionBook book = new PromotionBook(bookJson.getString("id"),
                                                            bookJson.getString("title"),
                                                            bookJson.getDouble("price"),
                                                            bookJson.getJSONArray("book_ids"));
                    for(int k = 0 ; k < book.getB().length() ; k++ ) {
                        String id = book.getB().getString(i);
                        book.addBook(getBookFromId(id));
                    }
                    results.add(book);
                }
            } catch (JSONException e) {
                return null;
            }
            return results;
        }

        private String loadBookJson() {
            String result = "";
            try {
                URL dataUrl = new URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/promotions.json");
                URLConnection conn = dataUrl.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader((
                        conn.getInputStream()
                )));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                }
                return result;
            } catch (IOException e) {
                return result;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<PromotionBook> results) {
            if( results != null ) {
                promotionBooks.clear();
                for (PromotionBook t : results) {
                    promotionBooks.add(t);
                }
                setChanged();
                notifyObservers(PROMOTION_CODE);
            }
        }
    }
}
