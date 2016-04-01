package ren.solid.materialdesigndemo.bean;

import java.util.List;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:14:18
 * <p/>
 * 书籍实体类
 */
public class BookBean {

    private Rating rating;
    private String subtitle;
    private List<String> author;
    private String pubdate;
    private String origin_title;
    private String image;
    private String catalog;
    private String pages;
    private Images images;
    private String publisher;
    private String title;
    private String url;
    private String author_intro;
    private String summary;
    private String price;
    private String isbn10;
    private String isbn13;

    public BookBean.Rating getRating() {
        return rating;
    }

    public void setRating(BookBean.Rating rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public BookBean.Images getImages() {
        return images;
    }

    public void setImages(BookBean.Images images) {
        this.images = images;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }


    public class Rating {
        private float max;
        private int numRaters;
        private float average;
        private float min;

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public float getAverage() {
            return average;
        }

        public void setAverage(float average) {
            this.average = average;
        }

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }
    }

    public class Images {
        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

}
