package childrenCategory;

public class KidFactory extends ChildrenFactory{

    @Override
    public ChildrenCategory createChildrenCategory() {
        return new Kid();
    }
}
