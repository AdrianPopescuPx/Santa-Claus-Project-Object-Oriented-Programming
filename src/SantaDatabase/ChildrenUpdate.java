package SantaDatabase;

import java.util.ArrayList;

public class ChildrenUpdate {
    private int id;
    private Double niceScore;
    private ArrayList<String> newPreferences;

    public ChildrenUpdate(int id, Double niceScore, ArrayList<String> newPreferences){
        this.id = id;
        this.niceScore = niceScore;
        this.newPreferences = newPreferences;
    }

    public int getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getNewPreferences() {
        return newPreferences;
    }
}
