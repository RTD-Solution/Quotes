package org.rtd.quotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseAdapter {

	static final String DATABASE_CREATE = "create table  quote (_id integer primary key autoincrement,"
			+ " genre text,"
			+ " name text,"
			+ " id integer,"
			+ " body text,"
			+ " favorite integer);";
	// Variable to hold the database instance
	public SQLiteDatabase db;
	// Context of the application using the database.
	private final Context context;
	// Database open/upgrade helper
	DBQuotes dbHelper;

	public DataBaseAdapter(Context _context) {
		context = _context;
		dbHelper = DBQuotes.getInstance(context);
	}

	public DataBaseAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}

	public void insertEntry(DAOObject daoObj) {
		ContentValues cv = new ContentValues();
		// Assign values for each row.
		cv.put("genre", daoObj.getGenre());
		cv.put("name", daoObj.getName());
		cv.put("id", daoObj.getId());
		cv.put("body", daoObj.getBody());
		cv.put("favorite", daoObj.getFavorite());
		// Insert the row into your table
		db.insert("quote", null, cv);
		// Toast.makeText(context, "Reminder Is Successfully Saved",
		// Toast.LENGTH_LONG).show();
	}

	public int delete() {
		db.delete("quote", null, null);
		return 0;
	}

	public Cursor getCursor() {
		Cursor cursor = db.query("quote", null, null, null, null, null, null);

		return cursor;
	}

	public void updateEntry(String userName, String password) {
		/*
		 * // Define the updated row content. ContentValues updatedValues = new
		 * ContentValues(); // Assign values for each row.
		 * updatedValues.put("USERNAME", userName);
		 * updatedValues.put("PASSWORD",password);
		 * 
		 * String where="USERNAME = ?"; db.update("LOGIN",updatedValues, where,
		 * new String[]{userName});
		 */
	}
}
