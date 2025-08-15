package mk.ukim.finki.skit.bmicalculatorapp.dto;

import mk.ukim.finki.skit.bmicalculatorapp.enumerations.BmiCategory;

public class BmiResponse {
    private double bmi;
    private BmiCategory category;

    public BmiResponse(double bmi, BmiCategory category) {
        this.bmi = bmi;
        this.category = category;
    }

    public double getBmi() { return bmi; }
    public BmiCategory getCategory() { return category; }
}
