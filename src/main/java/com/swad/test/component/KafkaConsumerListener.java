package com.swad.test.component;

import java.io.IOException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
	/**
	 * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
	 * 
	 * @param record
	 * @throws IOException
	 */
	@KafkaListener(topics = { "test" })
	public void listen(ConsumerRecord<?, ?> record) throws IOException {
		String value = (String) record.value();
		System.out.println(value);
		System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
	}
}
