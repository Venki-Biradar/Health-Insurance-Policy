package Health_INSURANCE_POLICY.MANAGE_CLAIMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import Health_INSURANCE_POLICY.MANAGE_CLAIMS.Entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
    // Additional query methods can be added here if needed.
}