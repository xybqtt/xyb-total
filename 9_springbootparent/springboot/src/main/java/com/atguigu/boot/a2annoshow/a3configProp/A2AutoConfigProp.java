package com.atguigu.boot.a2annoshow.a3configProp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ConfigurationProperties(prefix = "a2.auto.config.prop2")
public class A2AutoConfigProp {

    private String field1;

}
