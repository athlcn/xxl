package xxl.ch2;

import java.nio.ByteBuffer;

/*
 * 缓冲区（Buffer）：在Java NIO中负责存取数据，底层数组实现。可存取不同类型的数据。
 * 根据数据的类型不同，提供了相应类型的缓冲区（Boolean除外）：
 * ByteBuffer、CharBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer。
 * 上述缓冲区管理方式几乎一致，通过allocate()获取。put();get();
 */

public class BufferTest {
	public static void main(String[] args) {
		String str = "abcde";

		/*
		*           ———————————————————————————————
		*           |  |  |  |  |  |  |  |  |  |  |
		*           ———————————————————————————————
		*           ↑                             ↑
		*       position=0                    capacity=10
		*                                      limit=10
		*/
		
		// 1.分配指定缓冲区大小
		ByteBuffer bb = ByteBuffer.allocate(1024);
		System.out.println("------------------allocate------------------");
		System.out.println("缓冲区位置：" + bb.position());// 表示缓冲区正在操作数据位置
		System.out.println("缓冲区界限：" + bb.limit());// limit后面数据不能读写
		System.out.println("缓冲区大小：" + bb.capacity());// 表示缓冲区最大容量，一旦声明不可改变，类似数组。

		
		/*
		*           ———————————————————————————————                                                   
		*           |a |b |c |d |e |  |  |  |  |  |                                                   
		*           ———————————————————————————————                                                   
		*                          ↑              ↑
		*                      position=5     capacity=10
		*                                      limit=10
		*/
		
		// 2.利用put()存入数据到缓冲区
		bb.put(str.getBytes());
		System.out.println("------------------put------------------");
		System.out.println("缓冲区位置：" + bb.position());
		System.out.println("缓冲区界限：" + bb.limit());
		System.out.println("缓冲区大小：" + bb.capacity());

		// 2.切换读数据模式
		bb.flip();
		System.out.println("------------------flip------------------");
		System.out.println("缓冲区位置：" + bb.position());
		System.out.println("缓冲区界限：" + bb.limit());
		System.out.println("缓冲区大小：" + bb.capacity());
		 
		//4.利用get()读缓冲区中的数据
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		System.out.println("------------------get------------------");
		System.out.println( new String(b,0,b.length));
		System.out.println("缓冲区位置：" + bb.position());
		System.out.println("缓冲区界限：" + bb.limit());
		System.out.println("缓冲区大小：" + bb.capacity());
		
		//5.rewind() 可重复读
		bb.rewind();
		System.out.println("------------------rewind------------------");
		System.out.println("缓冲区位置：" + bb.position());
		System.out.println("缓冲区界限：" + bb.limit());
		System.out.println("缓冲区大小：" + bb.capacity());
		
		//6.clear() 清空缓冲区,缓冲区数据依然存在，处于“被遗忘”状态。
		bb.clear();
		System.out.println("------------------clear------------------");
		System.out.println("缓冲区位置：" + bb.position());
		System.out.println("缓冲区界限：" + bb.limit());
		System.out.println("缓冲区大小：" + bb.capacity());
		System.out.println( (char)bb.get());//缓冲区 数据依然存在
		
		//6.mark() 标记缓冲区位置，reset() 恢复到mark的

	}

}
