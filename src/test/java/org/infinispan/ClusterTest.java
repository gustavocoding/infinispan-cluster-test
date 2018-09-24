package org.infinispan;


import java.util.concurrent.TimeUnit;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.spark.test.CacheType;
import org.infinispan.spark.test.Cluster;
import org.jboss.dmr.scala.ModelNode;
import org.junit.Test;

import scala.Option;
import scala.concurrent.duration.Duration;

public class ClusterTest {

   @Test
   public void testClusterParallel() {
      // Start a cluster of 3 nodes
      String property = System.getProperty("server.location");
      if(property == null) {
         System.out.println("System property 'server.location' not found!");
         System.exit(0);
      }

      Cluster cluster = new Cluster(3, property);
      cluster.startAndWait(Duration.apply(30, TimeUnit.SECONDS), true);

      // Create a cache
      cluster.createCache("test", CacheType.DISTRIBUTED(), Option.apply(null));

      // Populate some data
      RemoteCacheManager remoteCacheManager = cluster.getFirstServer().remoteCacheManager();

      RemoteCache<Object, Object> test = remoteCacheManager.getCache("test");
      test.put(1, "One");

      // Shutdown the cluster
      cluster.shutDown();
   }
}

