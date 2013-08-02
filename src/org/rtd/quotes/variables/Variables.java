package org.rtd.quotes.variables;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * ��������� ����������.
 * 
 * @author Doronda R.A. 2013
 */
public class Variables {

	// �������� ����� ��� ����������� ��� ����������
	public static final String VARIABLES_FILE_NAME = "where_to_find_variable";
	
	// �������� ����� ��� ����������� ��� ����������
	public static final String GUI_FILE_NAME = "where_to_find_gui";

	// �������� ���������� �������� �������� ��������������
	public static final String AUTO_REFRESH = "AUTO_REFRESH";

	// ������ ����������
	public static final String VERSION = "1.0.0";

	// �������� �������, ���������� �� �������� � ������� �����
	public enum BarButtonPressed {
		BAR, BACK, REDO, MAP, FILTER, SETTINGS, HELP, ALL, REFRESH, LIST
	}

	// ��������� ������ �������� ����
	public enum UpBarButton {
		NOTHING, MAP, REFRESH
	}

	// ��������� ������ ������ "�����" �������� ����
	public enum UpBarBackButton {
		NOTHING, BACK, LIST
	}

	// ��������� ������ �������� ����
	public enum UpBarActiveButton {
		MAP, LIST, NOTHING
	}

	// ��������� ������ ������� ����
	public enum LowBarActiveButton {
		MAP, FILTER, SETTINGS, HELP, NOTHING, ALL
	}

	// ��������� ������ ������ ���������� ������
	public enum MapControlPanelButton {
		ZOOMIN, ZOOMOUT, MYPOSITION
	}

	// ��������� ��������
	private Context m_context;
	// private SharedPreferences m_settings;

	// Singleton
	private static Variables m_instance = null;

	// ��������� ����������
	public static SharedPreferences mMyPreferences;
	
	// ��������� ����������
	public static SharedPreferences mMyGui;

	// �����������
	private Variables() {
	}

	/**
	 * ���������� ������.
	 * 
	 * @return instance
	 */
	public static Variables getInstance() {
		if (m_instance == null)
			m_instance = new Variables();
		return m_instance;
	}

	/**
	 * ������������� ��������� ����������
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
	 * ����� ���������� ���������� � ���������
	 * 
	 * @param _name
	 *            - ��� ����������
	 * @param _variable
	 *            - �������� ����������
	 */
	public void setVariable(String _name, String _variable) {
		mMyPreferences = m_context.getSharedPreferences(VARIABLES_FILE_NAME, 2);
		Editor ed = mMyPreferences.edit();
		ed.putString(_name, _variable);
		ed.commit();
	}

	/**
	 * ����� ���������� ���������� �� ���������
	 * 
	 * @param _name
	 *            - ��� ����������
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
	 * ����� ���������� ���������� �� ���������
	 * 
	 * @param _name
	 *            - ��� ����������
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
	 * ����� ���������� ���������� � ���������
	 * 
	 * @param _name
	 *            - ��� ����������
	 * @param _variable
	 *            - �������� ����������
	 */
	public void setVariableGui(String _name, String _variable) {
		mMyGui = m_context.getSharedPreferences(GUI_FILE_NAME, 2);
		Editor ed = mMyGui.edit();
		ed.putString(_name, _variable);
		ed.commit();
	}
}
