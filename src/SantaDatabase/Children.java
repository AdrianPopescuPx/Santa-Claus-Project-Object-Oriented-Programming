package SantaDatabase;

import java.util.ArrayList;

public class Children {

    private final int id;
    private final String lastName;
    private final String firstName;
    private final int age;
    private final String city;
    private final Double niceScore;
    private Double averageScore;
    private Double assignedBudget;
    private ArrayList<String> giftsPreferences;
    private ArrayList<Double> niceScoreList = new ArrayList<>();

    private ArrayList<Double> averageScoreHistory = new ArrayList<>();
    private ArrayList<SantaGiftsList> recievedGifts = new ArrayList<>();


    public Children(final int id, final String lastName,
                    final String firstName, final int age,
                    final String city, final Double niceScore,
                    final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public void addScoreToList(Double number) {
        niceScoreList.add(number);
    }
    public ArrayList<Double> getNiceScoreList() {
        return niceScoreList;
    }
    public void addGifts(SantaGiftsList gift) {
        recievedGifts.add(gift);
    }
    public ArrayList<SantaGiftsList> getRecievedGifts() {
        return recievedGifts;
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

    public Double getNiceScore() {
        return niceScore;
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
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                '}';
    }
}
