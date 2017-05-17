package net.zsygfddsd.qujing.modules.welviewpager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import net.zsygfddsd.qujing.base.fragment.net_recyclerview.BaseNetRecyclerPresenter;
import net.zsygfddsd.qujing.data.DataSource;
import net.zsygfddsd.qujing.data.bean.Welfare;

import java.util.List;

import rx.Observable;

/**
 * Created by mac on 16/7/24.
 */
public class TabListPresenter extends BaseNetRecyclerPresenter<List<Welfare>, Welfare> implements TabsContract.Presenter<List<Welfare>, Welfare> {

    private Context _context;
    private TabsContract.View _view;
    private DataSource.WelfareDataSource _repository;

    public TabListPresenter(Context context, @NonNull TabsContract.View mView, DataSource.WelfareDataSource mRepository) {
        super(context, mView);
        _context = context;
        _view = mView;
        _repository = mRepository;
        setup();
    }

    public void setup() {
        _view.setPresenter(this);
    }

    @Override
    public boolean getIsHasNextFromResponse(List<Welfare> result) {
        return true;
    }

    @Override
    public List<Welfare> getListFromResponse(List<Welfare> result) {
        return result;
    }

    @Override
    public Observable<ComRespInfo<List<Welfare>>> getRequestObservable(int page, int pageSize) {
        return _repository.getWelfareList(_context, false, "福利", pageSize + "", page + "");
    }

    public TabsContract.View getView() {
        return _view;
    }

}
