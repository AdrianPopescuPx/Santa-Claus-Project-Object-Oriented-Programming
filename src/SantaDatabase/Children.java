package SantaDatabase;

import java.util.ArrayList;

public class Children {

    private final int id;
    private final String lastName;
    private final String firstName;
    private final String city;
    private final int age;
    private final ArrayList<String> giftsPreferences;
    private Double averageScore;
    private final ArrayList<Double> niceScoreHistory = new ArrayList<>();
    private Double assignedBudget;
    private final ArrayList<SantaGiftsList> receivedGifts = new ArrayList<>();
    private final ArrayList<Double> averageScoreHistory = new ArrayList<>();

    public Children(final int id, final String lastName,
                    final String firstName, final int age,
                    final String city, final Double niceScore,
                    final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScoreHistory.add(niceScore);
        this.giftsPreferences = giftsPreferences;
    }

    public void addScoreToList(Double number) {
        niceScoreHistory.add(number);
    }
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }
    public void addGifts(SantaGiftsList gift) {
        receivedGifts.add(gift);
    }
    public ArrayList<SantaGiftsList> getReceivedGifts() {
        return receivedGifts;
    }
    public void setAverageScore(Double number) {
        averageScore = number;
    }
    public void addAverageHistory(Double number) {
        averageScoreHistory.add(number);
    }
    public Double getAverageScore() {
        return averageScore;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }


    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    @Override
    public String toString() {
        return "Children{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", niceScore=" +
                ", giftsPreferences=" + giftsPreferences +
                '}';
    }
}
