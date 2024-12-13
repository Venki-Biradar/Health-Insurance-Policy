package Health_INSURANCE_POLICY.MANAGE_CLAIMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Health_INSURANCE_POLICY.MANAGE_CLAIMS.Entity.Claim;
import Health_INSURANCE_POLICY.MANAGE_CLAIMS.Repository.ClaimRepository;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    /**
     * Retrieve all claims.
     *
     * @return List of all claims.
     */
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    /**
     * Retrieve a claim by its ID.
     *
     * @param claimId The ID of the claim.
     * @return Optional containing the claim if found, empty otherwise.
     */
    public Optional<Claim> getClaimById(Integer claimId) {
        return claimRepository.findById(claimId);
    }

    /**
     * Add a new claim.
     *
     * @param claim The claim to be added.
     * @return The saved claim.
     */
//    public Claim addClaim(Claim claim) {
//        return claimRepository.save(claim);
//    }

    public List<Claim> addClaims(List<Claim> claims) {
        return claimRepository.saveAll(claims);
    }
    /**
     * Update an existing claim.
     *
     * @param claimId The ID of the claim to update.
     * @param updatedClaim The updated claim data.
     * @return The updated claim, or null if the claim does not exist.
     */
    public Claim updateClaim(Integer claimId, Claim updatedClaim) {
        return claimRepository.findById(claimId).map(existingClaim -> {
            existingClaim.setPolicyId(updatedClaim.getPolicyId());
            existingClaim.setClientId(updatedClaim.getClientId());
            existingClaim.setClaimType(updatedClaim.getClaimType());
            existingClaim.setClaimAmount(updatedClaim.getClaimAmount());
            existingClaim.setClaimStatus(updatedClaim.getClaimStatus());
            existingClaim.setClaimDescription(updatedClaim.getClaimDescription());
            return claimRepository.save(existingClaim);
        }).orElse(null);
    }

    /**
     * Delete a claim by its ID.
     *
     * @param claimId The ID of the claim to delete.
     * @return True if the claim was deleted, false otherwise.
     */
    public boolean deleteClaim(Integer claimId) {
        if (claimRepository.existsById(claimId)) {
            claimRepository.deleteById(claimId);
            return true;
        }
        return false;
    }
}
