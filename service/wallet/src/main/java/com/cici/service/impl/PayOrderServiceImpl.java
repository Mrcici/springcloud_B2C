package com.cici.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cici.entity.wallet.PayOrder;
import com.cici.entity.wallet.requestbody.PayOrderCreateRequestBody;
import com.cici.mapper.PayOrderMapper;
import com.cici.service.PayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 17:45
 */
@Service
@Slf4j
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {


    @Override
    public PayOrder payApi(PayOrderCreateRequestBody body) {
        return null;
    }
}
