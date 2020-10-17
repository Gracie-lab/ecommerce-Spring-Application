package com.ecommerce.ecommerce.models.paymentInformation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingInformationRepository extends MongoRepository<BillingInformation, String> {
}
