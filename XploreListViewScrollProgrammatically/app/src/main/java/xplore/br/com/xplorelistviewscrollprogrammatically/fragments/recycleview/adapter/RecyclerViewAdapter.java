package xplore.br.com.xplorelistviewscrollprogrammatically.fragments.recycleview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xplore.br.com.xplorelistviewscrollprogrammatically.R;

import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.AppBaseFragment;
import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.dummy.DummyContent.DummyItem;
import xplore.br.com.xplorelistviewscrollprogrammatically.fragments.recycleview.viewholder.ViewHolder;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link xplore.br.com.xplorelistviewscrollprogrammatically.fragments.AppBaseFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<DummyItem> mValues;
    private final AppBaseFragment.OnListFragmentInteractionListener mListener;

    public RecyclerViewAdapter(List<DummyItem> items, AppBaseFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complex_data_view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}
