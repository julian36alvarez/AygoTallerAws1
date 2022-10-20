package dao;

import entity.Uuid;
import org.bson.Document;
import repository.UuidRepository;

import java.util.HashMap;
import java.util.List;

import config.Config;

public class UuidDao implements UuidRepository {

    private HashMap<String, Uuid> uuids = new HashMap<>();
    private Config config = new Config();

    public UuidDao(){
        uuids = new HashMap<>();
    }


    @Override
    public List<Document> getAllUuids() {
        // serialize to JSON
      return  config.getLastTenUuids();
    }

    @Override
    public void saveUuid(Uuid uuid) {
        config.writeToMongoDb(uuid.getUuid(), uuid.getDate());
        uuids.put(uuid.getUuid(), uuid);
    }
}
