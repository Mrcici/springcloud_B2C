package com.cici.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cici.entity.wallet.PayOrder;
import com.cici.entity.wallet.requestbody.PayOrderCreateRequestBody;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 17:44
 */
public interface PayOrderService extends IService<PayOrder> {

    PayOrder payApi(PayOrderCreateRequestBody body);

}
