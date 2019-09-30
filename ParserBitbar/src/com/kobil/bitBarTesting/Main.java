package com.kobil.bitBarTesting;
import java.net.MalformedURLException;


public class Main {
	public static void main(String[] args) throws MalformedURLException  {
		String url = "https://cloud.bitbar.com/api/v2/me/projects/140858123/runs/141167097/device-sessions?limit=200";
		String auth = "AnDVcMDiWB4BqGRi3r7j7LUYBYWucywv";
		String storage = "/Users/yuguan.zhao/Downloads/validDevices.csv";
		RestApi ra = new RestApi(url, auth);
		JsParser jp = new JsParser(ra.getResponse());
        jp.getBody().setValidDevices2();
        OutPut o = new OutPut(storage);
		o.setText(jp.getBody().getAllValidDevices());
		print("Done");
    } 
	
	public static void print(Object o) {
		  System.out.println(o);
	  }
}
