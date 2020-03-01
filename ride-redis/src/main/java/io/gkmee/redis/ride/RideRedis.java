package io.gkmee.redis.ride;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

public class RideRedis {
	

	public static void main(String[] args) {
		
		
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

	}

}
