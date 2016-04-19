package ren.solid.materialdesigndemo.bean;

/**
 * Created by _SOLID
 * Date:2016/4/19
 * Time:10:59
 */
public class GanHuoBean {

    /**
     * _id : 5714449667765974f5e27da7
     * createdAt : 2016-04-18T10:21:10.634Z
     * desc : Android 实现图片圆角显示的几种方式
     * publishedAt : 2016-04-18T12:05:28.120Z
     * source : web
     * type : Android
     * url : http://gavinliu.cn/2016/04/12/Android-实现图片圆角显示的几种方式/
     * used : true
     * who : null
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
