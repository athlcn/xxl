package xxl.ch2;

import java.nio.ByteBuffer;

/*
 * 作为 BufferTest 的补充 
 * mark() 标记缓冲区位置
 * reset() 恢复到mark的位置
 * 0 <= mark <= position <= limit <= capacity
 */
public class BufferTest01 {
	public static void main(String[] args) {
		String str = "abcde";
		ByteBuffer bb = ByteBuffer.allocate(1024);
		bb.put(str.getBytes());
		bb.flip();
		byte[] b = new byte[bb.limit()];
		bb.get(b, 0, 2);
		System.out.println(new String(b, 0, 2));
		System.out.println("位置：" + bb.position());
		System.out.println();
		bb.mark();
		bb.get(b, 2, 2);
		System.out.println(new String(b, 2, 2));
		System.out.println("位置：" + bb.position());
		System.out.println();
		bb.reset();
		System.out.println("位置：" + bb.position());
		if (bb.hasRemaining()) { // 判断缓冲区是否有剩余数据
			System.out.println("缓冲区剩余可操作数据的数量 " + bb.remaining()); // 获取剩余可操作数据的数量

		}

	}

}
