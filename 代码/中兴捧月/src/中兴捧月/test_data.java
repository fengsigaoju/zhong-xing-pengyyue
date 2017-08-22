package 中兴捧月;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
class point2{
	//x
	int x;
	int y;
	point2(int x,int y){
		this.x=x;
		this.y=y;
	}
	@Override
	public boolean equals(Object obj){
		if (obj instanceof point2){
			point2 p=(point2)obj;
			return p.x==this.x&&p.y==this.y;
		}
		return super.equals(obj);
	}
	public int hashcode(){
		return  Integer.hashCode(x)*17+Integer.hashCode(y)*31;
	}
}
public class test_data {
  /*生成大量的测试数据
   * 
   */
	private int n;
	private int m;
	private static int s[][];
	private static int must_point;
	private static int must_edge;
	private static List<Integer>list=new ArrayList<Integer>();
	private static List<point2>list2=new ArrayList<point2>();
	public test_data(){
		n=(int)(Math.random()*100);
		m=(int)(Math.random()*(n-1)*(n-2)/2);
		int t=0;
		s=new int[n+1][n+1];
		while(t<m){
			int a=(int)(Math.random()*n)+1;
			int b=(int)(Math.random()*n)+1;
			int c=(int)(Math.random()*10)+1;
			if ((b==a)||(s[a][b]!=0))
				t--;
			else
				s[a][b]=c;
			t++;
		}
		must_point=(int)(Math.random()*n/10);
		t=0;
		while(t<must_point){
			int a=(int)(Math.random()*100)+1;
			if (list.contains(a)){
				t--;
			}
			else{
				list.add(a);
			}
			t++;
		}
	   must_edge=(int)(Math.random()*m/10);
	   t=0;
	   while(t<must_edge){
		   int a=(int)(Math.random()*n)+1;
		   int b=(int)(Math.random()*n)+1;
		   if (b==a)
			   {
			   t--;
			   continue;
			   }
		   point2 p=new point2(a,b);
		   if(list2.contains(p))
			   t--;
		   else{
			   list2.add(p);
		   }
		   t++;
	   }
	  
		
	}
	public static void main(String[] args) {
		String url="F://test_data.txt";
		test_data t=new test_data();
		PrintWriter pw=null;
		System.out.println(t.n+" "+t.m);
		try {
			pw=new PrintWriter(url);
			pw.print(t.n);
			pw.write(" ");
			pw.print(t.m);
			pw.write("\r\n");
			for (int i=1;i<=t.n;i++)
				for (int j=1;j<=t.n;j++){
					if (s[i][j]!=0)
						pw.print(i+" "+j+" "+s[i][j]+"\r\n");
				}
			pw.print(must_point+"\r\n");
		    Iterator<Integer>it=list.iterator();
		    while(it.hasNext()){
		    	int a=it.next();
		    	System.out.println(a);
		    	pw.print(a+" ");
		    	
		    }
		    pw.write("\r\n");
		    pw.print(must_edge+"\r\n");//必须要到达的边
		    for (point2 p:list2)
		    pw.print(p.x+" "+p.y+"\r\n");
		    pw.write("0");
		 } catch (FileNotFoundException e) {
			System.out.println("文件未找到");
			e.printStackTrace();
		}finally{
			if (pw!=null)
			pw.close();
		}
		
	}
}


