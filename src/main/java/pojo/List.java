package pojo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 17:58
 */
public class List {
    private int _id;
    private String list_name;
    private int user_id;
    private java.util.List<Thing> things;

    public List() {
    }

    public List(String list_name, int user_id) {
        this.list_name = list_name;
        this.user_id = user_id;
    }

    public List(int _id, String list_name, int user_id, java.util.List<Thing> things) {
        this._id = _id;
        this.list_name = list_name;
        this.user_id = user_id;
        this.things = things;
    }

    public List(int _id, String list_name, int user_id) {
        this._id = _id;
        this.list_name = list_name;
        this.user_id = user_id;
    }

    public List(String list_name, int user_id, java.util.List<Thing> things) {
        this.list_name = list_name;
        this.user_id = user_id;
        this.things = things;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public java.util.List<Thing> getThings() {
        return things;
    }

    public void setThings(Thing things) {
        if (this.things == null){
            this.things = new ArrayList<>();
        }
        this.things.add(things);
    }

    @Override
    public String toString() {
        return "List{" +
                "_id=" + _id +
                ", list_name='" + list_name + '\'' +
                ", user_id=" + user_id +
                ", things=" + things +
                '}';
    }
}
