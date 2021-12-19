package com.xyb.a15command;

/**
 * 命令模式，扩展方便
 */
public class A1CommandClient {

    public static void main(String[] args) {
        // 开电视
        A1Invoker a1Invoker = new A1Invoker(new A1TvOnCommand(new A1TvReceiver()));
        a1Invoker.invoke();

        // 关电视
        a1Invoker.setA1Command(new A1TvOffCommand(new A1TvReceiver()));
        a1Invoker.invoke();

        // 关电脑
        a1Invoker.setA1Command(new A1ComOffCommand(new A1ComputerReceiver()));
        a1Invoker.invoke();
    }

}

/**
 * 调用者
 */
class A1Invoker {

    private A1Command a1Command;

    public A1Invoker(A1Command a1Command) {
        this.a1Command = a1Command;
    }

    public void setA1Command(A1Command a1Command) {
        this.a1Command = a1Command;
    }

    public void invoke() {
        this.a1Command.execute();
    }

}

/**
 * 电视接收者
 */
class A1TvReceiver {
    public void tvOn() {
        System.out.println("打开电视");
    }

    public void tvOff() {
        System.out.println("关闭电视");
    }
}

/**
 * 电脑接收者
 */
class A1ComputerReceiver {
    public void comOn() {
        System.out.println("打开电脑");
    }

    public void comOff() {
        System.out.println("关闭电脑");
    }
}

interface A1Command {

    public void execute();

}

/**
 * 开电视命令
 */
class A1TvOnCommand implements A1Command {

    private A1TvReceiver a1TvReceiver;

    public A1TvOnCommand(A1TvReceiver a1TvReceiver) {
        this.a1TvReceiver = a1TvReceiver;
    }

    @Override
    public void execute() {
        this.a1TvReceiver.tvOn();
    }
}

/**
 * 关电视命令
 */
class A1TvOffCommand implements A1Command {

    private A1TvReceiver a1TvReceiver;

    public A1TvOffCommand(A1TvReceiver a1TvReceiver) {
        this.a1TvReceiver = a1TvReceiver;
    }

    @Override
    public void execute() {
        this.a1TvReceiver.tvOff();
    }
}

/**
 * 关电脑命令
 */
class A1ComOffCommand implements A1Command {

    private A1ComputerReceiver a1ComputerReceiver;

    public A1ComOffCommand(A1ComputerReceiver a1ComputerReceiver) {
        this.a1ComputerReceiver = a1ComputerReceiver;
    }

    @Override
    public void execute() {
        this.a1ComputerReceiver.comOff();
    }
}
