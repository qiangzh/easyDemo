package com.testfinally;

/**
 *  ����Finally�д����쳣�Ƿ����ִ��
 *  @author zhqiang
 *  @created 2013-12-4 ����02:45:10
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
            System.out.println("end");// ���߸ô�����

        }
    }
}
