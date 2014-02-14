package com.testfinally;

/**
 *  测试Finally中存在异常是否继续执行
 *  @author zhqiang
 *  @created 2013-12-4 下午02:45:10
 *  @lastModified       
 *  @history           
 */

public class FinallyExceptionTest {

    public static void main(String[] args) {

        try {
            throw new Exception();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            System.out.println("begin");
            int i = 1 / 0;
            System.out.println("end");// 不走该处代码

        }
    }
}
