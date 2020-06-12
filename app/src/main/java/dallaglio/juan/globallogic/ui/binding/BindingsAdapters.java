package dallaglio.juan.globallogic.ui.binding;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import dallaglio.juan.globallogic.R;

public class BindingsAdapters {

    @BindingAdapter("bind:loadImage")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView)
                .load(TextUtils.isEmpty(url) ? null : url)
                .placeholder(R.drawable.ic_photo)
                .error(R.drawable.ic_photo)
                .thumbnail(0.1f)
                .into(imageView);
    }

    @BindingAdapter("bind:loadOriginalImage")
    public static void loadOriginalImage(ImageView imageView, String url) {
        RequestOptions options = new RequestOptions().centerInside();
        Glide.with(imageView)
                .load(TextUtils.isEmpty(url) ? null : url)
                .placeholder(R.drawable.ic_photo)
                .error(R.drawable.ic_photo)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .thumbnail(0.35f)
                .apply(options)
                .into(imageView);
    }

}
