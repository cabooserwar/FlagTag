package com.lab318.flagtag;

import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.alexngai.framework.implementation.HttpClient;
import com.alexngai.herring.gameobjects.GameCharacter;

import android.util.Log;

public class RealTimeControl {

	private int enemyPosX;
	private int enemyPosY;
	private int enemyVelX;
	private int enemyVelY;
	
	private static final String TAG = "RealTimeControl";
	private static final String URL = "http://1-dot-halogen-oxide-529.appspot.com/nativeApp";
	
	public RealTimeControl (){
		enemyPosX = 0;
		enemyPosY = 0;
		enemyVelX = 0;
		enemyVelY = 0;
	}
	
	public void update (GameCharacter gchar) {
		
		final int posX = gchar.getPosX();
		final int posY = gchar.getPosY();
		new Thread(new Runnable() {
			public void run() {
				
				try{
					
					JSONObject jsonObjSend = new JSONObject();
					
					try {
						// Add key/value pairs
						jsonObjSend.put("Xpos", posX);
						jsonObjSend.put("Ypos", posY);

						// Add a nested JSONObject (e.g. for header information)
						JSONObject header = new JSONObject();
						header.put("deviceType","Android"); // Device type
						header.put("deviceVersion","2.0"); // Device OS version
						header.put("language", "es-es");	// Language of the Android client
						jsonObjSend.put("header", header);
						
						// Output the JSON object we're sending to Logcat:
						Log.i(TAG, jsonObjSend.toString(2));

					} catch (JSONException e) {
						e.printStackTrace();
					}
					
                    
					JSONObject jsonObjRecv = HttpClient.SendHttpPost(URL, jsonObjSend);
					
				}catch(Exception e)
				{
					Log.d("Exception",e.toString());
				}
			}
		}).start();
	}
	
	
	
	
}
