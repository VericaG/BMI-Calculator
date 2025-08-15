package mk.ukim.finki.skit.bmicalculatorapp.service;

import mk.ukim.finki.skit.bmicalculatorapp.dto.BmiRequest;
import mk.ukim.finki.skit.bmicalculatorapp.dto.BmiResponse;
import mk.ukim.finki.skit.bmicalculatorapp.enumerations.BmiCategory;
import mk.ukim.finki.skit.bmicalculatorapp.exceptions.InvalidBmiException;
import org.springframework.stereotype.Service;

@Service
public class BmiService {

    public BmiResponse calculate(BmiRequest request) {
        double height = request.getHeight();
        double weight = request.getWeight();
        int age = request.getAge();
        String unit = request.getUnit();

        // Determine if adult
        boolean isAdult = age >= 18;

        // Convert imperial to metric if needed
        if ("imperial".equalsIgnoreCase(unit)) {
            weight = weight * 0.453592;      // pounds to kg
            height = height * 0.0254;        // inches to meters
        }

        // Throw exception for invalid input
        if (height <= 0 || weight <= 0) {
            throw new InvalidBmiException("Height and weight must be positive values.");
        }

        double bmi = weight / (height * height);
        BmiCategory category = classifyBmi(bmi, isAdult);

        return new BmiResponse(Math.round(bmi * 100.0) / 100.0, category);
    }

    private BmiCategory classifyBmi(double bmi, boolean isAdult) {
        // Underweight
        if ((isAdult && bmi < 18.5) || (!isAdult && bmi < 17.0)) {
            return BmiCategory.UNDERWEIGHT;
        }
        // Normal
        else if ((isAdult && bmi < 25.0) || (!isAdult && bmi < 22.0)) {
            return BmiCategory.NORMAL;
        }
        // Overweight+ (combines overweight and obese)
        else {
            return BmiCategory.OVERWEIGHT;
        }
    }
}