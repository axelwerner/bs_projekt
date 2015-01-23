package example.axel.bs_projekt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
<<<<<<< Updated upstream
=======
import android.widget.TextView;
>>>>>>> Stashed changes

/**
 * Created by luke on 29.11.14.
 */
public class GameView extends SurfaceView implements Runnable, View.OnTouchListener, SensorEventListener {

    private Thread mThread;
    private boolean runFlag = false;
    private SurfaceHolder sHolder;
    private int displayWidth;
    private int displayHeight;
    private Guy guy;
    private Brick brick;
    private GameEngine gameEngine;
    private Bitmap back;
    private CreateRandom random;
    private int touchY;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // get Surface Holder
        sHolder = getHolder();

        // get Display dimensions
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        displayWidth = size.x;
        displayHeight = size.y;

        // get Brick object
        brick = new Brick();

        // get Guy object
        guy = new Guy();

        // get GameEngine object
        gameEngine = new GameEngine();

        // initialize GameEngine
        gameEngine.GameEngine(random, guy, brick);

        // initialize Brick
        brick.Brick(random, displayWidth, displayHeight);

        // initialize Brick Start Position
        brick.intBrick(getResources());

        // initialize Guy Start Position
        guy.intGuy(brick.getBrickY(0), brick.getBrickX(0), getResources());

        // Background Bitmap
        back = BitmapFactory.decodeResource(getResources(), R.drawable.blue);

        // initialize sensor
        this.touchY = guy.getGuyY();
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
    }

    public void resume(){

        // get net thread
        mThread = new Thread(this);

        // set runFlag to start painting
        runFlag = true;

        // start painting
        mThread.start();
    }

    public void pause(){

        // remove runFlag to stop painting
        runFlag = false;
    }

    @Override
    public void run() {

        // run wile runFlag == true
        while (runFlag){

            // check if surface holds a valid object
            if(!sHolder.getSurface().isValid())
                continue;

            // Get canvas object
            Canvas canvas = sHolder.lockCanvas();

            // check jump
            gameEngine.jump();

            // move direction
            gameEngine.directionMove(touchY);

            // reorganize bricks
            gameEngine.reorganizeBrick(displayHeight);

            // Draw a Background
            canvas.drawBitmap(back, 0, 0, new Paint());

            // Draw bricks
            for (int a = 0 ; a<10; a++){
                canvas.drawBitmap(brick.getBrick(a), brick.getBrickY(a), brick.getBrickX(a), new Paint());
            }

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
            // Draw guy
            canvas.drawBitmap(guy.getGuy(), guy.getGuyY(), guy.getGuyX(), new Paint());

            // unlock canvas and post it
            sHolder.unlockCanvasAndPost(canvas);

        }

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch(motionEvent.getAction()){

            case MotionEvent.ACTION_DOWN:
                this.touchY = (int) motionEvent.getX();
        }

        return false;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public int getTouchY() {
        return touchY;
    }
}
