package com.bootcamp.bc.bc_yahoo_finance.infra;

import java.time.Duration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisHelper {

  private RedisTemplate<String, String> redisTemplate;
  private ObjectMapper objectMapper;

  public RedisHelper(RedisConnectionFactory factory,
      ObjectMapper objectMapper) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }

  public <T> void set(String key, T obj) {
    try {
      String json = this.objectMapper.writeValueAsString(obj);
      redisTemplate.opsForValue().set(key, json);
    } catch (JsonProcessingException e) {
      throw new RedisException();
    }
  }

  public <T> void set(String key, T obj, Duration duration) {
    try {
      String json = this.objectMapper.writeValueAsString(obj);
      redisTemplate.opsForValue().set(key, json, duration);
    } catch (JsonProcessingException e) {
      throw new RedisException();
    }
  }

  public <T> T get(String key, Class<T> clazz) {
    try {
      String json = this.redisTemplate.opsForValue().get(key);
      return json == null ? null : this.objectMapper.readValue(json, clazz);
    } catch (JsonProcessingException e) {
      throw new RedisException();
    }
  }
  
  public void delete(String key) {
      this.redisTemplate.delete(key);
  }

  public void clean() {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.serverCommands().flushDb();
            return null;
        });
    }
}
