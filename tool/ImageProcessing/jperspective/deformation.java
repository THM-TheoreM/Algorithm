import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public final class deformation 
{
	private deformation(){}
	
	private static int dot(int[] x, int[] y)
	{return x[0] * y[0] + x[1] * y[1];}
	
	private static boolean isColinear(int x1, int y1, int x2, int y2, int x3, int y3)
	{
		if(x1 * y2 + x2 * y3 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3 == 0) return true;
		return false;
	}
	
	private static int ConvexNumber(int x1, int y1, int x2, int y2, int x3, int y3)
	{
		if (isColinear(x1, y1, x2, y2, x3, y3) || isColinear(0, 0, x2, y2, x3, y3) || isColinear(x1, y1, 0, 0, x3, y3) || isColinear(x1, y1, x2, y2, 0, 0))
			throw new RuntimeException("Colinear, algorithm fails.");
		
		int[] v0 = new int[]{x2 - x1, y2 - y1};
		int[] v1 = new int[]{x3 - x1, y3 - y1};
		int[] v2 = new int[]{-x1, -y1};
		
		int[][] M = new int[][]{{dot(v0, v0), dot(v1, v0)}, {dot(v0, v1), dot(v1, v1)}};
		int[] b = new int[]{dot(v2, v0), dot(v2, v1)};

		double detA = 1.0 * M[0][0] * M[1][1] - 1.0 * M[0][1] * M[1][0];
		double u = (1.0 * M[1][1] * b[0] - 1.0 * M[0][1] * b[1]) / detA;
		double v = (1.0 * M[0][0] * b[1] - 1.0 * M[1][0] * b[0]) / detA;
		
		if(u > 0 && v > 0 && u + v > 1) return 1;
		
		v0 = new int[]{x1 - x2, y1 - y2};
		v1 = new int[]{x3 - x2, y3 - y2};
		v2 = new int[]{-x2, -y2};
		
		M = new int[][]{{dot(v0, v0), dot(v1, v0)}, {dot(v0, v1), dot(v1, v1)}};
		b = new int[]{dot(v2, v0), dot(v2, v1)};
		
		detA = 1.0 * M[0][0] * M[1][1] - 1.0 * M[0][1] * M[1][0];
		u = (1.0 * M[1][1] * b[0] - 1.0 * M[0][1] * b[1]) / detA;
		v = (1.0 * M[0][0] * b[1] - 1.0 * M[1][0] * b[0]) / detA;
		
		if(u > 0 && v > 0 && u + v > 1) return 2;
		
		v0 = new int[]{x1 - x3, y1 - y3};
		v1 = new int[]{x2 - x3, y2 - y3};
		v2 = new int[]{-x3, -y3};
		
		M = new int[][]{{dot(v0, v0), dot(v1, v0)}, {dot(v0, v1), dot(v1, v1)}};
		b = new int[]{dot(v2, v0), dot(v2, v1)};
		
		detA = 1.0 * M[0][0] * M[1][1] - 1.0 * M[0][1] * M[1][0];
		u = (1.0 * M[1][1] * b[0] - 1.0 * M[0][1] * b[1]) / detA;
		v = (1.0 * M[0][0] * b[1] - 1.0 * M[1][0] * b[0]) / detA;
		
		if(u > 0 && v > 0 && u + v > 1) return 3;
		
		throw new RuntimeException("Concave, algorithm fails.");
	}
	
	private static int[][] line(int x1, int y1, int x2, int y2, int x3, int y3)
	{
		int[][] e = new int[4][4];
		int number = ConvexNumber(x1, y1, x2, y2, x3, y3);
		
		if(number == 1)
		{
			e[0] = new int[]{x2, y2, 0, 0};
			e[1] = new int[]{x3, y3, 0, 0};
			e[2] = new int[]{x3, y3, x1, y1};
			e[3] = new int[]{x1, y1, x2, y2};
		}
		else if(number == 2)
		{
			e[0] = new int[]{x3, y3, 0, 0};
			e[1] = new int[]{x1, y1, 0, 0};
			e[2] = new int[]{x1, y1, x2, y2};
			e[3] = new int[]{x2, y2, x3, y3};
		}
		else
		{
			e[0] = new int[]{x1, y1, 0, 0};
			e[1] = new int[]{x2, y2, 0, 0};
			e[2] = new int[]{x2, y2, x3, y3};
			e[3] = new int[]{x3, y3, x1, y1};
		}
		return e;
	}
	
	private static double[] intersection(int c, int[] a)
	{
		double[] inter = new double[3];
		for(int i=0;i<3;i++)
			inter[i] = 0;
		
		if(a[1]==c && a[3]==c)
		{
			inter[0] = 2;
			inter[1] = Math.min(a[0],a[2]);
			inter[2] = Math.max(a[0],a[2]);
		}
		else if((a[1]-c)*(a[3]-c)<=0)
		{
			inter[0] = 1;
			inter[1] = a[0]+1.0*(c-a[1])*(a[2]-a[0])/(a[3]-a[1]);
		}
		
		return inter;
	}
	
	private static double[] intersection(int c, int[][] a)
	{
		int n = 0;
		double[] inter = new double[4];
		
		for(int i=0;i<4;i++)
		{
			double[] in = intersection(c,a[i]);
			if(in[0]==1)
			{
				inter[n] = in[1];
				n++;
			}
			else if(in[0]==2)
			{
				inter[n] = in[1];
				n++;
				inter[n] = in[2];
				n++;
			}
			if(n==2) break;
		}
		
		for(int i=0;i<4;i++)
		{
			double[] in = intersection(c-1,a[i]);
			if(in[0]==1)
			{
				inter[n] = in[1];
				n++;
			}
			else if(in[0]==2)
			{
				inter[n] = in[1];
				n++;
				inter[n] = in[2];
				n++;
			}
			if(n==4) break;
		}
		
		double[] x = new double[2];
		x[0] = inter[0];
		x[1] = inter[0];
		for(int i=1;i<4;i++)
		{
			if(x[0]>inter[i]) x[0] = inter[i];
			if(x[1]<inter[i]) x[1] = inter[i];
		}
		
		return x;
	}
	
	private static int[] frame(int[][] x)
	{
		int[] fr = new int[4];
		fr[0] = x[0][0];
		fr[1] = x[0][0];
		fr[2] = x[0][1];
		fr[3] = x[0][1];
		for(int i=1;i<4;i++)
		{
			if(x[i][0]<fr[0]) fr[0] = x[i][0];
			if(x[i][0]>fr[1]) fr[1] = x[i][0];
			if(x[i][1]<fr[2]) fr[2] = x[i][1];
			if(x[i][1]>fr[3]) fr[3] = x[i][1];
		}
		return fr;
	}
	
	private static double[] leftProduct(double[][] ma, int i, int j, int k)
	{
		double[] p = new double[3];
		p[0] = i * ma[0][0] + j * ma[1][0] + k * ma[2][0];
		p[1] = i * ma[0][1] + j * ma[1][1] + k * ma[2][1];
		p[2] = i * ma[0][2] + j * ma[1][2] + k * ma[2][2];
		return p;
	}

	private static double[][] inverse(double[][] ma)
	{
		double[][] inma = new double[3][3];
		
		double A = ma[0][0] * ma[1][1] * ma[2][2] + ma[0][1] * ma[1][2] * ma[2][0] + ma[1][0] * ma[2][1] * ma[0][2] - ma[0][2] * ma[1][1] * ma[2][0] - ma[0][1] * ma[1][0] * ma[2][2] - ma[1][2] * ma[2][1] * ma[0][0];
		
		inma[0][0] =  (ma[1][1] * ma[2][2] - ma[1][2] * ma[2][1])/A;
		inma[0][1] = -(ma[0][1] * ma[2][2] - ma[0][2] * ma[2][1])/A;		
		inma[0][2] =  (ma[0][1] * ma[1][2] - ma[0][2] * ma[1][1])/A;
		inma[1][0] = -(ma[1][0] * ma[2][2] - ma[1][2] * ma[2][0])/A;
		inma[1][1] =  (ma[0][0] * ma[2][2] - ma[0][2] * ma[2][0])/A;
		inma[1][2] = -(ma[0][0] * ma[1][2] - ma[0][2] * ma[1][0])/A;
		inma[2][0] =  (ma[1][0] * ma[2][1] - ma[1][1] * ma[2][0])/A;
		inma[2][1] = -(ma[0][0] * ma[2][1] - ma[0][1] * ma[2][0])/A;
		inma[2][2] =  (ma[0][0] * ma[1][1] - ma[1][0] * ma[0][1])/A;
		
		return inma;
	}
	
	public static BufferedImage perspective(BufferedImage im, BufferedImage im2, int[][] x)
	{
		double shun = 1.0*(x[1][0]-x[0][0])*(x[2][1]-x[0][1])-1.0*(x[2][0]-x[0][0])*(x[1][1]-x[0][1]);
		double shi  = 1.0*(x[2][0]-x[0][0])*(x[3][1]-x[0][1])-1.0*(x[3][0]-x[0][0])*(x[2][1]-x[0][1]);
		if(shun <= 0 || shi <= 0) throw new RuntimeException("Need clockwise, algorithm fails."); 
		
		int w = im2.getWidth();
		int h = im2.getHeight();

		int[][] u = new int[4][2];
		u[0][0] = 0;
		u[0][1] = 0;
		u[1][0] = w-1;
		u[1][1] = 0;
		u[2][0] = w-1;
		u[2][1] = h-1;
		u[3][0] = 0;
		u[3][1] = h-1;
		
		double[][] ma = new double[3][3];
		ma[2][0] = (double)x[0][0];
		ma[2][1] = (double)x[0][1];
		ma[2][2] = 1.0;
		
		double det = (1.0 * u[1][0] * x[1][0] - 1.0 * u[1][0] * x[2][0]) * (1.0 * u[2][1] * x[3][1] - 1.0 * u[2][1] * x[2][1]) - (1.0 * u[2][1] * x[3][0] - 1.0 * u[2][1] * x[2][0]) * (1.0 * u[1][0] * x[1][1] - 1.0 * u[1][0] * x[2][1]);
		double det1 = (x[2][0] - x[3][0] - x[1][0] + x[0][0]) * (1.0 * u[2][1] * x[3][1] - 1.0 * u[2][1] * x[2][1]) - (1.0 * u[2][1] * x[3][0] - 1.0 * u[2][1] * x[2][0]) * (x[2][1] - x[3][1] - x[1][1] + x[0][1]);
		double det2 = (1.0 * u[1][0] * x[1][0] - 1.0 * u[1][0] * x[2][0]) * (x[2][1] - x[3][1] - x[1][1] + x[0][1]) - (1.0 * u[1][0] * x[1][1] - 1.0 * u[1][0] * x[2][1]) * (1.0 * x[2][0] - x[3][0] - x[1][0] + x[0][0]);
		
		ma[0][2] = 1.0 * det1/det;
		ma[1][2] = 1.0 * det2/det;
		ma[0][0] = 1.0 * (ma[0][2] * u[1][0] * x[1][0] + x[1][0] - x[0][0]) / u[1][0];
		ma[0][1] = 1.0 * (ma[0][2] * u[1][0] * x[1][1] + x[1][1] - x[0][1]) / u[1][0];
		ma[1][0] = 1.0 * (ma[1][2] * u[2][1] * x[3][0] + x[3][0] - x[0][0]) / u[2][1];
		ma[1][1] = 1.0 * (ma[1][2] * u[2][1] * x[3][1] + x[3][1] - x[0][1]) / u[2][1];

		double[][] inma = inverse(ma);
		int[] fr = frame(x);
		
		int index = 0;
		for(int i=0;i<4;i++)
			if(x[i][1]==fr[3])
				index = i;
		
		int n = 0;
		int[] co = new int[6];
		for(int i=0;i<4;i++)
			if(i!=index)
			{
				co[n] = x[i][0] - x[index][0];
				n++;
				co[n] = x[index][1] - x[i][1];
				n++;
			}

		int[][] e = line(co[0],co[1],co[2],co[3],co[4],co[5]);

		for(int j=fr[2];j<fr[3];j++)
		{	
			double[] in = intersection(fr[3] - j,e);

			for(int i=(int)(Math.floor(in[0]))+x[index][0];i<(int)(Math.ceil(in[1]))+x[index][0]+1;i++)
			{
					double[] origin = leftProduct(inma,i,j,1);
					double a = origin[0]/origin[2];
					double b = origin[1]/origin[2];
					int xmin = Math.min(Math.max((int)(a)+1,0),w-1);
					int xmax = (int)(a);
					int ymin = Math.min(Math.max((int)(b)+1,0),h-1);
					int ymax = (int)(b);
					int color;
					if(xmax<=w-1 && ymax<=h-1) color = im2.getRGB(xmin,ymin);
					else if(xmax>w-1)          color = im2.getRGB(w-1,ymin);
					else                       color = im2.getRGB(xmin,h-1);
					im.setRGB(i,j,color);
			}
		}
		
		w = im.getWidth();
		h = im.getHeight();
		int[] rgb = im.getRGB(0, 0, w, h, null, 0, w);
		BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		result.setRGB(0, 0, w, h, rgb, 0, w);
		return result;
	}

	public static void main(String[] args) throws IOException 
	{
		System.out.println("test line");
		int[][] e = line(-1,1,3,2,2,3);
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
				System.out.print(e[i][j]+"\t");
			System.out.println();
		}
		System.out.println();
		
		System.out.println("test intersection");
		double[] inter = intersection(0, new int[]{1,0,0,1});
		for(double i: inter)
			System.out.print(i+"\t");
		System.out.println();
		inter = intersection(0, new int[]{0,1,1,1});
		for(double i: inter)
			System.out.print(i+"\t");
		System.out.println();
		inter = intersection(0, new int[]{1,0,2,0});
		for(double i: inter)
			System.out.print(i+"\t");
		System.out.println("\n");
		
		System.out.println("test intersection");
		double[] x = intersection(1, e);
		for(double i: x)
			System.out.print(i+"\t");
		System.out.println("\n");
		
		System.out.println("test frame");
		int[] fr = frame(new int[][]{{0,0},{3,2},{-1,1},{1,5}});
		for(int i: fr)
			System.out.print(i+"\t");
		System.out.println("\n");
		
		System.out.println("test leftProduct");
		double[] p = leftProduct(new double[][]{{3,5,6},{0,2,4},{0,0,1}}, 7, 8, 9);
		for(double i: p)
			System.out.print(i+"\t");
		System.out.println("\n");
		
		System.out.println("test inverse");
		double[][] inv = inverse(new double[][]{{10,1,1},{1,10,1},{2,2,10}});
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
				System.out.print(inv[i][j]+"\t");
			System.out.println();
		}
		System.out.println();
		
		System.out.println("test perspective");
		long a = System.currentTimeMillis();
		File image = new File("d:\\12.jpg");
		BufferedImage im = ImageIO.read(image);
		File image2 = new File("d:\\boss.jpg");
		BufferedImage im2 = ImageIO.read(image2);
		int[][] y = new int[][]{{634,617}, {1901,432}, {1898,1162}, {491,1272}};
		BufferedImage result = perspective(im,im2,y);
		ImageIO.write(result, "jpg", new File("d:\\test12.jpg"));
		System.out.println("Time is "+(1.0*System.currentTimeMillis()-a)/1000+"s!");
	}
}
