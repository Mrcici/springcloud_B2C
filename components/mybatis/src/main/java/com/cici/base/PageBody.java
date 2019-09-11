package com.cici.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cici
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class PageBody<T> extends BaseBody<T> {
    Page<T> page;
}
