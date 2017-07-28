package com.tck.commonlibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tck.commonlibrary.R;

/**
 * Description:
 * <p>
 * Created by tck on 2017/7/28.
 */

public class ImageLoadUtils {


    private static volatile ImageLoadUtils instance;

    private ImageLoadUtils() {
    }

    public static ImageLoadUtils getInstance() {
        if (instance == null) {
            synchronized (ImageLoadUtils.class) {
                if (instance == null) {
                    instance = new ImageLoadUtils();
                }
            }
        }
        return instance;
    }

    public void load(Context context, ImageView imageView, String url) {
        load(context, imageView, url, R.mipmap.ic_launcher_round1);
    }

    private void load(Context context, ImageView imageView, String url, int defaultImage) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).into(imageView);
        } else {
            Glide.with(context).load(defaultImage).into(imageView);
        }
    }
}
