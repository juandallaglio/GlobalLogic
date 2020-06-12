package dallaglio.juan.globallogic.viewModels;

import dallaglio.juan.globallogic.entities.MyObject;

public class DetailActivityViewModel extends BaseViewModel {

    MyObject myObject;

    public MyObject getMyObject() {
        return myObject;
    }

    public void setMyObject(MyObject myObject) {
        this.myObject = myObject;
    }
}
