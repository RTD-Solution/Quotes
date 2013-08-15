package org.rtd.quotes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBQuotes extends SQLiteOpenHelper {

	static final String DATABASE_NAME = "quotes.db";
	static final int DATABASE_VERSION = 1;

	private static DBQuotes dbInstance;

	public static DBQuotes getInstance(Context context) {
		if (dbInstance == null) {
			dbInstance = new DBQuotes(context.getApplicationContext());
		}
		return dbInstance;
	}

	public DBQuotes(Context context) {
		// конструктор суперкласса
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase _db) {
		// создаем таблицу с полями
		_db.execSQL(DataBaseAdapter.DATABASE_CFG_CREATE);
		_db.execSQL(DataBaseAdapter.DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {

		_db.execSQL("DROP TABLE IF EXISTS " + "quote");

		onCreate(_db);
	}
}
