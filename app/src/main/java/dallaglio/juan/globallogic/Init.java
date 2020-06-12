package dallaglio.juan.globallogic;

import android.app.Application;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

public class Init extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Glide.init(getApplicationContext(),
                new GlideBuilder()
                .setLogLevel(Log.VERBOSE)
        );
    }
}
