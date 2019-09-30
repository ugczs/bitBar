package com.kobil.bitBarTesting;

/**
 * Diese Klasse dient zur Speicherung von eingegebenen Daten
 * @author Yuguan Zhao
 */
public class SavedValue {
	private String sProjectId;
	private String sTestId;
	private String sApiKey;
	private String sStorage;
	
	public SavedValue(String sProjectId, String sTestId, String sApiKey, String sStorage) {
		this.sProjectId = sProjectId;
		this.sTestId = sTestId;
		this.sApiKey = sApiKey;
		this.sStorage = sStorage;
	}

	public String getsProjectId() {
		return sProjectId;
	}

	public void setsProjectId(String sProjectId) {
		this.sProjectId = sProjectId;
	}

	public String getsTestId() {
		return sTestId;
	}

	public void setsTestId(String sTestId) {
		this.sTestId = sTestId;
	}

	public String getsApiKey() {
		return sApiKey;
	}

	public void setsApiKey(String sApiKey) {
		this.sApiKey = sApiKey;
	}

	public String getsStorage() {
		return sStorage;
	}

	public void setsStorage(String sStorage) {
		this.sStorage = sStorage;
	}
	
	
	
}
