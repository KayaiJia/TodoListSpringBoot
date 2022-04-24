package pojo;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 18:00
 */
public class ThingsMap {
    private int _id;
    private int things_id;
    private int classify;
    private int map_id;

    public ThingsMap() {
    }

    public ThingsMap(int things_id, int classify, int map_id) {
        this.things_id = things_id;
        this.classify = classify;
        this.map_id = map_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getThings_id() {
        return things_id;
    }

    public void setThings_id(int things_id) {
        this.things_id = things_id;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public int getMap_id() {
        return map_id;
    }

    public void setMap_id(int map_id) {
        this.map_id = map_id;
    }

    @Override
    public String toString() {
        return "ThingsMap{" +
                "_id=" + _id +
                ", things_id=" + things_id +
                ", classify=" + classify +
                ", map_id=" + map_id +
                '}';
    }
}
