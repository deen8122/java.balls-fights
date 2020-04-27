package com.deen812.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;



public class Constants {
	public String base64EncodedPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsG/" +
			"c5ocHdVju9l8+B5z3iWbDxvgh8Xx9BQODJNbiW2hy5dPcnK2vUrzTzIYHDb7QE4OmI+D9CBIbbgB/" +
			"4+ZC/7AAyrccY8ckJG/+9wBs/zHalGw1FOib0pugEBThueM+qpH2o++zZuBgqUK4BHdCI9AszC6djYLu/" +
			"xJ4n83dZ3FecN8j/ktvknmZA9rTq/8ZXvKAT0KXchrciASNNAZTwjODHX0N4/" +
			"hI32eKJI4liEPyJNTwFkuQoL0VNXWbIMUZKcYV2grcsnIkLTe0NuH3x1ZvPygfW9FcPmqZE2KTgvvv092iZe1ihS5/" +
			"opPhlsLq+JjFwCeK3Wne3tHtxkxYWQIDAQAB";
	
	//chunk_split($publicKey, 64, "\n") 
	//AIzaSyBfNkrtXBZu9EyLB61v33FEM8FZysw0z-I
	public String base64EncodedPublicKey2 ="31129deen";
	int CAMERA_Y_CENTER = 240;
	public float CAMERA_WIDTH = 800;
	public float CAMERA_HEIGHT = 480;  
	public float TABLE_WIDTH = 750;
	public float TABLE_HEIGHT = 420;  
    float GAME_TABLE_X = 210;
    float GAME_TABLE_Y = 210;
	float WORLD_WIDTH = CAMERA_WIDTH +GAME_TABLE_X*2 ;
	float WORLD_HEIGHT = CAMERA_HEIGHT +GAME_TABLE_Y*2;
    float ZOOM_MAX =1.5f;
    float ZOOM_MIN = 0.5f;
    float GROUND_HEIGHT = 150;

    
    //BOMBS
    int APPLE    =  1;
    int HUMMER   =  2;
    int DINAMIT  =  3;
    
    //BOXES
    int  PALKA = 1;//простая палка. при сильном удрае ломатеся 
    int  PALKA2 = 2;
    int  PALKA3 = 3;
    //----------------------
    int PLAYER_BALL_N = 0;
	int ENEMY_BALL_N = 0;
    
    //---------------------
    int  BOX = 4;
    int  BOX2 = 5;
    int  BOX3 = 6;
	 int BREAKER=7;
	 int CAT_PLAYER = 0x1;
	 int CAT_ENEMY  = 0x2;
	 
	 //int CAT_PLAYER_COL = 0;
	 //int CAT_ENEMY_COL = 0;
	 
	 //BONUS TYPE--------------------------------
	 int BONUS_LIFE = 0;
	 int BONUS_PLUS_POWER = 3;
	 int BONUS_DOUBLE = 4;
	 int BONUS_PLUS = 2;
	 int BONUS_DINAMIT = 1;
	 //-----------------------------------------
	public int PLAYER = 0;
	public int ENEMY =  1;
    
	int COL_DINAMIT = 10;
	protected int BOMB_OSK = 0x5;
	public int POINT_SIMPLE = 0;
	public int FACE_CIRCLE = 0;
	public int FACE_BOX = 1;
	public int MOVED_BALL_PLAYER =0;
	public int MOVED_BALL_ENEMY =0;
	public int WHO_MOVE = 10;
	
	//TEXT
	public int TEXT_YOU_TURN  = 3;
	public int TEXT_EXCELLENT = 4;
	public int TEXT_OOPS = 2;
	public boolean BALL_PRESSED = false;
	public int TEXT_YOU_WIN  = 0;
	public Body ball = null;
	public int BALL_U = 0;
	//public Vector2 point1 = null;
	public float LEN_FIRST=0;
	protected Vector2 point3;
	public float point3_x;
	public float point3_y;
	protected float BALL_X = 0;
	public float BALL_Y = 0;
	public int PLAYER_SCORE = 0;
	public int LEVEL= 1;
	public int TEXT_U_LOST = 1;
	public int MAX_LEVEL = 45;
	protected int SELECTED_MAP;
	
	
	
	
	
	
    private static Constants instance;

	  public static Constants getInstance() {
	    if(instance == null) {
	      instance = new Constants();
	    }
	    return instance;
	  }

	  private Constants() {
	    
	  }
	  
	  
}
