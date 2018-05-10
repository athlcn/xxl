package xxl.ch2;

import java.nio.ByteBuffer;

/*
 * ��������Buffer������Java NIO�и����ȡ���ݣ��ײ�����ʵ�֡��ɴ�ȡ��ͬ���͵����ݡ�
 * �������ݵ����Ͳ�ͬ���ṩ����Ӧ���͵Ļ�������Boolean���⣩��
 * ByteBuffer��CharBuffer��ShortBuffer��IntBuffer��LongBuffer��FloatBuffer��DoubleBuffer��
 * ��������������ʽ����һ�£�ͨ��allocate()��ȡ��put();get();
 */

public class BufferTest {
	public static void main(String[] args) {
		String str = "abcde";

		/*
		*           ��������������������������������������������������������������
		*           |  |  |  |  |  |  |  |  |  |  |
		*           ��������������������������������������������������������������
		*           ��                             ��
		*       position=0                    capacity=10
		*                                      limit=10
		*/
		
		// 1.����ָ����������С
		ByteBuffer bb = ByteBuffer.allocate(1024);
		System.out.println("------------------allocate------------------");
		System.out.println("������λ�ã�" + bb.position());// ��ʾ���������ڲ�������λ��
		System.out.println("���������ޣ�" + bb.limit());// limit�������ݲ��ܶ�д
		System.out.println("��������С��" + bb.capacity());// ��ʾ���������������һ���������ɸı䣬�������顣

		
		/*
		*           ��������������������������������������������������������������                                                   
		*           |a |b |c |d |e |  |  |  |  |  |                                                   
		*           ��������������������������������������������������������������                                                   
		*                          ��              ��
		*                      position=5     capacity=10
		*                                      limit=10
		*/
		
		// 2.����put()�������ݵ�������
		bb.put(str.getBytes());
		System.out.println("------------------put------------------");
		System.out.println("������λ�ã�" + bb.position());
		System.out.println("���������ޣ�" + bb.limit());
		System.out.println("��������С��" + bb.capacity());

		// 2.�л�������ģʽ
		bb.flip();
		System.out.println("------------------flip------------------");
		System.out.println("������λ�ã�" + bb.position());
		System.out.println("���������ޣ�" + bb.limit());
		System.out.println("��������С��" + bb.capacity());
		 
		//4.����get()���������е�����
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		System.out.println("------------------get------------------");
		System.out.println( new String(b,0,b.length));
		System.out.println("������λ�ã�" + bb.position());
		System.out.println("���������ޣ�" + bb.limit());
		System.out.println("��������С��" + bb.capacity());
		
		//5.rewind() ���ظ���
		bb.rewind();
		System.out.println("------------------rewind------------------");
		System.out.println("������λ�ã�" + bb.position());
		System.out.println("���������ޣ�" + bb.limit());
		System.out.println("��������С��" + bb.capacity());
		
		//6.clear() ��ջ�����,������������Ȼ���ڣ����ڡ���������״̬��
		bb.clear();
		System.out.println("------------------clear------------------");
		System.out.println("������λ�ã�" + bb.position());
		System.out.println("���������ޣ�" + bb.limit());
		System.out.println("��������С��" + bb.capacity());
		System.out.println( (char)bb.get());//������ ������Ȼ����
		
		//6.mark() ��ǻ�����λ�ã�reset() �ָ���mark��

	}

}
