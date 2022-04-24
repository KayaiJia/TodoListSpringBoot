package pojo;

import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 17:35
 */
public class User {
    private int _id;
    private String nic_name;
    private String open_id;

    private List<Project> projectsList;
    private List<pojo.List> listList;

    public User() {
    }

    public User(String nic_name, String open_id) {
        this.nic_name = nic_name;
        this.open_id = open_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", nic_name='" + nic_name + '\'' +
                ", open_id='" + open_id + '\'' +
                ", projectsList=" + projectsList +
                ", listList=" + listList +
                '}';
    }

    public User(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNic_name() {
        return nic_name;
    }

    public void setNic_name(String nic_name) {
        this.nic_name = nic_name;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public List<Project> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }

    public List<pojo.List> getListList() {
        return listList;
    }

    public void setListList(List<pojo.List> listList) {
        this.listList = listList;
    }
}
