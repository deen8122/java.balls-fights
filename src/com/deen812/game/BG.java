package com.deen812.game;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BG {

	public BG(final Game game) {
	
	//	Background bg = new Background(1,0,0);
		
		
		game.backgrund = new Sprite(0, 0,game.mConst.WORLD_WIDTH,game.mConst.WORLD_HEIGHT, game.mRes.bg1){
			 @Override
             public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				 game.mScrollDetector.onTouchEvent(pSceneTouchEvent);
				 return true;
				 
			 }
		 };
	       
		// game.mScene.registerTouchArea(backgrund);
	    //    backgrund.setWidth(game.mConst.WORLD_WIDTH);
	      //  backgrund.setHeight(game.mConst.WORLD_HEIGHT);
	     //   backgrund.setPosition(0, 0);
		 float x = game.mConst.GAME_TABLE_X;
		 float y = game.mConst.GAME_TABLE_Y;
	        game.mScene.getChild(0).attachChild(game.backgrund);
	        
	        //final Shape ground = new Rectangle(x, game.mConst.WORLD_HEIGHT -y , game.mConst.WORLD_WIDTH-x, 5);
	        Sprite ground = new Sprite(x, game.mConst.TABLE_HEIGHT +y , game.mRes.wall1);
	       // final Shape roof = new Rectangle(x, y, game.mConst.WORLD_WIDTH -y, 5);
	        Sprite roof = new Sprite(x, y , game.mRes.wall1);
	      //  final Shape left = new Rectangle(x, y, 5, game.mConst.WORLD_HEIGHT-y);
	        Sprite left = new Sprite(x, y , game.mRes.wall2);
	       // final Shape right = new Rectangle(game.mConst.WORLD_WIDTH -x, y, 5, game.mConst.WORLD_HEIGHT -y);
	        Sprite right = new Sprite(game.mConst.TABLE_WIDTH +x, y , game.mRes.wall2);
	        
	        final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.4f, 0.5f);
	     //   wallFixtureDef.filter.categoryBits = 0x02;
	      //  wallFixtureDef.filter.maskBits = -1;
	        PhysicsFactory.createBoxBody(game.mPhysicsWorld, ground, BodyType.StaticBody, wallFixtureDef);
	        PhysicsFactory.createBoxBody(game.mPhysicsWorld, roof, BodyType.StaticBody, wallFixtureDef);
	        PhysicsFactory.createBoxBody(game.mPhysicsWorld, left, BodyType.StaticBody, wallFixtureDef);
	        PhysicsFactory.createBoxBody(game.mPhysicsWorld, right, BodyType.StaticBody, wallFixtureDef);
	        Sprite bg2 = new Sprite(x,y,game.mRes.bg2);
	        game.mScene.attachChild(bg2);
	      
	        bg2.setZIndex(100);
	        ground.setZIndex(0);
	        game.mScene.sortChildren();
	        
	        game.mScene.attachChild(ground);
	        game.mScene.attachChild(roof);
	        game.mScene.attachChild(left);
	        game.mScene.attachChild(right);
	        
	       
	        
	        
	}

}
