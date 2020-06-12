package dallaglio.juan.globallogic.remote;

import java.util.List;

import dallaglio.juan.globallogic.entities.MyObject;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

public class ListRemote {

    ApiService mService;

    public ListRemote() {
        this.mService = new RemoteFactory().createApiClient(ApiService.class);
    }

    public Single<Response<List<MyObject>>> getList(){
        return mService.getList();
    }

    private interface ApiService{
        @GET("/list")
        Single<Response<List<MyObject>>> getList();
    }
}
