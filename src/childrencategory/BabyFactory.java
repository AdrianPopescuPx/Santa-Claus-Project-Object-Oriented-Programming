package childrencategory;

public class BabyFactory extends ChildrenFactory {
    @Override
    public ChildrenCategory createChildrenCategory() {
        return new Baby();
    }
}
