package net.zsygfddsd.qujing.common.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by mac on 2017/8/30.
 */

public class CountDownTextView extends AppCompatTextView {

    CountDownTextViewHelper countDownTextViewHelper;

    public CountDownTextView(Context context) {
        super(context);
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnCountDownListener(final int max, final int interval, final CountDownTextViewHelper.OnCountDownListener listener) {
        //利用反射获取OnAttachStateChangeListeners并清空，保证一个view只有一个OnAttachStateChangeListener监听器
        clearOnAttachStateChangeListeners();

        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                if (countDownTextViewHelper == null) {
                    countDownTextViewHelper = new CountDownTextViewHelper(max, interval);
                    if (listener != null) {
                        countDownTextViewHelper.setOnCountDownListener(listener);
                    }
                    countDownTextViewHelper.start();
                }
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                if (countDownTextViewHelper != null) {
                    countDownTextViewHelper.stop();
                }
                countDownTextViewHelper = null;
            }
        });
    }

    public void clearOnAttachStateChangeListeners() {
        Class cls = null;
        try {
            cls = Class.forName("android.view.View");
            Method method = cls.getDeclaredMethod("getListenerInfo");
            method.setAccessible(true);
            Object obj = method.invoke(this);
            Field filed = obj.getClass().getDeclaredField("mOnAttachStateChangeListeners");
            filed.setAccessible(true);
            CopyOnWriteArrayList<OnAttachStateChangeListener> onAttachStateChangeListeners = (CopyOnWriteArrayList<OnAttachStateChangeListener>) filed.get(obj);
            Log.e("timeclear", "onAttachStateChangeListeners 的 size----" + onAttachStateChangeListeners.size());
            //清空 OnAttachStateChangeListener
            //            onAttachStateChangeListeners.clear();
        } catch (Exception e) {
            Log.e("timeclear", "反射出异常了,---" + e.getMessage());
        }
    }
}
