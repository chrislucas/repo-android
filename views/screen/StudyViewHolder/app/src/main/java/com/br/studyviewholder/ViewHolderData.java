package com.br.studyviewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by C.Lucas on 18/12/2016.
 */
public class ViewHolderData {
    TextView id, textData;
    ImageView image;

    public TextView getId() {
        return id;
    }

    public TextView getTextData() {
        return textData;
    }

    public ImageView getImage() {
        return image;
    }

    public ViewHolderData(View view) {
        id          = (TextView) view.findViewById(R.id.id_data);
        textData    = (TextView) view.findViewById(R.id.text_data);
        image       = (ImageView) view.findViewById(R.id.image_data);
    }
}
