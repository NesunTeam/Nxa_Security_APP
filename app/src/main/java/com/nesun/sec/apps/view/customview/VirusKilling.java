package com.nesun.sec.apps.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */
public class VirusKilling extends View{

		private float mWidth;
		private float mHeight;
		private Paint mPaint, mPaint2, mPaint3, mPaint4, mLinePaint;
		private RectF mRectF;
		private float startAngle = 360;
		private float radius1, radius2, radius3, radius4;
		private Path path;

		public VirusKilling(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			initData();
		}

		public VirusKilling(Context context, AttributeSet attrs) {
			super(context, attrs);
			initData();
		}

		public VirusKilling(Context context) {
			super(context);
			initData();
		}

		// 初始化画笔操作
		private void initData() {
			mPaint = new Paint();
			mPaint.setStrokeWidth(1);
			mPaint.setAntiAlias(true);
			mPaint.setStyle(Style.STROKE);
			mPaint.setColor(Color.parseColor("#ffffff"));

			mPaint2 = new Paint();
			mPaint2.setStrokeWidth(5);
			mPaint2.setColor(Color.parseColor("#00ff00"));

			mPaint3 = new Paint();
			mPaint3.setStrokeWidth(3);
			mPaint3.setAntiAlias(true);
			mPaint3.setStyle(Style.STROKE);
			mPaint3.setColor(Color.parseColor("#ffffff"));

			mPaint4 = new Paint();
			mPaint4.setStrokeWidth(2);
			mPaint4.setAntiAlias(true);
			mPaint4.setStyle(Style.STROKE);
			mPaint4.setColor(Color.parseColor("#ffffff"));

			mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mLinePaint.setStrokeWidth(1);
			mLinePaint.setStyle(Paint.Style.STROKE);
			mLinePaint.setAntiAlias(true);
			mLinePaint.setColor(Color.parseColor("#ffffff"));

			path = new Path();

		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			super.onSizeChanged(w, h, oldw, oldh);
			mWidth = getWidth();
			mHeight = getHeight();
			mRectF = new RectF((float) (mWidth * 0.1), (float) (mWidth * 0.1),
					(float) (mWidth * 0.9), (float) (mWidth * 0.9));
			// 绘制渐变效果
			LinearGradient gradient = new LinearGradient((float) (mWidth * 0.3),
					(float) (mWidth * 0.9), (float) (mWidth * 0.1),
					(float) (mWidth * 0.5), new int[] {
					Color.parseColor("#458EFD"), Color.GREEN,
					Color.parseColor("#458EFD"), Color.WHITE,
					Color.parseColor("#458EFD") }, null,
					Shader.TileMode.CLAMP);
			mPaint2.setShader(gradient);
			// 四个圆的半径
			radius1 = (float) (mWidth * 0.4);
			radius2 = (float) (mWidth * 0.3);
			radius3 = (float) (mWidth * 0.2);
			radius4 = (float) (mWidth * 0.1);

		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvasArc(canvas);
			canvasArc2(canvas);
			canvasCircle(canvas);
			canvasLine(canvas);

		}

		// 绘制虚线
		private void canvasLine(Canvas canvas) {
			for (int i = 0; i < 8; i++) {
				path.moveTo(mWidth / 2, mHeight / 2);
				path.lineTo(radius1, radius4);
				PathEffect effects = new DashPathEffect(new float[] {
						(float) (mWidth * 0.005), (float) (mWidth * 0.02),
						(float) (mWidth * 0.005), (float) (mWidth * 0.02) }, 0);
				mLinePaint.setPathEffect(effects);
				canvas.drawPath(path, mLinePaint);
				canvas.rotate(45, mWidth / 2, mHeight / 2);
			}
		}

		// 绘制四个圆
		private void canvasCircle(Canvas canvas) {
			canvas.drawCircle(mWidth / 2, mHeight / 2, radius1, mPaint3);
			canvas.drawCircle(mWidth / 2, mHeight / 2, radius2, mPaint);
			canvas.drawCircle(mWidth / 2, mHeight / 2, radius3, mPaint);
			canvas.drawCircle(mWidth / 2, mHeight / 2, radius4, mPaint4);
		}

		// 绘制旋转的扇形
		private void canvasArc2(Canvas canvas) {
			canvas.drawArc(mRectF, startAngle, 1, true, mPaint3);
		}

		// 绘制旋转的扇形
		private void canvasArc(Canvas canvas) {
			canvas.drawArc(mRectF, startAngle, 60, true, mPaint2);
		}

/**
 * 开启一个线程
 *
 * @author Administrator
 *
 */
class MyThread extends Thread {
	@Override
	public void run() {
		while (true) {
			if (running) {
				SystemClock.sleep(10);
				handler.sendEmptyMessage(2);
			}
		}

	}
}

	private boolean running = true;

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			synchronized (this) {
				if (startAngle < 1) {
					startAngle = 360;
				} else {
					startAngle--;
					invalidate();
				}
			}
		};
	};

	MyThread thread;

	// 开启动画
	public void setStartAngle() {
		thread = new MyThread();
		thread.start();
	}

	// 重新开启动画
	public void startAnge() {
		running = true;
	}

	// 暂停动画
	public void stopAnge() {
		running = false;
	}

	// 是否在运动
	public boolean isRunning() {
		return running;
	}

}
