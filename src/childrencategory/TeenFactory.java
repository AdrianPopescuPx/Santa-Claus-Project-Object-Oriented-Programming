package childrencategory;

public class TeenFactory extends ChildrenFactory {
    @Override
    public ChildrenCategory createChildrenCategory() {
        return new Teen();
    }
}
