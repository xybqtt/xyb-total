package com.xyb.a6builder;

/**
 * 简单建造者模式，是除去了指挥者和抽象构造者而产生的，它的作用在于可以在创建对象的时候,可以通过内类的一个构造者.来灵活的创建。
 */
public class A2BuilderClient {

    public static void main(String[] args) {
        A2Product a2Product = new A2Product.Builder()
                .setField1("1")
                .setField2("2")
                .setField3("3")
                .build();
        System.out.println(a2Product);
    }

}

class A2Product {

    private String field1;
    private String field2;
    private String field3;

    private A2Product() {
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "A1Product{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }

    public static class Builder {

        private String field1;
        private String field2;
        private String field3;

        public Builder() {
        }

        public Builder setField1(String field1) {
            this.field1 = field1;
            return this;
        }

        public Builder setField2(String field2) {
            this.field2 = field2;
            return this;
        }

        public Builder setField3(String field3) {
            this.field3 = field3;
            return this;
        }

        public A2Product build() {
            A2Product a2Product = new A2Product();
            a2Product.setField1(this.field1);
            a2Product.setField2(this.field2);
            a2Product.setField3(this.field3);
            return a2Product;
        }
    }
}

