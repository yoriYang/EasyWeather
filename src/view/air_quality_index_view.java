package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class air_quality_index_view extends View {
	private Paint paint;
	private int pm25n;
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		invalidate();
		requestLayout();
	}

	public int getPm25n() {
		return pm25n;
	}

	public void setPm25n(int pm25n) {
		this.pm25n = pm25n;
		invalidate();
		requestLayout();
	}

	/**
	 * @param context
	 */
	public air_quality_index_view(Context context) {
		super(context);
		this.paint = new Paint();
		this.paint.setAntiAlias(true); // 消除锯齿
		this.paint.setStyle(Paint.Style.STROKE); // 绘制空心圆
	}

	public air_quality_index_view(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.paint = new Paint();
		this.paint.setAntiAlias(true); // 消除锯齿
		this.paint.setStyle(Paint.Style.STROKE); // 绘制空心圆

	}

	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		int centerX = getWidth() / 2;
		int centerY = (int) (getHeight() * 0.8);
		// int innerCircle = dip2px(context, centerY / 2); // 设置内圆半径
		// int ringWidth = dip2px(context, 15); // 设置圆环宽度

		// 绘制内圆
		// this.paint.setARGB(155, 167, 190, 206);
		// this.paint.setStrokeWidth(2);
		// canvas.drawCircle(centerX,centerY, centerY/2, this.paint);

		// 绘制圆环
		// this.paint.setARGB(255, 212 ,225, 233);
		// this.paint.setStrokeWidth(ringWidth);
		// canvas.drawCircle(centerX,centerY, centerY/2+ringWidth/2,
		// this.paint);
		// canvas.drawCircle(cx, cy, radius, paint);

		// 绘制外圆
		this.paint.setARGB(255, 208, 208, 208);
		this.paint.setStrokeWidth(5);
		paint.setStrokeCap(Paint.Cap.ROUND);
		// canvas.drawCircle(center,center, innerCircle+ringWidth, this.paint);
		RectF oval = new RectF(); // RectF对象
		oval.left = getWidth() / 2 - centerY / 2; // 左边
		oval.top = getHeight() / 2 - centerY / 2; // 上边
		oval.right = getWidth() / 2 + centerY / 2; // 右边
		oval.bottom = getHeight() / 2 + centerY / 2; // 下边
		canvas.drawArc(oval, 145, 250, false, paint);

		this.paint.setStrokeWidth(5);
		paint.setStrokeCap(Paint.Cap.ROUND);
		if ((float) pm25n > 400) {
			pm25n = 400;
		}
		switch (level) {
		case 1:
			this.paint.setARGB(255, 34, 225, 0);
			break;
		case 2:
			this.paint.setARGB(255, 240, 240, 10);
			break;
		case 3:
			this.paint.setARGB(255, 255, 170, 0);
			break;
		case 4:
			this.paint.setARGB(255, 224, 90, 50);
			break;
		default:
			this.paint.setARGB(255, 224, 90, 50);
			break;
		}
		canvas.drawArc(oval, 145, (((float) pm25n / 400) * 250), false, paint);

		Paint TextPaint = new Paint();
		TextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		TextPaint.setTextAlign(Paint.Align.CENTER);
		String pm25String = String.valueOf(pm25n);
		switch (level) {
		case 1:
			TextPaint.setARGB(255, 34, 225, 0);
			break;
		case 2:
			TextPaint.setARGB(255, 240, 240, 10);
			break;
		case 3:
			TextPaint.setARGB(255, 255, 170, 0);
			break;
		case 4:
			TextPaint.setARGB(255, 224, 90, 50);
			break;
		default:
			TextPaint.setARGB(255, 224, 90, 50);
			break;
		}

		TextPaint.setTextSize(80);
		canvas.drawText(pm25String, getWidth() / 2, getHeight() * 11 / 20,
				TextPaint);

		TextPaint.setARGB(255, 120, 120, 120);
		TextPaint.setTextSize(35);
		TextPaint.setStrokeWidth((float) 0.5);
		canvas.drawText("空气质量指数", getWidth() / 2, getHeight() * 6 / 7,
				TextPaint);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}
