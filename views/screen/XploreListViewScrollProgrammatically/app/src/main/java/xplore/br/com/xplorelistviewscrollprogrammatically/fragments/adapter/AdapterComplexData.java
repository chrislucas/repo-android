package xplore.br.com.xplorelistviewscrollprogrammatically.fragments.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by r028367 on 19/09/2017.
 */

public class AdapterComplexData extends ArrayAdapter<ComplexData> {

    private List<ComplexData> list;
    private ViewHolderComplexData complexData;

    public AdapterComplexData(@NonNull Context context
            , @LayoutRes int resource, @NonNull List<ComplexData> objects) {
        super(context, resource, objects);
        this.list = objects;
    }

    @Nullable
    @Override
    public ComplexData getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }
}
