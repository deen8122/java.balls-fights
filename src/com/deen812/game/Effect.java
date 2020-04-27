package com.deen812.game;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;

public class Effect {

	
	
	private ChangeableText scoreText;
	private IUpdateHandler updateH;
	private int life  = 240;
	private float pAlpha =1;
	public Effect(float x , float y , String text , final Scene mScene ,Resource res){
		
		 scoreText = new ChangeableText(x, y, res.mFont, text, text.length());
		 mScene.attachChild(scoreText);
		 
		 updateH = new IUpdateHandler() {  
			 

			@Override  
             public void onUpdate(float arg0) { 
				 life--;
				 scoreText.setPosition(scoreText.getX(), scoreText.getY() - 2);
				 pAlpha-=0.01f;
				 scoreText.setAlpha(pAlpha);
				 if(life   < 1){
					 mScene.unregisterUpdateHandler(this);
					 mScene.detachChild(scoreText);
					 //scoreText.detachSelf();
					
				 }
				 
			 }  

           @Override  
            public void reset() {   }  
         };
		 
		 
		 mScene.registerUpdateHandler(updateH);  
		 
		 
		 
	}
	
	public void Update(){
		
		
	}
}
