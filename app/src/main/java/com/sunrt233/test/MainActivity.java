package com.sunrt233.test;

import android.app.*;
import android.opengl.*;
import android.os.*;
import com.sunrt233.auroragl.*;
import com.sunrt233.auroragl.tool.*;
import com.sunrt233.auroragl.geometry.*;
import com.sunrt233.auroragl.vertex.*;
import android.renderscript.*;
import android.widget.*;

public class MainActivity extends Activity 
{
	private GLSurfaceView glv;
	private World world;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getActionBar().hide();
		glv = (GLSurfaceView) findViewById(R.id.activity_mainGLV);
		glv.setEGLContextClientVersion(2);
		world = new World(glv,new Re());
		world.initWorld();
        
		
		
		Matrix4f n = new Matrix4f();
		n.translate(1,0,0);
		n.rotate(90,0,1,0);
		Toast.makeText(getApplicationContext(),n.toString(),Toast.LENGTH_LONG).show();
		
    }

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		//glv.onResume();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		//glv.onPause();
	}
	
	class Re implements World.Renderer
	{
		BaseGeometry a;
		float angle = 0f;

		@Override
		public void onWorldInit()
		{
			// TODO: Implement this method
			world.setSkyColor(new RGBColor(100f,181f,264f));
			world.getWorldCam().setPosition(0f,0f,-1f);
			world.getWorldCam().setViewPoint(0f,0f,0f,0f,1f,0f);
			a = new BaseGeometry(new BaseVertex());
			a.translate(0f,1f,2f);
			world.addGeometry(a);
			
		}

		@Override
		public void onViewChanged(int width, int height)
		{
			// TODO: Implement this method
		}

		@Override
		public void onDrawFrame()
		{
			// TODO: Implement this method
			a.rotate(angle,0f,1f,0f);
			angle++;
		}
		
	}
	
}
