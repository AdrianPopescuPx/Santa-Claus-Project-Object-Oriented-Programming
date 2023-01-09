package SantaDatabase;

import java.util.ArrayList;

public class Children {

    private final int id;
    private final String lastName;
    private final String firstName;
    private final int age;
    private final String city;
    private final int niceScore;
    private ArrayList<String> giftsPreferences;

    public Children(final int id, final String lastName,
                    final String firstName, final int age,
                    final String city, final int niceScore,
                    final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
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

    public int getNiceScore() {
        return niceScore;
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
