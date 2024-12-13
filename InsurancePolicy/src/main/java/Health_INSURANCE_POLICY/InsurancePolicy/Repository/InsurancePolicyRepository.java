package Health_INSURANCE_POLICY.InsurancePolicy.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Health_INSURANCE_POLICY.InsurancePolicy.InsurancePolicyEntity.InsurancePolicy;


@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {

	//List<InsurancePolicy> findAll();
    // JpaRepository provides built-in CRUD methods such as save, findById, findAll, deleteById, etc.

    // Additional custom query methods (if needed) can be added here
}
