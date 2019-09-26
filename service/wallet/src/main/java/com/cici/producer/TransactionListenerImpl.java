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

	/**
	 * 如果用try的话，能保证return LocalTransactionState的值，但是就只能是单挑数据库操作，不能用其他代码逻辑操作了，
	 * 因为如果错误发生在数据库更新前，则不会回滚
	 * @Transactional与try冲突了，用了之后事务注解就失效了，所以问题还没完全得到解决
	 * @param msg
	 * @param arg
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
		System.err.println("执行本地事务单元------------");
		Map<String, Object> params = (Map<String, Object>) arg;
		Long walletId = (Long) params.get("walletId");
		Integer amount = (Integer)params.get("amount");
		log.info("--------------------------------");
		//updateBalance 传递当前的支付款 数据库操作:
		Date currentTime = new Date();
		userWalletMapper.updateBalance(walletId, amount, currentTime);
//		userWalletMapper.excute();
		return LocalTransactionState.COMMIT_MESSAGE;
	}

	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
