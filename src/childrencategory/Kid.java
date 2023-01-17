package childrencategory;

import SantaDatabase.Children;

public class Kid implements ChildrenCategory {

    @Override
    public void calculateAverageScore(final Children children) {
        int cnt = 0;
        Double suma = 0.0;
        for (int i = 0; i < children.getNiceScoreHistory().size(); ++i) {
            suma += children.getNiceScoreHistory().get(i);
            cnt++;
        }
        children.setAverageScore(suma / cnt);
    }
}
