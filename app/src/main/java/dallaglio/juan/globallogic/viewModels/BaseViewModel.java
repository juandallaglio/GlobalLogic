package dallaglio.juan.globallogic.viewModels;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    ObservableBoolean isLoading = new ObservableBoolean(false);

    public void setLoading(boolean isLoading){
        this.isLoading.set(isLoading);
    }

    public ObservableBoolean getLoadingObservable(){
        return isLoading;
    }
}
