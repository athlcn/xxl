package xxl.ch2;

import java.nio.ByteBuffer;

/*
 * ��Ϊ BufferTest �Ĳ��� 
 * mark() ��ǻ�����λ��
 * reset() �ָ���mark��λ��
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
		System.out.println("λ�ã�" + bb.position());
		System.out.println();
		bb.mark();
		bb.get(b, 2, 2);
		System.out.println(new String(b, 2, 2));
		System.out.println("λ�ã�" + bb.position());
		System.out.println();
		bb.reset();
		System.out.println("λ�ã�" + bb.position());
		if (bb.hasRemaining()) { // �жϻ������Ƿ���ʣ������
			System.out.println("������ʣ��ɲ������ݵ����� " + bb.remaining()); // ��ȡʣ��ɲ������ݵ�����

		}

	}

}
