package com.cici.inject;

import com.cici.api.v1.feign.account.AccountServiceFeign;
import com.cici.entity.account.Account;
import com.cici.util.SecurityUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class SecurityInjectAop {

    @Autowired
    private AccountServiceFeign accountService;
    @Autowired
    private Cache<String, Optional<Long>> accountIdCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(1, TimeUnit.HOURS)
            .build();
    /**
     * 拦截器具体实现
     */
    @Before("@annotation(inject)")
    public void beforeHandler(JoinPoint jp, FieldInject inject) throws Exception {

        InjectType[] injectTypes = inject.value();

        for (InjectType injectType : injectTypes) {
            Long accountId;
            Object[] args = jp.getArgs();
            for (Object obj : args) {
                try {

                    switch (injectType) {
                        case CREATE:
                            accountId = getAccountId();
                            BeanUtils.setProperty(obj, "createBy", accountId);
                            break;

                        case UPDATE:
                            accountId = getAccountId();
                            BeanUtils.setProperty(obj, "updateBy", accountId);
                            break;
                        default:
                    }
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e) {
                }
            }
        }
    }

    private Long getAccountId() {
        try {
            String username = SecurityUtils.getCurrentUserLogin();
            Optional<Long> result = accountIdCache.get(username, () -> {
                Account account = accountService.showMe(username);
                return Optional.of(account.getId());
            });
            return result.get();
        } catch (ExecutionException e) {
            return null;
        }
    }
}
