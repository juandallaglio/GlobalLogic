package dallaglio.juan.globallogic.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T, V extends RecyclerView.ViewHolder, S extends ViewDataBinding> extends RecyclerView.Adapter<V> {

    protected List<T> mList;
    private Context mContext;
    protected static ItemClickListener mClickListener;

    protected abstract V createViewHolder(S bind);
    protected abstract int getLayout();

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater view = LayoutInflater.from(mContext);
        S binding = DataBindingUtil.inflate(view, getLayout(), parent, false);
        return this.createViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public T getItem(int position){
        return position < mList.size() ? mList.get(position) : null;
    }

    public void setList(List<T> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener listener){
        mClickListener = listener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

}
