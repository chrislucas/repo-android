package xplore.br.com.xplorersparsearrayandroid.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xplore.br.com.xplorersparsearrayandroid.R;
import xplore.br.com.xplorersparsearrayandroid.fragments.dummy.DummyContent.DummyItem;
import xplore.br.com.xplorersparsearrayandroid.fragments.viewholder.ViewHolderBadWord;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class BadWordRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderBadWord> {

    private final List<String> mValues;
    private final OnListFragmentInteractionListener mListener;

    public BadWordRecyclerViewAdapter(List<String> items, OnListFragmentInteractionListener listener) {
        mValues     = items;
        mListener   = listener;
    }

    @Override
    public ViewHolderBadWord onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bad_words, parent, false);
        return new ViewHolderBadWord(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderBadWord holder, int position) {
        String wordSelected = mValues.get(position);
        holder.getWord().setText(wordSelected);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}
