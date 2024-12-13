package Health_INSURANCE_POLICY.MANAGE_CLAIMS.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Integer claimId;

    @NotNull(message = "Policy ID is required")
    @Column(name = "policy_id")
    private Integer policyId; // Assuming a claim is associated with a specific policy

    @NotNull(message = "Client ID is required")
    @Column(name = "client_id")
    private Integer clientId; // Reference to the client who made the claim

    @NotBlank(message = "Claim type is required")
    @Column(name = "claim_type")
    private String claimType;

    @NotNull(message = "Claim amount is required")
    @Column(name = "claim_amount")
    private Double claimAmount;

    @Column(name = "claim_status")
    private String claimStatus = "Pending"; // Default claim status

    @Column(name = "claim_description", columnDefinition = "TEXT")
    private String claimDescription;

    @Column(name = "created_at", nullable = false, updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedAt;
}
