package com.code.nts_prj.redis;

public interface RedisService {
	Object findByKey(Object key);

	void set(Object key, Object value);

	void delete(Object key);
}
