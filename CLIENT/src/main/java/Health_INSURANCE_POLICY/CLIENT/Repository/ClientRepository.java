package Health_INSURANCE_POLICY.CLIENT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Health_INSURANCE_POLICY.CLIENT.Entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>  {

}
