package dallaglio.juan.globallogic.ui.adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dallaglio.juan.globallogic.R;
import dallaglio.juan.globallogic.databinding.ItemMyObjectBinding;
import dallaglio.juan.globallogic.entities.MyObject;

public class MyObjectAdapter extends BaseAdapter<MyObject, MyObjectAdapter.ViewHolder, ItemMyObjectBinding> {

    public MyObjectAdapter(Context context) {
        super(context);
    }

    @Override
    protected ViewHolder createViewHolder(ItemMyObjectBinding bind) {
        return new ViewHolder(bind);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_my_object;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyObject object = mList.get(position);
        holder.setBind(object);
    }


     static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemMyObjectBinding mBind;

        public ViewHolder(ItemMyObjectBinding itemView) {
            super(itemView.getRoot());
            this.mBind = itemView;
            mBind.clickRoot.setOnClickListener(this);
        }

        protected void setBind(MyObject object) {
            mBind.setModel(object);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null)
                mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
