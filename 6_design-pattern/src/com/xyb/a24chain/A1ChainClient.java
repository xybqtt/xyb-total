package com.xyb.a24chain;

/**
 * 责任链模式
 */
public class A1ChainClient {

    public static void main(String[] args) {
        // 组装责任链
        A1Handler handler = new A1ConcreteHandler1(new A1ConcreteHandler2(new A1ConcreteHandler3(null)));

        // 调用责任链
        handler.handleRequest(20);
    }

}

abstract class A1Handler {
    private A1Handler next;

    A1Handler(A1Handler next) {
        this.next = next;
    }

    public A1Handler getNext() {
        return next;
    }

    public void setNext(A1Handler next) {
        this.next = next;
    }

    public abstract void handleRequest(int a);
}

class A1ConcreteHandler1 extends A1Handler {

    A1ConcreteHandler1(A1Handler next) {
        super(next);
    }

    @Override
    public void handleRequest(int a) {
        if(a > 10) {
            if (getNext() != null) {
                System.out.println(this + "：处理值为" + a + "，处理不了，后面处理。");
                getNext().handleRequest(a);
            } else {
                System.out.println(this + "：处理值为" + a + "，处理不了，后面没人处理了。");
            }
            return;
        }
        System.out.println(this + "：处理值为" + a);
    }
}

class A1ConcreteHandler2 extends A1Handler {

    A1ConcreteHandler2(A1Handler next) {
        super(next);
    }

    @Override
    public void handleRequest(int a) {
        if(a > 20) {
            if (getNext() != null) {
                System.out.println(this + "：处理值为" + a + "，处理不了，后面处理。");
                getNext().handleRequest(a);
            } else {
                System.out.println(this + "：处理值为" + a + "，处理不了，后面没人处理了。");
            }
            return;
        }
        System.out.println(this + "：处理值为" + a);
    }
}

class A1ConcreteHandler3 extends A1Handler {

    A1ConcreteHandler3(A1Handler next) {
        super(next);
    }

    @Override
    public void handleRequest(int a) {
        if(a > 30) {
            if (getNext() != null) {
                System.out.println(this + "：处理值为" + a + "，处理不了，后面处理。");
                getNext().handleRequest(a);
            } else {
                System.out.println(this + "：处理值为" + a + "，处理不了，后面没人处理了。");
            }
            return;
        }
        System.out.println(this + "：处理值为" + a);
    }
}
