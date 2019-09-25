package com.cici.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

@Component
public class SyncProducer {

	private DefaultMQProducer producer;
	
	private static final String NAMESERVER = "127.0.0.1:9876";

	private static final String PRODUCER_GROUP_NAME = "callback_pay_producer_group_name";
	
	private SyncProducer() {
		this.producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
		this.producer.setNamesrvAddr(NAMESERVER);
		this.producer.setRetryTimesWhenSendFailed(3);
		start();
	}
	
	public void start() {
		try {
			this.producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}
	
	public SendResult sendMessage(Message message) {
		SendResult sendResult = null;
			try {
				sendResult = this.producer.send(message);
			} catch (MQClientException e) {
				e.printStackTrace();
			} catch (RemotingException e) {
				e.printStackTrace();
			} catch (MQBrokerException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        return sendResult;
	}

	public void shutdown() {
		this.producer.shutdown();
	}
}
