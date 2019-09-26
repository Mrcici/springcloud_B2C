package com.cici.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cici.entity.wallet.PayOrder;
import com.cici.entity.wallet.UserWallet;
import com.cici.entity.wallet.requestbody.PayOrderCreateRequestBody;
import com.cici.exception.common.ExceptionEnumImpl;
import com.cici.exception.common.RestPreconditionFailedException;
import com.cici.mapper.PayOrderMapper;
import com.cici.producer.CallbackService;
import com.cici.producer.TransactionProducer;
import com.cici.service.PayOrderService;
import com.cici.service.UserWalletService;
import com.cici.util.FastJsonConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 17:45
 */
@Service
@Slf4j
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {

    public static final String TX_PAY_TOPIC = "tx_pay_topic";

    public static final String TX_PAY_TAGS = "pay";

    @Autowired
    private UserWalletService userWalletService;

    @Autowired
    private TransactionProducer transactionProducer;

    @Autowired
    private CallbackService callbackService;

    @Override
    public PayOrder payApi(PayOrderCreateRequestBody body) {
        String paymentRet = "";
        UserWallet old = userWalletService.selectByWalletId(body.getWalletId());
        PayOrder payOrder = new PayOrder();
        Integer current = old.getAmount() - body.getAmount();
            if (current > 0) {

                String keys = UUID.randomUUID().toString() + "$" + System.currentTimeMillis();
                Map<String, Object> params = new HashMap<>();
                params.put("walletId", body.getWalletId());
                params.put("orderId", body.getOrderId());
                params.put("amount", body.getAmount());
                Message message = new Message(TX_PAY_TOPIC, TX_PAY_TAGS, keys, FastJsonConvertUtil.convertObjectToJSON(params).getBytes());
                //	可能需要用到的参数
                params.put("current", current);
                params.put("currentVersion", old.getVersion());

                payOrder.setAmount(body.getAmount());
                payOrder.setOrderId(body.getOrderId());
                payOrder.setWalletId(body.getWalletId());
                payOrder = this.createPayOrder(payOrder);

                //	消息发送并且 本地的事务执行
                TransactionSendResult sendResult = transactionProducer.sendMessage(message, params);
                log.info("===========================================================================");
                if(sendResult.getSendStatus() == SendStatus.SEND_OK
                        && sendResult.getLocalTransactionState() == LocalTransactionState.COMMIT_MESSAGE) {
                    log.info("进入SUCCESS回调");
                    //	回调order通知支付成功消息,可以调用其他服务，例如积分服务等
                    paymentRet = "支付成功!";
                    baseMapper.updateById(payOrder);
                    payOrder.setResult(paymentRet);
                } else {
                    log.info("进入FAIL回调");
                    paymentRet = "支付失败!";
                    payOrder.setResult(paymentRet);
                }
            } else {
                paymentRet = "余额不足!";
                payOrder.setResult(paymentRet);
            }
        return payOrder;
    }

    @Override
    public PayOrder createPayOrder(PayOrder payOrder) {

        if (baseMapper.insert(payOrder) < 0){
            throw new RestPreconditionFailedException(ExceptionEnumImpl.CREATE_ERROR,
                    "create error.");
        }
        return baseMapper.selectById(payOrder.getId());
    }
}
