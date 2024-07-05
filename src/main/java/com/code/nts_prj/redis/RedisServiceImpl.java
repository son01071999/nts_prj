package com.code.nts_prj.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
	private final RedisTemplate<Object, Object> redisTemplate;

	public RedisServiceImpl(RedisTemplate<Object, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Object findByKey(Object key) {
		return this.redisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(Object key, Object value) {
		this.redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void delete(Object key) {
		this.redisTemplate.delete(key);
	}
}
