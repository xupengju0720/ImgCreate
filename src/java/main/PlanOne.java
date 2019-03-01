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
 *利用JFrame构建验证码的板块  运行之后进行弹窗显示
 */
public class PlanOne {
		 
		Random rand = new Random();
		/**
		 * 随机产生的加数和被加数
		 */
		private int jiashu=0;
		private int beijiashu=0;
		/**
		 * 随机产生的计算方式,0表示加,1表示减
		 */
		private int js=0;  
	    private char[] aa={'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
	    private char[] action={'加','减'};
	    private char[] jieguo={'等','是'};
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
		 * 第二种验证码的计算方式,两位数的加减法
		 * @return 一个新的验证码图片
		 */
		public BufferedImage getVerificationCode2()
		{
	        int width=150;
	        int height=60;
	        int degree=0;//记录一共旋转的角度,方便最后的时候旋转回来
	        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_4BYTE_ABGR); 
	        // 获取图形上下文 
	        Graphics g = image.getGraphics(); 
	        //画随机干扰框
	        //setSquareBackGround(g,width,height,5); 
	        //画干扰点
	        CreateRandomPoint(width, height,50,g,255);       
	        //随机画几条线
	       // CreateRandomLine(width, height,2,g,255);         
	        // 设定背景色 
	        Color background=getColor(180);
	        g.setColor(background);
	        g.fillRect(0, 0, width, height);                   
	        //画边框 
	        g.setColor(background);
	        g.drawRect(0,0,width-1,height-1);                       
	        // 　　将认证码显示到图像中,如果要生成更多位的认证码
	        char[] content=getDrawContent2();
	        int[] xs=getRadonWidths(width,content.length);
	        int[] ys=getRadomHeights(height,content.length);
	        for(int i=0;i<content.length;i++)
	        {   String s=content[i]+"";
	                   if(content[i]=='!')
	                	   s="";
	                   //如果在画字之前旋转图片
	            if(i!=2){       
	                   int maxDegree=rand.nextInt(2);
	                   if(maxDegree==0)
	                   	maxDegree=0;
	                   else maxDegree=305;
	           degree=rand.nextInt(45)+maxDegree;           
	            }   else degree=0;
	            g.setColor(getColor(background,230)); 
	            if(i==2)//运算符号显示大一些
	            	g.setFont(new Font("Atlantic Inline",Font.PLAIN,getIntRandom(22,25)));
	            else
	            g.setFont(new Font("Atlantic Inline",Font.PLAIN,getIntRandom(20,23)));          
	            RotateString(s, xs[i], ys[i], g, degree);
	        }       
	        //画随机干扰框
	         //setSquareBackGround(g,width,height,3); 
	        //画干扰点
	        CreateRandomPoint(width, height,100,g,100);       
	        //随机画几条线
	        //CreateRandomLine(width, height,8,g,100);      
	        // 释放图形上下文
	        g.dispose();
	        System.out.println("计算的结果是="+getResult2());      
	        return image;
	    }
		/**
		 * 旋转并且画出指定字符串
		 * @param s 需要旋转的字符串
		 * @param x 字符串的x坐标
		 * @param y 字符串的Y坐标
		 * @param g 画笔g
		 * @param degree 旋转的角度
		 */
	    private void RotateString(String s,int x,int y,Graphics g,int degree)
		{
			Graphics2D g2d = (Graphics2D) g.create();                                  
	        //   平移原点到图形环境的中心  ,这个方法的作用实际上就是将字符串移动到某一个位置
	        //g2d.translate(x-1, y+3);             
			g2d.translate(getIntRandom(x, x+2), getIntRandom(y,y+2));   
			//   旋转文本  
	         g2d.rotate(degree* Math.PI / 180);
	         //特别需要注意的是,这里的画笔已经具有了上次指定的一个位置,所以这里指定的其实是一个相对位置
	         g2d.drawString(s,0 , 0);
		}
		
	    /**
		 * 随机产生干扰点
		 * @param width
		 * @param height
		 * @param many
		 * @param g
		 * @param alpha 透明度0~255 0表示全透
		 */
		private void CreateRandomPoint(int width,int height,int many,Graphics g,int alpha)
		{  // 随机产生干扰点
	        for (int i=0;i<many;i++) {
	            int x = rand.nextInt(width); 
	            int y = rand.nextInt(height); 
	            g.setColor(getColor(alpha));
	            g.drawOval(x,y,rand.nextInt(3),rand.nextInt(3)); 
	        } 
		}
	/**
	 * 随机产生干扰线条
	 * @param width
	 * @param height
	 * @param minMany 最少产生的数量
	 * @param g
	 * @param alpha 透明度0~255 0表示全透
	 */
		private void CreateRandomLine(int width,int height,int minMany,Graphics g,int alpha)
		{  // 随机产生干扰线条
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
		 * 由随机产生的方块来作为干扰背景
		 */
		private void setSquareBackGround(Graphics g,int width,int height,int count){
			// 随机产生干扰线条
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
		 * @return 随机返一个指定区间的数字
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
		
		/*** 随机返回一种颜色,透明度0~255 0表示全透
		 * @return 随机返回一种颜色
		 * @param alpha 透明度0~255 0表示全透
		 */
		private Color getColor(int alpha)
		{
			int R=(int) (Math.random()*255);
			int G=(int) (Math.random()*255);
			int B=(int) (Math.random()*255);
		return new Color(R,G,B,alpha);
		}
		
		/*** 
		 * @return 随机返回一种颜色,与给定颜色相类似
		 * @param alpha 透明度0~255 0表示全透
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
		 * 在颜色值和给定的随机数之间返回一个随机颜色值0~255
		 * @param colorValue
		 * @param randomValue
		 * @param deep,默认为0
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
	     * @return 返回getVerificationCode2需要画出的内容:两位数加减法字符数组
	     */
	    private char[] getDrawContent2()
	    { beijiashu=0;
	      jiashu=0;
	    char[] temp=new char[6];
	    char[] w =aa;  
	    int k=0;    
	          /**
	           * 产生被加数
	           */
	        //从aa\bb\cc中选择一个字符数组作为素材    
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
	           * 产生加数
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
	          //选择加减乘除
	          w=action;
	          k=(int)(Math.random()*2 );
	          temp[2]=w[k];
	          js=k%2;
	          //结果
	          w=jieguo;
	          k=(int)(Math.random()*2);
	          temp[5]=w[k];
	          return temp;
	    }   
	    /**
	     * 对图片选择,这里保留以方便以后使用
	     * @param bufferedimage
	     * @param degree
	     * @return 一张旋转后的图片
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
	     * 得到验证码getVerificationCode2,计算出来的结果
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
	     * @return 画图的时候随机的高度的数组
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
	     * @return 画图的时候起始x坐标的数组
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
	      //在的规定的范围内产生一个随机数
	      return (int)(Math.random()*temp)+minWidth+minJianju;   	
	    }
	 
	 
	}

