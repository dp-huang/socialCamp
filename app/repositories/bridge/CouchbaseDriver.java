package repositories.bridge;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import rx.Observable;

import java.util.List;

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

    public Observable<List<JsonDocument>> asyncGetById(String id) {
        this.openBucket();
        return Observable.just(id).flatMap(asyncBucket::get).toList();
    }

    public Observable<List<JsonDocument>> asyncGetByIds(List<String> ids) {
        this.openBucket();
        return Observable.from(ids).flatMap(asyncBucket::get).toList();
    }

    public Observable<JsonDocument> asyncSet(JsonDocument json) {
        this.openBucket();
        return asyncBucket.upsert(json);
    }

}
