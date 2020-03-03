package io.gkmee.redis.ride;

import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;

public class RideRedis {

	public static void main(String[] args) {

		// Lettuce
		// Syntax: redis://[password@]host[:port]
		RedisClusterClient redisClient = RedisClusterClient.create("redis://192.168.10.20:6379");
		StatefulRedisClusterConnection<String, String> connection = redisClient.connect();
		System.out.println("Connected to Redis");
		RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
		syncCommands.set("key", "Hello, Redis!");
		String value = syncCommands.get("key");
		System.out.println(value);
		connection.close();
		redisClient.shutdown();

		// Jedis
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		// Jedis Cluster will attempt to discover cluster nodes automatically
		jedisClusterNodes.add(new HostAndPort("192.168.10.20", 6379));
		JedisCluster cluster = new JedisCluster(jedisClusterNodes);
		cluster.set("foo", "bar");
		String jedisValue = cluster.get("foo");
		System.out.println(jedisValue);
		cluster.close();
		
		
		//Jedis Sentinel
		
		/* if one single redis master
		Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.10.20:26379");
        sentinels.add("192.168.10.21:26379");
        sentinels.add("192.168.10.22:26379");
        JedisSentinelPool pool = new JedisSentinelPool("redis3", sentinels);
        Jedis jedis = pool.getResource();
        Socket socket = jedis.getClient().getSocket();
        System.out.println("remote socket: "+socket.getRemoteSocketAddress());
        jedis.set("myKey", "You Got It!");
        System.out.println(jedis.get("myKey"));
        jedis.close();
        pool.close();
        */
		
		
        HostAndPort sentinel = new HostAndPort("192.168.10.20", 26379);
        Jedis jds = new Jedis(sentinel);
        List<Map<String, String>> masters = jds.sentinelMasters();
        for (Map<String, String> master : masters) {
        	System.out.println(master.get("name")+" - "+master.get("ip")+":"+master.get("port"));
        }
        jds.close();

	}

}
