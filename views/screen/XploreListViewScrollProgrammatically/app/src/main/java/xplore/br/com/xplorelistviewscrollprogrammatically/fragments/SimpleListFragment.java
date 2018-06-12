package xplore.br.com.xplorelistviewscrollprogrammatically.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import xplore.br.com.xplorelistviewscrollprogrammatically.R;
import xplore.br.com.xplorelistviewscrollprogrammatically.data.GetWordsOnline;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SimpleListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SimpleListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleListFragment extends AppBaseFragment implements GetWordsOnline.Callback {

    private ListView listView;
    private ArrayAdapter listAdapter;
    private List<String> sample;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GetWordsOnline getWordsOnline;

    private FloatingActionButton floatingActionButton;

    public SimpleListFragment() {
        // Required empty public constructor
    }

    public static SimpleListFragment newInstance() {
        SimpleListFragment fragment = new SimpleListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view   = inflater.inflate(R.layout.fragment_simple_list, container, false);
        listView    = view.findViewById(R.id.simple_data_sample_list_view);
        swipeRefreshLayout = view.findViewById(R.id.simple_data_swipe_refresh_layout);
        floatingActionButton = view.findViewById(R.id.floating_action_button);
        sample      = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, sample) ;
        listView.setAdapter(listAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dismissRefreshing();
            }
        });
        getWordsOnline = new GetWordsOnline(this);
        getWordsOnline.execute();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listAdapter.getCount() > 0) {
                    run(2, 0, listAdapter.getCount(), listView.getLastVisiblePosition() + 1);
                }
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /*
                Log.i("ON_SCROLL", String.format(Locale.getDefault()
                        , "%d %d %d", firstVisibleItem, visibleItemCount, totalItemCount));
                        */
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
            }
        });

        return view;
    }

    public void run(long seconds, int startPostion, int endPositon, int amount) {
        double ratio = seconds * 1500  / (endPositon - startPostion);
        accelerated(1, ratio, startPostion, endPositon, amount,  new Handler());
    }

    private void accelerated(double startDelay, final double accRatio
            , int startPostion, final int endPosition, final int amount, final Handler handler) {
        if(startPostion < endPosition ) {
            final int firstPositionVisible = startPostion + amount;
            final double accumulatedDelay = (startDelay + accRatio);
            long delay = (long)(accumulatedDelay);
            Log.i("PROCESS", String.format("Posicao: %d Delay %d", startPostion, delay));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.smoothScrollToPosition(firstPositionVisible);
                    accelerated(accumulatedDelay, accRatio, firstPositionVisible, endPosition , amount, handler);
                }
            }, delay);
        }
    }

    public void showRefreshing() {
        if(swipeRefreshLayout != null) {
            if(!swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void dismissRefreshing() {
        if(swipeRefreshLayout != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                        swipeRefreshLayout.setEnabled(false);
                    }
                }
            }, 500);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateList(List<String> strs) {
        sample.clear();
        sample.addAll(strs);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelAsyncTask();
    }

    public void cancelAsyncTask() {
        if(getWordsOnline != null && ! getWordsOnline.isCancelled()) {
            getWordsOnline.cancel(true);
            getWordsOnline = null;
        }
    }

    @Override
    public void afterProcess(List<String> result) {
        dismissRefreshing();
        updateList(result);
    }

    @Override
    public void beforeProcess() {
        showRefreshing();
    }
}
