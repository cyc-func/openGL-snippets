package com.cycfunc.openglcircle;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLSurface extends Activity
{
  private GLSurfaceView glSurfaceView;
  private boolean rendererSet = false;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    glSurfaceView = new GLSurfaceView(this);
    glSurfaceView.setEGLContextClientVersion(2);
    glSurfaceView.setRenderer(new GLRenderer());
    // glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    rendererSet = true;

    setContentView(glSurfaceView);
  }

  @Override
  protected void onPause()
  {
    super.onPause();

    if(rendererSet)
    {
      glSurfaceView.onPause();
    }
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    if(rendererSet)
    {
      glSurfaceView.onResume();
    }
  }
}
