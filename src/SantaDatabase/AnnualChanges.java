package SantaDatabase;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChanges {

    private final Double newSantaBudget;
    private List<SantaGiftsList> newGifts;
    private List<Children> newChildren;
    private ArrayList<String> newPreferences;


    public AnnualChanges(Double newSantaBudget, List<SantaGiftsList> newGifts,
                         List<Children> newChildren,
                         ArrayList<String> newPreferences) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.newPreferences = newPreferences;
    }
}
