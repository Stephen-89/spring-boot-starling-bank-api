package com.stephen.starling.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AccountUidCacheManager {

	private static AccountUidCacheManager instance;
	private static Object monitor = new Object();
	private Map<String, String> cache = Collections.synchronizedMap(new HashMap<>());

	private AccountUidCacheManager() {
	}

	public void put(String cacheKey, String value) {
		cache.put(cacheKey, value);
	}

	public String get(String cacheKey) {
		return cache.get(cacheKey);
	}

	public void clear(String cacheKey) {
		cache.put(cacheKey, null);
	}

	public void clear() {
		cache.clear();
	}

	public static AccountUidCacheManager getInstance() {
		if (instance == null) {
			synchronized (monitor) {
				if (instance == null) {
					instance = new AccountUidCacheManager();
				}
			}
		}
		return instance;
	}

}
