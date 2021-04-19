package com.example.redisdemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfiguration {
	
	@Bean
	@ConditionalOnBean(RedisTemplate.class)
	public RedisTemplate<String, Object> template(RedisConnectionFactory connectionFactory) {
		
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		
		configureTemplate(connectionFactory, template);
		
		return template;
	}
	
	@Bean
	@ConditionalOnBean(RedisTemplate.class)
	public StringRedisTemplate stringTemplate(RedisConnectionFactory connectionFactory) {
		
		var template = new StringRedisTemplate();
		configureTemplate(connectionFactory, template);
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
		redisTemplate.setEnableTransactionSupport(true);
		
		
		return template;
	}
	
	private void configureTemplate(final RedisConnectionFactory connectionFactory, final RedisTemplate template) {
		template.setConnectionFactory(connectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new JdkSerializationRedisSerializer());
		template.setValueSerializer(new JdkSerializationRedisSerializer());
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
	}
	
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		
		return RedisCacheManager
				       .builder(redisConnectionFactory)
				       .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues())
				       .build();
	}
	
	@Bean
	public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		return container;
	}
}
