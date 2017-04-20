package com.example.piromsurang.ebookk.data;

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
import java.util.Observable;

/**
 * Created by Piromsurang on 4/21/2017 AD.
 */

public class RealBookRepository extends Observable implements Repository {

    private ArrayList<Book> books;
    private static RealBookRepository instance;

    public RealBookRepository() {
        books = new ArrayList<>();
    }

    public static RealBookRepository getInstance() {
        if(instance == null) {
            instance = new RealBookRepository();
        }
        return instance;
    }

    public ArrayList<Book> getBookList() {
        return books;
    }

    @Override
    public void loadData() {
        BookFetcherTask task = new BookFetcherTask();
        task.execute();
    }

    public class BookFetcherTask extends AsyncTask<Void, Void, ArrayList<Book> > {

        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            String bookListJsonStr = loadTopicJson();
            if (bookListJsonStr == null) {
                return null;
            }
            ArrayList<Book> results = new ArrayList<>();
            try {
                JSONArray dataArray = new JSONArray(bookListJsonStr);

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject bookJson = dataArray.getJSONObject(i);
                    Book book = new Book(bookJson.getDouble("prize"),
                            bookJson.getString("img_url"), bookJson.getString("id"),
                            bookJson.getString("title"), bookJson.getString("pub_year"));
                    results.add(book);
                }
            } catch (JSONException e) {
                return null;
            }
            return results;
        }

        private String loadTopicJson() {
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
                    books.add(t);
                }
                setChanged();
                notifyObservers();
            }
        }
    }
}
