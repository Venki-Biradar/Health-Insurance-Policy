package Health_INSURANCE_POLICY.InsurancePolicy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Health_INSURANCE_POLICY.InsurancePolicy.InsurancePolicyEntity.InsurancePolicy;
import Health_INSURANCE_POLICY.InsurancePolicy.Repository.InsurancePolicyRepository;



@Service
public class InsurancePolicyService {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    /**
     * Retrieve all insurance policies.
     *
     * @return List of all insurance policies.
     */
    public List<InsurancePolicy> getAllPolicies() {
        return insurancePolicyRepository.findAll();
    }
 
// // Add this method in InsurancePolicyService
//    public List<InsurancePolicy> getPoliciesSortedBy(String sortBy) {
//        return insurancePolicyRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
//    }

    public List<InsurancePolicy> getPoliciesSortedBy(String sortBy) {
        // Dynamically sort using the `Sort` class
        Sort sort = Sort.by(sortBy);
        return insurancePolicyRepository.findAll(sort);
    }



    /**
     * Retrieve an insurance policy by its ID.
     *
     * @param policyId The ID of the insurance policy.
     * @return Optional containing the insurance policy if found, empty otherwise.
     */
//    public Optional<InsurancePolicy> getPolicyById(Integer policyId) {
//        return insurancePolicyRepository.findById(policyId);
//    }
    
    public Optional<InsurancePolicy> getPolicyById(Integer policyId) {
        return insurancePolicyRepository.findById(policyId);
    }


    /**
     * Add a new insurance policy.
     *
     * @param insurancePolicy The insurance policy to be added.
     * @return The saved insurance policy.
     */
//    public InsurancePolicy addPolicy(InsurancePolicy insurancePolicy) {
//        return insurancePolicyRepository.save(insurancePolicy);
//    }
    
    public List<InsurancePolicy> addPolicies(List<InsurancePolicy> insurancePolicies) {
        return insurancePolicyRepository.saveAll(insurancePolicies);
    }

    /**
     * Update an existing insurance policy.
     *
     * @param policyId The ID of the insurance policy to update.
     * @param updatedPolicy The updated insurance policy data.
     * @return The updated insurance policy, or null if the policy does not exist.
     */
    public InsurancePolicy updatePolicy(Integer policyId, InsurancePolicy updatedPolicy) {
        return insurancePolicyRepository.findById(policyId).map(existingPolicy -> {
            existingPolicy.setPolicyName(updatedPolicy.getPolicyName());
            existingPolicy.setPolicyType(updatedPolicy.getPolicyType());
            existingPolicy.setPremiumAmount(updatedPolicy.getPremiumAmount());
            existingPolicy.setCoverageAmount(updatedPolicy.getCoverageAmount());
            existingPolicy.setDurationYears(updatedPolicy.getDurationYears());
            return insurancePolicyRepository.save(existingPolicy);
        }).orElse(null);
    }

    /**
     * Delete an insurance policy by its ID.
     *
     * @param policyId The ID of the insurance policy to delete.
     * @return True if the policy was deleted, false otherwise.
     */
//    public boolean deletePolicy(Integer policyId) {
//        if (insurancePolicyRepository.existsById(policyId)) {
//            insurancePolicyRepository.deleteById(policyId);
//            return true;
//        }
//        return false;
        
        public boolean deletePolicy(Integer policyId) {
            Optional<InsurancePolicy> policy = insurancePolicyRepository.findById(policyId);

            if (policy.isPresent()) {
                insurancePolicyRepository.deleteById(policyId);
                return true;
            }
            return false;
        }
    }

