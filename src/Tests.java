/*
class B {
	
	int g;
}; 


class A {
	
	int a;
	B b[];
	int meth(int i) {
		return(b[i].g);
	
	};
};
public class Tests {

	
	public static class B {
		public int g;
	}
	
	public static class A {
		
		public A() {
			b = new B[2]; 
			b[0] = new B();
			b[1] = new B();
		}
		
		public int a;
		public B[] b;
		public int meth(int i) {
			return(b[i].g);
		}
	}
	

	public static void main(String[] args) {
		
		int r;
		int a[][];
		
		int m;
		A b;
		b.b[0].g = 200;
		b.b[1].g = 400;
		m = 5;
		a[1][1] = 99;
		
		r = a[1 | 0][4 - 3] * fun2(fun1()) * m + b.meth(1 & 1 | 1 & a[1][fun2(1)]) + b.b[fun1() - 2].g + m;
		
		System.out.println(r); 

	}
	
	static int fun1() {
		
		return(3);
		
	};


	static int fun2(int a) {
		
		return(a * 3);
		
	};

}
*/