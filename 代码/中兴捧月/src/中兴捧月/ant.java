package 中兴捧月;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * 总体思路:先跑BFS，记录当前经过的节点信息，同时记录当前节点的权值,如果当前节点多余9个则直接返回
 * 当遍历到终点时，判断会不会经过必须到达的两个点和边，不能经过的边在读入的时候跳过
 * 记录下最优的路径值和路径路线
 */
class point implements Cloneable{//继承克隆接口
	 int  index;//之前的路径上的最后一个点的下标
	 ArrayList<Integer>arraylist;
	 int value;//经过顶点的权值
	 int length;//经过节点的个数
	  public Object clone(){//重写clone方法，实现深拷贝，这里主要是指arraylist的浅拷贝问题
		 point p=null;
		 try {
			p=(point)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		 //p.arraylist=(ArrayList)arraylist.clone();浅拷贝
		 p.index=this.index;
		 p.arraylist=new ArrayList<Integer>();
		 p.arraylist.addAll(this.arraylist);
		 p.value=this.value;
		 p.length=this.length;
		 return p;
	 }
}
public class ant{
   /*先告诉有多少个点n（默认点的坐标是1~n,起点是1终点是n),多少条路径m
    *再告诉m条路径的起点,终点,路径值(起点和终点都是1~n)
    *再输入一个数k1,代表一定要到达的顶点
    *接下去k1个数,代表k1个必须要到达的顶点
    *再输入一个数k2,代表边数
    *接下去k2行，每一行代表k2个起点,终点和权值
    *再输入k3行,代表一定不能去的k3个路径
    *接下去k3行，每一行代表k3个起点,终点和权值
    *样例输入:
    *17 
    */
   private int n;//图的点数
   private int m;//图的边数
   private int s[][];//用来存储距离矩阵
   private int k1;//用来记录必须到达的顶点信息
   private int k2;//用来记录必须到达的边数
   private int k3;//用来记录下一定不能去的边数
   private int mustreach_point[];//必须要达到的顶点数组
   private int mustreach_edge[][];//必须要到达的边数组
   private int cannotreach_edge[][];//必须不能到达的边数组
   public ant(){//用来进行数据的初始化,同时记录数据的
	   Scanner cin=new Scanner(System.in);
	   n=cin.nextInt();
	   m=cin.nextInt();
	   s=new int [n+1][n+1];//1~n的矩阵
	   for (int i=1;i<=m;i++){
		   int a=cin.nextInt();
		   int b=cin.nextInt();
		   int c=cin.nextInt();
		   if ((s[a][b]==0)||(s[a][b]>c)){//考虑两个点之间存在多条路径
		   s[a][b]=c;
		   s[b][a]=c;
		   }
		 }
	   k1=cin.nextInt();//读入一定要到达的顶点
	   mustreach_point=new int[k1+1];
	   for (int i=1;i<=k1;i++){
		 mustreach_point[i]=cin.nextInt();
	   }
	   k2=cin.nextInt();//读入一定要到达的边
	   mustreach_edge=new int[k2+1][2];//一个低矮表起点，另一个终点
	   for (int i=1;i<=k2;i++){
	   mustreach_edge[i][0]=cin.nextInt();
	   mustreach_edge[i][1]=cin.nextInt();
	   }
	   k3=cin.nextInt();//读入一定不能去的边数
	   cannotreach_edge=new int[k3+1][2];
	   for (int i=1;i<=k3;i++){
		  cannotreach_edge[i][0]=cin.nextInt();
		  cannotreach_edge[i][1]=cin.nextInt();
		  s[cannotreach_edge[i][0]][cannotreach_edge[i][1]]=0;
		  s[cannotreach_edge[i][1]][cannotreach_edge[i][0]]=0;
		 }
	   cin.close();
 } 
    public void BFS(){//通过跑BFS来获得最终结果 
    ArrayDeque<point>arraydeque=new ArrayDeque<point>();
    point p=new point();
    p.index=1;
    p.arraylist=new ArrayList<Integer>();
    p.arraylist.add(1);
    p.value=0;
    p.length=1;
    int min=0xFFFFFF;//最小权值
    //System.out.println(min);
    ArrayList<Integer>ans=new ArrayList<Integer>();//最小路径值
    arraydeque.offer(p);//把起点压入队列中
    /*for (int i=1;i<=n;i++)
    {	for (int j=1;j<=n;j++)
    		System.out.print(s[i][j]);
    System.out.println();
    }
    */
    while(!arraydeque.isEmpty()){
    	point temp=arraydeque.poll();//获得顶部元素
    	//System.out.println("顶部元素"+temp.index);
         if (temp.length==10)
    		continue;
         if (temp.value>min)//剪枝，进行优化数据
        	 continue;
    	if (temp.index==n){//System.out.println(temp.value+" "+temp.arraylist);
    	//System.out.println("是否符合"+judge(temp));
    		if (judge(temp)&&temp.value<min){//如果经过了指定的边和点，并且最大路径小于当前最优解
    			min=temp.value;//更新最优解得价值
    			ans=temp.arraylist;//更新路径列表
    		}
    		continue;
    	}
    	for (int i=2;i<=n;i++){
    		if (s[temp.index][i]!=0&&(!temp.arraylist.contains(i))){
    			point ptemp=(point)temp.clone();
    			ptemp.index=i;
    			ptemp.arraylist.add(i);
    			ptemp.length++;
    			ptemp.value=ptemp.value+s[temp.index][i];
    			//System.out.println("准备压入队列"+ptemp.index);
    			//System.out.println("列表"+ptemp.arraylist);
    			arraydeque.offer(ptemp);
    		}
    	}
    }
    if (!ans.isEmpty()){//判断是否有解
    System.out.println("最小代价为:"+min);
    System.out.println("路径为:"+ans);
    }
    else{
    	System.out.println(-1);                        
    }
   }
   public boolean judge(point p){
	   /*判断这个点是否经过必须经过的某些点和边
	    * 
	    */
	  boolean ok=true;
	  for (int i=1;i<=k1;i++){//判断是否经过指定点
		  if (!(p.arraylist.contains(mustreach_point[i]))){
			  ok=false;
			  break;
		  }
	  }
	  if (!ok)
		  return false;
	  int tempfirst=-1;
	  int tempsecond=-1;
	  for (int i=1;i<=k2;i++){//判断是否经过指定边
		  if ((tempfirst=p.arraylist.indexOf(mustreach_edge[i][0]))==-1||(tempsecond=p.arraylist.indexOf(mustreach_edge[i][1]))==-1){
			  ok=false;
		  }else{
			 if (tempsecond-tempfirst!=1){
				 ok=false;
			 }
		  }
	  }
	  if (!ok)
		  return false;
	  return true;
   }
   public static void main(String[] args) {
	ant a=new ant();
	a.BFS();
}
}
