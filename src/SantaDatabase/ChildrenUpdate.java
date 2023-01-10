package SantaDatabase;

import java.util.ArrayList;

public class ChildrenUpdate {
    private int id;
    private Integer niceScore;
    private ArrayList<String> newPreferences;

    public ChildrenUpdate(int id, Integer niceScore, ArrayList<String> newPreferences){
        this.id = id;
        this.niceScore = niceScore;
        this.newPreferences = newPreferences;
    }

    public Integer getId() {
        return id;
    }

    public int getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getNewPreferences() {
        return newPreferences;
    }
}
