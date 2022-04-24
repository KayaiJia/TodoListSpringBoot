package pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 17:55
 */
public class Project {
    private int _id;
    private String project_name;
    private int user_id;
    private List<Thing> things;

    public Project() {
    }

    public Project(String project_name, int user_id) {
        this.project_name = project_name;
        this.user_id = user_id;
    }

    public Project(int _id, String project_name, int user_id, List<Thing> things) {
        this._id = _id;
        this.project_name = project_name;
        this.user_id = user_id;
        this.things = things;
    }

    public Project(int _id, String project_name, int user_id) {
        this._id = _id;
        this.project_name = project_name;
        this.user_id = user_id;
    }

    public Project(String project_name, int user_id, List<Thing> things) {
        this.project_name = project_name;
        this.user_id = user_id;
        this.things = things;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Thing> getThings() {
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
        return "Project{" +
                "_id=" + _id +
                ", project_name='" + project_name + '\'' +
                ", user_id=" + user_id +
                ", things=" + things +
                '}';
    }
}
