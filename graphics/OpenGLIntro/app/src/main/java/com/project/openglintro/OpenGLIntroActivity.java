package com.project.openglintro;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.project.openglintro.shapes.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLIntroActivity extends AppCompatActivity {

    private GLSurfaceView glSurfaceView;

    public static final class InnerGLSurfaceView extends GLSurfaceView {
        private InnerGLRenderer glRenderer;
        public InnerGLSurfaceView(Context context) {
            super(context);
            // Criar contexto OPENGL ES 2.0
            setEGLContextClientVersion(2);
            glRenderer = new InnerGLRenderer();
            setRenderer(glRenderer);
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }

        public InnerGLSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
        private float mPreviousX;
        private float mPreviousY;

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            // MotionEvent reports input details from the touch screen
            // and other input controls. In this case, you are only
            // interested in events where the touch position changed.
            float x = e.getX();
            float y = e.getY();
            switch (e.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    float dx = x - mPreviousX;
                    float dy = y - mPreviousY;
                    // reverse direction of rotation above the mid-line
                    if (y > getHeight() / 2) {
                        dx = dx * -1 ;
                    }
                    // reverse direction of rotation to left of the mid-line
                    if (x < getWidth() / 2) {
                        dy = dy * -1 ;
                    }
                    glRenderer.setAngle(
                            glRenderer.getAngle() +
                                    ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                    requestRender();
            }

            mPreviousX = x;
            mPreviousY = y;
            return true;
        }
    }

    public static final class InnerGLRenderer implements GLSurfaceView.Renderer {
        public InnerGLRenderer() {}

        private static final String TAG = "InnerGLRenderer";

        private Square mSquare;

        // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
        private final float[] mMVPMatrix = new float[16];
        private final float[] mProjectionMatrix = new float[16];
        private final float[] mViewMatrix = new float[16];
        private final float[] mRotationMatrix = new float[16];

        private float mAngle;

        @Override
        public void onSurfaceCreated(GL10 unused, EGLConfig config) {
            // Set the background frame color
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            mSquare = new Square();
        }

        @Override
        public void onDrawFrame(GL10 unused) {
            float[] scratch = new float[16];
            // Draw background color
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            // Set the camera position (View matrix)
            Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
            // Calculate the projection and view transformation
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
            // Draw square
            mSquare.draw(mMVPMatrix);
            // Create a rotation for the triangle
        }

        @Override
        public void onSurfaceChanged(GL10 unused, int width, int height) {
            // Adjust the viewport based on geometry changes,
            // such as screen rotation
            GLES20.glViewport(0, 0, width, height);
            float ratio = (float) width / height;
            // this projection matrix is applied to object coordinates
            // in the onDrawFrame() method
            Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

        }

        /**
         * Utility method for compiling a OpenGL shader.
         *
         * <p><strong>Note:</strong> When developing shaders, use the checkGlError()
         * method to debug shader coding errors.</p>
         *
         * @param type - Vertex or fragment shader type.
         * @param shaderCode - String containing the shader code.
         * @return - Returns an id for the shader.
         */
        public static int loadShader(int type, String shaderCode){
            // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
            int shader = GLES20.glCreateShader(type);
            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);
            return shader;
        }

        /**
         * Utility method for debugging OpenGL calls. Provide the name of the call
         * just after making it:
         *
         * <pre>
         * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
         * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
         *
         * If the operation is not successful, the check throws an error.
         *
         * @param glOperation - Name of the OpenGL call to check.
         */
        public static void checkGlError(String glOperation) {
            int error;
            while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
                Log.e(TAG, glOperation + ": glError " + error);
                throw new RuntimeException(glOperation + ": glError " + error);
            }
        }

        /**
         * Returns the rotation angle of the triangle shape (mTriangle).
         *
         * @return - A float representing the rotation angle.
         */
        public float getAngle() {
            return mAngle;
        }

        /**
         * Sets the rotation angle of the triangle shape (mTriangle).
         */
        public void setAngle(float angle) {
            mAngle = angle;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new OpenGLIntroActivity.InnerGLSurfaceView(this);
        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }
}
