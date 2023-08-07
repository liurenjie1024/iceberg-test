import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.data.IcebergGenerics;
import org.apache.iceberg.hadoop.HadoopCatalog;

public class Main {
    public static void main(String[] args) throws Exception {
        icebergApi();
    }

    static void icebergApi() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.s3a.endpoint", "http://localhost:9000");
        conf.set("fs.s3a.path.style.access", "true");
        conf.set("fs.s3a.access.key", "admin");
        conf.set("fs.s3a.secret.key", "password");
        String warehousePath = "s3a://icebergdata/demo";
        try(var catalog = new HadoopCatalog(conf, warehousePath)) {
            var name = TableIdentifier.of("s1", "t1");
            var table = catalog.loadTable(name);

            System.out.println(table.location());

            var io = table.io();
            for (var manifest: table.currentSnapshot().allManifests(io)) {
                System.out.println(manifest.toString());
            }

            for(var task : table.newScan().planFiles()) {
                System.out.println(task.file());
            }

            try(var records = IcebergGenerics.read(table).build()) {
                for (var record: records) {
                    System.out.println(record);
                }
            }
        }
    }
}
