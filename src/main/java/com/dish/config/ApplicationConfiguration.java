package com.dish.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;

import com.dish.GemfireBootApplication;
import com.dish.model.Customer;
import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.PartitionAttributesFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionFactory;
import com.gemstone.gemfire.cache.RegionShortcut;

@Configuration
@EnableAutoConfiguration
public class ApplicationConfiguration {

	private static final String CUSTOMER_REGION_NAME = "Customer";
	static final String DEFAULT_GEMFIRE_LOG_LEVEL = "info";

	String applicationName() {
		return GemfireBootApplication.class.getSimpleName();
	}

	String gemfireLogLevel() {
		return System.getProperty("gemfire.log-level", DEFAULT_GEMFIRE_LOG_LEVEL);
	}

	@Bean
	CacheFactoryBean gemfireCache() {

		CacheFactoryBean gemfireCache = new CacheFactoryBean();
		gemfireCache.setClose(true);
		gemfireCache.setProperties(gemfireProperties());

		return gemfireCache;
	}

	Properties gemfireProperties() {
		
		Properties gemfireProperties = new Properties();
		gemfireProperties.setProperty("name", applicationName());
		gemfireProperties.setProperty("log-level", gemfireLogLevel());

		return gemfireProperties;
	}

	@Bean(name = CUSTOMER_REGION_NAME)
	public Region<Long, Customer> createAccountRegion(Cache cache) {

		RegionFactory<Long, Customer> rf = cache.createRegionFactory(RegionShortcut.PARTITION);
		rf.setKeyConstraint(Long.class);
		rf.setValueConstraint(Customer.class);

		PartitionAttributesFactory<Long, Customer> paf = new PartitionAttributesFactory<>();
		rf.setPartitionAttributes(paf.create());

		Region<Long, Customer> r = rf.create(CUSTOMER_REGION_NAME);
		return r;
	}

}
