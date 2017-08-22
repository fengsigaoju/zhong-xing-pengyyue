package ÖÐÐËÅõÔÂ;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * ×ÜÌåË¼Â·:ÏÈÅÜBFS£¬¼ÇÂ¼µ±Ç°¾­¹ýµÄ½ÚµãÐÅÏ¢£¬Í¬Ê±¼ÇÂ¼µ±Ç°½ÚµãµÄÈ¨Öµ,Èç¹ûµ±Ç°½Úµã¶àÓà9¸öÔòÖ±½Ó·µ»Ø
 * µ±±éÀúµ½ÖÕµãÊ±£¬ÅÐ¶Ï»á²»»á¾­¹ý±ØÐëµ½´ïµÄÁ½¸öµãºÍ±ß£¬²»ÄÜ¾­¹ýµÄ±ßÔÚ¶ÁÈëµÄÊ±ºòÌø¹ý
 * ¼ÇÂ¼ÏÂ×îÓÅµÄÂ·¾¶ÖµºÍÂ·¾¶Â·Ïß
 */
class point implements Cloneable{//¼Ì³Ð¿ËÂ¡½Ó¿Ú
	 //注释
	 int  index;//Ö®Ç°µÄÂ·¾¶ÉÏµÄ×îºóÒ»¸öµãµÄÏÂ±ê
	 ArrayList<Integer>arraylist;
	 int value;//¾­¹ý¶¥µãµÄÈ¨Öµ
	 int length;//¾­¹ý½ÚµãµÄ¸öÊý
	  public Object clone(){//ÖØÐ´clone·½·¨£¬ÊµÏÖÉî¿½±´£¬ÕâÀïÖ÷ÒªÊÇÖ¸arraylistµÄÇ³¿½±´ÎÊÌâ
		 point p=null;
		 try {
			p=(point)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		 //p.arraylist=(ArrayList)arraylist.clone();Ç³¿½±´
		 p.index=this.index;
		 p.arraylist=new ArrayList<Integer>();
		 p.arraylist.addAll(this.arraylist);
		 p.value=this.value;
		 p.length=this.length;
		 return p;
	 }
}
public class ant{
   /*ÏÈ¸æËßÓÐ¶àÉÙ¸öµãn£¨Ä¬ÈÏµãµÄ×ø±êÊÇ1~n,ÆðµãÊÇ1ÖÕµãÊÇn),¶àÉÙÌõÂ·¾¶m
    *ÔÙ¸æËßmÌõÂ·¾¶µÄÆðµã,ÖÕµã,Â·¾¶Öµ(ÆðµãºÍÖÕµã¶¼ÊÇ1~n)
    *ÔÙÊäÈëÒ»¸öÊýk1,´ú±íÒ»¶¨Òªµ½´ïµÄ¶¥µã
    *½ÓÏÂÈ¥k1¸öÊý,´ú±ík1¸ö±ØÐëÒªµ½´ïµÄ¶¥µã
    *ÔÙÊäÈëÒ»¸öÊýk2,´ú±í±ßÊý
    *½ÓÏÂÈ¥k2ÐÐ£¬Ã¿Ò»ÐÐ´ú±ík2¸öÆðµã,ÖÕµãºÍÈ¨Öµ
    *ÔÙÊäÈëk3ÐÐ,´ú±íÒ»¶¨²»ÄÜÈ¥µÄk3¸öÂ·¾¶
    *½ÓÏÂÈ¥k3ÐÐ£¬Ã¿Ò»ÐÐ´ú±ík3¸öÆðµã,ÖÕµãºÍÈ¨Öµ
    *ÑùÀýÊäÈë:
    *17 
    */
   private int n;//Í¼µÄµãÊý
   private int m;//Í¼µÄ±ßÊý
   private int s[][];//ÓÃÀ´´æ´¢¾àÀë¾ØÕó
   private int k1;//ÓÃÀ´¼ÇÂ¼±ØÐëµ½´ïµÄ¶¥µãÐÅÏ¢
   private int k2;//ÓÃÀ´¼ÇÂ¼±ØÐëµ½´ïµÄ±ßÊý
   private int k3;//ÓÃÀ´¼ÇÂ¼ÏÂÒ»¶¨²»ÄÜÈ¥µÄ±ßÊý
   private int mustreach_point[];//±ØÐëÒª´ïµ½µÄ¶¥µãÊý×é
   private int mustreach_edge[][];//±ØÐëÒªµ½´ïµÄ±ßÊý×é
   private int cannotreach_edge[][];//±ØÐë²»ÄÜµ½´ïµÄ±ßÊý×é
   public ant(){//ÓÃÀ´½øÐÐÊý¾ÝµÄ³õÊ¼»¯,Í¬Ê±¼ÇÂ¼Êý¾ÝµÄ
	   Scanner cin=new Scanner(System.in);
	   n=cin.nextInt();
	   m=cin.nextInt();
	   s=new int [n+1][n+1];//1~nµÄ¾ØÕó
	   for (int i=1;i<=m;i++){
		   int a=cin.nextInt();
		   int b=cin.nextInt();
		   int c=cin.nextInt();
		   if ((s[a][b]==0)||(s[a][b]>c)){//¿¼ÂÇÁ½¸öµãÖ®¼ä´æÔÚ¶àÌõÂ·¾¶
		   s[a][b]=c;
		   s[b][a]=c;
		   }
		 }
	   k1=cin.nextInt();//¶ÁÈëÒ»¶¨Òªµ½´ïµÄ¶¥µã
	   mustreach_point=new int[k1+1];
	   for (int i=1;i<=k1;i++){
		 mustreach_point[i]=cin.nextInt();
	   }
	   k2=cin.nextInt();//¶ÁÈëÒ»¶¨Òªµ½´ïµÄ±ß
	   mustreach_edge=new int[k2+1][2];//Ò»¸öµÍ°«±íÆðµã£¬ÁíÒ»¸öÖÕµã
	   for (int i=1;i<=k2;i++){
	   mustreach_edge[i][0]=cin.nextInt();
	   mustreach_edge[i][1]=cin.nextInt();
	   }
	   k3=cin.nextInt();//¶ÁÈëÒ»¶¨²»ÄÜÈ¥µÄ±ßÊý
	   cannotreach_edge=new int[k3+1][2];
	   for (int i=1;i<=k3;i++){
		  cannotreach_edge[i][0]=cin.nextInt();
		  cannotreach_edge[i][1]=cin.nextInt();
		  s[cannotreach_edge[i][0]][cannotreach_edge[i][1]]=0;
		  s[cannotreach_edge[i][1]][cannotreach_edge[i][0]]=0;
		 }
	   cin.close();
 } 
    public void BFS(){//Í¨¹ýÅÜBFSÀ´»ñµÃ×îÖÕ½á¹û 
    ArrayDeque<point>arraydeque=new ArrayDeque<point>();
    point p=new point();
    p.index=1;
    p.arraylist=new ArrayList<Integer>();
    p.arraylist.add(1);
    p.value=0;
    p.length=1;
    int min=0xFFFFFF;//×îÐ¡È¨Öµ
    //System.out.println(min);
    ArrayList<Integer>ans=new ArrayList<Integer>();//×îÐ¡Â·¾¶Öµ
    arraydeque.offer(p);//°ÑÆðµãÑ¹Èë¶ÓÁÐÖÐ
    /*for (int i=1;i<=n;i++)
    {	for (int j=1;j<=n;j++)
    		System.out.print(s[i][j]);
    System.out.println();
    }
    */
    while(!arraydeque.isEmpty()){
    	point temp=arraydeque.poll();//»ñµÃ¶¥²¿ÔªËØ
    	//System.out.println("¶¥²¿ÔªËØ"+temp.index);
         if (temp.length==10)
    		continue;
         if (temp.value>min)//¼ôÖ¦£¬½øÐÐÓÅ»¯Êý¾Ý
        	 continue;
    	if (temp.index==n){//System.out.println(temp.value+" "+temp.arraylist);
    	//System.out.println("ÊÇ·ñ·ûºÏ"+judge(temp));
    		if (judge(temp)&&temp.value<min){//Èç¹û¾­¹ýÁËÖ¸¶¨µÄ±ßºÍµã£¬²¢ÇÒ×î´óÂ·¾¶Ð¡ÓÚµ±Ç°×îÓÅ½â
    			min=temp.value;//¸üÐÂ×îÓÅ½âµÃ¼ÛÖµ
    			ans=temp.arraylist;//¸üÐÂÂ·¾¶ÁÐ±í
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
    			//System.out.println("×¼±¸Ñ¹Èë¶ÓÁÐ"+ptemp.index);
    			//System.out.println("ÁÐ±í"+ptemp.arraylist);
    			arraydeque.offer(ptemp);
    		}
    	}
    }
    if (!ans.isEmpty()){//ÅÐ¶ÏÊÇ·ñÓÐ½â
    System.out.println("×îÐ¡´ú¼ÛÎª:"+min);
    System.out.println("Â·¾¶Îª:"+ans);
    }
    else{
    	System.out.println(-1);                        
    }
   }
   public boolean judge(point p){
	   /*ÅÐ¶ÏÕâ¸öµãÊÇ·ñ¾­¹ý±ØÐë¾­¹ýµÄÄ³Ð©µãºÍ±ß
	    * 
	    */
	  boolean ok=true;
	  for (int i=1;i<=k1;i++){//ÅÐ¶ÏÊÇ·ñ¾­¹ýÖ¸¶¨µã
		  if (!(p.arraylist.contains(mustreach_point[i]))){
			  ok=false;
			  break;
		  }
	  }
	  if (!ok)
		  return false;
	  int tempfirst=-1;
	  int tempsecond=-1;
	  for (int i=1;i<=k2;i++){//ÅÐ¶ÏÊÇ·ñ¾­¹ýÖ¸¶¨±ß
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
