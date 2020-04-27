package com.deen812.game;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.MathUtils;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public class Ball {

	protected boolean f_gun = true;
	public Body body;
	Game game;
	 int type=1;
	public AnimatedSprite spr;
	TiledTextureRegion tt;
	private Rectangle rect;
	public boolean f_moved = false;
	public int player;
	public boolean f_detect_stop;
	public IUpdateHandler detect_handler;
	public Vector2 postion;
	private int curent_index =0;
	
	
	
	
	public Ball(float x2, float y2, final Game game,int type,int player2) {
		this.game = game;
        this.player = player2;
		float x = game.mConst.GAME_TABLE_X + x2;
		float y = game.mConst.GAME_TABLE_Y + y2;
		this.type = type;
		
		
		if(type == game.mConst.FACE_BOX){
			  if(player== game.mConst.PLAYER)     tt =game.mRes.face_box.clone();
			  else                                tt =game.mRes.face_box_enemy.clone();
		}else {
			  if(player== game.mConst.PLAYER)     tt =game.mRes.face_circle.clone();
			  else                                tt =game.mRes.face_circle_enemy.clone();
		}
		
		
		
		
		rect = new Rectangle(100,100,10,4);
		rect.setColor(1, 0.3f, 0.3f);
		 game.mScene.attachChild(rect);
		 rect.setVisible(false);
		 
		 if(player== game.mConst.PLAYER) {	 
	spr = new AnimatedSprite(x, y,tt)    {
       	  private float Xpoint;
       	 
			  private float Ypoint;
			  private float shootAngle,shootPower,distanceX2,distanceY2;

			@Override
             public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {	 
			      if(f_moved) return true;
			      if(player != game.mConst.WHO_MOVE) return true;
				if(pSceneTouchEvent.isActionDown()){
					       game.gui.startBall();
			               game.mScrollDetector.setEnabled(false);
					       this.setScale(1.1f);
                   	       Xpoint = pSceneTouchEvent.getX();
             			   Ypoint = pSceneTouchEvent.getY();
             			   rect.setVisible(true);
             			   rect.setPosition(spr.getX() + spr.getWidth()/2, spr.getY()+ spr.getHeight()/2);
                     }
				
				
                     if(pSceneTouchEvent.isActionUp() ||pSceneTouchEvent.isActionCancel() ){ 
                   	  shootPower = 1.6f*(float) Math.sqrt(distanceX2*distanceX2 + distanceY2*distanceY2);
                   	  
                      fire(shootPower , shootAngle);
                      rect.setVisible(false);
                   	  Ball.this.spr.setScale(1f); 
                    	game.mScrollDetector.setEnabled(true);
                    	game.gui.stopFireBall();
                    	
                     }
                     
                     if(pSceneTouchEvent.isActionMove()){
                           float  distanceX = Xpoint-  pSceneTouchEvent.getX();
                           float distanceY  = Ypoint - pSceneTouchEvent.getY();
                           distanceX2 = distanceX;
                           distanceY2 = distanceY;
                          
                           shootAngle = (float)Math.atan2(distanceY,distanceX);
                          float  shootPower = (float) (1.5* Math.sqrt(distanceX2*distanceX2 + distanceY2*distanceY2));
                        
                          float ang = (float) (180*shootAngle/Math.PI);
                       //    circle_text.setText(""+shootAngle);
                          // rect.setPosition(pSceneTouchEvent.getX()+20, pSceneTouchEvent.getY() +20);
                          // rect.setRotationCenter(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                          rect.setRotation(ang);
                          rect.setWidth(shootPower);
                         //  selectedGun.SetRotation(shootAngle);
                    }
		
			  
			  
			 
			  
                 return true;
             };        	 	        };
             
		 }
		 else {
			 spr = new AnimatedSprite(x, y,tt);
		 }
             final float pPixelToMeterRatio = 30;
             final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(6, 0.4f, 0.1f );
        if(type == game.mConst.FACE_CIRCLE) {               
    		 final BodyDef circleBodyDef = new BodyDef(); 
    		// spr.getVertexBuffer();
    			circleBodyDef.type =  BodyType.DynamicBody;              
    			circleBodyDef.position.x = spr.getX() / pPixelToMeterRatio;
    			circleBodyDef.position.y = spr.getY() / pPixelToMeterRatio;
    			circleBodyDef.linearDamping = 0.7f;
    			circleBodyDef.angularDamping = 0.8f;
    			circleBodyDef.angle = MathUtils.degToRad(0);
    			  final Body circleBody = game.mPhysicsWorld.createBody(circleBodyDef);
    			  final CircleShape circlePoly2 = new CircleShape();  			
    			  final float radius2 = spr.getWidth() / (pPixelToMeterRatio*2);
    			  circlePoly2.setRadius(radius2);
    			  objectFixtureDef.shape = circlePoly2;
    			  circleBody.createFixture(objectFixtureDef);
    			  body = circleBody;
    			  circlePoly2.dispose();	
        }
        else {
        	final BodyDef boxBodyDef = new BodyDef();
    		boxBodyDef.type =  BodyType.DynamicBody;
    		boxBodyDef.linearDamping = 0.9f;
    		boxBodyDef.angularDamping = 0.4f;
    		boxBodyDef.position.x = spr.getX() / pPixelToMeterRatio;
    		boxBodyDef.position.y =spr.getY() / pPixelToMeterRatio;

    		body = game.mPhysicsWorld.createBody(boxBodyDef);

    		final PolygonShape boxPoly = new PolygonShape();

    		final float halfWidth = spr.getWidth() * 0.5f / pPixelToMeterRatio;
    		final float halfHeight = spr.getHeight() * 0.5f / pPixelToMeterRatio;

    		boxPoly.setAsBox(halfWidth, halfHeight);
    		objectFixtureDef.shape = boxPoly;
    		body.createFixture(objectFixtureDef);
    		boxPoly.dispose();
    		body.setTransform(body.getWorldCenter(), MathUtils.degToRad(0));
        }
	//	body.setAngularDamping(50.0f);
	//	body.setAngularVelocity(50.0f);
        body.setUserData(this);
        body.setBullet(true);
		game.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(spr, body, true, true));
          game.mScene.attachChild(spr);
          game.mScene.registerTouchArea(spr);
          this.postion = body.getPosition();
          
	}

	
	
	protected void moved() {
		if(this.player == game.mConst.PLAYER){
			game.gm.movedPlayer();
		}else {
			game.gm.movedEnemy();
		}
		
		f_moved = true;		
		
	}
	void setmoved(){
		f_moved = false;
		curent_index=0;
		this.spr.setCurrentTileIndex(curent_index);
	}

	protected void fire(final float shootPower, final float shootAngle) {
		moved();
		this.postion = body.getPosition();
		game.mConst.BALL_X = this.spr.getX();
		game.mConst.BALL_Y = this.spr.getY();
		Log.v("VALL","game.mConst.BALL_X " + game.mConst.BALL_X+":"+game.mConst.BALL_Y );

	
		detect_stop();
		if(this.player == game.mConst.PLAYER) {
			game.mConst.ball = this.body;
			game.mConst.BALL_U=0;
			
		}
		 this.game.mScene.registerUpdateHandler(new IUpdateHandler() {  
			 @Override  
              public void onUpdate(float arg0) { 
				 float c =  (float) (shootPower*Math.cos(shootAngle));
				 float s = (float) (shootPower*Math.sin(shootAngle)) ;
				
					body.applyLinearImpulse(new Vector2(c ,   s), body.getWorldCenter());
					
				 game.mScene.unregisterUpdateHandler(this);
			 }  

            @Override  
             public void reset() {   }  
          }); 
		
	}

	protected void detect_stop() {

		Log.v("BALL","start_detect_stop()"+body.getLinearVelocity().x);
	f_detect_stop = true;
	
    detect_handler = 	new IUpdateHandler() { 
    	float y2 = 0;
    	float x2 = 0;
			 @Override  
             public void onUpdate(float arg0) { 
				
				 float x = Ball.this.spr.getX();
				 float y = Ball.this.spr.getY();
				// Log.v("SPEED",""+body.getLinearVelocity().x + ":"+body.getLinearVelocity().y);
				 if(  ( Math.abs(body.getLinearVelocity().x) < 0.1) && (Math.abs(body.getLinearVelocity().y)< 0.1)  ) {
					 unset_detect_stop();
					 game.mScene.unregisterUpdateHandler(this);
				 }
				 x2 = Ball.this.spr.getX();
				 y2 = Ball.this.spr.getY();
			 }  

           @Override  
            public void reset() {   }  
         };
         this.game.mScene.registerUpdateHandler(detect_handler);
         
		
		
	}

	public void contactPoint() {
	   this.spr.animate(new long[] { 500,100}, new int[]{1,curent_index }, 0);
	   
		
	}

	public void unset_detect_stop() {
		
    if(f_detect_stop) {
		curent_index=2;
		if(spr.isAnimationRunning())
			spr.stopAnimation();
		
			this.spr.setCurrentTileIndex(curent_index);
    	Log.v("BALL","unset_detect_stop()");
    	game.mScene.unregisterUpdateHandler(detect_handler);
    	 game.gm.ball_stop(player);
	     f_detect_stop = false;
    }
		
	}



	public void destroy() throws Throwable {
		this.finalize();
		
	}

}
