package com.atguitu.boot.a2annoshow.a1configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Class1 {

    private String field1;

    private Class2 class2;

}
