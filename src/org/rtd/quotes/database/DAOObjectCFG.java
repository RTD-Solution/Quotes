package org.rtd.quotes.database;

import android.database.Cursor;

public class DAOObjectCFG {
	private int _id;
	private String vers;
	private String num;
	private String date;
	private String link;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getVers() {
		return vers;
	}

	public void setVers(String vers) {
		this.vers = vers;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public DAOObjectCFG retDAOcfg(Cursor c, int num) {

		DAOObjectCFG daoObj = new DAOObjectCFG();
		// ставим позицию курсора на первую строку выборки
		// если в выборке нет строк, вернется false
		c.moveToPosition(num);

		daoObj.set_id(c.getInt(c.getColumnIndex("_id")));
		daoObj.setDate(c.getString(c.getColumnIndex("date")));
		daoObj.setNum(c.getString(c.getColumnIndex("num")));
		daoObj.setVers(c.getString(c.getColumnIndex("vers")));
		daoObj.setLink(c.getString(c.getColumnIndex("link")));

		return daoObj;
	}

}
