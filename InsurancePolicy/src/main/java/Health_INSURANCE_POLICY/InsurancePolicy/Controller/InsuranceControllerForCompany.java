package Health_INSURANCE_POLICY.InsurancePolicy.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance")
public class InsuranceControllerForCompany {

	@PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculatePremium(@RequestBody Map<String, Object> data) {
        Integer coverage = (Integer) data.get("coverage"); // e.g., 10 Lakh
        Integer years = (Integer) data.get("years"); // e.g., 5 years
        Double basePremium = 466.0; // Base premium per month for ₹10 Lakh

        // Calculate premium
        Double monthlyPremium = basePremium * (coverage / 10.0);
        Double totalPremium = monthlyPremium * 12 * years;

        Map<String, Object> response = new HashMap<>();
        response.put("monthlyPremium", monthlyPremium);
        response.put("totalPremium", totalPremium);

        return ResponseEntity.ok(response);
    }
}
	

