package xplore.br.com.xplorelistviewscrollprogrammatically.fragments.recycleview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import xplore.br.com.xplorelistviewscrollprogrammatically.R;
import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.dummy.DummyContent;

/**
 * Created by r028367 on 26/09/2017.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mIdView;
    public final TextView mContentView;
    public DummyContent.DummyItem mItem;

    public ViewHolder(View view) {
        super(view);
        mView           = view;
        mIdView         = (TextView) view.findViewById(R.id.id);
        mContentView    = (TextView) view.findViewById(R.id.content);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}
