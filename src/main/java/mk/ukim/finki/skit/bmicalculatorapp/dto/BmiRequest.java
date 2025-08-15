package mk.ukim.finki.skit.bmicalculatorapp.dto;

public class BmiRequest {
    private double height;
    private double weight;
    private int age;
    private String unit;  // "metric" or "imperial"



    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}