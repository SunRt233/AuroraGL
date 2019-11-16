package com.sunrt233.auroragl;

import android.opengl.*;
import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import javax.microedition.khronos.egl.EGLConfig;
import com.sunrt233.auroragl.geometry.*;
import com.sunrt233.auroragl.vertex.*;

public class MyRenderer implements GLSurfaceView.Renderer
{

	float[] mProjMatrix = new float[16];
	float[]	mVMatrix = new float[16];
	float[]	mMVPMatrix = new float[16];

	BaseGeometry b;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// TODO: Implement this method
		//GLES20.glClearColor(100f,181f,246f,0f);
		b = new BaseGeometry(new BaseVertex());
		//GLES20.glCullFace(GLES20.GL_BACK);

		//GLES20.glEnable(GLES20.GL_CULL_FACE);
		//GLES20.glCullFace(GLES20.GL_BACK);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		// TODO: Implement this method
		GLES20.glViewport(0, 0, width, height);

		//计算高宽比
		float ratio = (float) width / height;

		// 在onDrawFrame()方法中，将投影矩阵应用到对象的坐标
		Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

		// 设置相机的位置(视图矩阵)
		Matrix.setLookAtM(mVMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

		// 计算投影和视图变换
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mVMatrix, 0);
	}

	@Override
	public void onDrawFrame(GL10 gl)
	{
		// TODO: Implement this method
		GLES20.glClearColor(100f * 1f / 255f,181f * 1f / 255f,246f * 1f / 100f,0f);
		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);




		//b.drawSelf(mMVPMatrix);
	}
	
}
