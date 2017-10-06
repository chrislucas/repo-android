package xplore.br.com.xplorersparsearrayandroid.fragments.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import xplore.br.com.xplorersparsearrayandroid.R;

/**
 * Created by r028367 on 04/10/2017.
 */
public class ViewHolderBadWord extends RecyclerView.ViewHolder {
    private View mView;
    private TextView word;

    public ViewHolderBadWord(View view) {
        super(view);
        mView = view;
        word = view.findViewById(R.id.bar_word);
    }

    public View getView() {
        return mView;
    }

    public TextView getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word.getText().toString();
    }
}
