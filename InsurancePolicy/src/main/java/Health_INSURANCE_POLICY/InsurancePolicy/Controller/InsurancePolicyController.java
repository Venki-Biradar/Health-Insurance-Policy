package Health_INSURANCE_POLICY.InsurancePolicy.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Health_INSURANCE_POLICY.InsurancePolicy.InsurancePolicyEntity.InsurancePolicy;
import Health_INSURANCE_POLICY.InsurancePolicy.Service.InsurancePolicyService;

@RestController
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    /**
     * Retrieve all insurance policies.
     *
     * @return List of insurance policies.
     */
    @GetMapping("/insurance-policies")
    public ResponseEntity<List<InsurancePolicy>> getAllPolicies() {
        List<InsurancePolicy> policies = insurancePolicyService.getAllPolicies();
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }
    
//    @GetMapping("/insurance-policies/sort")
//    public ResponseEntity<List<InsurancePolicy>> getSortedPolicies(
//            @RequestParam String sortBy) {
//        if (!sortBy.equals("policyName") && !sortBy.equals("premiumAmount")) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Validate sort field
//        }
//        List<InsurancePolicy> sortedPolicies = insurancePolicyService.getPoliciesSortedBy(sortBy);
//        return new ResponseEntity<>(sortedPolicies, HttpStatus.OK);
//    }

    @GetMapping("/insurance-policies/sort")
    public ResponseEntity<List<InsurancePolicy>> getSortedPolicies(@RequestParam String sortBy) {
        // Validate the sortBy field
        if (!sortBy.equalsIgnoreCase("policyName") && !sortBy.equalsIgnoreCase("premiumAmount")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid sort field
        }

        // Get sorted policies from the service layer
        List<InsurancePolicy> sortedPolicies = insurancePolicyService.getPoliciesSortedBy(sortBy);
        return new ResponseEntity<>(sortedPolicies, HttpStatus.OK);
    }

    /**
     * Retrieve an insurance policy by its ID.
     *
     * @param policyId The ID of the insurance policy.
     * @return The insurance policy, or 404 if not found.
     */
//    @GetMapping("/insurance-policies/{policyId}")
//    public ResponseEntity<InsurancePolicy> getPolicyById(@PathVariable Integer policyId) {
//        Optional<InsurancePolicy> policy = insurancePolicyService.getPolicyById(policyId);
//        return policy.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
//                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
    
    @GetMapping("/insurance-policies/{policyId}")
    public ResponseEntity<InsurancePolicy> getPolicyById(@PathVariable Integer policyId) {
        Optional<InsurancePolicy> policy = insurancePolicyService.getPolicyById(policyId);

        // Return the policy if found, otherwise return NOT_FOUND
        return policy.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * Add a new insurance policy.
     *
     * @param insurancePolicy The insurance policy to be added.
     * @return The saved insurance policy.
     */
//    @PostMapping("/insurance-policies")
//    public ResponseEntity<InsurancePolicy> addPolicy(@RequestBody InsurancePolicy insurancePolicy) {
//        InsurancePolicy savedPolicy = insurancePolicyService.addPolicy(insurancePolicy);
//        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
//    }
    
    @PostMapping("/insurance-policies")
    public ResponseEntity<List<InsurancePolicy>> addPolicies(@RequestBody List<InsurancePolicy> insurancePolicies) {
        List<InsurancePolicy> savedPolicies = insurancePolicyService.addPolicies(insurancePolicies);
        return new ResponseEntity<>(savedPolicies, HttpStatus.CREATED);
    }


    /**
     * Update an existing insurance policy.
     *
     * @param policyId The ID of the insurance policy to update.
     * @param updatedPolicy The updated insurance policy data.
     * @return The updated insurance policy, or 404 if not found.
     */
    @PutMapping("/insurance-policies/{policyId}")
    public ResponseEntity<InsurancePolicy> updatePolicy(@PathVariable Integer policyId, 
                                                       @RequestBody InsurancePolicy updatedPolicy) {
        InsurancePolicy updated = insurancePolicyService.updatePolicy(policyId, updatedPolicy);
        return updated != null ? new ResponseEntity<>(updated, HttpStatus.OK)
                               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/submitInsuranceDetails")
    public String submitInsuranceDetails(
        @RequestParam("gender") String gender,
        @RequestParam(value = "members", required = false) String[] members,
        Model model
    ) {
        // Pass data to the model for displaying on the confirmation page
        model.addAttribute("gender", gender);
        model.addAttribute("members", members != null ? Arrays.asList(members) : Collections.emptyList());

        return "confirmationPage"; // A new JSP page to display the details
    }


    /**
     * Delete an insurance policy by its ID.
     *
     * @param policyId The ID of the insurance policy to delete.
     * @return 200 if deleted, 404 if not found.
     */
//    @DeleteMapping("/insurance-policies/{policyId}")
//    public ResponseEntity<Void> deletePolicy(@PathVariable Integer policyId) {
//        boolean isDeleted = insurancePolicyService.deletePolicy(policyId);
//        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
//                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        @DeleteMapping("/insurance-policies/{policyId}")
        public ResponseEntity<Void> deletePolicy(@PathVariable Integer policyId) {
            // Call the service method to delete the policy
            boolean isDeleted = insurancePolicyService.deletePolicy(policyId);

            // Return NO_CONTENT if deleted successfully, NOT_FOUND otherwise
            return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                             : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

