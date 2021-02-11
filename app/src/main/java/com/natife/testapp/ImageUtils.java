package com.natife.testapp;

import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ImageUtils {

    public static void load(ImageView imageView, String uri) {
        Glide.with(imageView)
                .load(uri)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
