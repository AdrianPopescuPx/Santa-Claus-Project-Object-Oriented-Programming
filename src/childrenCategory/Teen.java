package childrenCategory;

import SantaDatabase.Children;

public class Teen implements ChildrenCategory {

    @Override
    public void calculateAverageScore(Children children) {
        Double averageScore = 0.0;
        double sum = 0.0;
        for (int i = 0; i < children.getNiceScoreList().size(); ++i) {
            averageScore += (i + 1) * children.getNiceScoreList().get(i);
            sum += i + 1;
        }
        children.setAverageScore(averageScore / sum);
    }
}
