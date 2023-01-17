package SantaDatabase;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChanges {


    private Double newSantaBudget;
    private List<SantaGiftsList> newGifts;
    private List<Children> newChildren;
    private List<ChildrenUpdate> childrenUpdates;


    public AnnualChanges(Double newSantaBudget, List<SantaGiftsList> newGifts,
                         List<Children> newChildren,
                         List<ChildrenUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<SantaGiftsList> getNewGifts() {
        return newGifts;
    }

    public List<Children> getNewChildren() {
        return newChildren;
    }

    public List<ChildrenUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }
}
