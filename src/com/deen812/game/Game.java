package com.deen812.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.level.LevelLoader;
import org.anddev.andengine.level.LevelLoader.IEntityLoader;
import org.anddev.andengine.level.util.constants.LevelConstants;
import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;








import com.deen812.game.SceneManager.AllScenes;
import com.deen812.game.util.IabHelper;
import com.deen812.game.util.IabResult;
import com.deen812.game.util.Inventory;
import com.deen812.game.util.Purchase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;



public class Game extends BaseGameActivity implements  IOnSceneTouchListener, IOnAreaTouchListener, IScrollDetectorListener
{
	// ˝ÚÓ ·Û‰ÂÚ ËÏÂÌÂÏ Ù‡ÈÎ‡ Ì‡ÒÚÓÂÍ
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_COUNTER = "map2";
	SharedPreferences mSettings;
	private static final String TAG_ENTITY = "entity";
	protected static final String TAG = "GAME";
	private static final String ITEM_SKU = "premium";
    static final String SKU_PREMIUM = "premium";
	 static final int RC_REQUEST = 10001;
	protected static final String SKU_INFINITE_GAS = "gas";
	protected static final int TANK_MAX = 0;
	protected static final String SKU_GAS = null;
	public Constants mConst;
	public SmoothCamera mSmoothCamera;
	public Resource mRes;
	public PhysicsWorld mPhysicsWorld;
	public Scene mScene;
	public SurfaceScrollDetector mScrollDetector;
	public Object mPinchZoomDetector;
	public Sprite backgrund;
	public GameManager gm;
	public GUI gui=null;
	public int map = 1;

	private int PLAYER_BALL_N = 0;
	private int ENEMY_BALL_N = 0;
	public Imozg imozg;
	public SceneManager sceneManager;
	protected ArrayList<Ball> Ball_arr;
	protected ArrayList<Vector2> Ball_arr_pos;
	protected int PLAYER_BALL_N_ORIGIN;
	protected int ENEMY_BALL_N_ORIGIN;
	public boolean sound_enable = false;
	public boolean f_restart = false;
	protected boolean vibro_enable =false;
	protected boolean music_enable = false;
	public Vibrator _vibrato;
	public VSM vsm;
	private IabHelper mHelper;
	private int mTank;
	private boolean mIsPremium = false;

	@Override
	public Engine onLoadEngine() {
		     
		
		//    Intent myIntent = getIntent(); // gets the previously created intent
		//    this.map = myIntent.getIntExtra("firstKeyName",1); // will return "FirstKeyValue"
		    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
	     	map = mSettings.getInt(APP_PREFERENCES_COUNTER, 1);
		
	    	
	     	int vibro  = mSettings.getInt("VIBRO", 1);  
	     	if(vibro==1)vibro_enable = true;
	     	else vibro_enable=false;
		     
	
		     	
		    mConst  =  Constants.getInstance();
		    mConst.LEVEL = map;
		    Log.v("GAME","MAP = "+map);
		    DisplayMetrics dm = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
            this.mSmoothCamera = new SmoothCamera(0, 0, mConst.CAMERA_WIDTH, mConst.CAMERA_HEIGHT, 1000, 1000, 1.0f);
                 mSmoothCamera.setBounds(0, mConst.WORLD_WIDTH, 0, mConst.WORLD_HEIGHT);
                 mSmoothCamera.setBoundsEnabled(true); 
      //   mSmoothCamera.setCenterDirect(  mConst.GAME_TABLE_X+ mConst.CAMERA_WIDTH/2,mConst.GAME_TABLE_X+ mConst.CAMERA_HEIGHT/2 );
         EngineOptions engineOptions =   new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(mConst.CAMERA_WIDTH, mConst.CAMERA_HEIGHT), this.mSmoothCamera);
         engineOptions.setNeedsSound(true);
         engineOptions.setNeedsMusic(true);
        // engineOptions.
         final Engine mEngine = new Engine(engineOptions);
         this.mEngine = mEngine;
          _vibrato = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE); 
	        return mEngine;
	}

	
	
	
	@Override
	public void onLoadResources() {
		sceneManager = new SceneManager(this, mEngine, mSmoothCamera);
		sceneManager.loadSplashResources();

	//	mRes  = Resource.getInstance();
	//	mRes.onLoadResources(this ,this.mEngine );
		vsm = VSM.getInstance(this);
		vsm.f_vibro = vibro_enable;
     	int sound  = mSettings.getInt("SOUND", 1);  
     	if(sound==1)vsm.f_sound  = true;
     	else vsm.f_sound  = false;
     	
	}

	
	
	@Override
	public Scene onLoadScene() {
		    init_bye();
		    this.mEngine.registerUpdateHandler(new FPSLogger());
	        this.mScene =    sceneManager.createSplashScene();
	        mRes  = Resource.getInstance();
			mRes.onLoadResources(this ,this.mEngine );
			sceneManager.setRes(mRes);
            this.mScrollDetector = new SurfaceScrollDetector(this);
            this.mScrollDetector.setEnabled(true);
            
            mEngine.registerUpdateHandler(new TimerHandler(2f,
    				new ITimerCallback() {

    					@Override
    					public void onTimePassed(TimerHandler pTimerHandler) {
    						Log.v("GAME","onTimePassed ");
    						mEngine.unregisterUpdateHandler(pTimerHandler);
    						sceneManager.createMenuScene();
    						sceneManager.setCurrentScene(AllScenes.MENU);
    					}
    				}));
   	
	        return this.mScene;
	}
	
	
//_______________________________________________________________________________________________

	@Override
    public void onDestroy() {
        super.onDestroy();

        // very important:
        Log.d(TAG, "Destroying helper.");
        if (mHelper != null) {
            mHelper.dispose();
            mHelper = null;
        }
    }

public void init_bye(){
	Log.v(TAG,"init_bye()");
	mHelper = new IabHelper(this, mConst.base64EncodedPublicKey);
	 mHelper.enableDebugLogging(true);
	mHelper.startSetup(new  IabHelper.OnIabSetupFinishedListener() {
	   	        public void onIabSetupFinished(IabResult result) 
	             {
	                   if (!result.isSuccess()) {
	                           Log.d(TAG, "In-app Billing setup failed: " + result);
	           } else {             Log.d(TAG, "In-app Billing is set up OK");
               }
	         }
	});
}





//-----------------------------------------------------------
	public void loadGameScene() {
		Log.v("GAME","loadGameScene");
		
		 PLAYER_BALL_N = 0;
		 ENEMY_BALL_N = 0;
	
			 this.mPhysicsWorld = new PhysicsWorld(new Vector2(0, 0), false);
	         mPhysicsWorld.setContactListener(createContactListener());
			 this.mScene = new Scene(1);	
			 this.mScene.attachChild(new Entity());
		        this.mScene.setBackgroundEnabled(false); 
		        this.mScene.registerUpdateHandler(this.mPhysicsWorld);	        
	            this.mScene.setTouchAreaBindingEnabled(true);
	            
	            
	            mScene.setOnAreaTouchListener(this); 

	            onLoadComplete2();
		
		
            this.mEngine.setScene(this.mScene);
        	Log.v("GAME","loadGameScene mScene.getChildCount()=" + mScene.getChildCount());
            
            mSmoothCamera.setCenterDirect(  mConst.GAME_TABLE_X+ mConst.CAMERA_WIDTH/2,mConst.GAME_TABLE_X+ mConst.CAMERA_HEIGHT/2 );
    		
}
	

	public void closeScene() {
	
	 if(mPhysicsWorld !=null){

	
		 if(gui != null){gui.hide(); }
	// mScene.clearUpdateHandlers();
	// mScene.detachChildren();
	// mScene.clearTouchAreas();
// mEngine.stop();
	// System.gc();

	 mScene.registerUpdateHandler(new IUpdateHandler() {  
		
			@Override  
           public void onUpdate(float arg0) { 
				Log.v("GANE" ,"closeScene() onUpdate ");
				mScene.unregisterUpdateHandler(this);
				
				// mScene.setIgnoreUpdate(true);  
				 clearPhysicsWorld( mPhysicsWorld);
				  mPhysicsWorld.dispose();
				 
				  
				  int N  =  Ball_arr.size();
					Log.v("GAME","N = "+N);
					for( int i =0 ; i < N; i++){
						
						
						mScene.detachChild(Ball_arr.get(i).spr);
						Ball_arr.remove(i);
					//	Ball_arr.get(i).destroy();
					
						
					}
					Ball_arr=null;
					mScene.detachChildren();
					mScene.clearUpdateHandlers();
					mScene.clearEntityModifiers();
					mScene.clearTouchAreas();
				   System.gc();
				  
				/*
				  clearPhysicsWorld( mPhysicsWorld);
				  mPhysicsWorld.dispose();
				 
				  
				  int N  =  Ball_arr.size();
					Log.v("GAME","N = "+N);
					for( int i =0 ; i < N; i++){
						
						
						mScene.detachChild(Ball_arr.get(i).spr);
						Ball_arr.remove(i);
					//	Ball_arr.get(i).destroy();
					
						
					}
					Ball_arr=null;
					mScene.detachChildren();
					mScene.clearUpdateHandlers();
					mScene.clearEntityModifiers();
					mScene.clearTouchAreas();
				  System.gc();
				  */
				//	mScene.setIgnoreUpdate(true);  
		         }  

         @Override  
          public void reset() {   }  
       });  
	 sceneManager.setSelectScene();
//	 sceneManager.setSelectScene();
	// mScene.setScene(AllScenes.SELECT_LEVEL);
	// mEngine.start();
	 
	 } //if
	
	}
	
	
	
	
	
	

	public void winPlayer() {
		gm.stop();
		
		Log.v("GAME","winPlayer() GAME");
		mScene.setIgnoreUpdate(true);
        mEngine.registerUpdateHandler(new TimerHandler(1f,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						Log.v("GAME","winPlayer 1 ");
						mEngine.unregisterUpdateHandler(pTimerHandler);

						mScene.clearUpdateHandlers();
						 clearPhysicsWorld( mPhysicsWorld);
						// mPhysicsWorld.dispose();
						 mScene.clearTouchAreas();
							mScene.setBackgroundEnabled(true);
					 	 mScene.detachChildren();
					 
					 	 System.gc();
					 	Game.this.runOnUpdateThread(new Runnable() {
					          @Override
					          public void run() {									
									 loadGameScene();

					          }});
						
					}
				}));
        
        /*
        mEngine.registerUpdateHandler(new TimerHandler(2f,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						Log.v("GAME","winPlayer 2  ");

						mEngine.unregisterUpdateHandler(pTimerHandler);
						Game.this.runOnUpdateThread(new Runnable() {
					          @Override
					          public void run() {									
									 loadGameScene();

					          }});

					}
				}));
        
        /*
		  mScene.registerUpdateHandler(new IUpdateHandler() { 
		    @Override
		    public void onUpdate(float pSecondsElapsed) {
		    	mScene.unregisterUpdateHandler(this);
		    	
		    	Game.this.runOnUpdateThread(new Runnable() {
		          @Override
		          public void run() {
		        	     //clearPhysicsWorld( mPhysicsWorld);
		            	 mPhysicsWorld.dispose();
						 mScene.clearTouchAreas();
					 	 mScene.detachChildren();
						 System.gc();
						 loadGameScene();
						// mEngine.start();
		           // spinningRect.detachSelf();
		          }});
		     // }
		    }
		    @Override public void reset() {}
		  });
		  */
		  
   //     runOnUpdateThread(new Runnable() {
           // @Override
    //        public void run() {
             // spinningRect.detachSelf();
            	// clearPhysicsWorld( mPhysicsWorld);
            //	 mPhysicsWorld.dispose();
			//	 mScene.clearTouchAreas();
			 ////	 mScene.detachChildren();
			//	 System.gc();
			//	 loadGameScene();
       //     }});
	  
		
		
	}
	
	
	
	
	public void restartLevel() {
		f_restart=true;	
	Log.v("GAME","getBodyCount = "+	mPhysicsWorld.getBodyCount());
	    gm.enemyWin = true;
	    gm.stop();
	
		 mScene.registerUpdateHandler(new IUpdateHandler() {  
				
				@Override  
	           public void onUpdate(float arg0) { 
					mScene.unregisterUpdateHandler(this);
					int N  =  Ball_arr.size();
					Log.v("GAME","N = "+N);
					for( int i =0 ; i < N; i++){
						
						Body b = Ball_arr.get(i).body;
						
						Ball_arr.get(i).unset_detect_stop();
						Ball_arr.get(i).setmoved();
						Ball_arr.get(i).spr.setScale(1);
						Vector2 pos = Ball_arr_pos.get(i);
						Log.v("GAME","POS="+pos.toString());
						b.setAngularVelocity(0);
						b.setLinearVelocity(new Vector2(0,0));
						b.setActive(true);	
						b.setAwake(true);	
						b.setTransform((pos.x+mConst.GAME_TABLE_X)/30, (pos.y+mConst.GAME_TABLE_Y)/30, 0);
						
					}

				 	    PLAYER_BALL_N =PLAYER_BALL_N_ORIGIN;
					    ENEMY_BALL_N = ENEMY_BALL_N_ORIGIN;
					    mConst.PLAYER_BALL_N = PLAYER_BALL_N;
						mConst.ENEMY_BALL_N =  ENEMY_BALL_N;
					    mConst.MOVED_BALL_PLAYER = mConst.PLAYER_BALL_N;
					    mConst.MOVED_BALL_ENEMY = mConst.ENEMY_BALL_N;
					    Log.v("GAME","PLAYER_BALL_N "+PLAYER_BALL_N);
					    gm = new GameManager(Game.this);
					    gm.canMovePlayer();
					    imozg = new Imozg(Game.this);
					 
			         }  

	         @Override  
	          public void reset() {   }  
	       });  

}
	
	public void onLoadComplete2() {
		 new BG(this);
		 create_object();
		 //gui = new GUI(this);
		 gui  = GUI.getInstance(this);
		 gui.show();
		 gm = new GameManager(this);
	     gm.canMovePlayer();
	     imozg = new Imozg(this);
		 mScene.registerTouchArea(backgrund);
		
		
	}
	

	protected void clearPhysicsWorld(PhysicsWorld physicsWorld)
	{
		/*
		Iterator<Joint> allMyJoints = physicsWorld.getJoints();
		while (allMyJoints.hasNext())
		{
			try
			{
				final Joint myCurrentJoint = allMyJoints.next();
				physicsWorld.destroyJoint(myCurrentJoint);
			} 
			catch (Exception localException)
			{
				Debug.d("SPK - THE JOINT DOES NOT WANT TO DIE: " + localException);
			}
		}
		*/
		Iterator<Body> localIterator = physicsWorld.getBodies();
		while (true)
		{
			if (!localIterator.hasNext())
			{
				physicsWorld.clearForces();
				physicsWorld.clearPhysicsConnectors();
				physicsWorld.reset();
				physicsWorld.dispose();
				physicsWorld = null;
				return;
			}
			try
			{
				physicsWorld.destroyBody(localIterator.next());
			} 
			catch (Exception localException)
			{
				Debug.d("SPK - THE BODY DOES NOT WANT TO DIE: " + localException);
			}
		}
	}

	
	
	
	
	
	
	
	
	 //-------------------------«¿√–”« ¿ ”–Œ¬Õﬂ -------------------------------------------------------       
	    public void init_map(){
	    	  Log.v("GAME","«¿œ”—“»À» MAP()");
		     //  Log.i(TAG, "›ÚÓ ÏÓÂ ÒÓÓ·˘ÂÌËÂ ‰Îˇ Á‡ÔËÒË ‚ ÊÛÌ‡ÎÂ");
	    		 Ball_arr = new  ArrayList<Ball>();
	    		 Ball_arr_pos  = new  ArrayList<Vector2>() ;
	         final LevelLoader levelLoader = new LevelLoader();
	         levelLoader.setAssetBasePath("level/");
	         levelLoader.registerEntityLoader(LevelConstants.TAG_LEVEL, new IEntityLoader() {
	          @Override
	          public void onLoadEntity(final String pEntityName, final Attributes pAttributes) {
	                 // final int width = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH);
	                 // final int height = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT);
	                 
	          }

			
	  });

	  levelLoader.registerEntityLoader(TAG_ENTITY, new IEntityLoader() {
	         


			@Override
	          public void onLoadEntity(final String pEntityName, final Attributes pAttributes) {
	                  final float x = SAXUtils.getIntAttributeOrThrow(pAttributes, "x");
	                  final float y = SAXUtils.getIntAttributeOrThrow(pAttributes, "y");
	                 // final int width = SAXUtils.getIntAttributeOrThrow(pAttributes, "width");
	                //  final int height = SAXUtils.getIntAttributeOrThrow(pAttributes, "height");
	                  final String type = SAXUtils.getAttributeOrThrow(pAttributes, "type");
	                  final int player = SAXUtils.getIntAttributeOrThrow(pAttributes, "player");
	                  
	                  if(type.equals("box")){
	                	  if(player== mConst.PLAYER){
	                		  PLAYER_BALL_N ++;
	                	  }else  ENEMY_BALL_N++;
	                	 Ball ball = new Ball(x,y,Game.this,mConst.FACE_BOX ,player);
	                	  Ball_arr.add(ball);
	                	  Ball_arr_pos.add(new Vector2(x,y));
	                     
	                  }
	                  
	                  
	                  if(type.equals("circle")){
	                	  if(player== mConst.PLAYER){
	                		  PLAYER_BALL_N ++;
	                	  }else  ENEMY_BALL_N++;
	                	 Ball ball =  new Ball(x,y,Game.this,mConst.FACE_CIRCLE ,player);
	                	  Ball_arr.add(ball);
	                	  Ball_arr_pos.add(new Vector2(x,y));
	                     
	                  }
	                  
	                  PLAYER_BALL_N_ORIGIN = PLAYER_BALL_N;
	                  ENEMY_BALL_N_ORIGIN = ENEMY_BALL_N;
	                  if(type.equals("point")){
	                	  new Point(x,y,Game.this , mConst.POINT_SIMPLE);
	                  }
	                  


	          }
	  });

	  
	  
	  try {
		       this.map = mConst.SELECTED_MAP;

	          levelLoader.loadLevelFromAsset(this, "map"+this.map+".lvl");
	          Log.v(" 548 GAME","MAP="+this.map);
	  } catch (final IOException e) {
	          //Debug.e(e);
		  Log.v("levelLoader","exeption =(");
	  }

			
		}
	    
	    
	    
	
	protected void ContactBallPoint(Ball ball, Point point) {point.contactBall(ball);}


	
	@Override
	public void onLoadComplete() {
		
		// create_object();
		// gui = new GUI(this);
		// gm = new GameManager(this);
	  //   gm.canMovePlayer();
	 //    imozg = new Imozg(this);
	//	 mScene.registerTouchArea(backgrund);
		
		
	}

	
	
	
	public void create_object() {
		init_map();

		mConst.PLAYER_BALL_N =this.PLAYER_BALL_N;
		mConst.ENEMY_BALL_N =ENEMY_BALL_N;
		Log.v("GAME","BALL_N_PLAYER"+ mConst.PLAYER_BALL_N);

	}


	

	@Override
	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY) {
	//	Log.v("GAME","-----------onScroll-------"+pDistanceX);
		 this.mSmoothCamera.offsetCenter(-pDistanceX , -pDistanceY);	
	}
	


//___________________________________________________________________________________
	private ContactListener createContactListener() {
        ContactListener contactListener = new ContactListener()
        {
            @SuppressLint("FloatMath")
			@Override
            public void beginContact(Contact contact)
            {
            	//Log.v("BALL","CONTACT");
                final Fixture x1 = contact.getFixtureA();
                final Fixture x2 = contact.getFixtureB();
                Body b1 = x1.getBody();
                Body b2 = x2.getBody();
                //-------- BALL ---------------------------
                if(b1.getUserData() instanceof Ball ){Ball ball = (Ball)b1.getUserData();ball.contactPoint();vsm.vibro(50);}
                if(b2.getUserData() instanceof Ball ){Ball ball = (Ball)b2.getUserData();ball.contactPoint();vsm.vibro(50);}
             
                             if(b1.getUserData() instanceof Ball) {      	 
            	  if(b1 == mConst.ball) {
            		  mConst.BALL_U++;
            		  Log.v("BALL","¿ “»¬Õ€… —“ŒÀ Õ”À—ﬂ"+ mConst.BALL_U);
            		 
            	  }
              }
              if(b2.getUserData() instanceof Ball) {      	 
            	  if(b2 == mConst.ball) {
            		  mConst.BALL_U++;
            		  Log.v("BALL","¿ “»¬Õ€… —“ŒÀ Õ”À—ﬂ"+ mConst.BALL_U);
            		 
            	  }
              }
              
              // LEN
              if(b1.getUserData() instanceof Ball && b2.getUserData() instanceof Ball) { 
                       if(mConst.BALL_X != 0){       
                       Ball ball = (Ball)b1.getUserData();
                       Log.v("BALL","POSITION :"+ball.spr.getX() +":"+ball.spr.getY() );
                               mConst.LEN_FIRST = (float) (Math.sqrt( (ball.spr.getX()  - mConst.BALL_X)*(ball.spr.getX()- mConst.BALL_X) + 
                    		                                 (ball.spr.getY() - mConst.BALL_Y)*(ball.spr.getY() - mConst.BALL_Y)));
                               Log.v("BALL","WHO& LenFirst="+mConst.LEN_FIRST);
                               mConst.BALL_X=0;
                               mConst.BALL_Y = 0;
                       }
                     
              }
              
              //------------ POINT BALL -------------------------------------------
              if(b1.getUserData() instanceof Ball && b2.getUserData() instanceof Point ){
            	  Ball ball = (Ball)b1.getUserData();
            	  Point point =(Point)b2.getUserData();
            	  ContactBallPoint(ball, point);
            	  vsm.vibro(90);
              }
              if(b2.getUserData() instanceof Ball && b1.getUserData() instanceof Point ){
            	  Ball ball = (Ball)b2.getUserData();
            	  Point point =(Point)b1.getUserData();
            	  ContactBallPoint(ball, point);
            	  vsm.vibro(90);
              }

              
              
       
        
       
       
            }

			@Override
            public void endContact(Contact contact) {  }
            @Override
            public void preSolve(Contact contact, Manifold oldManifold){ }
            @Override
            public void postSolve(Contact contact, final ContactImpulse impulse) { }};
            return contactListener;}

	

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		Log.v("GAME","onAreaTouched");
		 mScrollDetector.onTouchEvent(pSceneTouchEvent);
		return false;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		 
		if(pSceneTouchEvent.isActionDown()) {
           // mScrollDetector.setEnabled(true);
            Log.v("GAME","onSceneTouchEvent"+pSceneTouchEvent.getX());
    }
   mScrollDetector.onTouchEvent(pSceneTouchEvent);
		
		return false;
	}


	
	
}//enf class

