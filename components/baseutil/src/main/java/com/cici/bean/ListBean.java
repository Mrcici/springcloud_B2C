package com.cici.bean;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ListBean extends AbstractBean{

    private String sourceField;
    private String targetField;

    private Class targetClass;

    @Override
    public String getOptField() {
        return targetField;
    }
}
