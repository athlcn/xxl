package xxl.ch1;

public class Iplusplus {
	public static void main(String[] args) {
		int i = 0;
//		int j=i++;
		int j=++i;
		System.out.println("j = "+j);
		
		//i++
//		public static void main(java.lang.String[] args);
//	     0  iconst_0 // ��������0
//	     1  istore_1 [i]  // ������0��ֵ��1�Ŵ洢��Ԫ��������i��
//	     2  iload_1 [i] // ��1�Ŵ洢��Ԫ��ֵ���ص�����ջ����ʱ i=0��ջ��ֵΪ0��
//	     3  iinc 1 1 [i] // 1�Ŵ洢��Ԫ��ֵ+1����ʱ i=1��
//	     6  istore_2 [j] // ������ջ����ֵ��0��ȡ������ֵ��2�Ŵ洢��Ԫ��������j����ʱi=1��j=0��
//	     7  getstatic java.lang.System.out : java.io.PrintStream [16]
//	    10  new java.lang.StringBuilder [22]
//	    13  dup
//	    14  ldc <String "j = "> [24]
//	    16  invokespecial java.lang.StringBuilder(java.lang.String) [26]
//	    19  iload_2 [j]
//	    20  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [29]
//	    23  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [33]
//	    26  invokevirtual java.io.PrintStream.println(java.lang.String) : void [37]
//	    29  return

		//++i
//		public static void main(java.lang.String[] args);
//	     0  iconst_0 // ��������0
//	     1  istore_1 [i] // ������0��ֵ��1�Ŵ洢��Ԫ��������i��
//	     2  iinc 1 1 [i] // 1�Ŵ洢��Ԫ��ֵ+1����ʱ i=1��
//	     5  iload_1 [i] // ��1�Ŵ洢��Ԫ��ֵ���ص�����ջ����ʱ i=1��ջ��ֵΪ1��
//	     6  istore_2 [j] // ������ջ����ֵ��1��ȡ������ֵ��2�Ŵ洢��Ԫ��������j����ʱi=1��j=1��
//	     7  getstatic java.lang.System.out : java.io.PrintStream [16]
//	    10  new java.lang.StringBuilder [22]
//	    13  dup
//	    14  ldc <String "j = "> [24]
//	    16  invokespecial java.lang.StringBuilder(java.lang.String) [26]
//	    19  iload_2 [j]
//	    20  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [29]
//	    23  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [33]
//	    26  invokevirtual java.io.PrintStream.println(java.lang.String) : void [37]
//      29 return

		/*
		 * i++ ���š����������ġ�����д������������������в���ԭ�Ӳ��������߳��л�������⡣��Ҫ�����ڴ�ɼ���
		 */
	}

}
