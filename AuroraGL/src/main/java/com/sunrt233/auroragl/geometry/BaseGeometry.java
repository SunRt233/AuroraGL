package com.sunrt233.auroragl.geometry;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.sunrt233.auroragl.math.Vector;
import com.sunrt233.auroragl.vertex.BaseVertex;

import java.nio.*;

public class BaseGeometry {

    private float angle = 0f;
    private FloatBuffer vertexBuffer,colorBuffer;
    private final String vertexShaderCode =
			"uniform mat4 u_MVPMatrix;" +

            "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = u_MVPMatrix*vPosition;" +
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    private int mProgram;
    private int mPositionHandle,mColorHandle,mMVPMatrixHandle;

    private Vector position,rotateUp;

    float[] color = {
            1f, 1f, 1f, 0f,
            1f, 1f, 1f, 0f,
            1f, 1f, 1f, 0f};
    public BaseVertex baseVertex;
    float colors[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    public BaseGeometry(BaseVertex vertex) {

        baseVertex = vertex;

        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (每个浮点数占用4个字节)
                vertex.getVertex().length * 4);
        // 设置使用设备硬件的原生字节序
        bb.order(ByteOrder.nativeOrder());

        // 从ByteBuffer中创建一个浮点缓冲区
        vertexBuffer = bb.asFloatBuffer();
        // 把坐标都添加到FloatBuffer中
        vertexBuffer.put(vertex.getVertex());
        // 设置buffer从第一个坐标开始读
        vertexBuffer.position(0);

        ByteBuffer cb = ByteBuffer.allocateDirect(color.length * 4);
        colorBuffer = cb.asFloatBuffer();
        colorBuffer.put(color);
        colorBuffer.position(0);

        position = new Vector(0,0,0);
        rotateUp = new Vector(0,1,0);

    }

    public void initGLSL() {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();             // 创建空的OpenGL ES Program
        GLES20.glAttachShader(mProgram, vertexShader);   // 将vertex shader添加到program
        GLES20.glAttachShader(mProgram, fragmentShader); // 将fragment shader添加到program
        GLES20.glLinkProgram(mProgram);                  // 创建可执行的 OpenGL ES program

    }

    public void drawSelf(float[] ProjMatrix, float[] VMatrix) {

        Log.i("Geometry","drawing");

        float[] mMVPMatrix = new float[16];
        float[] tran = new float[16];
        Matrix.setIdentityM(tran,0);
        Matrix.translateM(tran,0,position.x,position.y,position.z);
        Matrix.rotateM(tran,0, angle, rotateUp.x,rotateUp.y,rotateUp.z);

        Matrix.multiplyMM(mMVPMatrix, 0, VMatrix, 0, tran,0);
        Matrix.multiplyMM(mMVPMatrix, 0, ProjMatrix, 0, mMVPMatrix, 0);

        GLES20.glUseProgram(mProgram);

        //链接handle
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix");
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        //int mTranMatrix = GLES20.glGetUniformLocation(mProgram, "tranMatrix");

        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
       // GLES20.glUniformMatrix4fv(mTranMatrix,1,false, tran ,0);

        //启用顶点数组handle 加载顶点数据
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, vertexBuffer);//stride:坐标维度数乘数据大小

        //启用颜色数组 设置颜色
        //GLES20.glEnableVertexAttribArray(mColorHandle);
       // GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false, 4 * 4, colorBuffer);
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES , 0, baseVertex.getVertex().length / 3);

        GLES20.glDisableVertexAttribArray(mPositionHandle);


    }

    public void translate(float x,float y,float z){
        position = new Vector(x,y,z);
    }

    public void translate(Vector p) {
        position = p;
    }

    public void rotate(float angle, float upx, float upy,float upz) {
        this.angle = angle;
        rotateUp = new Vector(upx,upy,upz);
    }

    public void rotate(float angle, Vector rotateUp) {
        this.angle = angle;
        this.rotateUp = rotateUp;
    }


    public static int loadShader(int type, String shaderCode) {

        // 创建一个vertex shader类型(GLES20.GL_VERTEX_SHADER)
        // 或一个fragment shader类型(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // 将源码添加到shader并编译它
        try {
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);
        }catch (Throwable e){
            Log.e("着色器编译","错误");
        }

        return shader;
    }

}
