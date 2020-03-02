package io.gkmee.redis.ride;

import java.util.HashSet;
import java.util.Set;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

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
		JedisCluster jc = new JedisCluster(jedisClusterNodes);
		jc.set("foo", "bar");
		String jedisValue = jc.get("foo");
		System.out.println(jedisValue);
		jc.close();

	}

}
