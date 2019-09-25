//package com.cici.consumer;
//
//import com.cici.entity.order.Order;
//import com.cici.service.OrderService;
//import com.cici.util.FastJsonConvertUtil;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
//import org.apache.rocketmq.common.message.MessageConst;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class OrderConsumer {
//
//	private DefaultMQPushConsumer consumer;
//
//	public static final String CALLBACK_PAY_TOPIC = "callback_pay_topic";
//
//	public static final String CALLBACK_PAY_TAGS = "callback_pay";
//
//	public static final String NAMESERVER = "127.0.0.1:9876";
//
//	@Autowired
//	private OrderService orderService;
//
//	public OrderConsumer() throws MQClientException {
//		consumer = new DefaultMQPushConsumer("callback_pay_consumer_group");
//        consumer.setConsumeThreadMin(10);
//        consumer.setConsumeThreadMax(50);
//        consumer.setNamesrvAddr(NAMESERVER);
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//        consumer.subscribe(CALLBACK_PAY_TOPIC, CALLBACK_PAY_TAGS);
//        consumer.registerMessageListener(new MessageListenerConcurrently4Pay());
//        consumer.start();
//	}
//
//	class MessageListenerConcurrently4Pay implements MessageListenerConcurrently {
//
//		@Override
//		public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//        	MessageExt msg = msgs.get(0);
//        	try {
//				String topic = msg.getTopic();
//				String msgBody = new String(msg.getBody(), "utf-8");
//				String tags = msg.getTags();
//				String keys = msg.getKeys();
//				System.err.println("收到消息：" + "  topic :" + topic + "  ,tags : " + tags + "keys :" + keys + ", msg : " + msgBody);
//				String orignMsgId = msg.getProperties().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
//				System.err.println("orignMsgId: " + orignMsgId);
//
//
//				Map<String, Object> body = FastJsonConvertUtil.convertJSONToObject(msgBody, Map.class);
//				Long orderId = (Long) body.get("orderId");
//				Order order = orderService.selectOrderById(orderId);
//				order.setState("waitShip");
//				orderService.updateOrder(order);
//
//
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//			}
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//		}
//
//	}
//
//}
