package MyDatabase;

import SantaDatabase.Children;
import SantaDatabase.Input;
import childrenCategory.*;

import java.util.List;

public class Operations {

    private List<Children> childrenData;
    public Operations(List<Children> input) {
        this.childrenData = input;
    }

    public void doAverageOperation() {
        for (int i = 0; i < childrenData.size(); ++i) {
            if (childrenData.get(i).getAge() < 5) {
                ChildrenFactory childrenFactory = new BabyFactory();
                ChildrenCategory babyCat = childrenFactory.calculateKidsAverage(childrenData.get(i));
            }   else if (childrenData.get(i).getAge() >= 5 &&
            childrenData.get(i).getAge() < 12) {
                ChildrenFactory childrenFactory = new KidFactory();
                ChildrenCategory kidCat = childrenFactory.calculateKidsAverage(childrenData.get(i));
            }   else if (childrenData.get(i).getAge() > 11 &&
                    childrenData.get(i).getAge() < 19) {
                ChildrenFactory childrenFactory = new TeenFactory();
                ChildrenCategory teenCat = childrenFactory.calculateKidsAverage(childrenData.get(i));
            }   else {
                childrenData.remove(childrenData.get(i));
                i--;
            }
        }
    }
}
