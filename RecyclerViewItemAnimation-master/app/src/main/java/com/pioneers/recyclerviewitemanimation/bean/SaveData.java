package com.pioneers.recyclerviewitemanimation.bean;

import java.io.Serializable;

/**
 * Created by sushanqiang on 2016/5/30.
 */
public class SaveData implements Serializable {

    /**
     * content : <p style=\"margin-right:0;margin-left:0;text-indent:28px;text-autospace:ideograph-numeric;text-align:justify;text-justify:inter-ideograph\"><span style=\";font-family:宋体;font-size:14px\">近日，厄瓜多尔银行<span style=\"font-family:Calibri\">Banco Del Austro of Cuenca<\/span><span style=\"font-family:宋体\">成为网络盗窃案件受害者（也是最早受害者），攻击者通过关于<\/span><span style=\"font-family:Calibri\">SWIFT<\/span><span style=\"font-family:宋体\">系统的漏洞盗取了<\/span><span style=\"font-family:Calibri\">1200<\/span><span style=\"font-family:宋体\">百万美元。<\/span><\/span><\/p><p style=\"margin-right:0;margin-left:0;text-indent:28px;text-autospace:ideograph-numeric;text-align:justify;text-justify:inter-ideograph\"><span style=\";font-family:宋体;font-size:14px\">/p>
     * id : 487
     * editor : yf
     * text : 正常的朋友圈点赞是不需要下载软件的，因此不要随便安装来历不明的软件，不要随便打开未知网页，以防感染病毒造成财产损失。
     * title : 诈骗又出新花样 “朋友圈点赞”银行卡被盗刷
     * source : 新华社
     * stime : 2016-05-27 17:50:24
     * image : upload/news_images/47e5759f116b40259a4beeb179a65d0c.jpg
     * type : 1
     * url : upload/news/11120160527175024324.html
     */

    private String content;
    private int id;
    private String editor;
    private String text;
    private String title;
    private String source;
    private String stime;
    private String image;
    private int type;
    private String url;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
