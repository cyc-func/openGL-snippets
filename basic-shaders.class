package com.cycfunc.openglcircle;


import android.opengl.GLES20;

class ShaderHelper
{
  private final String vertexShader =
      "attribute vec4 a_Position;   \n" +
      "void main()                  \n" +
      "{                            \n" +
      "  gl_Position = a_Position;  \n" +
      "}                            \n";

  private final String fragmentShader =
      "precision mediump float;     \n" +
      "uniform vec4 u_Color;        \n" +
      "void main()                  \n" +
      "{                            \n" +
      "  gl_FragColor = u_Color;    \n" +
      "}                            \n";

  private int compileVertexShader()
  {
    return compileShader(GLES20.GL_VERTEX_SHADER, vertexShader);
  }

  private int compileFragmentShader()
  {
    return compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);
  }

  private int compileShader(int type, String shaderCode)
  {
    final int shaderObjectId = GLES20.glCreateShader(type);

    GLES20.glShaderSource(shaderObjectId, shaderCode);
    GLES20.glCompileShader(shaderObjectId);

    return shaderObjectId;
  }

  int linkProgram()
  {
    final int programObjectId = GLES20.glCreateProgram();

    GLES20.glAttachShader(programObjectId, compileVertexShader());
    GLES20.glAttachShader(programObjectId, compileFragmentShader());

    GLES20.glLinkProgram(programObjectId);

    return programObjectId;
  }

  /**
   * Validates an OpenGL program. Should only be called when developing the
   * application.
   */
  /*public static boolean validateProgram(int programObjectId) {
    GLES20.glValidateProgram(programObjectId);

    final int[] validateStatus = new int[1];
    GLES20.glGetProgramiv(programObjectId, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);
    Log.v("tag", "Results of validating program: " + validateStatus[0]
        + "\nLog:" + GLES20.glGetProgramInfoLog(programObjectId));

    return validateStatus[0] != 0;
  }*/
}
