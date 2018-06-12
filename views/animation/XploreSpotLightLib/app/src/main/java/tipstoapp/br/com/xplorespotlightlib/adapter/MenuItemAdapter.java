package tipstoapp.br.com.xplorespotlightlib.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import tipstoapp.br.com.xplorespotlightlib.entities.MenuItemEntity;

/**
 * Created by r028367 on 12/09/2017.
 */

public class MenuItemAdapter extends ArrayAdapter<MenuItemEntity> {

    private Context context;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public MenuItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MenuItemEntity> objects) {
        super(context, resource, objects);
    }

    private class ViewHolder {
        private TextView textView;
        private Drawable icon;
        private int id;

        public ViewHolder(TextView textView, Drawable icon, int id) {
            this.textView = textView;
            this.icon = icon;
            this.id = id;
        }
    }

    @Nullable
    @Override
    public MenuItemEntity getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
