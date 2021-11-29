package com.xyb.a6builder;

/**
 * 建造者模式，标准写法
 */
public class A1BuilderClient {

    public static void main(String[] args) {
        A1Product product = new A1Director(new A1ProductBuilder1()).contruct();
        System.out.println("获取一个已经设定好值的A1Product对象：" + product.toString());
    }

}

/**
 * 最终的产品。
 * 产品具体业务数据的赋值，不应该体现在产品类的定义中。
 * 其实每个属性可以是个简单的class，而不是String，更能体现建造者模式。
 */
class A1Product {

    private String field1;
    private String field2;
    private String field3;

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
}



/**
 * 抽象建造者，抽象可以设置产品所有属性的方法
 */
abstract class A1AbsProductBuilder {

    protected A1Product a1Product = new A1Product();

    public abstract void setField1();
    public abstract void setField2();
    public abstract void setField3();

    public A1Product getA1Product() {
        return a1Product;
    }
}


/**
 * 实际的建造者，如果想赋值不同的默认参数，再写另一个建造者即可。
 */
class A1ProductBuilder1 extends A1AbsProductBuilder {

    public A1Product product = new A1Product();

    @Override
    public void setField1() {
        this.product.setField1("属性1");
    }

    @Override
    public void setField2() {
        this.product.setField2("属性2");
    }

    @Override
    public void setField3() {
        this.product.setField3("属性3");
    }

    @Override
    public A1Product getA1Product() {
        return this.product;
    }
}


/**
 * 指导者，可以设定构造顺序
 */
class A1Director {

    private A1AbsProductBuilder a1AbsProductBuilder;

    public A1Director(A1AbsProductBuilder a1AbsProductBuilder) {
        this.a1AbsProductBuilder = a1AbsProductBuilder;
    }

    public A1Product contruct() {
        // 此处可以设定构造顺序
        this.a1AbsProductBuilder.setField1();
        this.a1AbsProductBuilder.setField3();
        this.a1AbsProductBuilder.setField2();
        return this.a1AbsProductBuilder.getA1Product();
    }

}