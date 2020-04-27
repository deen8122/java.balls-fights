package com.deen812.game;


import java.io.IOException;
import java.util.ArrayList;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class Resource {


public Engine mEngine;
public TextureRegion mBackgroundTextureRegion;
//ÊÎØÊÀ
public TiledTextureRegion cat_head;
public TextureRegion cat_body;
public TextureRegion cat_hand;
	
//BOMB
public TextureRegion bomb_apple;
public TextureRegion hummer;
public TextureRegion bomb_osk2;
public TiledTextureRegion z_head;	
//zombie
public TiledTextureRegion z0;


//TEXT
public Texture mFontTexture;
public Font mFont;
public TiledTextureRegion z_body;
public TiledTextureRegion z_leg;
public TextureRegion z_hand;
public TextureRegion cat_hvost;
public TextureRegion btn_hummer;
public TiledTextureRegion btn_apple;
public TiledTextureRegion btn_dinamit;
public TextureRegion dinamit;
public TiledTextureRegion boom;
//BOSS1
public TiledTextureRegion boss1_head;
public TextureRegion boss1_body;
public TiledTextureRegion boss1_leg;
public TextureRegion boss1_hand;

//GUN
public TextureRegion gun_body_txrgn;
public TextureRegion gun_stvol_txrgn;

//BOXES
public TiledTextureRegion palka1;
public TiledTextureRegion palka3;
public TiledTextureRegion palka2;
public TiledTextureRegion box;
public TextureRegion box_osk0;
public TextureRegion box_osk1;
public TextureRegion box_osk2;
public TextureRegion circle;
//BREAK
public TiledTextureRegion breaker;
public TiledTextureRegion cat;

public TiledTextureRegion cat_enemy;
// BIRD
public TiledTextureRegion bird_bonus;
public TiledTextureRegion bonus;
public TextureRegion circle_point;

// BUttons
public TextureRegion btn_pause;
public TextureRegion paused;
public TiledTextureRegion ball_purpure;
public TiledTextureRegion point;
public TiledTextureRegion face_box;
public TiledTextureRegion face_circle;
public TextureRegion bg1;
public TextureRegion wall1;
public TextureRegion wall2;
public TiledTextureRegion face_box_enemy;
public TiledTextureRegion face_circle_enemy;
public TiledTextureRegion text;
public TextureRegion paused_content;
public TextureRegion btn_reload;
public TextureRegion btn_menu;
public TextureRegion bg2;
public TextureRegion btn_exit;
public TextureRegion bg4;
public TextureRegion txt_start;
public TextureRegion btn_sound;

public TextureRegion btn_back;
public TextureRegion  btn_next;
public TiledTextureRegion menu_level;
public TextureRegion game_name;
public TextureRegion btn_prev;
public TextureRegion head_select;
public TextureRegion btn_bye;
public TextureRegion btn_info;
public TextureRegion btn_no;
public Music music_ok;
public Sound mysound;
public TextureRegion how;
public TextureRegion vibro;
public TextureRegion music;


private static Resource instance;

	  public static Resource getInstance() {
	    if(instance == null) {
	      instance = new Resource();
	    }
	    return instance;
	  }

	  private Resource() {
		    
	  }
//=============================================================
	public void onLoadResources(Context ctx , Engine mEngine){
		   this.mEngine = mEngine;
		   
		   SoundFactory.setAssetBasePath("mfx/");
		   MusicFactory.setAssetBasePath("mfx/");
		      
		   
		//   try
		//   {
		  //     music_ok = MusicFactory.createMusicFromAsset(mEngine.getMusicManager(), ctx,"munch.ogg");
		//   }
		//   catch (IOException e)
		//   {
	//	       e.printStackTrace();
		//   }
		   
		 //load
		
		   try {
		   mysound = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(), ctx, "munch.ogg");
		   } catch (IOException e) {
		   e.printStackTrace();
		   }
		   
		   
		   
		   TextureRegionFactory.setAssetBasePath("gfx/");
		   Constants mConst = Constants.getInstance();
	        //this.mCatTexture = new Texture(128, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	      //  mCatTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mCatTexture,  ctx,  "cat3.png", 0, 0, 3, 6); //96-192/ 64x32
	          
		 //Çàãðóæàþ øðèôò BosaNova ðàçìåðîì 22 ïèêñåëÿ
		//   BitmapTextureAtlas font_BosaNova22_Texture = new BitmapTextureAtlas(512, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		 //  Font font_BosaNova22 = FontFactory.createFromAsset(font_BosaNova22_Texture, this, "fonts/bosanova.ttf", 22, true, Color.WHITE);
		    
		   //Çàãðóæàþ øðèôò BosaNova ðàçìåðîì 54 ïèêñåëÿ
		//   BitmapTextureAtlas font_BosaNova54_Texture = new BitmapTextureAtlas(512, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		 //  Font font_BosaNova54 = FontFactory.createFromAsset(font_BosaNova54_Texture, this, "fonts/bosanova.ttf", 54, true, Color.WHITE);
		 //   
		//   this.mEngine.getTextureManager().loadTextures(font_BosaNova22_Texture, font_BosaNova54_Texture );
		//   this.mEngine.getFontManager().loadFonts(font_BosaNova22, font_BosaNova54);
		    this.mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR);      
	        this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 45, true, Color.YELLOW );
	        this.mEngine.getTextureManager().loadTexture(this.mFontTexture); 
	        this.mEngine.getFontManager().loadFont(this.mFont);  

		        
	  
	          
	          // ÔÎÍTextureOptions.DEFAULT
		   Texture mBackgroundTexture = new Texture(512, 1024,  TextureOptions.BILINEAR);
	        this.mBackgroundTextureRegion = TextureRegionFactory.createFromAsset(mBackgroundTexture, ctx, "bg5.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture);
	       
	        Texture mBackgroundTexture2 = new Texture(512, 256,  TextureOptions.BILINEAR);
	        this.how = TextureRegionFactory.createFromAsset(mBackgroundTexture2, ctx, "how.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture2);
	        
	        Texture mBackgroundTexture3 = new Texture(128, 128,  TextureOptions.BILINEAR);
	        this.vibro = TextureRegionFactory.createFromAsset(mBackgroundTexture3, ctx, "vibro.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture3);
	        
	        Texture mBackgroundTexture4 = new Texture(128, 128,  TextureOptions.BILINEAR);
	        this.music = TextureRegionFactory.createFromAsset(mBackgroundTexture4, ctx, "music.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture4);
	      //  Texture t1 = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	       // ball_purpure = TextureRegionFactory.createTiledFromAsset(t1, ctx, "ball_purple.png", 0, 0, 1, 1); // 64x32
	      //  this.mEngine.getTextureManager().loadTextures(t1);
	        
	        Texture t2 = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        point = TextureRegionFactory.createTiledFromAsset(t2, ctx, "point_2.png", 0, 0, 1, 1); // 64x32
	        this.mEngine.getTextureManager().loadTextures(t2);
	        
	        
	        //FACE
	        Texture t8 = new Texture(128, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        face_box_enemy = TextureRegionFactory.createTiledFromAsset(t8, ctx, "face_box_enemy3.png", 0, 0, 3, 1); // 64x32
	        this.mEngine.getTextureManager().loadTextures(t8);
	        
	        Texture t9= new Texture(128, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        face_circle_enemy = TextureRegionFactory.createTiledFromAsset(t9, ctx, "face_circle_enemy.png", 0, 0, 3, 1); // 64x32
	        this.mEngine.getTextureManager().loadTextures(t9);
	        
	        Texture t3 = new Texture(128, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        face_box = TextureRegionFactory.createTiledFromAsset(t3, ctx, "face_box.png", 0, 0, 2, 1); // 64x32
	        this.mEngine.getTextureManager().loadTextures(t3);
	        
	        Texture t4 = new Texture(128, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        face_circle = TextureRegionFactory.createTiledFromAsset(t4, ctx, "face_circle4.png", 0, 0, 3, 1); // 64x32
	        this.mEngine.getTextureManager().loadTextures(t4);
	        
	        
	        //-----------------------------------------------------------------------------------------
	        Texture t5 = new Texture(32, 32,  TextureOptions.REPEATING);
	        this.bg1 = TextureRegionFactory.createFromAsset(t5, ctx, "bg1.png", 0, 0);
	        //BG
	        this.bg1.setWidth((int) mConst.TABLE_WIDTH);
	        this.bg1.setHeight((int) mConst.TABLE_HEIGHT);
	        this.mEngine.getTextureManager().loadTextures(t5);
	        
	        Texture t51 = new Texture(32, 32,  TextureOptions.REPEATING);
	        this.bg2 = TextureRegionFactory.createFromAsset(t51, ctx, "bg2.png", 0, 0);
	        
	        this.bg2.setWidth((int) mConst.TABLE_WIDTH);
	        this.bg2.setHeight((int) mConst.TABLE_HEIGHT);
	        this.mEngine.getTextureManager().loadTextures(t51);
	        
	        
	       // Texture t52 = new Texture(1024, 512,  TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    //    this.bg4 = TextureRegionFactory.createFromAsset(t52, ctx, "bg4.png", 0, 0);
	     //   this.mEngine.getTextureManager().loadTextures(t52);
	        //=====================
	        
	        
	        
	        Texture t6 = new Texture(32, 32,  TextureOptions.REPEATING);
	        this.wall1 = TextureRegionFactory.createFromAsset(t6, ctx, "10.png", 0, 0);
	        
	        this.wall1.setWidth((int) mConst.TABLE_WIDTH);
	       
	        this.mEngine.getTextureManager().loadTextures(t6);
	        
	        Texture t7 = new Texture(32, 32,  TextureOptions.REPEATING);
	        this.wall2 = TextureRegionFactory.createFromAsset(t7, ctx, "10.png", 0, 0);
	        
	        this.wall2.setHeight((int) mConst.TABLE_HEIGHT+32);
	       
	        this.mEngine.getTextureManager().loadTextures(t7);
	        
	     
	        
	        
	        //TEXTS
	        Texture t10= new Texture(512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        text = TextureRegionFactory.createTiledFromAsset(t10, ctx, "text.png", 0, 0, 1, 5); // 64x32
	        this.mEngine.getTextureManager().loadTextures(t10);
	        
	        
	        //BTN
	        Texture t11 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_pause = TextureRegionFactory.createFromAsset(t11, ctx, "btn_pause.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t11);
	        
	        Texture t12 = new Texture(512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        this.paused_content = TextureRegionFactory.createFromAsset(t12, ctx, "paused.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t12);
	        
	        Texture t14 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_reload = TextureRegionFactory.createFromAsset(t14, ctx, "btn_replay.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t14);
	        
	        
	        Texture t13 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_menu = TextureRegionFactory.createFromAsset(t13, ctx, "btn_menu.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t13);
	        
	        Texture t15 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_exit = TextureRegionFactory.createFromAsset(t15, ctx, "btn_exit.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t15);
	        
	        
	        
	        Texture t16 = new Texture(128, 128,  TextureOptions.REPEATING);
	        this.txt_start = TextureRegionFactory.createFromAsset(t16, ctx, "play_btn.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t16);
	      
	        
	        
	        
	        
	        Texture t17 = new Texture(128, 128,  TextureOptions.REPEATING);
	        this.btn_sound = TextureRegionFactory.createFromAsset(t17, ctx, "sound.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t17);
	        
	        
	        Texture t171 = new Texture(128, 128,  TextureOptions.REPEATING);
	        this.btn_info = TextureRegionFactory.createFromAsset(t171, ctx, "btn_info.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t171);
	        
	        
	        Texture t172 = new Texture(128, 128,  TextureOptions.REPEATING);
	        this.btn_bye = TextureRegionFactory.createFromAsset(t172, ctx, "btn_bye.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t172);
	        
	        Texture t173 = new Texture(128, 128,  TextureOptions.REPEATING);
	        this.btn_no = TextureRegionFactory.createFromAsset(t173, ctx, "btn_no.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t173);
	 //       Texture t18 = new Texture(512, 128,  TextureOptions.REPEATING);
	//        this.txt_exit = TextureRegionFactory.createFromAsset(t18, ctx, "txt_exit.png", 0, 0);
	//        this.mEngine.getTextureManager().loadTextures(t18);
	  //      
	        
	        Texture t19 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_back = TextureRegionFactory.createFromAsset(t19, ctx, "prev2.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t19);
	        
	        Texture t20 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_next = TextureRegionFactory.createFromAsset(t20, ctx, "next2.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t20);
	        
	        Texture btntxt2 = new Texture(128, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        this.menu_level = TextureRegionFactory.createTiledFromAsset(btntxt2, ctx, "sel_origin.png", 0, 0,1,2);
	        this.mEngine.getTextureManager().loadTextures(btntxt2);
	        //public TextureRegion btn_back;
	        //public TiledTextureRegion menu_level;
	        
	        Texture t21 = new Texture(512, 256,  TextureOptions.REPEATING);
	        this.game_name = TextureRegionFactory.createFromAsset(t21, ctx, "game_name.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t21);
	        
	        
	        Texture t22 = new Texture(512, 256,  TextureOptions.REPEATING);
	        this.head_select = TextureRegionFactory.createFromAsset(t22, ctx, "select_level_text.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t22);
	        
	        //Texture t22 = new Texture(64, 64,  TextureOptions.REPEATING);
	     //   this.btn_prev = TextureRegionFactory.createFromAsset(t22, ctx, "next1.png", 0, 0);
	      //  this.mEngine.getTextureManager().loadTextures(t22);
	        
	}
}

