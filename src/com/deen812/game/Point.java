package com.deen812.game;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.util.MathUtils;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Point {

	public AnimatedSprite box_spr;
	 Game game;
	private int type;
	public Body box_body;
	float rx = 0;
	float ry = 0;
	private float x;
	private float y;
	private AnimatedSprite spr;
	
	//----------------------------------------------------------------
	public Point(float x, float y, Game game,int type) {
this.game   = game;
this.type = type;

x = game.mConst.GAME_TABLE_X + x;
y = game.mConst.GAME_TABLE_Y + y;


		final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(20f, 0.1f, 1f,true );
		  this.box_spr =new  AnimatedSprite(x , y , game.mRes.point.clone());
		  box_spr.setZIndex(-1);
		  game.mScene.attachChild(box_spr);
		 // box_body = PhysicsFactory.createBoxBody(game.mPhysicsWorld, box_spr, BodyType.DynamicBody, objectFixtureDef);
         
          final BodyDef circleBodyDef = new BodyDef(); 
 		
 			circleBodyDef.type =  BodyType.DynamicBody;
            
 			circleBodyDef.position.x = (box_spr.getX() + box_spr.getWidth()/2) / 30;
 			circleBodyDef.position.y = (box_spr.getY()  + box_spr.getHeight()/2) / 30;
 			box_body = game.mPhysicsWorld.createBody(circleBodyDef);

 			final CircleShape circlePoly2 = new CircleShape();
 			
 			final float radius2 = 5 / 30;
 			circlePoly2.setRadius(radius2);
 			objectFixtureDef.shape = circlePoly2;
 			box_body.createFixture(objectFixtureDef);
 		
 			 circlePoly2.dispose();
 			 game.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(box_spr, box_body, true, true)); 
 	          box_body.setUserData(this);
 	        
   
          
        
		
	}

	public void contactBall(final Ball ball) {
		//  ball.body.setTransform(1, 1, 0);
		// ball.body.setTransform(box_body.getPosition().x,box_body.getPosition().y, box_body.getAngle());
		//	 game. mPhysicsWorld.destroyBody(ball.body);
		 spr =ball.spr; 
		// game.mScene.attachChild(spr);
		 
		ball.unset_detect_stop();
if(ball.player == game.mConst.PLAYER){
	game.gm.minusPlayerBall();
	if(ball.f_moved == false)game.gm.minusPlayerNoMoved();
}else {
	game.gm.minusEnemyBall();
	if(ball.f_moved == false) game.gm.minusEnemyNoMoved();
}
		
		
		
		if(this.type == game.mConst.POINT_SIMPLE){
			//Log.v("Point","x="+xb+" yb="+yb +"  x:"+x+"|y:"+y);
			
				game. mScene.registerUpdateHandler(new IUpdateHandler() {  
					

					@Override  
		              public void onUpdate(float arg0) {
						 ball.body.setTransform(box_body.getPosition().x,box_body.getPosition().y, box_body.getAngle());
						// game. mPhysicsWorld.destroyBody(ball.body);
						         // spr =ball.spr;
						          ball.body.setActive(false);
						          ball.body.setAwake(false);
						         // ball.body.setTransform(0,0,0);
						          padaet(ball.spr , ball.body);
						        
						 game. mScene.unregisterUpdateHandler(this);
					 }  

		            @Override  
		             public void reset() {   }  
		          });  

			
		}
		
	}

	protected void padaet(final AnimatedSprite spr,final Body body) {
		
		game. mScene.registerUpdateHandler(new IUpdateHandler() {  
			 private float pScale = 1;

			@Override  
             public void onUpdate(float arg0) {
				 if(pScale  < 0.1) {
					// body.setTransform(0,0,0);
					// game. mScene.detachChild(spr);
					 //ball_body.setTransform(1, 1, 0);
					// spr.setScale(1f );
					 //ball_body.setActive(false);
					// game.mScene.detachChild(spr);
				     game. mScene.unregisterUpdateHandler(this);
				 }
				 pScale= pScale -0.08f;
				 spr.setScale(pScale );
			 }  

           @Override  
            public void reset() {   }  
         });  
		
	}

}
