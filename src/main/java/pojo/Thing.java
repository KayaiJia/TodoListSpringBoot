package pojo;

import java.sql.Date;
import java.sql.Time;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 17:51
 */
public class Thing {
    private int _id;
    private String title;
    private String tag;
    private String describe;
    private String remark;
    private Time remind_time;
    private Date reminder_date;
    private Boolean done;
    private User user;

    public Thing() {
    }

    public Thing(int _id, String title, String tag, String describe, String remark, Time remind_time, Date reminder_date, Boolean done, User user) {
        this._id = _id;
        this.title = title;
        this.tag = tag;
        this.describe = describe;
        this.remark = remark;
        this.remind_time = remind_time;
        this.reminder_date = reminder_date;
        this.done = done;
        this.user = user;
    }

    public Thing(String title, String tag, String describe, String remark, Time remind_time, Date reminder_date, Boolean done, User user) {
        this.title = title;
        this.tag = tag;
        this.describe = describe;
        this.remark = remark;
        this.remind_time = remind_time;
        this.reminder_date = reminder_date;
        this.done = done;
        this.user = user;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Time getRemind_time() {
        return remind_time;
    }

    public void setRemind_time(Time remind_time) {
        this.remind_time = remind_time;
    }

    public Date getReminder_date() {
        return reminder_date;
    }

    public void setReminder_date(Date reminder_date) {
        this.reminder_date = reminder_date;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", describe='" + describe + '\'' +
                ", remark='" + remark + '\'' +
                ", remind_time=" + remind_time +
                ", reminder_date=" + reminder_date +
                ", done=" + done +
                ", user=" + user +
                '}';
    }
}
