package xplore.br.com.xplorelistviewscrollprogrammatically.fragments.adapter;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by r028367 on 19/09/2017.
 */

public class ViewHolderComplexData {
    private TextView title;
    private TextView date;
    private ImageView photo;

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
}
