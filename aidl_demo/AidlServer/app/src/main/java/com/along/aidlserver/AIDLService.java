package com.along.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.along.aidlclient.Book;
import com.along.aidlclient.BookManager;
import com.along.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {

    public static final String TAG = AIDLService.class.getSimpleName();

    //包含Book对象的list
    private List<Book> mBooks = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        AppLog.debug(TAG, "onCreate()");
        Book book = new Book();
        book.setName("Android开发艺术探索");
        book.setPrice(28);
        mBooks.add(book);
    }

    private final BookManager.Stub mBookManager = new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this) {
                AppLog.debug(TAG, "getBooks(), now the list is : " + mBooks.toString());
                if (mBooks != null) {
                    return mBooks;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if (book == null) {
                    AppLog.debug(TAG, "Book is null in In");
                    book = new Book();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
                book.setPrice(2333);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                AppLog.debug(TAG, "addBooks(), now the list is : " + mBooks.toString());
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBookManager;
    }
}
