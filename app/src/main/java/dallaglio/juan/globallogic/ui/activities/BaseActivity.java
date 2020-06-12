package dallaglio.juan.globallogic.ui.activities;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    T mBind;

    protected abstract int getLayout();
    protected abstract void bind(T bind);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, getLayout());
        bind(mBind);
    }

    protected void handleError(Exception e){

    }

    protected void handleError(Throwable throwable) {

    }
}
