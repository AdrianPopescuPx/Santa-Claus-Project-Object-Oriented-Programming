package childrenCategory;

import SantaDatabase.Children;

public class Baby implements ChildrenCategory {

    @Override
    public void calculateAverageScore(Children children) {
        children.setAverageScore(10d);
    }

}
