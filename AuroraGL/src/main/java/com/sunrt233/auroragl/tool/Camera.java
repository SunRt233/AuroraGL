package com.sunrt233.auroragl.tool;

import com.sunrt233.auroragl.math.Vector;

public class Camera {

    private Vector camPosition;
    private Vector viewPointPosition;
    private Vector camUpVector;

    public Camera() {
        camPosition = new Vector(0,0f,0f);
        viewPointPosition = new Vector(0f,0f,5f);
        camUpVector = new Vector(0f,1f,0f);
    }

    public void setPosition(float x, float y, float z) {
        camPosition = new Vector(x,y,z);
    }

    public void setPosition(Vector position) {
        camPosition = position;
    }

    public void setViewPoint(float x, float y, float z, float upX, float upY, float upZ) {
        viewPointPosition = new Vector(x,y,z);
        camUpVector = new Vector(upX,upY,upZ);
    }

    public void setViewPoint(Vector viewPointPosition, Vector camUpVector) {
        this.viewPointPosition = viewPointPosition;
        this.camUpVector = camUpVector;
    }

    public Vector getCamPosition() {
        return camPosition;
    }

    public Vector getCamUpVector() {
        return camUpVector;
    }

    public Vector getViewPointPosition() {
        return viewPointPosition;
    }
}
