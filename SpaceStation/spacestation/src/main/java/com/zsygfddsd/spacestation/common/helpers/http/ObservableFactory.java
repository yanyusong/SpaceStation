package com.zsygfddsd.spacestation.common.helpers.http;

import android.content.Context;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.zsygfddsd.spacestation.base.activity.F_RxAppCompatActivity;
import com.zsygfddsd.spacestation.base.fragment.F_RxFragment;
import com.zsygfddsd.spacestation.common.helpers.http.transformer.SchedulerTransformer;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import rx.Observable;


/**
 * Created by mac on 16/7/26.
 */
public class ObservableFactory {

    public static <T> Observable<ComRespInfo<T>> createNetObservable(Context context, Observable<ComRespInfo<T>> observable, F_RxFragment rxFragment) {

        return observable
                .compose(new SchedulerTransformer<T>())
                .compose(rxFragment.<ComRespInfo<T>>bindUntilEvent(FragmentEvent.DESTROY));
    }

    public static <T> Observable<ComRespInfo<T>> createNetObservable(Context context, Observable<ComRespInfo<T>> observable, F_RxAppCompatActivity rxAppCompatActivity) {

        return observable
                .compose(new SchedulerTransformer<T>())
                .compose(rxAppCompatActivity.<ComRespInfo<T>>bindUntilEvent(ActivityEvent.DESTROY));
    }


}
