package com.deen812.game;

import java.util.ArrayList;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.layer.Layer;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ClickDetector;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.deen812.game.SceneManager.AllScenes;

import android.content.Intent;
import android.util.Log;

public class SelectScene {

	private Scene mScene;
	private Constants mConst;
	private Resource mRes;
	private Game game;
	private Sprite play_spr;
	private float mMinY = 0;
    private float mMaxY = 0;
    private float mCurrentY = 0;
    private int iLevelClicked = -1;
   
    protected static int FONT_SIZE = 35;

    protected static int LEVELS = 15;
    protected static int LEVEL_COLUMNS_PER_SCREEN = 5;
    protected static int LEVEL_ROWS_PER_SCREEN = 3;
    protected static int LEVEL_PADDING = 10;
    
    
    
    //This value will be loaded from whatever method used to store data.
    private int mMaxLevelReached = 10;
	private Sprite back_spr;
	private Entity levelsEntity;
	private Sprite backgrund;
	private int leftPadding;
	private ClickDetector mClickDetector;
	private Sprite btn_prev;
	private Sprite btn_next;
	private ArrayList<Entity> layer;
	private int zz =0;
	private float xx;
	private float yy;
	private ArrayList<AnimatedSprite> arr_list;
	private int LEVEL;

	public SelectScene( final Game game) {
		mRes = game.mRes;
		this.game = game;
		this.mScene = new Scene(3);
        this.mScene.attachChild(new Entity());
        this.mScene.setBackgroundEnabled(true);
        mConst = Constants.getInstance();
        LEVEL  = mConst.LEVEL;
        Sprite backgrund = new Sprite(0, 0, mRes.mBackgroundTextureRegion);
      //  float x = (mConst.CAMERA_WIDTH - backgrund.getWidth())/2;
     //   float y = (mConst.CAMERA_HEIGHT - backgrund.getHeight())/2;
        float width = mConst.CAMERA_WIDTH - 40;
        mConst  =  Constants.getInstance();
       
        mMaxLevelReached=mConst.LEVEL+1;
       // mMaxLevelReached=46;
        
        
        backgrund.setWidth(width);
        backgrund.setHeight(mConst.CAMERA_HEIGHT-40);
        backgrund.setPosition(20, 30);
        this.mScene.setTouchAreaBindingEnabled(true);
        this.mScene.attachChild(backgrund);
        Sprite backgrund2 = new Sprite(170, -35, mRes.head_select);
        backgrund.attachChild(backgrund2);
     //   mScene.setOnAreaTouchListener(game); 

        //BTN_PREV
         btn_prev  = new Sprite(10 , 220, mRes.btn_back) {
   		 @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                if (pSceneTouchEvent.isActionDown()     ) {
               	 this.setScale((float) 1.2);
                }
                if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
               	 this.setScale((float) 1.0);
               	 //game.mRes.mysound.play();
               	 game.vsm.play1();
               	 back();
                }
                
                return true;
            }; 
     };
     mScene.attachChild(btn_prev);
     this.mScene.registerTouchArea(btn_prev);

     
     //BTN_NEXT
     btn_next  = new Sprite(width-30 , 220, mRes.btn_next) {
   		 @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                if (pSceneTouchEvent.isActionDown()     ) {
               	 this.setScale((float) 1.2);
                }
                if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
               	 this.setScale((float) 1.0);
               	 next();
               	  game.vsm.play1();
               	 //game.loadGameScene();
               	// game.sceneManager.setCurrentScene(AllScenes.SELECT_LEVEL);
                }
                
                return true;
            }; 
     };
     mScene.attachChild(btn_next);
     this.mScene.registerTouchArea(btn_next);
     
     
     layer = new ArrayList<Entity>(3);
     layer.add(new Layer());
     layer.add(new Layer());
    // new Layer();
     layer.add(new Layer());
        onLoadComplete();
        
        
      
	}

	
	// нопки назад и вперед
	protected void next() {
		
		 layer.get(zz).setPosition(-10000, -1000);
		 mScene.detachChild(layer.get(zz));
		 //mScene.registerTouchArea(layer.get(zz));
		 zz++;
		 if(zz ==3)zz=0;
		 mScene.attachChild(layer.get(zz));
		 layer.get(zz).setPosition(xx, yy);
		
	}
	protected void back() {
		 layer.get(zz).setPosition(-10000, -1000);
		 mScene.detachChild(layer.get(zz));
		 zz--;
		 if(zz < 0)zz=2;
		 mScene.attachChild(layer.get(zz));
		 layer.get(zz).setPosition(xx, yy);
		
	}
	

	public void onLoadComplete() {
		//	Animation animRotateIn_icon = AnimationUtils.loadAnimation(this,
			//		R.anim.rotate);
		  //   this.getCurrentFocus().startAnimation(animRotateIn_icon);
			 float xCenter = mConst.CAMERA_WIDTH/2  - mRes.txt_start.getWidth()/2;
		      Sprite btn_start  = new Sprite(mConst.CAMERA_WIDTH - 80 , 30, mRes.btn_exit) {
		    		 @Override
		             public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		                 if (pSceneTouchEvent.isActionDown()     ) {
		                	 this.setScale((float) 1.2);
		                 }
		                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		                	 this.setScale((float) 1.0);
		                	 game.sceneManager.setCurrentScene(AllScenes.MENU);
		                	 //game.mRes.mysound.play();
	 
		                 }
		                 
		                 return true;
		             }; 
		      };
		      mScene.attachChild(btn_start);
		      this.mScene.registerTouchArea(btn_start);		   
		      CreateLevelBoxes();
	  			
		}

	

	
	
    private void CreateLevelBoxes() {

    	arr_list = new ArrayList<AnimatedSprite>();
        // calculate the amount of required columns for the level count
        int totalRows = (LEVELS / LEVEL_COLUMNS_PER_SCREEN);

        // Calculate space between each level square
        int spaceBetweenRows = (int) ((mConst.CAMERA_HEIGHT / LEVEL_ROWS_PER_SCREEN) - LEVEL_PADDING);
        int spaceBetweenColumns = (int) (( (mConst.CAMERA_WIDTH )/ LEVEL_COLUMNS_PER_SCREEN) - LEVEL_PADDING);
        spaceBetweenRows -= 40;
        spaceBetweenColumns -=50;
        //Current Level Counter
         int iLevel = 1;
         leftPadding = 100;
        // boxX = 20;
        //Create the Level selectors, one row at a time.
       
        
 for(int z = 0; z < 3; z++){    
	 int boxX = leftPadding, boxY = LEVEL_PADDING+80;
        for (int y = 0; y < totalRows; y++) {
                for (int x = 0; x < LEVEL_COLUMNS_PER_SCREEN; x++) {
                        final int levelToLoad = iLevel;
                         AnimatedSprite  Bspr = new AnimatedSprite(boxX, boxY, mRes.menu_level.clone()){
                                @Override
                                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {                                	
                                	game.map = levelToLoad;
                            		game.mConst.SELECTED_MAP = levelToLoad;
                                	if(pSceneTouchEvent.isActionDown()){
                                    	//  this.setScale(1.0f);
                                		
                                  	    game.loadGameScene();
                                    Log.v("SELELECTSCREEN","isActionDown levelToLoad="+levelToLoad);	 
                                      }
                                      if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()){
                                    	 // this.setScale(1.0f);
                                    	  Log.v("SELELECTSCREEN","isActionUp levelToLoad="+levelToLoad);
                                    	  
                                      }
                                   //   if (levelToLoad >= mMaxLevelReached)
                                  //        iLevelClicked = -1;
                                  //    else { game.map = levelToLoad; iLevelClicked = levelToLoad;                                        
                                  ///   }
                                        return false;
                                }
                            };
                            
                            
                        //this.mScene.attachChild(Bspr);
                            
                            layer.get(z).attachChild(Bspr);

                        if (iLevel >= mMaxLevelReached){ 
                        	       arr_list.add(Bspr);   
                        	       Bspr.setCurrentTileIndex(0);
                        	       
                        }
                        else { 
                        	 //  Log.v("SELELECTSCREEN","registerTouchArea");
                        	   Bspr.setCurrentTileIndex(1);
                               this.mScene.registerTouchArea(Bspr);
                               }
                        
                    float x2=0;
                    if(iLevel < 10)x2=8;
                    
                    layer.get(z).attachChild(new Text(boxX + 31+x2, boxY + 30, mRes.mFont, String.valueOf(iLevel)));
                        iLevel++;
                        boxX += spaceBetweenColumns + LEVEL_PADDING;
                        //if (iLevel > LEVELS)break;
                }
              //  if (iLevel > LEVELS) break;
                boxY += spaceBetweenRows + LEVEL_PADDING;
                boxX = leftPadding;
        }
        mMaxY = boxY - mConst.CAMERA_HEIGHT + 200;
}
 
 
 mScene.attachChild(layer.get(0));
 xx = layer.get(zz).getX();
 yy = layer.get(zz).getY();
 zz=0;
 layer.get(1).setPosition(-10000, -1000);
 layer.get(2).setPosition(-10000, -1000);
 
    }//
	

    
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.mScene;
	}


	public void checkLevels() {
		  Log.v("SELELECTSCREEN","checkLevels mConst.LEVEL="+mConst.LEVEL +" mMaxLevelReached="+mMaxLevelReached);
		if(mConst.LEVEL >=mMaxLevelReached) {
			Log.v("SG","checkLevels");
			int m = mConst.LEVEL - mMaxLevelReached;
			int n = arr_list.size();
			for(int i =0; i < n; i++) {
				if(arr_list.get(i)==null) continue;
				arr_list.get(i).setCurrentTileIndex(1);
			
				this.mScene.registerTouchArea(arr_list.get(i));
				//arr_list.remove(i);
				if(m==i) break;
			}
			
		}
			
		
	}
	

}
