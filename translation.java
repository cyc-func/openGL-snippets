/**
 * Code for renderer which can perform translation (and rotation)
 */


package com.cycfunc.openglcircle;


import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


class GLRenderer implements GLSurfaceView.Renderer
{
  private final int POSITION_COMPONENT_COUNT = 2;
  private final int BYTES_PER_FLOAT = 4;

  private static final String U_COLOR = "u_Color";
  private static final String A_POSITION = "a_Position";
  private static final String U_MVPMATRIX = "u_MVPMatrix";
  private int uColorLocation;
  private int aPositionLocation;
  private int uMVPMatrixLocation;

  private final FloatBuffer vertexData;

  private final float[] mMVPMatrix = new float[16];

  private int program;

  GLRenderer()
  {
    final float[] barVertices = {
        -0.5f, -0.5f,
        0.5f, 0.5f,
        -0.5f, 0.5f,

        -0.5f, -0.5f,
        0.5f, -0.5f,
        0.5f, 0.5f
    };

    vertexData = ByteBuffer
        .allocateDirect(barVertices.length * BYTES_PER_FLOAT)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer();
    vertexData.put(barVertices);
  }

  @Override
  public void onSurfaceCreated(GL10 glUnused, EGLConfig config)
  {
    GLES20.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

    program = (new ShaderHelper()).linkProgram();   // validate the program after this. use the same instance
    GLES20.glUseProgram(program);

    uColorLocation = GLES20.glGetUniformLocation(program, U_COLOR);
    aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
    uMVPMatrixLocation = GLES20.glGetUniformLocation(program, U_MVPMATRIX);
  }

  @Override
  public void onSurfaceChanged(GL10 glUnused, int width, int height)
  {
    GLES20.glViewport(0, 0, width, height);
  }

  @Override
  public void onDrawFrame(GL10 glUnused)
  {
    GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

    vertexData.position(0);
    GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT,
        false, 0, vertexData);

    GLES20.glEnableVertexAttribArray(aPositionLocation);

    Matrix.setIdentityM(mMVPMatrix, 0);
    Matrix.translateM(mMVPMatrix, 0, 0.5f, 0f, 0f);

    GLES20.glUniformMatrix4fv(uMVPMatrixLocation, 1, false, mMVPMatrix, 0);

    GLES20.glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
    GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
  }
}
