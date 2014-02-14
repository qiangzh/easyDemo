package com.enumerate;

public class EnumTest {

    public static void main(String[] args) {
        System.out.println(TrafficLamp.RED);
        System.out.println(TrafficLamp.RED.next());
        System.out.println(TrafficLamp.RED.getCode());
        System.out.println(TrafficLamp.valueOf("RED").toString());
    }

}

/**
 * 
 *  Test枚举、构造函数、抽象方法
 *  @author zhqiang
 *  @created 2013-2-25 上午09:06:40
 *  @lastModified       
 *  @history
 */
enum TrafficLamp {
    RED(0) {
        @Override
        public TrafficLamp next() {
            return YELLOW;

        }

    },
    YELLOW(1) {
        @Override
        public TrafficLamp next() {

            return GREEN;
        }
    },
    GREEN(2) {
        @Override
        public TrafficLamp next() {

            return RED;
        }
    };
    private int code;
    // 1、构造函数
    private TrafficLamp(int code) {
        this.code = code;
    };
    // 2、抽象方法
    public abstract TrafficLamp next();

    public int getCode() {
        return code;
    }
}
