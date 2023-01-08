package SantaDatabase;

import java.util.List;

public final class Input {
    private final int numberOfYears;
    private final int santaBudget;
    private List<Children> childrenData;
    private final List<SantaGiftsList> giftsData;



    public Input() {
        this.numberOfYears = 0;
        this.santaBudget = 0;
        this.childrenData = null;
        this.giftsData = null;
    }
}
