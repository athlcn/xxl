package xxl.ch1;

/*
 * ĳ��˾����Ҫ��a��b��c�����߳��������ABCABCABCABCABCABCABC...
 *
 */
public class RunableTest {
	public static void main(String[] args) {
		A a = new A();
		for (;;) { // ��ѭ�� �� while(true)Ч�ʸߣ�û���ж�
			new Thread(new Runnable() {
				@Override
				public void run() {
					a.a();
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					a.b();
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					a.c();
				}
			}).start();
		}
	}

}

class A {
	private volatile int num = 0;// �ڴ����ݿɼ���volatile��������

	public synchronized void a() {
		try {
			while (num != 0) {
				wait();// wait ����д��ѭ����
			}
			System.out.print("A");
			num = 1;
			notifyAll();
		} catch (Exception e) {

		}
	}

	public synchronized void b() {
		try {
			while (num != 1) {
				wait();
			}
			System.out.print("B");
			num = 2;
			notifyAll();
		} catch (Exception e) {

		}
	}

	public synchronized void c() {
		try {
			while (num != 2) {
				wait();
			}
			System.out.print("C");
			num = 0;
			notifyAll();
		} catch (Exception e) {

		}
	}
}