package childrencategory;

import SantaDatabase.Children;

public class Baby implements ChildrenCategory {

    @Override
    public void calculateAverageScore(final Children children) {
        final Double number = 10.0;
        children.setAverageScore(number);
    }

}
