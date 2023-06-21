package com.example.canvaschar2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();
    Paint mGaris = new Paint();

    private Paint mPaint = new Paint();

    private ConstraintLayout my_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));
        my_layout = findViewById(R.id.layout);
        mGaris.setColor(getResources().getColor(R.color.black));
        mGaris.setStrokeWidth(17f);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();
        float centerX = vWidth / 2f;
        float centerY = vHeight / 2f;
        float radiusX = vWidth / 3f;
        float radiusY = vHeight / 4f;

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        mImgView.setVisibility(View.VISIBLE);


        my_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                effect(view);
                mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
                mCanvas = new Canvas(mBitmap);
                mCanvas.drawColor(mColorBackground);
                drawHead();
                drawRightEye();
                drawLeftEye();
                drawEyeConnector();
                mCanvas.rotate(360, centerX, centerY);
            }


            private void drawEyeConnector() {
                int vWidth = mImgView.getWidth();
                mCanvas.drawLine(650, 750, 400, 750, mGaris);

            }

            private void drawLeftEye() {
                mCanvas.drawCircle(650, 750, 40, mCirclePaint);
            }

            private void drawRightEye() {
                mCanvas.drawCircle(400, 750, 40, mCirclePaint);
            }

            private void drawHead() {

                mCanvas.drawOval(250, 925, 800, 575, mHeadPaint);
            }

            public void effect(View view) {
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mImgView, "alpha", 0f, 1f);
                alphaAnimator.setDuration(1000);

                ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(mImgView, "rotationY", 180);
                rotationAnimator.setDuration(1000);

                ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(mImgView, "alpha", 1f, 0f);
                alphaAnimator2.setDuration(1000);
                alphaAnimator2.start();

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(alphaAnimator, rotationAnimator, alphaAnimator2);
                animatorSet.start();
            }
        });

    }
}