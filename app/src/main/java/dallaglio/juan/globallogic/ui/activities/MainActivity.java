package dallaglio.juan.globallogic.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dallaglio.juan.globallogic.Constants;
import dallaglio.juan.globallogic.R;
import dallaglio.juan.globallogic.databinding.ActivityMainBinding;
import dallaglio.juan.globallogic.entities.MyObject;
import dallaglio.juan.globallogic.remote.ListRemote;
import dallaglio.juan.globallogic.ui.adapters.BaseAdapter;
import dallaglio.juan.globallogic.ui.adapters.MyObjectAdapter;
import dallaglio.juan.globallogic.viewModels.MainActivityViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements BaseAdapter.ItemClickListener {

    private MainActivityViewModel mViewModel;
    private RecyclerView mRecycler;
    private MyObjectAdapter mAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void bind(ActivityMainBinding bind) {
        mRecycler = bind.recycler;
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        bind.setIsLoading(mViewModel.getLoadingObservable());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyObjectAdapter(this);
        mAdapter.setClickListener(this);
        mRecycler.setAdapter(mAdapter);

        if (mViewModel.getList() == null) {
            loadData();
        } else {
            setList(mViewModel.getList());
        }


    }

    private void loadData() {
        Single<Response<List<MyObject>>> listObservable = new ListRemote().getList();

        Disposable disposable = listObservable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(d -> mViewModel.setLoading(true))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listResponse -> {
                    mViewModel.setLoading(false);
                    setList(listResponse.body());
                }, this::handleError);
        compositeDisposable.add(disposable);

    }

    private void setList(List<MyObject> myObjects) {
        mViewModel.setList(myObjects);
        mRecycler.scheduleLayoutAnimation();
        mAdapter.setList(myObjects);

    }


    @Override
    public void onItemClick(View view, int position) {
        MyObject my = mAdapter.getItem(position);
        if (my != null) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(Constants.IntentData.MY_OBJECT, my);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}

