package com.sunrt233.auroragl.vertex;

public class BaseVertex {

    public static float[] getVertex() {

        float[] vertex = {
                -0.5f, 0.5f, 0.5f, // 0, Top Left
                -0.5f, -0.5f, 0.5f, // 1, Bottom Left
                0.5f, -0.5f, 0.5f, // 2, Bottom Right
                //正面1
                -0.5f, 0.5f, 0.5f, // 0, Top Left
                0.5f, -0.5f, 0.5f, // 2, Bottom Right
                0.5f, 0.5f, 0.5f, // 3, Top Right

                //背面0
                0.5f, 0.5f, -0.5f, // 0, Top Left
                0.5f, -0.5f, -0.5f, // 1, Bottom Left
                -0.5f, -0.5f, -0.5f, // 2, Bottom Right
                //背面1
                0.5f, 0.5f, -0.5f, // 0, Top Left
                -0.5f, -0.5f, -0.5f, // 2, Bottom Right
                -0.5f, 0.5f, -0.5f, // 3, Top Right

                //左面0
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                //左面1
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                //右面0
                0.5f,0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,-0.5f,-0.5f,

                //右面1
                0.5f,0.5f,0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                //上面0
                -0.5f,0.5f,-0.5f,
                -0.5f,0.5f,0.5f,
                0.5f,0.5f,0.5f,

                //上面1
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,
                0.5f,0.5f,-0.5f,

                //底面0
                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,

                //底面1
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f
        };




        return vertex;

    }

}
