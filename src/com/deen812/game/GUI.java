package com.deen812.game;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.input.touch.TouchEvent;

import com.deen812.game.SceneManager.AllScenes;

import android.content.Intent;

public class GUI {

    float Xcenter = 0;
	private AnimatedSprite text_spr;
	private Game game;
	private float Ycenter;
	private Sprite btn_pause;
	final HUD hud;
	private Sprite paused_content;
	private Sprite btn_replay;
	private ChangeableText ScoreText;
	private Sprite btn_exit;
	private static GUI instance;
	
	
	
	public static GUI getInstance(final Game game2) {
		if(instance == null) {
		      instance = new GUI( game2);
		    }
		    return instance;
		  }
	
	
	
	
	
	
	private GUI(final Game game) {
	this.game = game;
	 hud = new HUD();
	
		text_spr = new AnimatedSprite(10 ,10, game.mRes.text);
		hud.attachChild(text_spr);
		//text_spr.setVisible(false);
		 btn_pause = new Sprite(game.mConst.CAMERA_WIDTH - 80 ,10, game.mRes.btn_pause){
			 @Override
             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {
				if(game.mConst.BALL_PRESSED) return true;
				 
                 if (pSceneTouchEvent.isActionDown()     ) {
                 	 this.setScale(1.3f);
                 	//paused_spr.setVisible(true);
                 	// hud.registerTouchArea(paused_spr);
                 	//game.mScene.setIgnoreUpdate(true); 
        
                 }
                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
                	 this.setScale(1.0f);
                	 show_paused_content();
                 }
                 
                 return true;
             };
		};
		
		
	
		hud.attachChild(btn_pause);
		hud.registerTouchArea(btn_pause);
		
		game.mSmoothCamera.setHUD(hud);
		Ycenter = game.mConst.CAMERA_HEIGHT/2;
		Xcenter = game.mConst.CAMERA_WIDTH/2;
		init_paused_content();
		 intiScore();
		
		
	}
	
	
	
	public void hide() {
		hud.setPosition( -1000, -1000);
		hud.setIgnoreUpdate(true);
		
		
	}
	
	public void show() {
		hud.setPosition( 0, 0);
		hud.setIgnoreUpdate(false);
	}
	
	
	private void show_paused_content(){
		 hud.attachChild(paused_content);
         paused_content.setPosition(Xcenter - 200 ,Ycenter-100);
    	 hud.registerTouchArea(btn_replay);
    	 hud.registerTouchArea(btn_exit);
	}
	protected void hide_paused_content() {
		hud.detachChild(paused_content);
		 paused_content.setPosition( - 500 ,-500);
		hud.unregisterTouchArea(paused_content);
    	
		
	}

	
	//-----------------------------------------------------------------------------
	
	private void init_paused_content() {
		 paused_content = new Sprite( - 1000 ,-1000, game.mRes.paused_content);
		 paused_content.setIgnoreUpdate(true);
		 
		  btn_replay = new Sprite(50 ,70, game.mRes.btn_reload){
			 @Override
             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                 if (pSceneTouchEvent.isActionDown()     ) {
                	
                	 
                 }
                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
                	 hide_paused_content();
                	 game.restartLevel();
                 }
                 
                 return true;
             };
		};
		btn_replay.setScale(1.5f);
		paused_content.attachChild(btn_replay);
		
		Sprite btn_menu = new Sprite(300 ,70, game.mRes.btn_menu){
			 @Override
             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {
				
                 if (pSceneTouchEvent.isActionDown()     ) {
                 	 this.setScale(2.0f);
                 
                 }
                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
                	 this.setScale(1.5f);
                	 hide_paused_content();
                	game.closeScene();
                	 
                 }
                 
                 return true;
             };
		};
		btn_menu.setScale(1.5f);
		paused_content.attachChild(btn_menu);
	
		hud.registerTouchArea(btn_menu);
		
		 btn_exit = new Sprite(350 ,0, game.mRes.btn_exit){
			 @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {
				
                if (pSceneTouchEvent.isActionDown()     ) { }
                if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) { hide_paused_content(); }        
                return true;
            };
		};
		//btn_menu.setScale(1.5f);
		paused_content.attachChild(btn_exit);
		
		
		
		
		
		
	}



	




	public void show_text(int text_type) {
		
		if(text_type == game.mConst.TEXT_YOU_TURN) {
			text_spr.setCurrentTileIndex(3);
		
			
		}
		if(text_type == game.mConst.TEXT_EXCELLENT) {
			text_spr.setCurrentTileIndex(4);
		
		}
		if(text_type == game.mConst.TEXT_OOPS) {
			text_spr.setCurrentTileIndex(2);
		
		}
		if(text_type == game.mConst.TEXT_U_LOST) {
			text_spr.setCurrentTileIndex(1);
		
		}
		if(text_type == game.mConst.TEXT_YOU_WIN) {
			text_spr.setCurrentTileIndex(0);
		
		}
		
		show_text();
		
	}

	
	public void show_text() {
		text_spr.setPosition(-50, Ycenter);
		text_spr.setVisible(true);
		game. mScene.registerUpdateHandler(new IUpdateHandler() {  
			 @Override  
             public void onUpdate(float arg0) {
				 text_spr.setPosition(text_spr.getX() +20, Ycenter);
				 if(text_spr.getX()+100  > Xcenter) {
					 hide_text();
					 
				 game. mScene.unregisterUpdateHandler(this);
				 }
			 }  

           @Override  
            public void reset() {   }  
         });  
	}


	public void show_text_zoom(int type) {
		text_spr.setPosition(Xcenter, Ycenter);
		text_spr.setVisible(true);
		 text_spr.setScale(0.1f);
		text_spr.setCurrentTileIndex(type);
		game. mScene.registerUpdateHandler(new IUpdateHandler() {  
			float  scale = 1;
			 @Override  
             public void onUpdate(float arg0) {
				 scale+=0.1;
				 text_spr.setPosition(text_spr.getX() -5, Ycenter);
				 text_spr.setScale(scale);
				 if(scale > 2.5) {
					 hide_text();
					 
				 game. mScene.unregisterUpdateHandler(this);
				 }
			 }  

           @Override  
            public void reset() {   }  
         });  
		
	}

	protected void hide_text() {
		 game.mScene.registerUpdateHandler(new TimerHandler(2f,true, new ITimerCallback(){

				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
				
					text_spr.setVisible(false);
					 game.mScene.unregisterUpdateHandler(pTimerHandler);
					
				}
				   
			   }));
		
	}
	
	
	
	public void intiScore(){
		ScoreText = new ChangeableText(15 , 15, game.mRes.mFont,"0", 15);
		hud.attachChild(ScoreText);

	}
	public void SetScore(int score){
		game.mConst.PLAYER_SCORE+=score;
		ScoreText.setText(""+game.mConst.PLAYER_SCORE);
	}





	public void startBall() {
		hud.unregisterTouchArea(btn_pause);
		
	}





	public void stopFireBall() {
		hud.registerTouchArea(btn_pause);
		
	}









	
	
}//
