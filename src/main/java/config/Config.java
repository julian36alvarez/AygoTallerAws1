package config;

import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.ReadConfig;
import com.mongodb.spark.config.WriteConfig;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.sql.SparkSession;
import org.bson.Document;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Config {

    public SparkSession getMongoDbConnection() {
        System.out.println("     _       _ _                _    _\n" +
                "    | |_   _| (_) __ _ _ __    / \\  | |_   ____ _ _ __ ___ ____\n" +
                " _  | | | | | | |/ _` | '_ \\  / _ \\ | \\ \\ / / _` | '__/ _ \\_  /\n" +
                "| |_| | |_| | | | (_| | | | |/ ___ \\| |\\ V / (_| | | |  __// /\n" +
                " \\___/ \\__,_|_|_|\\__,_|_| |_/_/   \\_\\_| \\_/ \\__,_|_|  \\___/___|\n");

        return SparkSession.builder()
                .master("local")
                .appName("MongoSparkConnectorIntro")
                .config("spark.mongodb.input.uri", "mongodb://54.224.131.70:27017/test.aygo")
                .config("spark.mongodb.output.uri", "mongodb://54.224.131.70:27017/test.aygo")
                .getOrCreate();
    }

    public void writeToMongoDb(String uuid, String date) {


        SparkSession spark = getMongoDbConnection();
        spark.sparkContext().setLogLevel("INFO");

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        Map<String, String> writeOverrides = new HashMap<>();
        writeOverrides.put("collection", "aygo");
        writeOverrides.put("writeConcern.w", "majority");
        WriteConfig writeConfig = WriteConfig.create(jsc).withOptions(writeOverrides);

        JavaRDD<Document> documents = jsc.parallelize(Arrays.asList(new Document("uuid", uuid).append("date", date)))
                .map(doc -> Document.parse(doc.toJson()));
        MongoSpark.save(documents, writeConfig);

        spark.close();

    }

    public List<Document> getLastTenUuids() {
        SparkSession spark = getMongoDbConnection();
        spark.sparkContext().setLogLevel("INFO");

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        Map<String, String> readOverrides = new HashMap<>();
        readOverrides.put("collection", "aygo");
        readOverrides.put("readPreference.name", "secondaryPreferred");
        ReadConfig readConfig = ReadConfig.create(jsc).withOptions(readOverrides);
        JavaMongoRDD<Document> customRdd = MongoSpark.load(jsc, readConfig);

        List<Document> result = customRdd.sortBy(doc -> doc.get("date"), false, 1).take(10);
        spark.close();
        return result;
    }


}
