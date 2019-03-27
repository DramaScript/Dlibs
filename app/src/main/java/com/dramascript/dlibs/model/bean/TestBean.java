package com.dramascript.dlibs.model.bean;

public class TestBean {


    /**
     * id : 234
     * title : 10.08 A股收盘点评：A股节后“开门黑” 沪指收盘重挫逾百点
     * link : /html/1538992028545.html
     * zan : 0
     * readcount : 0
     * createtime : 1538992029000
     * isshow : 1
     */

    private int id;
    private String title;
    private String link;
    private int zan;
    private int readcount;
    private long createtime;
    private int isshow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public int getIsshow() {
        return isshow;
    }

    public void setIsshow(int isshow) {
        this.isshow = isshow;
    }
}
