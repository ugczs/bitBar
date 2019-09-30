package com.kobil.bitBarTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Bodypart einer Json-Datei
 * @author Yuguan Zhao
 */
public class Body {
	/*Eine Liste mit Body-Bloecken*/
	private List<String> bodyList = new ArrayList<String>();
	/*Liste von valid Devices*/
	private List<String> validDevices = new ArrayList<String>();
	
	/**
	 * Klassenkonstruktor
	 * @param bodyList eine Liste von den Bodyelementen
	 */
	public Body(List<String> bodyList) {
		this.bodyList = bodyList;
	}
	
	public List<String> getBodyList() {
		return bodyList;
	}
	
	public List<String> getValidDeviceList() {
		return validDevices;
	}
	
	public void setValidDevices() {
		for(String s: this.bodyList) {
			if(s.contains("SUCCEEDED")) { 
				String s2 = returnTag(s, "displayName") + " " + returnTag(s, "releaseVersion");
				validDevices.add(s2);
				Collections.sort(validDevices);
			}		
		}
		validDevices.add(0, "Device, Version");
	}
	
	public void setValidDevices2() {
		System.out.println("df");
		for(String s: this.bodyList) {
			System.out.println(s);
		}
		
		for(String s: this.bodyList) {
			if(s.contains("SUCCEEDED")) { 
				String s2 = returnTag2(s, "displayName") + ", " + returnTag2(s, "releaseVersion");
				validDevices.add(s2);
				Collections.sort(validDevices);
			}		
		}
		validDevices.add(0, "Device, Version");
	}
	
	/**
	 * Diese Methode liefert Strings an der i. Stelle von der Liste
	 * @param i übergebene Stelle von der Liste
	 * @return ein Nachrichtenblock mit dem Index i
	 */
	public String getContent(int i) {
		if (i < bodyList.size()) {
			return bodyList.get(i).trim() + '\n';
		}
		else {
			System.out.println("Invalid Number!!");
			return null;
		}
	}
	
	/**
	 * Diese Methode liefert die Anzahl der Teile von Body
	 * @return Anzahl von Bloecken
	 */
	public int getBodySize() {
		return bodyList.size();	
	}
	
	/**
	 * Diese Methode gibt den gesamten Body aus.
	 * @return gibt den Body in String aus
	 */
	public String printBody() {
		String s = "";
		for(String i : bodyList) {
			s = s + i;
			}
		return s;
	}
	
	/**
	 * Diese Methode liefert den Wert eines Tags zurück
	 * @param s übergebene String
	 * @param tag übergebener Tag
	 * @return Wert von Tag
	 */
	public String returnTag(String s, String tag) {
		s.replaceAll(" ", "");
		String[] temp = s.split("\n");
		for(int i = 0; i < temp.length; i++) {
			if(temp[i].contains("\"" + tag +"\":")) {
				String s2 = temp[i].replaceAll("\"", "");
				String s3 = s2.replaceAll(tag+": ", "").trim();
				return s3;
			}
		}
		return null;
	}
	
	/**
	 * Diese Methode liefert den Wert eines Tags zurück
	 * @param s übergebene String
	 * @param tag übergebener Tag
	 * @return Wert von Tag
	 */
	public String returnTag2(String s, String tag) {
		s.replaceAll(" ", "");
		String[] temp = s.split(",");
		for(int i = 0; i < temp.length; i++) {
			if(temp[i].contains("\"" + tag +"\":")) {
				String s2 = temp[i].replaceAll("\"", "");
				String s3 = s2.replaceAll(tag+":", "");
				String s4 = s3.replaceAll("}", "");
				return s4;
			}
		}
		return null;
	}
	
	public String getAllValidDevices() {
		StringBuilder sb = new StringBuilder();
		for(String s: validDevices) {
			sb.append(s);
            sb.append("\n");
		}
		return sb.toString();
	}
}
