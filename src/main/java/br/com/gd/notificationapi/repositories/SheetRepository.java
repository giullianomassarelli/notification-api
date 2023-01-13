package br.com.gd.notificationapi.repositories;

import br.com.gd.notificationapi.entities.SheetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SheetRepository extends MongoRepository <SheetEntity, String> {
}
