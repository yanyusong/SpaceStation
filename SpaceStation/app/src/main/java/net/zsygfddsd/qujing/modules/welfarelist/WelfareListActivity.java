package net.zsygfddsd.qujing.modules.welfarelist;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.zsygfddsd.spacestation.base.activity.Y_BaseToolBarActivity;

import net.zsygfddsd.qujing.R;
import net.zsygfddsd.qujing.modules.common.ContextModule;
import net.zsygfddsd.qujing.modules.common.RepositoryModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareListActivity extends Y_BaseToolBarActivity {

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
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, _welfareListFragment).commit();


    }
}
