package net.zsygfddsd.qujing.modules.welfarelist;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import net.zsygfddsd.qujing.base.fragment.net_recyclerview.BaseNetRecyclerPresenter;
import net.zsygfddsd.qujing.data.DataSource;
import net.zsygfddsd.qujing.data.bean.Welfare;
import net.zsygfddsd.qujing.data.local.SpCache;
import net.zsygfddsd.qujing.data.local.SpCacheKey;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by mac on 16/7/24.
 */
public class WelfareListPresenter extends BaseNetRecyclerPresenter<List<Welfare>, Welfare> implements WelfareListContract.Presenter<List<Welfare>, Welfare> {

    private Context _context;
    private WelfareListContract.View _view;
    private DataSource.WelfareDataSource _repository;

    @Inject
    public WelfareListPresenter(Context context, @NonNull WelfareListContract.View mView, DataSource.WelfareDataSource mRepository) {
        super(context, mView);
        _context = context;
        _view = mView;
        _repository = mRepository;
    }

    @Inject
    void setup() {
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
        if (page == 1) {
            return _repository.getWelfareList(_context, false, "福利", pageSize + "", page + "");
        } else if (page == 2) {
            return _repository.getWelfareList(_context, true, "福利", pageSize + "", page + "");
        } else if (page == 3) {
            SpCache.put(_context, SpCacheKey.Cookie, "3");
            return _repository.getWelfareList(_context, true, "福利", pageSize + "", page + "");
        } else
            return _repository.getWelfareList(_context, false, "福利", pageSize + "", page + "");
    }

}
