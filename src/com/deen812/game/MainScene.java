package com.deen812.game;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.deen812.game.SceneManager.AllScenes;

import android.content.Intent;

public class MainScene {

	/*
	 * 	 */
	private Scene mScene;
	private Constants mConst;
	private Resource mRes;
	private Game game;

	public MainScene(Game game) {
		mRes = game.mRes;
		this.game = game;
		this.mScene = new Scene(0);
        this.mScene.attachChild(new Entity());
        this.mScene.setBackgroundEnabled(true);
        mConst = Constants.getInstance();
        Sprite backgrund = new Sprite(0, 0, mRes.mBackgroundTextureRegion);
        Sprite backgrund2 = new Sprite(170, -40, mRes.game_name);
        backgrund.attachChild(backgrund2);
        
      //  float x = (mConst.CAMERA_WIDTH - backgrund.getWidth())/2;
     //   float y = (mConst.CAMERA_HEIGHT - backgrund.getHeight())/2;
        backgrund.setWidth(mConst.CAMERA_WIDTH-40);
        backgrund.setHeight(mConst.CAMERA_HEIGHT-40);
        backgrund.setPosition(20, 30);
        this.mScene.setTouchAreaBindingEnabled(true);
        this.mScene.attachChild(backgrund);
        onLoadComplete();
        
        
      
	}
	
	public void onLoadComplete() {
		
		Sprite btn_exit2  = new Sprite(mConst.CAMERA_WIDTH - 80 , 30, mRes.btn_exit) {
   		 @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                if (pSceneTouchEvent.isActionDown()     ) {
               	 this.setScale((float) 1.2);
                }
                if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
               	 this.setScale((float) 1.0);
               //	Process.killProcess(Process.thisMainScene.myPid());
              // 	 game.sceneManager.setCurrentScene(AllScenes.MENU);
               	game.finish();
               	android.os.Process.killProcess(android.os.Process.myPid());

                }
                
                return true;
            }; 
     };
     mScene.attachChild(btn_exit2);
     this.mScene.registerTouchArea(btn_exit2);
     
		//	Animation animRotateIn_icon = AnimationUtils.loadAnimation(this,
			//		R.anim.rotate);
		  //   this.getCurrentFocus().startAnimation(animRotateIn_icon);
			 float xCenter = mConst.CAMERA_WIDTH/2  - mRes.txt_start.getWidth()/2;
		      Sprite btn_start  = new Sprite(xCenter , 120, mRes.txt_start) {
		    		 @Override
		             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		                 if (pSceneTouchEvent.isActionDown()     ) {
		                	 this.setScale((float) 1.2);
		                 }
		                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		                	 this.setScale((float) 1.0);
		                	  game.vsm.play1();
		                	 //game.loadGameScene();
		                	 game.sceneManager.setCurrentScene(AllScenes.SELECT_LEVEL);
		                 }
		                 
		                 return true;
		             }; 
		      };
		      mScene.attachChild(btn_start);
		      this.mScene.registerTouchArea(btn_start);
		
		   
		      
		 	 // ÇÂÓÊ
		      final Sprite btn_no  = new Sprite(0 , 0, mRes.btn_no);
		      Sprite btn_setting  = new Sprite(60 , 320, mRes.btn_sound) {
		    		 @Override
		             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		                 if (pSceneTouchEvent.isActionDown()     ) {
		                	 this.setScale((float) 1.2);
		                	
		                	
		                 }
		                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		                	 this.setScale((float) 1.0);
		                	// game.sceneManager.createSettingScene();
		                	// game.sceneManager.setCurrentScene(AllScenes.SETTING);
		                	 if(game.sound_enable == false) {
		                		 this.detachChild(btn_no);
		                		 game.sound_enable = true;
		                		  game.vsm.setSound(true);
		                	 }else {
		                		  this.attachChild(btn_no);
		                		  game.sound_enable = false;
		                		  game.vsm.setSound(false);
		                	 }
		                	 
		                 }
		                 
		                 return true;
		             }; 
		      };
		      mScene.attachChild(btn_setting);
		      this.mScene.registerTouchArea(btn_setting);
		   //   if(game.vibro_enable == false) btn_vibro.attachChild(btn_no2);
		     if(game.vsm.f_sound == false) {
		    	 
		    	  btn_setting.attachChild(btn_no);
         	 }
		      
	
		     
		     
		     //INFO
		      Sprite btn_info  = new Sprite(160 , 320, mRes.btn_info) {
		    		 @Override
		             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		                 if (pSceneTouchEvent.isActionDown()     ) {
		                	 this.setScale((float) 1.2);
		                	
		                 }
		                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		                	 this.setScale((float) 1.0);
		                	 game.sceneManager.setCurrentScene(AllScenes.HELP);
		                	// game.sceneManager.createSettingScene();
		                	// game.sceneManager.setCurrentScene(AllScenes.SETTING);
		                 }
		                 
		                 return true;
		             }; 
		      };
		      mScene.attachChild(btn_info);
		      this.mScene.registerTouchArea(btn_info);
		      
		      //---------------------------------------------------------------------------
		      
		      // BYE
		     
		      Sprite btn_bye  = new Sprite(260 , 320, mRes.btn_bye) {
		    		 @Override
		             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		                 if (pSceneTouchEvent.isActionDown()     ) {
		                	 this.setScale((float) 1.2);
		                 }
		                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		                	 this.setScale((float) 1.0);
		                	// game.sceneManager.createSettingScene();
		                	// game.sceneManager.setCurrentScene(AllScenes.SETTING);
		                	 
		                	// MainScene.this.game.bye();
		                    //game.bye();
		                	 game.bye();
		                    
		                 }
		                 
		                 return true;
		             }; 
		      };
		      
		      
		      mScene.attachChild(btn_bye);
		      this.mScene.registerTouchArea(btn_bye);
		      //------------------------------------------------------------------------------
		      
		      
		      //VIBRO
		      final Sprite btn_no2  = new Sprite(0 , 0, mRes.btn_no);
		      Sprite btn_vibro  = new Sprite(360 , 320, mRes.vibro) {
		    		 @Override
		             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		                 if (pSceneTouchEvent.isActionDown()     ) {
		                	 this.setScale((float) 1.2);
		                 }
		                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		                	 game.vsm.vibro(100);
		                	 this.setScale((float) 1.0);
		                	 if(game.vibro_enable == false) {
		                		 this.detachChild(btn_no2);
		                		  game.vsm.setVibro(true);
		                		 game.vibro_enable = true;
		                	 }else {
		                		  this.attachChild(btn_no2);
		                		  game.vsm.setVibro(false);
		                		  game.vibro_enable = false;
		                	 }
		                 }
		                 
		                 return true;
		             }; 
		      };
		      mScene.attachChild(btn_vibro);
		      this.mScene.registerTouchArea(btn_vibro);
		      
		      if(game.vibro_enable == false) btn_vibro.attachChild(btn_no2);
		      
		   /*   
		   
		      final Sprite btn_no3  = new Sprite(0 , 0, mRes.btn_no);
		      Sprite btn_music  = new Sprite(460 , 320, mRes.music) {
		    		 @Override
		             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		                 if (pSceneTouchEvent.isActionDown()     ) {
		                	 this.setScale((float) 1.2);
		                 }
		                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		                	 this.setScale((float) 1.0);
		                	 //game.vsm.vibro();
		                	// game._vibrato.vibrate(500);
		                	 if(game.music_enable == false) {
		                		 this.detachChild(btn_no3);
		                		 game.music_enable = true;
		                	 }else {
		                		  this.attachChild(btn_no3);
		                		  game.music_enable = false;
		                	 }
		                	// game.mEngine.enableVibrator(this);
		                 }
		                 
		                 return true;
		             }; 
		      };
		      mScene.attachChild(btn_music);
		      this.mScene.registerTouchArea(btn_music);
		      */
		    //  CreateLevelBoxes();
			    //  this.mScene.attachChild(new Entity());
			      
			     // mScene.setOnSceneTouchListener(this);
		         // this.mScene.registerTouchArea(back_spr);//.setTouchAreaBindingEnabled(true);
			   //   mScene.attachChild(back_spr);
			      
			    //  levelsEntity = new Entity();
			    //  levelsEntity.setPosition(mConst.CAMERA_WIDTH,10);
			    //  mScene.attachChild(levelsEntity);
		   
		   
			
		}

	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.mScene;
	}
	

}
