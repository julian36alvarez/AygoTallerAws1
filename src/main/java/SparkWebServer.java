import entity.Uuid;
import org.bson.Document;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.List;
import java.util.logging.Level;

import static spark.Spark.*;


//java -cp target/dependency/*;target/classes/ SparkWebServer

public class SparkWebServer {


    public static void main(String... args){

        System.setProperty("spark.testing.memory", "2147480000");

        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");

        get("uuid", (req,res) -> {
            Uuid uuid = new Uuid();
            uuid.setUuid(java.util.UUID.randomUUID().toString());
            uuid.setDate(new java.util.Date().toString());

             dao.UuidDao uuidDao = new dao.UuidDao();
             uuidDao.saveUuid(uuid);

            List<Document> result = uuidDao.getAllUuids();
            return result.stream().map(Document::toJson).reduce((a,b)->a+b).get();
        });

        get("/memory", (req, res) -> {
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            return memoryMXBean.getHeapMemoryUsage().getUsed();
        });

        get("/health", (req, res) -> "OK");

        get("/log", (req, res) -> {
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.mongodb");
            logger.setLevel(Level.FINE);
            return "OK";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }


}
