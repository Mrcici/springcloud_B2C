package com.cici.producer;

import com.cici.entity.wallet.PayOrder;
import com.cici.entity.wallet.requestbody.PayOrderCreateRequestBody;
import com.cici.mapper.UserWalletMapper;
import com.cici.service.PayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class TransactionListenerImpl implements TransactionListener {

	@Autowired
	private UserWalletMapper userWalletMapper;

	@Autowired
	private PayOrderService payOrderService;
	
	@Override
	@Transactional
	public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
		System.err.println("执行本地事务单元------------");
		CountDownLatch currentCountDown = null;
		try {
			Map<String, Object> params = (Map<String, Object>) arg;
			Long walletId = (Long) params.get("walletId");
			Long orderId = (Long) params.get("orderId");
			Integer amount = (Integer)params.get("amount");
			currentCountDown = (CountDownLatch)params.get("currentCountDown");

			PayOrder payOrder = new PayOrder();
			payOrder.setAmount(amount);
			payOrder.setOrderId(orderId);
			payOrder.setWalletId(walletId);

			payOrderService.createPayOrder(payOrder);
			log.info("--------------------------------");


			//updateBalance 传递当前的支付款 数据库操作: 
			Date currentTime = new Date();
			int count = userWalletMapper.updateBalance(walletId, amount, currentTime);
			userWalletMapper.excute();
//			int b = 1/0;
			if(count == 1) {
				currentCountDown.countDown();
				return LocalTransactionState.COMMIT_MESSAGE;
			} else {
				currentCountDown.countDown();
				return LocalTransactionState.ROLLBACK_MESSAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			currentCountDown.countDown();
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}
		
	}

	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
