package com.deen812.game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import org.anddev.andengine.engine.handler.IUpdateHandler;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Imozg {

	private Game game;

	private ArrayList<Vector2> player_pos;
	private ArrayList<Vector2> enemy_pos;
	private ArrayList<Vector2> point_pos;
	private ArrayList<Ball> player_ball;
	private ArrayList<Ball> enemy_ball;

	private String TAG="Imozg";

	private ArrayList<Test> forTest;

	private float angle;

	private int index_enemy;

	private double shoot_angle;

	private float power =150;

	private double shoot_angle2;

	private int index_enemy2;


	public Imozg(Game game) {
	this.game = game;
	}
     
	class Test {
		public Test(float ugol2, float a, float b) {
			this.angel = ugol2;
			this.len_e_player = a;
			this.len_player_point =b;
		}
		public float angel = 0;
		public float len_e_player = 0;
		public float len_player_point = 0;
		
	}
	
	
	public void start() {//-----------------------------------------------------
	
		Log.v(TAG,"start()");
		player_pos = new ArrayList<Vector2>();
		enemy_pos = new ArrayList<Vector2>();
		point_pos = new ArrayList<Vector2>();
		
		forTest = new ArrayList<Test>();
		
		Iterator<Body> bodies = game.mPhysicsWorld.getBodies();
		 while(bodies.hasNext()){
            Body b = bodies.next();
            if(b.isActive() == false) continue;
            if(b.getUserData() instanceof Ball){
           	 
           	     Ball ball = (Ball)b.getUserData();
           	     //если  не активный значит пропускаем
           	   
           	     
           	     
           	     if(ball.player == game.mConst.PLAYER){
           	    	 player_pos.add(ball.body.getPosition());
           	      }else {
           	    	  if(ball.f_moved == false){
           	    	        enemy_pos.add(ball.body.getPosition()); 
           	    	  }
           	      }
            }
            
            if(b.getUserData() instanceof Point){
            	Point p = (Point)b.getUserData();
            	point_pos.add(p.box_body.getPosition()); 
            }
            
            }//while
		 
		 Log.v(TAG,"enemy_pos= "+enemy_pos.toString());
		 Log.v(TAG,"player_pos= "+player_pos.toString());   
		 Log.v(TAG,"point_pos="+point_pos.toString());   
		 analiz();
		
		 
	}//----------------------------------------------------------------------



	private void analiz() {
		Log.v(TAG,"analiz()");
		 angle = 91;
		 index_enemy=0;
		 boolean sel = false;
		 int target=1;
		   for(int i = 0 ; i < enemy_pos.size(); i++){
			 //  index_enemy = i;  
			   Vector2 en  = enemy_pos.get(i);
			   
			       for(int j = 0 ; j < player_pos.size(); j++){
			    	   Vector2 pl  = player_pos.get(j);
			    	   
			    	       for(int k = 0 ; k < point_pos.size(); k++){
				    	   Vector2 point  = point_pos.get(k);
				    	  // en.x = en.x*30;
				    	 //  en.y = en.y*30;
				    	 //  pl.x = pl.x*30;
				    	 //  pl.y = pl.y*30;
				    	//   point.y =point.y*30;
				    	 //  point.x = point.x*30;
				    	   
				    	      float a =(float) Math.sqrt( (en.x - pl.x)* (en.x - pl.x)  +  (en.y - pl.y)* (en.y - pl.y)  );
				    	      float b =  (float) Math.sqrt( (point.x - pl.x)* (point.x - pl.x)  +  (pl.y - point.y)* (pl.y - point.y)  );            
				    	      float c =  (float) Math.sqrt( (point.x - en.x)* (point.x - en.x)  +  (point.y - en.y)* (point.y - en.y)  );
				    	      
				    	      power = (a + b)/2*30;
				    	      float ugol  = (a*a + b*b - c*c)/(2*a*b);
				    	      float ugol2 =(float) (+Math.acos(ugol)*180/Math.PI);
				    	      Log.v(TAG,"enemy:"+i+" pl:"+j+"  cos ugol="+ugol+"  norm ="+Math.acos(ugol) + " u2="+ ugol2+ " a="+a + " b="+b + " c="+c);
				    	    
				    	      //провер€ем расположение +1 или -1
				    	      if(  (point.x > pl.x ) && (point.y > pl.y) ) target=1;
				    	      if(  (point.x > pl.x ) && (point.y < pl.y) ) target=-1;
				    	      
				    	      if(  (point.x < pl.x ) && (point.y > pl.y) ) target=-1;
				    	      if(  (point.x < pl.x ) && (point.y < pl.y) ) target=1;
				    	      
				    	    
				    	      if(ugol2 > 90){
				    	    	  
				    	    	  if(ugol2 > angle){
				    	    		  float coorect_angle = 0;
				    	    		  if(  ugol2 >90 &&ugol2 < 120 ) coorect_angle = 6;
				    	    		  if(  ugol2 >120 &&ugol2 < 135 ) coorect_angle = 5;
				    	    		  if(  ugol2 >135 &&ugol2 < 170 ) coorect_angle = 2;
				    	    		  if(  ugol2 >170 &&ugol2 < 181 ) coorect_angle = 0;
				    	    		  //gпровер€ем есть ли лунка между
				    	    		  
				    	    		 // coorect_angle -=power/30;
				    	    		  if(power > 400)coorect_angle/=2;
				    	    		  coorect_angle = (float) (target*coorect_angle*Math.PI/180);
				    	    		  // коррекционный угол.
				    	    		  angle = ugol2;
				    	    		  //пор€дковый номер в мире
				    	    		  index_enemy = i;
				    	    		  sel = true;
				    	    		  //¬ычисл€ем угол атаки
				    	    		  shoot_angle = Math.atan2( (pl.y - en.y),(pl.x - en.x)  );
				    	    		  shoot_angle+=coorect_angle;
				    	    	  }else {
				    	    		  index_enemy2 = i;
				    	    		  shoot_angle2 =ugol2; 
				    	    	  }
				    	    	//  forTest.add( new Test(ugol2, a,b));
				    	    	  
				    	      }
				    	   
				         
				          }
			    	   
			    	   
			         
			       }
			
		   }
		   if(sel == false){
			   index_enemy=0;
			   
			   Vector2 en  = enemy_pos.get(index_enemy);
			   Vector2 pl  = player_pos.get(index_enemy);
			   Vector2 point  = new Vector2(0,0);
			   point.x =  pl.x;
			   point.y = en.y;
			   float a =(float) Math.sqrt( (en.x - pl.x)* (en.x - pl.x)  +  (en.y - pl.y)* (en.y - pl.y)  );
	    	      float b =  (float) Math.sqrt( (point.x - pl.x)* (point.x - pl.x)  +  (pl.y - point.y)* (pl.y - point.y)  );            
	    	      float c =  (float) Math.sqrt( (point.x - en.x)* (point.x - en.x)  +  (point.y - en.y)* (point.y - en.y)  );
	    	      
	    	      power = 500;
	    	      
	    	      float ugol  = (a*a + c*c - b*b)/(2*a*c);
	    	      float ugol2 =(float) (+Math.acos(ugol)*180/Math.PI);
	    	      shoot_angle = Math.atan2( (pl.y - en.y),(pl.x - en.x)  );
	    		 // shoot_angle+=coorect_angle;
			   
		   }
		   
		   hook();
		
	}//analiz()



	private void hook() {
		Log.v(TAG,"hook()");
		Iterator<Body> bodies = game.mPhysicsWorld.getBodies();
		int i=0;
		 while(bodies.hasNext()){
	            Body b = bodies.next();
	            if(b.isActive() == false) continue;
	            if(b.getUserData() instanceof Ball){
	           	 
	           	     final Ball ball = (Ball)b.getUserData();
	           	     if(ball.player == game.mConst.ENEMY){
	           	      if(ball.f_moved == false){
	           	    	  
         	    	       if(index_enemy == i){
         	    	    	  game.mScene.registerUpdateHandler(new IUpdateHandler() {  
         	    	 			@Override  
         	    	            public void onUpdate(float arg0) { 
         	    	 				   game.mScene.unregisterUpdateHandler(this);
         	    	 				   ball.fire(power ,  (float)shoot_angle);
         	    	 		         }  
         	    	          @Override  
         	    	           public void reset() {   }  
         	    	        });  
         	    	    	   
         	    	    	   
         	    	    	   break;
         	    	       }
         	    	       i++;
         	    	  }
	           	     }
	            }
	            
	           
	            }//while
		 
		
	}
	
	
	
	public void stop(){
		
	}

}
