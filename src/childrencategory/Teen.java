package childrencategory;

import SantaDatabase.Children;

public class Teen implements ChildrenCategory {

    @Override
    public void calculateAverageScore(final Children children) {
        Double averageScore = 0.0;
        double sum = 0.0;
        for (int i = 0; i < children.getNiceScoreHistory().size(); ++i) {
            averageScore += (i + 1) * children.getNiceScoreHistory().get(i);
            sum += i + 1;
        }
        children.setAverageScore(averageScore / sum);
    }
}
