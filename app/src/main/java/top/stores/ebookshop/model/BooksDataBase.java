package top.stores.ebookshop.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Category.class,Book.class}, version = 1)
public abstract class BooksDataBase extends RoomDatabase {

    public static BooksDataBase instance;

    public abstract CategoryDAO categoryDAO();
    public abstract BookDAO bookDAO();

    public static synchronized BooksDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BooksDataBase.class, "books_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();

        }

        return instance;
    }


    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
        }

    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void>{
          private CategoryDAO categoryDAO;
          private BookDAO bookDAO;

          public  InitialDataAsyncTask(BooksDataBase booksDataBase){
              categoryDAO = booksDataBase.categoryDAO();
              bookDAO = booksDataBase.bookDAO();
          }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}
