package ren.solid.library.http.request;

import android.widget.ImageView;

import ren.solid.library.R;

/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:10:03
 * <br />
 * the request of image
 */
public class ImageRequest {

    private String url;
    private int placeHolder;
    private ImageView imageView;

    public ImageRequest(Builder builder) {
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imageView = builder.imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public static class Builder {
        private String url;
        private int placeHolder;
        private ImageView imageView;

        public Builder() {
            this.url = "";
            this.placeHolder = R.drawable.default_load_img;
            this.imageView = null;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder imgView(ImageView imgView) {
            this.imageView = imgView;
            return this;
        }

        public ImageRequest create() {
            if (imageView == null) throw new IllegalArgumentException("the imageView required");
            if ("".equals(url)) throw new IllegalArgumentException("the url cannot be empty");
            return new ImageRequest(this);
        }
    }


}
