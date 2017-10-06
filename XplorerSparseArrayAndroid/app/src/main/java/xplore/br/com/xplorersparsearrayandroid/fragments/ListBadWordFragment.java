package xplore.br.com.xplorersparsearrayandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xplore.br.com.xplorersparsearrayandroid.asynctasks.GetWordsAsyncTask;
import xplore.br.com.xplorersparsearrayandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListBadWordFragment extends Fragment implements GetWordsAsyncTask.InteractWith<String> {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private List<String> barWords;
    private GetWordsAsyncTask getWordsAsyncTask;
    private RecyclerView.Adapter adapter;

    @Override
    public void interact(SparseArrayCompat<String> sparseArray) {
        for(int idx=0; idx<sparseArray.size(); idx++) {
            String value = sparseArray.get(idx);
            barWords.add(value);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListBadWordFragment() {}

    public static ListBadWordFragment newInstance(OnListFragmentInteractionListener mListener) {
        ListBadWordFragment fragment = new ListBadWordFragment();
        fragment.mListener = mListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_bad_words, container, false);
        barWords = new ArrayList<>();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
            else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new BadWordRecyclerViewAdapter(barWords, mListener);
            recyclerView.setAdapter(adapter);
        }
        getWordsAsyncTask = new GetWordsAsyncTask(this);
        getWordsAsyncTask.execute();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            // throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelAsyncTask();
    }


    private void cancelAsyncTask() {
        if(getWordsAsyncTask != null && ! getWordsAsyncTask.isCancelled()) {
            getWordsAsyncTask.cancel(true);
        }
    }
}
