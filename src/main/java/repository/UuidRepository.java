package repository;

import entity.Uuid;
import org.bson.Document;

import java.util.List;

public interface UuidRepository {

    public List<Document> getAllUuids();
    public  void saveUuid(Uuid uuid);

}
