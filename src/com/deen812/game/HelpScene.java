package com.deen812.game;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.deen812.game.SceneManager.AllScenes;

import android.content.Intent;

public class HelpScene {

	private Scene mScene;
	private Constants mConst;
	private Resource mRes;
	private Game game;

	public HelpScene(Game game) {
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
             	 game.sceneManager.setCurrentScene(AllScenes.MENU);


                }
                
                return true;
            }; 
     };
     mScene.attachChild(btn_exit2);
     this.mScene.registerTouchArea(btn_exit2);
  
			 float xCenter = mConst.CAMERA_WIDTH/2  - mRes.how.getWidth()/2;
			 float yCenter = mConst.CAMERA_HEIGHT/2  - mRes.how.getHeight()/2+40;
		      Sprite btn_start  = new Sprite(xCenter , yCenter, mRes.how);
		      mScene.attachChild(btn_start);
		      btn_start.setScale(1.8f);
		  //    this.mScene.registerTouchArea(btn_start);
		
		   
		      
			
		}

	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.mScene;
	}
	

}
