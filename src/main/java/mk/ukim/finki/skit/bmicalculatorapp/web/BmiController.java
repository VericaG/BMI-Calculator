package mk.ukim.finki.skit.bmicalculatorapp.web;

import mk.ukim.finki.skit.bmicalculatorapp.dto.BmiRequest;
import mk.ukim.finki.skit.bmicalculatorapp.dto.BmiResponse;
import mk.ukim.finki.skit.bmicalculatorapp.exceptions.InvalidBmiException;
import mk.ukim.finki.skit.bmicalculatorapp.service.BmiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bmi")
public class BmiController {

    private final BmiService bmiService;

    public BmiController(BmiService bmiService) {
        this.bmiService = bmiService;
    }

    @PostMapping
    public BmiResponse calculate(@RequestBody BmiRequest request) {
        return bmiService.calculate(request);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(InvalidBmiException.class)
        public ResponseEntity<String> handleInvalidBmi(InvalidBmiException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleOtherExceptions(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
        }
    }

}