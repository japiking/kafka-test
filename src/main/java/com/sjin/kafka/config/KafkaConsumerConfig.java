package com.sjin.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.sjin.kafka.dto.User;
import com.sjin.kafka.dto.User2;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	@Bean
    public ConsumerFactory<String, User> consumerFactory(KafkaProperties kafkaProperties) {
		JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);

		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }
	@Bean
	public ConsumerFactory<String, User2> consumerFactory2(KafkaProperties kafkaProperties) {
		JsonDeserializer<User2> deserializer = new JsonDeserializer<>(User2.class);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	}

    @Bean("UserConsumer")
    public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {

        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));

        return factory;
    }
    
    @Bean("UserConsumer2")
    public ConcurrentKafkaListenerContainerFactory<String, User2> kafkaListenerContainerFactory2(KafkaProperties kafkaProperties) {
    	
    	ConcurrentKafkaListenerContainerFactory<String, User2> factory = new ConcurrentKafkaListenerContainerFactory<>();
    	factory.setConsumerFactory(consumerFactory2(kafkaProperties));
    	
    	return factory;
    }
    
}
