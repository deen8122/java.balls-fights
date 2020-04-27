package com.deen812.game;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.IBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;

public class SceneManager {

	private AllScenes currentScene;
	private Game activity;
	private Engine engine;
	private Camera camera;
	//private BitmapTextureAtlas splashTA;
	//private ITextureRegion splashTR;
	private Scene splashScene, gameScene, menuScene;
	private TiledTextureRegion face_box_enemy;
	private Resource mRes;
	private Scene settingScene;
	private Scene selectLevel;
	private Scene lastScene;
	private SelectScene ss;
	private Scene HelpScene;

	public enum AllScenes {
		SPLASH, MENU, GAME,SELECT_LEVEL, SETTING,HELP
	}

	public SceneManager(Game game, Engine eng, Camera cam) {
		// TODO Auto-generated constructor stub
		this.activity = game;
		this.engine = eng;
		this.camera = cam;
		mRes = game.mRes; 
	}

	public void loadSplashResources() {
		   TextureRegionFactory.setAssetBasePath("gfx/");
		    Texture t8 = new Texture(128, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        face_box_enemy = TextureRegionFactory.createTiledFromAsset(t8, this.activity, "face_box_enemy.png", 0, 0, 3, 1); // 64x32
	        engine.getTextureManager().loadTextures(t8);

	}

	public void loadGameResources() {

	}

	public void loadMenuResources() {

	}

	public Scene createSplashScene() {
		splashScene = new Scene( 0 );
		//splashScene.setBackground(new IBackground());
		final AnimatedSprite icon = new AnimatedSprite(0, 0,  face_box_enemy);
		icon.setPosition((camera.getWidth() - icon.getWidth()) / 2,
				(camera.getHeight() - icon.getHeight()) / 2);
		splashScene.attachChild(icon);
		splashScene.registerUpdateHandler(new IUpdateHandler() {  
			float a = icon.getRotation();
			@Override  
              public void onUpdate(float arg0) { 
				icon.setRotation(a);;
				a+=5;
		         }  

            @Override  
             public void reset() {   }  
          });  
		return splashScene;
	}

	public Scene createMenuScene() {
		  Log.v("SCENE_MANAGER","createMenuScene");
		    MainScene ms = new MainScene(this.activity);
		    menuScene = ms.getScene();
		    
		    HelpScene hs = new HelpScene(this.activity);
		    HelpScene = hs.getScene();
		    
		     ss = new SelectScene(this.activity);
		    selectLevel = ss.getScene();
		return menuScene;

	}

	public Scene createGameScene() {
		return null;
	}

	public AllScenes getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(AllScenes currentScene) {
		
		camera.setCenter(0, 0);
		activity.closeScene();
		this.currentScene = currentScene;
		switch (currentScene) {
		case SPLASH:
			break;
		case MENU:
	//		camera.getHUD().clearChildScene();
			
			engine.setScene(menuScene);
			break;
		case GAME:
			engine.setScene(gameScene);
			break;
		case SETTING:
			engine.setScene(settingScene);
			break;
		case HELP:
			engine.setScene(HelpScene);
			break;	
			
		case SELECT_LEVEL:
			//menuScene.
			//  SelectScene ss = new SelectScene(this.activity);
			  //  selectLevel = ss.getScene();
			//selectLevel.checkLevels();
			ss.checkLevels();
			
			engine.setScene(selectLevel);
			break;
		default:
			break;
		}
		//this.lastScene = currentScene;
	}

	public void setRes(Resource mRes2) {
		mRes = mRes2; 
		
	}

	public Scene createSettingScene() {
	
	//	  SettingScene ms = new SettingScene(this.activity);
		//  settingScene = ms.getScene();
		return settingScene;
		
	}

	public void setSelectScene() {
		camera.setCenter(0, 0);
		ss.checkLevels();
		this.currentScene = AllScenes.SELECT_LEVEL;
		engine.setScene(selectLevel);
		
	}
	
	
}
