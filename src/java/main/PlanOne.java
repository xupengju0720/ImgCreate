package java.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * @author xupengju
 *����JFrame������֤��İ��  ����֮����е�����ʾ
 */
public class PlanOne {
		 
		Random rand = new Random();
		/**
		 * ��������ļ����ͱ�����
		 */
		private int jiashu=0;
		private int beijiashu=0;
		/**
		 * ��������ļ��㷽ʽ,0��ʾ��,1��ʾ��
		 */
		private int js=0;  
	    private char[] aa={'��','Ҽ','��','��','��','��','½','��','��','��'};
	    private char[] action={'��','��'};
	    private char[] jieguo={'��','��'};
		public static void main(String[] args) {
			  PlanOne code=new PlanOne();
			  JFrame jFrame=new JFrame();
	          jFrame.setBounds(400, 400, 250, 250);
	          ImageIcon img = new ImageIcon(code.getVerificationCode2()); 
	          JLabel background = new JLabel(img);
	          jFrame.add(background);
	          jFrame.setVisible(true);
	          jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		}
		/**
		 * �ڶ�����֤��ļ��㷽ʽ,��λ���ļӼ���
		 * @return һ���µ���֤��ͼƬ
		 */
		public BufferedImage getVerificationCode2()
		{
	        int width=150;
	        int height=60;
	        int degree=0;//��¼һ����ת�ĽǶ�,��������ʱ����ת����
	        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_4BYTE_ABGR); 
	        // ��ȡͼ�������� 
	        Graphics g = image.getGraphics(); 
	        //��������ſ�
	        //setSquareBackGround(g,width,height,5); 
	        //�����ŵ�
	        CreateRandomPoint(width, height,50,g,255);       
	        //�����������
	       // CreateRandomLine(width, height,2,g,255);         
	        // �趨����ɫ 
	        Color background=getColor(180);
	        g.setColor(background);
	        g.fillRect(0, 0, width, height);                   
	        //���߿� 
	        g.setColor(background);
	        g.drawRect(0,0,width-1,height-1);                       
	        // ��������֤����ʾ��ͼ����,���Ҫ���ɸ���λ����֤��
	        char[] content=getDrawContent2();
	        int[] xs=getRadonWidths(width,content.length);
	        int[] ys=getRadomHeights(height,content.length);
	        for(int i=0;i<content.length;i++)
	        {   String s=content[i]+"";
	                   if(content[i]=='!')
	                	   s="";
	                   //����ڻ���֮ǰ��תͼƬ
	            if(i!=2){       
	                   int maxDegree=rand.nextInt(2);
	                   if(maxDegree==0)
	                   	maxDegree=0;
	                   else maxDegree=305;
	           degree=rand.nextInt(45)+maxDegree;           
	            }   else degree=0;
	            g.setColor(getColor(background,230)); 
	            if(i==2)//���������ʾ��һЩ
	            	g.setFont(new Font("Atlantic Inline",Font.PLAIN,getIntRandom(22,25)));
	            else
	            g.setFont(new Font("Atlantic Inline",Font.PLAIN,getIntRandom(20,23)));          
	            RotateString(s, xs[i], ys[i], g, degree);
	        }       
	        //��������ſ�
	         //setSquareBackGround(g,width,height,3); 
	        //�����ŵ�
	        CreateRandomPoint(width, height,100,g,100);       
	        //�����������
	        //CreateRandomLine(width, height,8,g,100);      
	        // �ͷ�ͼ��������
	        g.dispose();
	        System.out.println("����Ľ����="+getResult2());      
	        return image;
	    }
		/**
		 * ��ת���һ���ָ���ַ���
		 * @param s ��Ҫ��ת���ַ���
		 * @param x �ַ�����x����
		 * @param y �ַ�����Y����
		 * @param g ����g
		 * @param degree ��ת�ĽǶ�
		 */
	    private void RotateString(String s,int x,int y,Graphics g,int degree)
		{
			Graphics2D g2d = (Graphics2D) g.create();                                  
	        //   ƽ��ԭ�㵽ͼ�λ���������  ,�������������ʵ���Ͼ��ǽ��ַ����ƶ���ĳһ��λ��
	        //g2d.translate(x-1, y+3);             
			g2d.translate(getIntRandom(x, x+2), getIntRandom(y,y+2));   
			//   ��ת�ı�  
	         g2d.rotate(degree* Math.PI / 180);
	         //�ر���Ҫע�����,����Ļ����Ѿ��������ϴ�ָ����һ��λ��,��������ָ������ʵ��һ�����λ��
	         g2d.drawString(s,0 , 0);
		}
		
	    /**
		 * ����������ŵ�
		 * @param width
		 * @param height
		 * @param many
		 * @param g
		 * @param alpha ͸����0~255 0��ʾȫ͸
		 */
		private void CreateRandomPoint(int width,int height,int many,Graphics g,int alpha)
		{  // ����������ŵ�
	        for (int i=0;i<many;i++) {
	            int x = rand.nextInt(width); 
	            int y = rand.nextInt(height); 
	            g.setColor(getColor(alpha));
	            g.drawOval(x,y,rand.nextInt(3),rand.nextInt(3)); 
	        } 
		}
	/**
	 * ���������������
	 * @param width
	 * @param height
	 * @param minMany ���ٲ���������
	 * @param g
	 * @param alpha ͸����0~255 0��ʾȫ͸
	 */
		private void CreateRandomLine(int width,int height,int minMany,Graphics g,int alpha)
		{  // ���������������
			for (int i=0;i<getIntRandom(minMany, minMany+6);i++) { 
	            int x1 =getIntRandom(0,(int)(width*0.6)); 
	            int y1 =getIntRandom(0,(int)(height*0.6)); 
	            int x2 =getIntRandom((int)(width*0.4),width); 
	            int y2 =getIntRandom((int)(height*0.2),height);  
	            g.setColor(getColor(alpha));
	            g.drawLine(x1, y1, x2, y2);
	        } 
		}
		
		/**
		 * ����������ķ�������Ϊ���ű���
		 */
		private void setSquareBackGround(Graphics g,int width,int height,int count){
			// ���������������
			for (int i=0;i<getIntRandom(count, count+2);i++) { 
	            int x1 =getIntRandom(0,(int)(width*0.3)); 
	            int y1 =getIntRandom(0,(int)(height*0.3)); 
	            int x2 =getIntRandom((int)(width*0.5),width); 
	            int y2 =getIntRandom((int)(height*0.55),height);  
	            g.setColor(getColor(100));
	            int w=x2-x1;
	            int h=y2-y1;
	            if(w<0) w=-w;
	            if(h<0) h=-h;
	            g.drawRect(x1, y1, w, h);
	            g.setColor(getColor(25));
	            g.fillRect(x1, y1, w, h);
	        } 
		}
		
		/*** 
		 * @return �����һ��ָ�����������
		 */
		private int getIntRandom(int start,int end)
		{   if(end<start)
			{
			  int t=end;
			  end=start;
			  start=t;
			}
			int i=start+(int) (Math.random()*(end-start));
		    return i;
		}
		
		@SuppressWarnings("unused")
		private int getIntRandom(double start,double end)
		{   if(end<start)
			{
			  double t=end;
			  end=start;
			  start=t;
			}
			double i=start+(int) (Math.random()*(end-start));
		    return (int)i;
		}
		
		/*** �������һ����ɫ,͸����0~255 0��ʾȫ͸
		 * @return �������һ����ɫ
		 * @param alpha ͸����0~255 0��ʾȫ͸
		 */
		private Color getColor(int alpha)
		{
			int R=(int) (Math.random()*255);
			int G=(int) (Math.random()*255);
			int B=(int) (Math.random()*255);
		return new Color(R,G,B,alpha);
		}
		
		/*** 
		 * @return �������һ����ɫ,�������ɫ������
		 * @param alpha ͸����0~255 0��ʾȫ͸
		 */
		private Color getColor(Color c,int alpha)
		{
			int R=getIntRandom(-140,140);
			int G=getIntRandom(-140,140);
			int B=getIntRandom(-140,140);
			R=getCloserRandom(c.getRed(),R);
			B=getCloserRandom(c.getBlue(),B);
			G=getCloserRandom(c.getGreen(),G);
		return new Color(R,G,B,alpha);
		}
		/**
		 * ����ɫֵ�͸����������֮�䷵��һ�������ɫֵ0~255
		 * @param colorValue
		 * @param randomValue
		 * @param deep,Ĭ��Ϊ0
		 * @return
		 */
		@SuppressWarnings("unused")
		private int getCloserRandom(int colorValue,int randomValue){		
			if(colorValue+randomValue>255)
			{ 
				return getCloserRandom(colorValue,randomValue-getIntRandom(1, randomValue+20));
			}else if(colorValue+randomValue<0){
				return getCloserRandom(colorValue,randomValue+getIntRandom(1, randomValue+20));
			}else if(Math.abs(randomValue)<60)
			{
				return getCloserRandom(colorValue,getIntRandom(-255,255));
			}else{
				return colorValue+randomValue;
			}
		}
		
	    /**
	     * 
	     * @return ����getVerificationCode2��Ҫ����������:��λ���Ӽ����ַ�����
	     */
	    private char[] getDrawContent2()
	    { beijiashu=0;
	      jiashu=0;
	    char[] temp=new char[6];
	    char[] w =aa;  
	    int k=0;    
	          /**
	           * ����������
	           */
	        //��aa\bb\cc��ѡ��һ���ַ�������Ϊ�ز�    
	        k=(int)(Math.random()*4);
	      	  w=aa;
	          k=(int)(Math.random()*10);
	          temp[0]=w[k];
	          if(k==0) temp[0]='!';
	          beijiashu+=k*10;
	          k=(int)(Math.random()*10);
	          temp[1]=w[k];
	          beijiashu+=k;
	          /**
	           * ��������
	           */
	        k=(int)(Math.random()*2);
	      	  w=aa;
	       k=(int)(Math.random()*10);
	          temp[3]=w[k];
	      if(k==0) temp[3]='!';  
	      jiashu=k*10+jiashu;         
	        k=(int)(Math.random()*10);
	          temp[4]=w[k];
	          jiashu+=k;
	          //ѡ��Ӽ��˳�
	          w=action;
	          k=(int)(Math.random()*2 );
	          temp[2]=w[k];
	          js=k%2;
	          //���
	          w=jieguo;
	          k=(int)(Math.random()*2);
	          temp[5]=w[k];
	          return temp;
	    }   
	    /**
	     * ��ͼƬѡ��,���ﱣ���Է����Ժ�ʹ��
	     * @param bufferedimage
	     * @param degree
	     * @return һ����ת���ͼƬ
	     */
	    public BufferedImage rolateImage(BufferedImage bufferedimage,int degree,Color backGround)
	    {                
	    	BufferedImage img;
	    	int w = bufferedimage.getWidth();
	    	int h = bufferedimage.getHeight();
	    	int type = BufferedImage.TYPE_INT_RGB;
	    	Graphics2D graphics2d;
	    	graphics2d = (img = new BufferedImage(w, h, type)).createGraphics();
	    	graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	    	graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
	    	graphics2d.drawImage(bufferedimage,null, null);	
	    	return img;
	    }
	    
	    /**
	     * �õ���֤��getVerificationCode2,��������Ľ��
	     */
	    public int getResult2()
	    { if(js==0)
	    	return(beijiashu+jiashu);
	      else if(js==1) return (beijiashu-jiashu);
	    	return 0;
	    }
	    /**
	     * 
	     * @param many
	     * @return ��ͼ��ʱ������ĸ߶ȵ�����
	     */
	    private int[] getRadomHeights(int height,int many){
	    	int[] temp=new int[many]; 
	    	for(int i=0;i<many;i++){
	    		temp[i]=getRadomHeight(height);
	    	}
	    	return temp;
	    }
	    /**
	     * 
	     * @param many
	     * @return ��ͼ��ʱ����ʼx���������
	     */
	    private int[] getRadonWidths(int width,int many)
	    { int[] temp=new int[many]; 
		  for(int i=0;i<many;i++){
			  if(i==0)
			  temp[i]=getRadonWidth(many,0,width);
			  else temp[i]=getRadonWidth(many,temp[i-1],width);
		  }
		  return temp;   	
	    }
	    
	    private int getRadomHeight(int fullHeight)
	    { 
	      return getIntRandom((int)(fullHeight*0.2), (int)(fullHeight*0.75));   	
	    }
	    
	    private int getRadonWidth(int many,int minWidth, int maxWidth)
	    {
	      int minJianju=maxWidth/(many+2);
	      int maxJianju=maxWidth/(many);
	      int temp=maxJianju-minJianju;
	      //�ڵĹ涨�ķ�Χ�ڲ���һ�������
	      return (int)(Math.random()*temp)+minWidth+minJianju;   	
	    }
	 
	 
	}

