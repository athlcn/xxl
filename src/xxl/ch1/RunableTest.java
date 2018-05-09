package xxl.ch1;

/*
 * 某公司面试要求a、b、c三个线程有序输出ABCABCABCABCABCABCABC...
 *
 */
public class RunableTest {
	public static void main(String[] args) {
		A a = new A();
		for (;;) { // 死循环 比 while(true)效率高，没有判断
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
	private volatile int num = 0;// 内存数据可见用volatile声明变量

	public synchronized void a() {
		try {
			while (num != 0) {
				wait();// wait 必须写在循环中
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