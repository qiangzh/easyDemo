package com.bitset;

import java.util.BitSet;

/**
 * 
 *  找到数组中行最大、列最小的元素，没有打印"无"
 *  @author Administrator
 *  @created 2014-1-16 上午09:33:41
 *  @lastModified       
 *  @history
 */
public class BitSetTest {
    public static void main(String[] args) {
        int[][] arr = new int[][] { { 7, 8, 9 }, { 1, 2, 3 }, { 2, 5, 6 }, { 3, 2, 10 } };
        int rowCount = arr.length;
        int colCount = arr[0].length;
        BitSet maxBitSet = new BitSet();
        BitSet minBitSet = new BitSet();

        // 行最大值
        for (int i = 0; i < rowCount; i++) {
            int maxVal = arr[i][0];
            for (int j = 1; j < colCount; j++) {
                if (arr[i][j] > maxVal) {
                    maxVal = arr[i][j];
                }
            }
            for (int j = 0; j < colCount; j++) {
                if (arr[i][j] == maxVal) {
                    maxBitSet.set(i * rowCount + j);
                }
            }
        }

        // 列最小值
        for (int j = 0; j < colCount; j++) {
            int minVal = arr[0][j];
            for (int i = 1; i < rowCount; i++) {
                if (arr[i][j] < minVal) {
                    minVal = arr[i][j];
                }
            }
            for (int i = 0; i < rowCount; i++) {
                if (arr[i][j] == minVal) {
                    minBitSet.set(i * rowCount + j);
                }
            }
        }
        
        System.out.println(maxBitSet);
        System.out.println(minBitSet);
        maxBitSet.and(minBitSet); // 位与运算
        System.out.println(maxBitSet);

        int num = maxBitSet.nextSetBit(0);
        if (num > 0) {
            int row = num / rowCount;
            int col = num % rowCount;
            System.out.println("[" + row + "," + col + "] = " + arr[row][col]);
        }
        else {
            System.out.println("无");
        }

    }

}
