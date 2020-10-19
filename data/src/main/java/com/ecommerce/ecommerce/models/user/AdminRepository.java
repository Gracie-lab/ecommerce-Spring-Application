package com.ecommerce.ecommerce.models.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<StoreAdmin, String> {
}
