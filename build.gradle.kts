plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.iceberg:iceberg-api:1.3.1")
    implementation("org.apache.iceberg:iceberg-core:1.3.1")
    implementation("org.apache.iceberg:iceberg-data:1.3.1")
    implementation("org.apache.iceberg:iceberg-parquet:1.3.1")
    implementation("org.apache.iceberg:iceberg-mr:1.3.1")
    implementation("org.apache.hadoop:hadoop-common:3.3.6")
    implementation("org.apache.hadoop:hadoop-mapreduce-client-core:3.3.6")
    implementation("org.apache.hadoop:hadoop-aws:3.3.2")
}

tasks.test {
    useJUnitPlatform()
}
