package com.kobil.bitBarTesting;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Diese Utility Klasse liest eine Datei ueber einen URL ein
 * @author Yuguan Zhao
 */
public class JsReader {
	/*Adresse von der Datei*/
	private String url;
	/*Inhalt von Datei*/
	private String content;
	
	/**
	 * Klassenkonstruktor
	 * liest Text ueber einen URL ein und speichert
	 * diesen im Parameter content
	 * @param url der Uebergebener URL
	 */
	public JsReader(String url)throws Exception{
		FileReader file = new FileReader(url);
		BufferedReader reader = new BufferedReader(file);
		String line = reader.readLine();
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			sb.append(line);
            sb.append("\n");
			line = reader.readLine();
		}
		reader.close();
		this.content = sb.toString();
	}
	
	/**
	 * gibt den URL in String zurueck
	 * @return String-Wert von url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * setzt den URL 
	 * @param url String wert von URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}