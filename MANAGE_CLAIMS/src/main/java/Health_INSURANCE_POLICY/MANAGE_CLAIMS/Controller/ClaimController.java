package Health_INSURANCE_POLICY.MANAGE_CLAIMS.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Health_INSURANCE_POLICY.MANAGE_CLAIMS.Entity.Claim;
import Health_INSURANCE_POLICY.MANAGE_CLAIMS.Service.ClaimService;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    /**
     * Get all claims.
     *
     * @return ResponseEntity with the list of claims.
     */
    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }

    /**
     * Get a claim by its ID.
     *
     * @param claimId The ID of the claim.
     * @return ResponseEntity with the claim if found, or NOT_FOUND status.
     */
    @GetMapping("/{claimId}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Integer claimId) {
        Optional<Claim> claim = claimService.getClaimById(claimId);
        return claim.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Add a new claim.
     *
     * @param claim The claim to be added.
     * @return ResponseEntity with the saved claim.
     */
//    @PostMapping
//    public ResponseEntity< Claim> addClaim(@RequestBody Claim claim) {
//        Claim savedClaim = claimService.addClaim(claim);
//        return new ResponseEntity<>(savedClaim, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<List<Claim>> addClaims(@RequestBody List<Claim> claims) {
        List<Claim> savedClaims = claimService.addClaims(claims);
        return new ResponseEntity<>(savedClaims, HttpStatus.CREATED);
    }
    /**
     * Update an existing claim.
     *
     * @param claimId      The ID of the claim to update.
     * @param updatedClaim The updated claim data.
     * @return ResponseEntity with the updated claim, or NOT_FOUND status if the claim does not exist.
     */
    @PutMapping("/{claimId}")
    public ResponseEntity<Claim> updateClaim(@PathVariable Integer claimId, @RequestBody Claim updatedClaim) {
        Claim updated = claimService.updateClaim(claimId, updatedClaim);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a claim by its ID.
     *
     * @param claimId The ID of the claim to delete.
     * @return ResponseEntity with NO_CONTENT status if successful, or NOT_FOUND status if the claim does not exist.
     */
    @DeleteMapping("/{claimId}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Integer claimId) {
        boolean isDeleted = claimService.deleteClaim(claimId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
