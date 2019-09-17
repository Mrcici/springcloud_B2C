package com.cici.entity.account.requestbody;

import com.cici.base.BaseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cici
 * @Date: 2019/9/17 14:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequestBody extends BaseBody {

    private Long id;

    private String username;

    private String password;
}
