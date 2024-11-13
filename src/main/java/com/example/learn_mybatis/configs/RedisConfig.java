package com.example.learn_mybatis.configs;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        // Jedis Pool Configuration
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setTestOnBorrow(true);

        // Standalone Redis Configuration (host, port, etc.)
        RedisStandaloneConfiguration redisStandaloneConfig = new RedisStandaloneConfiguration();
        redisStandaloneConfig.setHostName("localhost");
        redisStandaloneConfig.setPort(6379);

        // Jedis Client Configuration
        JedisClientConfiguration jedisClientConfig = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(jedisPoolConfig)
                .build();

        // Creating JedisConnectionFactory with Standalone Configuration and Jedis Client Configuration
        return new JedisConnectionFactory(redisStandaloneConfig, jedisClientConfig);

    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
