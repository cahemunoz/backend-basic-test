package com.ifood.config.scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.cache.CacheBuilder;
import com.ifood.util.Constants;

@Configuration
@EnableCaching
@EnableScheduling
public class WeatherCacheScheduledConfig {

	private static final Logger log = LoggerFactory.getLogger(WeatherCacheScheduledConfig.class);

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();

		cacheManager.setCaches(Arrays.asList(
				new ConcurrentMapCache(Constants.CACHE_WEATHERS, CacheBuilder.newBuilder()
																	.expireAfterWrite(30, TimeUnit.MINUTES)
																	.maximumSize(100)
																	.build()
																	.asMap(), true)));

		return cacheManager;
	}

	
	@CacheEvict(allEntries = true, value = {Constants.CACHE_WEATHERS})
	@Scheduled(fixedDelay = 10000 * 6, initialDelay = 500) 
	public void clearCacheEvict() { 
			log.info("Cache limpo em {}!",
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
	}
}
