package net.zsygfddsd.qujing.modules.welfarelist;

import android.os.Bundle;
import android.widget.FrameLayout;

import net.zsygfddsd.qujing.R;
import net.zsygfddsd.qujing.base.activity.BaseNetToolbarActivity;
import net.zsygfddsd.qujing.modules.common.ContextModule;
import net.zsygfddsd.qujing.modules.common.RepositoryModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareListActivity extends BaseNetToolbarActivity {

    private static String Tag_WelfareListFragment = "WelfareListFragment";

    @BindView(R.id.mainFrame)
    FrameLayout mainFrame;
    //    @Inject
    //    Context _context;
    @Inject
    WelfareListPresenter _welfareListPresenter;
    //    @Inject
    //    DataSource.WelfareDataSource _welfareRepository;
    private WelfareListFragment _welfareListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addViewToContent(R.layout.activity_welfare_list);
        ButterKnife.bind(this);
        _welfareListFragment = WelfareListFragment.newInstance(R.layout.item_welfare);
        DaggerWelfareListActivityComponent.builder()
                .contextModule(new ContextModule(this))
                .repositoryModule(new RepositoryModule())
                .welfareListPresenterModule(new WelfareListPresenterModule(_welfareListFragment))
                .build()
                .inject(this);
        if (findFragment(WelfareListFragment.class.getName()) == null) {
            replaceLoadRootFragment(R.id.mainFrame, _welfareListFragment, false);
        }

    }
}
