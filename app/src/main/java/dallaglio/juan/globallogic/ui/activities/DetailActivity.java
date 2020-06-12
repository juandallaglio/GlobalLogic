package dallaglio.juan.globallogic.ui.activities;

import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;

import dallaglio.juan.globallogic.Constants;
import dallaglio.juan.globallogic.R;
import dallaglio.juan.globallogic.databinding.ActivityDetailBinding;
import dallaglio.juan.globallogic.exception.HandledException;
import dallaglio.juan.globallogic.viewModels.DetailActivityViewModel;

public class DetailActivity extends BaseActivity<ActivityDetailBinding> {

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void bind(ActivityDetailBinding bind) {
        DetailActivityViewModel mViewModel =
                new ViewModelProvider(this)
                        .get(DetailActivityViewModel.class);
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.IntentData.MY_OBJECT)){
            mViewModel.setMyObject(intent.getParcelableExtra(Constants.IntentData.MY_OBJECT));
        } else {
            handleError(new HandledException(getResources().getString(R.string.error)));
            finish();
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setTitle(mViewModel.getMyObject().getTitle());
        bind.setIsLoading(mViewModel.getLoadingObservable());
        bind.setModel(mViewModel.getMyObject());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
