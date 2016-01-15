package com.zncxi.huaxi.util;

import java.nio.ByteBuffer;

/**
 * 数据分析工具类
 * @author xiaoCheng
 *
 */
public class DataUtil {

	/**
	 * 把INT转byte2
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray(int i) {
		byte[] result = new byte[2];
		result[1] = (byte) (i % 0x100);
		result[0] = (byte) (i / 0x100);
		return result;
	}
	
	/**
	 * 截取byte数组
	 * @param src  原数组
	 * @param begin  开始位置
	 * @param count  截取长度
	 * @return
	 */
	public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i=begin; i<begin+count; i++) bs[i-begin] = src[i];
        return bs;
    }
	
	/** 
	 * 字节转换为浮点 (数据组转换)
	 *  
	 * @param b 字节（4个字节） 
	 * @param index 开始位置 
	 * @return 
	 */  
	public static float byte2float(byte[] b, int index) {
		byte[] b2 = subBytes(b, index, 4);
		ByteBuffer buf = ByteBuffer.allocate(4);
		buf.put(b2);
		buf.rewind();
		return buf.getFloat();
	}
	
	/**
	 * byte 位判断，如果判断位是1，返回true
	 * @param b  byte值
	 * @param index  要判断的位下标（从右向左）
	 * @return
	 */
	public static boolean byteBitChecked(byte b, int index){
		return (b >> index & 1) == 1;
	}
	
	/**
	 * 将byte中的某一Bit置为1
	 * 方向从右向左,下标从0开始
	 * @param n 置1的bit的位置
	 * @return
	 */
	public static byte byteByBitTo1(byte b, int n){
		switch (n) {
		case 7:
			return (byte) (b | (byte)0x80);
		case 6:
			return (byte) (b | (byte)0x40);
		case 5:
			return (byte) (b | (byte)0x20);
		case 4:
			return (byte) (b | (byte)0x10);
		case 3:
			return (byte) (b | (byte)0x08);
		case 2:
			return (byte) (b | (byte)0x04);
		case 1:
			return (byte) (b | (byte)0x02);
		case 0:
			return (byte) (b | (byte)0x01);
		default:
			return (byte) (b | (byte)0x00);
		}
	}
	
	public static void main(String[] args) {
//		float f = byte2float(new byte[]{(byte)0xbd, (byte)0x4c, (byte)0xcc, (byte)0xcd},0);
//		System.out.println(f);
//		byte data = 1;
//        for (int i = 7; i >= 0; i--) {
//            System.out.print((data >> i & 1)+"--");
//            System.out.println((data >> i & 1) == 1);
//        }
		byte a = (byte) ((byte)0x00 | byteByBitTo1((byte)0x00, 1));
		byte[] b = new byte[]{a,(byte)0x00};
//		int j = ByteUtilities.makeIntFromByte2(b);
//		System.out.println(j);
//		
//		b = DataUtil.intToByteArray(j);
		System.out.println(b[0]+","+b[1]);
		for (int i = 0; i < 8; i++) {
          System.out.print((b[0] >> 7-i & 1)+"--");
		}
//		j = ByteUtilities.makeIntFromByte2(b);
//		System.out.println(j);
		
		System.out.println(Byte.parseByte(0+"", 16));
//		
	}
}
