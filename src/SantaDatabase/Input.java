package SantaDatabase;

import java.util.List;

public final class Input {
    private int numberOfYears;
    private int santaBudget;
    private List<Children> childrenData;
    private List<SantaGiftsList> giftsData;
    private List<AnnualChanges> annualChanges;



    public Input() {
        this.numberOfYears = 0;
        this.santaBudget = 0;
        this.childrenData = null;
        this.giftsData = null;
        this.annualChanges = null;
    }

    public Input(int numberOfYears, int santaBudget, List<Children> childrenData,
                 List<SantaGiftsList> giftsData,
                 List<AnnualChanges> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.childrenData = childrenData;
        this.giftsData = giftsData;
        this.annualChanges = annualChanges;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public List<Children> getChildrenData() {
        return childrenData;
    }

    public List<SantaGiftsList> getGiftsData() {
        return giftsData;
    }

    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }
}
