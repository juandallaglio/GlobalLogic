package dallaglio.juan.globallogic.viewModels;

import java.util.List;

import dallaglio.juan.globallogic.entities.MyObject;

public class MainActivityViewModel extends BaseViewModel {

    List<MyObject> list = null;

    public void setList(List<MyObject> list) {
        this.list = list;
    }

    public List<MyObject> getList() {
        return list;
    }
}
