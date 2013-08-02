package org.rtd.quotes.variables;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Хранилище переменных.
 * 
 * @author Doronda R.A. 2013
 */
public class Variables {

	// Название файла где сохраняются все переменные
	public static final String VARIABLES_FILE_NAME = "where_to_find_variable";
	
	// Название файла где сохраняются все переменные
	public static final String GUI_FILE_NAME = "where_to_find_gui";

	// Название переменной хранящей значение автообновления
	public static final String AUTO_REFRESH = "AUTO_REFRESH";

	// Версия приложения
	public static final String VERSION = "1.0.0";

	// Варианты событий, полученных от верхнего и нижнего баров
	public enum BarButtonPressed {
		BAR, BACK, REDO, MAP, FILTER, SETTINGS, HELP, ALL, REFRESH, LIST
	}

	// Параметры работы верхнего бара
	public enum UpBarButton {
		NOTHING, MAP, REFRESH
	}

	// Параметры работы кнопки "Назад" верхнего бара
	public enum UpBarBackButton {
		NOTHING, BACK, LIST
	}

	// Параметры работы верхнего бара
	public enum UpBarActiveButton {
		MAP, LIST, NOTHING
	}

	// Параметры работы нижнего бара
	public enum LowBarActiveButton {
		MAP, FILTER, SETTINGS, HELP, NOTHING, ALL
	}

	// Параметры работы панели управления картой
	public enum MapControlPanelButton {
		ZOOMIN, ZOOMOUT, MYPOSITION
	}

	// Хранилище настроек
	private Context m_context;
	// private SharedPreferences m_settings;

	// Singleton
	private static Variables m_instance = null;

	// Хранилище переменных
	public static SharedPreferences mMyPreferences;
	
	// Хранилище переменных
	public static SharedPreferences mMyGui;

	// Конструктор
	private Variables() {
	}

	/**
	 * Возвращает объект.
	 * 
	 * @return instance
	 */
	public static Variables getInstance() {
		if (m_instance == null)
			m_instance = new Variables();
		return m_instance;
	}

	/**
	 * Инициализация хранилища переменных
	 * 
	 */
	public void Initialize(Context ctxt) {
		m_context = ctxt;
		//
		mMyPreferences = PreferenceManager
				.getDefaultSharedPreferences(m_context);
		mMyGui = PreferenceManager
				.getDefaultSharedPreferences(m_context);
	}

	/**
	 * Метод записывает переменную в хранилище
	 * 
	 * @param _name
	 *            - имя переменной
	 * @param _variable
	 *            - значение переменной
	 */
	public void setVariable(String _name, String _variable) {
		mMyPreferences = m_context.getSharedPreferences(VARIABLES_FILE_NAME, 2);
		Editor ed = mMyPreferences.edit();
		ed.putString(_name, _variable);
		ed.commit();
	}

	/**
	 * Метод возвращает переменную из хранилища
	 * 
	 * @param _name
	 *            - имя переменной
	 * 
	 * @return String
	 */
	public String getVariable(String _name) {
		// String savedText = "";
		mMyPreferences = m_context.getSharedPreferences(VARIABLES_FILE_NAME, 2);
		String savedText = mMyPreferences.getString(_name, "");
		return savedText;
	}
	
	/**
	 * Метод возвращает переменную из хранилища
	 * 
	 * @param _name
	 *            - имя переменной
	 * 
	 * @return String
	 */
	public String getVariableGui(String _name) {
		// String savedText = "";
		mMyGui = m_context.getSharedPreferences(GUI_FILE_NAME, 2);
		String savedText = mMyGui.getString(_name, "");
		return savedText;
	}
	
	/**
	 * Метод записывает переменную в хранилище
	 * 
	 * @param _name
	 *            - имя переменной
	 * @param _variable
	 *            - значение переменной
	 */
	public void setVariableGui(String _name, String _variable) {
		mMyGui = m_context.getSharedPreferences(GUI_FILE_NAME, 2);
		Editor ed = mMyGui.edit();
		ed.putString(_name, _variable);
		ed.commit();
	}
}
