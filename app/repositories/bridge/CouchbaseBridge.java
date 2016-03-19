package repositories.bridge;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import rx.Observable;

import java.util.List;

/**
 * Created by admin on 3/19/16.
 */
public class CouchbaseBridge {

    private List<java.lang.String> hosts;

    private String bucket;

    private Bucket syncBucket;

    private AsyncBucket asyncBucket;

    public CouchbaseBridge(List<String> hosts, String bucket) {
        this.hosts = hosts;
        this.bucket = bucket;
    }

    private void openBucket() {
        if (this.asyncBucket != null) {
            return;
        }
        Cluster cluster = CouchbaseCluster.create(this.hosts);
        this.syncBucket = cluster.openBucket(this.bucket);
        this.asyncBucket = this.syncBucket.async();
    }

    public Observable<JsonDocument> get(String id) {
        this.openBucket();
        return Observable.just(id).flatMap(asyncBucket::get);
    }

}
