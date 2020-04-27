package com.deen812.game;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Vibrator;


public class VSM {
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_COUNTER = "vibro";
	private Game game;
	public boolean f_vibro = true;
	public boolean f_sound = true;
	private static VSM instance;
	
	
	
	public static VSM getInstance(final Game game2) {
		if(instance == null) {
		      instance = new VSM( game2);
		    }
		    return instance;
		  }
	
	
	
	
	
	
	private VSM(final Game game) {
	this.game = game;
	

	
	}
	
	
	

	

	public void sound(int type){
		if(f_sound){
		
		}else{
			
		}
	}
	public void play1(){
		if(f_sound) game.mRes.mysound.play();
	}

	public void setSound(boolean da){
		int nnn=0;
		if(da){
			f_sound = true;
			nnn=1;
			
		}else {
			f_sound = false;
			nnn=0;
		}
		Editor editor = game.mSettings.edit();
		
		editor.putInt("SOUND", nnn);
		editor.commit();
		//vibro();
		game.mRes.mysound.play();
	}
	//-------------------------------------


	public void setVibro(boolean da){
		int nnn=0;
		if(da){
			f_vibro = true;
			nnn=1;
			
		}else {
			f_vibro = false;
			nnn=0;
		}
		Editor editor = game.mSettings.edit();
		
		editor.putInt("VIBRO", nnn);
		editor.commit();
		//vibro();
	}
	public void vibro(long sec){
		if(f_vibro){
			game._vibrato.vibrate(sec);
		}else{
			
		}
	}
	
}//
