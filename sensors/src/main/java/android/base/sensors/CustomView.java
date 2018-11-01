package android.base.sensors;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

// Кастомный элемент
// Большинство методов переопределено только для демонстрации жизненного цикла
public class CustomView extends View {

    private final static String TAG = "CustomView";

    private Paint paint;
    private int radius;
    private int color;
    private boolean pressed;    // признак того, что пользователь нажал на View
    private OnClickListener listener;      // Слушатель

    public CustomView(Context context) {
        super(context);
        init();
    }

    // Вызывается при добавлении элемента в макет
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs, 0, 0);
        init();
    }

    // Вызывается при добавлении элемента в макет, если был добавлен стиль
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs, defStyleAttr, 0);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    // Обработка параметров в xml
    private void initAttributes(Context context, AttributeSet attrs,
                                int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView,
                defStyleAttr, defStyleRes);
        setRadius(typedArray.getResourceId(R.styleable.CustomView_cv_Radius, 100));
        setColor(typedArray.getResourceId(R.styleable.CustomView_cv_Color, Color.BLUE));

        typedArray.recycle();
    }

    private void init() {
        Log.d(TAG, "Constructor");

        paint = new Paint();
        paint.setColor(color);
//        paint.setStyle(Paint.Style.FILL);
    }

    private void setRadius(int radius) {
        this.radius = radius;
    }

    private void setColor(int color) {
        this.color = color;
    }

    @Override
    protected void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.d(TAG, "layout");
        super.layout(l, t, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.d(TAG, "draw");
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        super.onDraw(canvas);

        radius += 10 * Math.random();

        canvas.drawCircle(radius, radius, radius, paint);
        if (pressed)
            canvas.drawCircle(radius, radius, radius/10, paint);
        else
            canvas.drawCircle(radius, radius, radius, paint);
    }

    @Override
    public void invalidate() {
        Log.d(TAG, "invalidate");
        super.invalidate();
    }

    @Override
    public void requestLayout() {
        Log.d(TAG, "requestLayout");
        super.requestLayout();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int Action = event.getAction();

        if (Action == MotionEvent.ACTION_DOWN) { // нажали
            pressed = true;
            invalidate();           // Перерисовка элемента
            if (listener != null) listener.onClick(this);
        }
        else if (Action == MotionEvent.ACTION_UP) { // отпустили
            pressed = false;
            invalidate();           // Перерисовка элемента
        }
        return true;
    }

    // установка слушателя
    @Override
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
