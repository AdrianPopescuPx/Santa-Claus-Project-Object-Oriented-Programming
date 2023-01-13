package childrenCategory;

import SantaDatabase.Children;

public abstract class ChildrenFactory {
    public ChildrenCategory calculateKidsAverage(Children children) {
        ChildrenCategory childrenCategory = createChildrenCategory();
        childrenCategory.calculateAverageScore(children);
        return childrenCategory;
    }
    public abstract ChildrenCategory createChildrenCategory();
}
