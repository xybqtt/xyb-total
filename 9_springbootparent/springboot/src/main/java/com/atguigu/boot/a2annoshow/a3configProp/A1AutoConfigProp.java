package com.atguigu.boot.a2annoshow.a3configProp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
@ConfigurationProperties(prefix = "a1.auto.config.prop")
public class A1AutoConfigProp {

    private String field1;

    private String[] field2;

}
