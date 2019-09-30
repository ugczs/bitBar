package com.kobil.bitBarTesting;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Diese Utility Klasse Parst einem die Strings, die über JsParser eingelesen werden
 * @author Yuguan
 */
public class JsParser {
	/*Instanz von JsonReader*/
	private JsReader jr;
	/*Instanz von Json-Body*/
	private Body body;
	
	/**
	 * Klassenkonstruktor
	 * @param mr uebergebener MailReader
	 */
	public JsParser(JsReader jr) {
		this.jr = jr;
		splitBody(jr);
	}
	
	public JsParser(String s) {
		splitBody(s);
	}
	
	public JsParser() {
	}
	
	public JsReader getJr() {
		return jr;
	}

	public void setJr(JsReader jr) {
		this.jr = jr;
	}
	
	/**
	 * Diese Methode teilt die Strings, die über JsReader eingelesen worden sind
	 * @param jr eingelesene Strings
	 */
	public void splitBody(JsReader jr) {
		try {
			String s = jr.getContent();
			System.out.println("hallo");
			System.out.println(s);
			ArrayList<String> bodyParts = new ArrayList<>(Arrays.asList(s.split("\"createTime\":")));
			this.body = new Body(bodyParts);
		} 
		catch (Exception e) {
			UtilMethods.print("Not possible to split!");
		}
	}
	
	public void splitBody(String s) {
		try {
			ArrayList<String> bodyParts = new ArrayList<>(Arrays.asList(s.split("\"createTime\":")));
			this.body = new Body(bodyParts);
		} 
		catch (Exception e) {
			UtilMethods.print("Not possible to split!");
		}
	}
	
	public void splitSemicolon(String s) {
		try {
			ArrayList<String> bodyParts = new ArrayList<>(Arrays.asList(s.split(";")));
			this.body = new Body(bodyParts);
		} 
		catch (Exception e) {
			UtilMethods.print("Not possible to split!");
		}
	}

	public Body getBody() {
		return body;
	}
}