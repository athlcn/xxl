package xxl.ch1;

public class Iplusplus {
	public static void main(String[] args) {
		int i = 0;
//		int j=i++;
		int j=++i;
		System.out.println("j = "+j);
		
		//i++
//		public static void main(java.lang.String[] args);
//	     0  iconst_0 // 生成整数0
//	     1  istore_1 [i]  // 将整数0赋值给1号存储单元（即变量i）
//	     2  iload_1 [i] // 将1号存储单元的值加载到数据栈（此时 i=0，栈顶值为0）
//	     3  iinc 1 1 [i] // 1号存储单元的值+1（此时 i=1）
//	     6  istore_2 [j] // 将数据栈顶的值（0）取出来赋值给2号存储单元（即变量j，此时i=1，j=0）
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
//	     0  iconst_0 // 生成整数0
//	     1  istore_1 [i] // 将整数0赋值给1号存储单元（即变量i）
//	     2  iinc 1 1 [i] // 1号存储单元的值+1（此时 i=1）
//	     5  iload_1 [i] // 将1号存储单元的值加载到数据栈（此时 i=1，栈顶值为1）
//	     6  istore_2 [j] // 将数据栈顶的值（1）取出来赋值给2号存储单元（即变量j，此时i=1，j=1）
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
		 * i++ 有着“读”，“改”，“写”操作，而且在语句中不是原子操作，多线程中会出现问题。需要数据内存可见。
		 */
	}

}
