package repositories.bridge;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import models.User;
import rx.Observable;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 3/19/16.
 */
public class CouchbaseDriver {

    private List<java.lang.String> hosts;

    private String bucket;

    private Bucket syncBucket;

    private AsyncBucket asyncBucket;

    private CouchbaseEnvironment couchbaseEnvironment = DefaultCouchbaseEnvironment.create();

    public CouchbaseDriver(List<String> hosts, String bucket) {
        this.hosts = hosts;
        this.bucket = bucket;
    }

    private void openBucket() {
        if (this.asyncBucket != null) {
            return;
        }
        Cluster cluster = CouchbaseCluster.create(couchbaseEnvironment, this.hosts);
        this.syncBucket = cluster.openBucket(this.bucket);
        this.asyncBucket = this.syncBucket.async();
    }

    public Observable<JsonDocument> asyncSet(JsonDocument json) {
        this.openBucket();
        return asyncBucket.upsert(json);
    }

    public Observable<RawJsonDocument> asyncSet(RawJsonDocument json) {
        this.openBucket();
        return asyncBucket.upsert(json);
    }

    public Observable<List<String>> asyncGetByIds(List<String> ids) {
        this.openBucket();
        return Observable.from(ids).flatMap(a -> asyncBucket.get(a, RawJsonDocument.class)).toList()
                .map(a -> a.stream().map(s -> s.content()).collect(Collectors.toList()));
    }

}
