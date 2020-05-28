package es.alafia.server.repository;

import es.alafia.server.model.DinnerTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinnerTableRepository extends MongoRepository<DinnerTable, String> {
}
