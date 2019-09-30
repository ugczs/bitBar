package com.kobil.bitBarTesting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 * Representational State Transfer mit Get-Methode
 * @author Yuguan Zhao
 */
public class RestApi {
	private URL url;
	private String auth;
	private String response;
	
	public RestApi(String url, String auth) throws MalformedURLException {
		this.url = new URL(url);
		this.auth = auth;
		formatAuth();
		getInfoFromServer();
	}
	
	public void formatAuth() {
		this.auth = "Basic " + removeLastChar(Base64.getEncoder().encodeToString(auth.getBytes())) + "6";
	}
	
	public String removeLastChar(String str) {
        str = str.substring(0, str.length() - 1);
        return str;
	}
	
	public void getInfoFromServer() {
        try {
        	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Authorization", this.auth);
	        if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
    	            (conn.getInputStream())));
    		String line = reader.readLine();
    		StringBuilder sb = new StringBuilder();
    		while (line != null) {
    			sb.append(line);
                sb.append("\n");
    			line = reader.readLine();
    		}
    		reader.close();
    		this.response = sb.toString();
    		conn.disconnect();
		}
        catch (MalformedURLException e) {
            e.printStackTrace();

        } 
        catch (IOException e) {
            e.printStackTrace();
        }    
	}

	public URL getUrl() {
		return url;
	}

	public String getAuth() {
		return auth;
	}

	public String getResponse() {
		return response;
	}
	

}
