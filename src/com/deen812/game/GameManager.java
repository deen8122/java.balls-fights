package com.deen812.game;

import java.util.Iterator;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;



import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class GameManager {

	public int PLAYER_BALL_N;
	public int ENEMY_BALL_N;
	Game game;
	private int player_move_n;
	private int enemy_move_n;
	boolean f_last_ball = false;
	public boolean enemyWin = false;
	private boolean stop;
	// ˝ÚÓ ·Û‰ÂÚ ËÏÂÌÂÏ Ù‡ÈÎ‡ Ì‡ÒÚÓÂÍ
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_COUNTER = "map2";


	public GameManager(final Game game) {
	this.game = game;
	this.stop = false;
	}

	
	
	public void setCountBalls() {
		
		
	}
    public void stop(){
    	this.stop = true;
    }


	public void setBallMoved() {
		if(stop) return ;
		game.mConst.MOVED_BALL_PLAYER  = game.mConst.PLAYER_BALL_N;
		game.mConst.MOVED_BALL_ENEMY   = game.mConst.ENEMY_BALL_N;
		//------------------
		Iterator<Body> bodies = game.mPhysicsWorld.getBodies();
		 while(bodies.hasNext()){
             Body b = bodies.next();
             if(b.getUserData() instanceof Ball){
            	 Log.v("GM","setBallMoved!");
            	 Ball ball = (Ball)b.getUserData();
            	 ball.setmoved();
             }
        
             }      
		
	}


//---------------------------------------------------------------
	public void minusPlayerBall() {
		if(stop) return ;
		if(enemyWin) return ;
		game.mConst.PLAYER_BALL_N--;
		game.gui.SetScore(-1);
	if(game.mConst.PLAYER_BALL_N==0) {
		winEnemy();
	}else {
		game.gui.show_text(game.mConst.TEXT_OOPS);
	}
	}
	
	public void minusEnemyBall() {
		if(stop) return ;
		if(enemyWin) return ;
		game.mConst.ENEMY_BALL_N--;
		
	if(game.mConst.ENEMY_BALL_N<=0) {
		winPlayer();
	}	
	else {
		game.gui.show_text(game.mConst.TEXT_EXCELLENT);
	}
	
	if(game.mConst.WHO_MOVE == game.mConst.PLAYER){
	game.mConst.LEN_FIRST/=100;
	if(game.mConst.LEN_FIRST <1)game.mConst.LEN_FIRST=1;
	int score = (int) ((game.mConst.BALL_U+1)*game.mConst.LEN_FIRST);
	Log.v("GM","LEN"+game.mConst.LEN_FIRST+"   BALL_U="+game.mConst.BALL_U +" SCORE="+score);
	
	new Effect(game.mSmoothCamera.getCenterX(),game.mSmoothCamera.getCenterY()   ,"+"+score, game.mScene , game.mRes);
	game.gui.SetScore(score);
	}
	
	}
//-----------------------------------------------------------------


	// ¬€»√–¿À œ–Œ“»¬Õ» 
	private void winEnemy() {
		if(stop) return ;
		this.stop();
		enemyWin = true;
		game.gui.show_text(game.mConst.TEXT_U_LOST);
		 game.mScene.registerUpdateHandler(new TimerHandler(3f,true, new ITimerCallback(){

				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					Log.v("GM","winEnemy()");
					game.mScene.unregisterUpdateHandler(pTimerHandler);
					game.restartLevel();
					
				}
				   
			   }));
	    	
		
	}
	// ¬€»√–¿À œŒÀ‹«Œ¬¿“≈À‹

	private void winPlayer() {
		if(stop) return ;
		stop = true;
		this.stop();
		Log.v("GM","winPlayer");
		game.map+=1;
		if ( game.map  > game.mConst.LEVEL) {
			if(game.map >game.mConst.MAX_LEVEL){
				game.map = 1;
			}
		Editor editor = game.mSettings.edit();
		game.gui.show_text(game.mConst.TEXT_YOU_WIN);
		editor.putInt(APP_PREFERENCES_COUNTER, game.map);
		 game.mConst.SELECTED_MAP = game.map;
		editor.commit();
		// game.map++;
		 game.mConst.LEVEL = game.map;
		}
		      //  Editor editor = game.mSettings.edit();	
				//editor.putInt(APP_PREFERENCES_COUNTER, game.map);
				//editor.commit();
		game.mScene.registerUpdateHandler(new TimerHandler(3f,true, new ITimerCallback(){

			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
		//		Log.v("GM","winEnemy()");
				if(game.f_restart== true){
				//	game.f_restart=false;
				}
				else{
				
				
				
				}
				game.winPlayer();
				game.f_restart=false;
				game.mScene.unregisterUpdateHandler(pTimerHandler);
				
			}
			   
		   }));
		
		
		
	}



	public void movedPlayer() {
		if(stop) return ;
	//	game.mConst.MOVED_BALL_PLAYER--;
		Log.v("GM","==== movedPlayer =" + game.mConst.MOVED_BALL_PLAYER + ":"+this.player_move_n);
//		if(game.mConst.MOVED_BALL_PLAYER == 0){
//			canMoveEnemy();
	//	}
		
	}



	public void movedEnemy() {
		if(stop) return ;
		Log.v("GM","==== movedEnemy =");
	//	game.mConst.MOVED_BALL_ENEMY--;
	//	if(game.mConst.MOVED_BALL_ENEMY == 0){
	//		setBallMoved();
	//		canMovePlayer();
	//	}
		
	}
	
	public void canMovePlayer(){
		if(stop) return ;
		if(enemyWin) return ;
		game.gui.show_text(game.mConst.TEXT_YOU_TURN);
		Log.v("GM","==== canMovePlayer =");
		game.mConst.MOVED_BALL_PLAYER = game.mConst.PLAYER_BALL_N;
		game.mConst.WHO_MOVE = game.mConst.PLAYER;
		
		
	}
	
	public void canMoveEnemy(){
		if(stop) return ;
		if(enemyWin) return ;
		Log.v("GM","==== canMoveEnemy =");
		game.mConst.MOVED_BALL_ENEMY= game.mConst.ENEMY_BALL_N;
		game.mConst.WHO_MOVE = game.mConst.ENEMY;
		game.imozg.start();
	}



	public void minusEnemyNoMoved() {
		if(stop) return ;
		game.mConst.MOVED_BALL_ENEMY--;
	//	if(game.mConst.MOVED_BALL_ENEMY == 0){
	//		setBallMoved();
	//		canMovePlayer();
//		}
		
		
	}



	public void minusPlayerNoMoved() {
		if(stop) return ;
	game.mConst.MOVED_BALL_PLAYER--;
//		if(game.mConst.MOVED_BALL_PLAYER == 0){
	//		canMoveEnemy();
	//	}
	}


	
	
//-------------------------------------------
	public void ball_stop(int player) {
		if(stop) return ;
		if(player == game.mConst.PLAYER) {
			this.player_ball_stop();
		}else {
			this.enemy_ball_stop();
		}
		
	}



	private void enemy_ball_stop() {
		if(stop) return ;
		Log.v("GM","enemy_ball_stop");
		game.mConst.MOVED_BALL_ENEMY--;
		if(game.mConst.MOVED_BALL_ENEMY == 0){
			setBallMoved();
			canMovePlayer();
		}else {
			game.imozg.start();
		}
	}



	private void player_ball_stop() {
		if(stop) return ;
		game.mConst.MOVED_BALL_PLAYER--;
		Log.v("GM","player_ball_stop"+game.mConst.MOVED_BALL_PLAYER);
		
		if(game.mConst.MOVED_BALL_PLAYER <= 0){
			canMoveEnemy();
		}else{
			
		}
		
		
	}
	

}
