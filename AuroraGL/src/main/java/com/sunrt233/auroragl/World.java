package com.sunrt233.auroragl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.sunrt233.auroragl.geometry.BaseGeometry;
import com.sunrt233.auroragl.math.Vector;
import com.sunrt233.auroragl.tool.Camera;
import com.sunrt233.auroragl.tool.RGBColor;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class World {

    private Renderer renderer;
    private GLSurfaceView GLV;
    private ArrayList<BaseGeometry> geometries;
    private RGBColor skyColor = new RGBColor(0,0,0);
    private Camera worldCam;
    private float[]	mMVPMatrix = new float[16];
    float[]	mProjMatrix = new float[16];
    float[]	mVMatrix = new float[16];

    public boolean isInited = false;

    float ratio;

    public World(GLSurfaceView glv, Renderer renderer) {

        geometries = new ArrayList<BaseGeometry>();
        GLV = glv;
        this.renderer = renderer;
        worldCam = new Camera();
    }

    public void initWorld() {

        isInited = true;

        GLV.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {

                GLES20.glEnable(GLES20.GL_DEPTH_TEST);
                renderer.onWorldInit();
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                GLES20.glViewport(0, 0, width, height);

                //计算高宽比
                ratio = (float) width / height;
                // 在onDrawFrame()方法中，将投影矩阵应用到对象的坐标
                Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 3, 21);

                // 设置相机的位置(视图矩阵)
                Vector camp = worldCam.getCamPosition();
                Vector viewp = worldCam.getViewPointPosition();
                Vector up = worldCam.getCamUpVector();
//                Matrix.setLookAtM(mVMatrix, 0, viewp.x, viewp.y, viewp.z, camp.x, camp.y, camp.z, up.x, up.y, up.z);
                Matrix.setLookAtM(mVMatrix, 0, camp.x, camp.y, camp.z, viewp.x, viewp.y, viewp.z, up.x, up.y, up.z);
                Log.i("cam","" + camp.x + camp.y + camp.z);


                // 计算投影和视图变换

//                float[] tran = new float[16];
//                Matrix.setIdentityM(tran,0);
//                Matrix.setRotateM(tran,0, 1f, 0f,1f,0f);
//                //Matrix.translateM(tran,0,0f,0f,0f);
//                //Matrix.multiplyMM(mMVPMatrix,0,mVMatrix,0,tran,0);
//
//                Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, tran,0);
//                Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
                renderer.onViewChanged(width, height);
            }

            @Override
            public void onDrawFrame(GL10 gl) {
                // 在onDrawFrame()方法中，将投影矩阵应用到对象的坐标
                Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 3, 21);

                // 设置相机的位置(视图矩阵)
                Vector camp = worldCam.getCamPosition();
                Vector viewp = worldCam.getViewPointPosition();
                Vector up = worldCam.getCamUpVector();
//                Matrix.setLookAtM(mVMatrix, 0, viewp.x, viewp.y, viewp.z, camp.x, camp.y, camp.z, up.x, up.y, up.z);
                Matrix.setLookAtM(mVMatrix, 0, camp.x, camp.y, camp.z, viewp.x, viewp.y, viewp.z, up.x, up.y, up.z);
                Log.i("cam","" + camp.x + camp.y + camp.z);

                GLES20.glClearColor(skyColor.getR(), skyColor.getG(), skyColor.getB(), skyColor.getA());
                GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
                renderer.onDrawFrame();
                drawTheWorld(mMVPMatrix);

            }

        });



    }

    public void addGeometry(BaseGeometry geometry) {
        geometry.initGLSL();
        geometries.add(geometry);
    }

    public void setSkyColor(RGBColor color) {
        skyColor = RGBColor.toGPURGB(color);
    }

    public void drawTheWorld(float[] MVPMatrix) {

        for(BaseGeometry geometry : geometries) {
            geometry.drawSelf(mProjMatrix, mVMatrix);
        }

    }

    public Camera getWorldCam() {
        return worldCam;
    }

    public interface Renderer {

        public void onWorldInit();
        public void onViewChanged(int width, int height);
        public void onDrawFrame();

    }

}
