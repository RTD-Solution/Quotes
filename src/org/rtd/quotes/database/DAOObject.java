package org.rtd.quotes.database;

import android.database.Cursor;

public class DAOObject {
	private int _id;
	private String genre;
	private String name;
	private int id;
	private String body;
	private int favorite;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public DAOObject retDAO(Cursor c, int num) {

		DAOObject daoObj = new DAOObject();
		// ставим позицию курсора на первую строку выборки
		// если в выборке нет строк, вернется false
		c.moveToPosition(num);

		daoObj.set_id(c.getInt(c.getColumnIndex("_id")));
		daoObj.setGenre(c.getString(c.getColumnIndex("genre")));
		daoObj.setName(c.getString(c.getColumnIndex("name")));
		daoObj.setId(c.getInt(c.getColumnIndex("id")));
		daoObj.setBody(c.getString(c.getColumnIndex("body")));
		daoObj.setFavorite(c.getInt(c.getColumnIndex("favorite")));

		return daoObj;
	}

}
