package ��������;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * ����˼·:����BFS����¼��ǰ�����Ľڵ���Ϣ��ͬʱ��¼��ǰ�ڵ��Ȩֵ,�����ǰ�ڵ����9����ֱ�ӷ���
 * ���������յ�ʱ���жϻ᲻�ᾭ�����뵽���������ͱߣ����ܾ����ı��ڶ����ʱ������
 * ��¼�����ŵ�·��ֵ��·��·��
 */
class point implements Cloneable{//�̳п�¡�ӿ�
	 int  index;//֮ǰ��·���ϵ����һ������±�
	 ArrayList<Integer>arraylist;
	 int value;//���������Ȩֵ
	 int length;//�����ڵ�ĸ���
	  public Object clone(){//��дclone������ʵ�������������Ҫ��ָarraylist��ǳ��������
		 point p=null;
		 try {
			p=(point)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		 //p.arraylist=(ArrayList)arraylist.clone();ǳ����
		 p.index=this.index;
		 p.arraylist=new ArrayList<Integer>();
		 p.arraylist.addAll(this.arraylist);
		 p.value=this.value;
		 p.length=this.length;
		 return p;
	 }
}
public class ant{
   /*�ȸ����ж��ٸ���n��Ĭ�ϵ��������1~n,�����1�յ���n),������·��m
    *�ٸ���m��·�������,�յ�,·��ֵ(�����յ㶼��1~n)
    *������һ����k1,����һ��Ҫ����Ķ���
    *����ȥk1����,����k1������Ҫ����Ķ���
    *������һ����k2,�������
    *����ȥk2�У�ÿһ�д���k2�����,�յ��Ȩֵ
    *������k3��,����һ������ȥ��k3��·��
    *����ȥk3�У�ÿһ�д���k3�����,�յ��Ȩֵ
    *��������:
    *17 
    */
   private int n;//ͼ�ĵ���
   private int m;//ͼ�ı���
   private int s[][];//�����洢�������
   private int k1;//������¼���뵽��Ķ�����Ϣ
   private int k2;//������¼���뵽��ı���
   private int k3;//������¼��һ������ȥ�ı���
   private int mustreach_point[];//����Ҫ�ﵽ�Ķ�������
   private int mustreach_edge[][];//����Ҫ����ı�����
   private int cannotreach_edge[][];//���벻�ܵ���ı�����
   public ant(){//�����������ݵĳ�ʼ��,ͬʱ��¼���ݵ�
	   Scanner cin=new Scanner(System.in);
	   n=cin.nextInt();
	   m=cin.nextInt();
	   s=new int [n+1][n+1];//1~n�ľ���
	   for (int i=1;i<=m;i++){
		   int a=cin.nextInt();
		   int b=cin.nextInt();
		   int c=cin.nextInt();
		   if ((s[a][b]==0)||(s[a][b]>c)){//����������֮����ڶ���·��
		   s[a][b]=c;
		   s[b][a]=c;
		   }
		 }
	   k1=cin.nextInt();//����һ��Ҫ����Ķ���
	   mustreach_point=new int[k1+1];
	   for (int i=1;i<=k1;i++){
		 mustreach_point[i]=cin.nextInt();
	   }
	   k2=cin.nextInt();//����һ��Ҫ����ı�
	   mustreach_edge=new int[k2+1][2];//һ���Ͱ�����㣬��һ���յ�
	   for (int i=1;i<=k2;i++){
	   mustreach_edge[i][0]=cin.nextInt();
	   mustreach_edge[i][1]=cin.nextInt();
	   }
	   k3=cin.nextInt();//����һ������ȥ�ı���
	   cannotreach_edge=new int[k3+1][2];
	   for (int i=1;i<=k3;i++){
		  cannotreach_edge[i][0]=cin.nextInt();
		  cannotreach_edge[i][1]=cin.nextInt();
		  s[cannotreach_edge[i][0]][cannotreach_edge[i][1]]=0;
		  s[cannotreach_edge[i][1]][cannotreach_edge[i][0]]=0;
		 }
	   cin.close();
 } 
    public void BFS(){//ͨ����BFS��������ս�� 
    ArrayDeque<point>arraydeque=new ArrayDeque<point>();
    point p=new point();
    p.index=1;
    p.arraylist=new ArrayList<Integer>();
    p.arraylist.add(1);
    p.value=0;
    p.length=1;
    int min=0xFFFFFF;//��СȨֵ
    //System.out.println(min);
    ArrayList<Integer>ans=new ArrayList<Integer>();//��С·��ֵ
    arraydeque.offer(p);//�����ѹ�������
    /*for (int i=1;i<=n;i++)
    {	for (int j=1;j<=n;j++)
    		System.out.print(s[i][j]);
    System.out.println();
    }
    */
    while(!arraydeque.isEmpty()){
    	point temp=arraydeque.poll();//��ö���Ԫ��
    	//System.out.println("����Ԫ��"+temp.index);
         if (temp.length==10)
    		continue;
         if (temp.value>min)//��֦�������Ż�����
        	 continue;
    	if (temp.index==n){//System.out.println(temp.value+" "+temp.arraylist);
    	//System.out.println("�Ƿ����"+judge(temp));
    		if (judge(temp)&&temp.value<min){//���������ָ���ıߺ͵㣬�������·��С�ڵ�ǰ���Ž�
    			min=temp.value;//�������Ž�ü�ֵ
    			ans=temp.arraylist;//����·���б�
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
    			//System.out.println("׼��ѹ�����"+ptemp.index);
    			//System.out.println("�б�"+ptemp.arraylist);
    			arraydeque.offer(ptemp);
    		}
    	}
    }
    if (!ans.isEmpty()){//�ж��Ƿ��н�
    System.out.println("��С����Ϊ:"+min);
    System.out.println("·��Ϊ:"+ans);
    }
    else{
    	System.out.println(-1);                        
    }
   }
   public boolean judge(point p){
	   /*�ж�������Ƿ񾭹����뾭����ĳЩ��ͱ�
	    * 
	    */
	  boolean ok=true;
	  for (int i=1;i<=k1;i++){//�ж��Ƿ񾭹�ָ����
		  if (!(p.arraylist.contains(mustreach_point[i]))){
			  ok=false;
			  break;
		  }
	  }
	  if (!ok)
		  return false;
	  int tempfirst=-1;
	  int tempsecond=-1;
	  for (int i=1;i<=k2;i++){//�ж��Ƿ񾭹�ָ����
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
