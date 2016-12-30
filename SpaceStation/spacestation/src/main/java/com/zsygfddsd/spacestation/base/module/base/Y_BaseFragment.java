package com.zsygfddsd.spacestation.base.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zsygfddsd.spacestation.base.fragment.F_RxFragment;


/**
 * Created by mac on 16/3/1.
 */
public abstract class Y_BaseFragment<T extends Y_I_Presenter> extends F_RxFragment implements Y_I_View<T> {

    private Toast toast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    @Override
    public F_RxFragment getRxView() {
        return this;
    }

}
