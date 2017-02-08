#coding=utf-8
import math
import numpy as np
from PIL import Image
from project import Frame, oneline, xintersection, transformation

#输出任意大小的黑色图片
def black(width,height):
    color = (0,0,0)
    image = Image.new('RGB',(width,height),color)
    return image
	
#给图片加上一个足够大的黑色背景
def background(image):
    w,h = image.size
    c = int(((w/2)**2+(h/2)**2)**0.5)+1
    bg = black(w+2*c,h+2*c)
	
    array = np.array(image)
    barray = np.array(bg)

    barray[c:h+c,c:w+c,:]=array[:,:,:]
    return Image.fromarray(barray)

#旋转图片
def rotateninty(image):
	w,h = image.size
	image = np.array(image)
	rot_image = black(h,w)
	rot_image = np.array(rot_image)
	for s in range(3):
		for i in range(h):
			for j in range(w):
				rot_image[j,i,s] = image[i,j,s]

	rot_image = Image.fromarray(rot_image)
	return rot_image

#按横轴形变，输入待处理图像，以及需要的宽度，输出新的符合宽度的图像
def xdeformation(image,width):
	w, h = image.size
	array = np.array(image)
	newimage = black(width,h)
	newarray = np.array(newimage)
	
	ratio = 1.0*w/width
	
	for i in range(width):
		for j in range(3):
			
			sum = np.zeros([1,h])
			min, max = i*ratio, (i+1)*ratio
			for k in range(int(math.ceil(min)), int(math.floor(max))):
				sum = sum + array[:,k,j]
			if(math.floor(min) != math.floor(max)):
				sum = sum + (math.ceil(min)-min)*array[:,int(math.floor(min)),j]
				if(max < w):
					sum = sum + (max-math.floor(max))*array[:,int(math.floor(max)),j]
			else:
				sum = ratio*array[:,int(math.floor(min)),j]
			newarray[:,i,j] = (1/ratio)*sum
	
	return Image.fromarray(newarray)
	
#按纵轴形变，输入待处理图像，以及需要的高度，输出新的符合高度的图像
def ydeformation(image,height):
	w, h = image.size
	array = np.array(image)
	
	newimage = black(w,height)
	newarray = np.array(newimage)
	
	ratio = 1.0*h/height
	
	for i in range(height):
		for j in range(3):
			
			sum = np.zeros([1,w])
			min, max = i*ratio, (i+1)*ratio
			for k in range(int(math.ceil(min)), int(math.floor(max))):
				sum = sum + array[k,:,j]
			if(math.floor(min) != math.floor(max)):
				sum = sum + (math.ceil(min)-min)*array[int(math.floor(min)),:,j]
				if(max < h):
					sum = sum + (max-math.floor(max))*array[int(math.floor(max)),:,j]
			else:
				sum = sum + ratio*array[int(math.floor(min)),:,j]
			newarray[i,:,j] = (1/ratio)*sum
	
	return Image.fromarray(newarray)

#根据相框要求，输出图像
def deformation(image,x1,y1,x2,y2,x3,y3):
	left, right, buttom, top = Frame(x1,y1,x2,y2,x3,y3)
	width, height = right - left, top - buttom 
	w, h = image.size
	
	image = ydeformation(image,height)
	array = np.array(image)
	
	newimage = black(width,height)
	newarray = np.array(newimage)
	
	for i in range(height):
		c, d = top - buttom - i - 1, top - buttom - i
		xmin, xmax = xintersection(c,d,x1,y1,x2,y2,x3,y3)
		pixelmin = int(math.floor(xmin) - left)
		if(xmax == math.floor(xmax)):
			pixelmax = int(math.floor(xmax) - left - 1)
		else:
			pixelmax = int(math.floor(xmax) - left)
		
		b = Image.fromarray(array[[i,i],:,:])
		im = xdeformation(b,pixelmax-pixelmin+1)
		newarray[i,pixelmin:pixelmax+1,:] = np.array(im)[0,:,:]

	return Image.fromarray(newarray)
	
#相框为平行四边形的情形
def paradeformation(image,x1,y1,x2,y2,x3,y3):
	a1,b1,a2,b2,a3,b3 = transformation(x1,y1,x2,y2,x3,y3)
	angle = oneline(x1,y1,x2,y2,x3,y3)
	if angle<0:
		image=rotateninty(image)

	image = deformation(image,a1,b1,a2,b2,a3,b3)
	image = background(image)
	image = image.rotate(180.0*angle/math.pi)
	
	array = np.array(image)
	y, x = image.size;
	xmin, xmax, ymin, ymax = 0, 0, 0, 0
	for i in range(x):
		if sum(array[i,:,0])+sum(array[i,:,1])+sum(array[i,:,2]) != 0:
			xmin = i
			break;
	for i in list(np.arange(x-1,-1,-1)):
		if sum(array[i,:,0])+sum(array[i,:,1])+sum(array[i,:,2]) != 0:
			xmax = i
			break;
	for i in range(y):
		if sum(array[:,i,0])+sum(array[:,i,1])+sum(array[:,i,2]) != 0:
			ymin = i
			break;
	for i in list(np.arange(y-1,-1,-1)):
		if sum(array[:,i,0])+sum(array[:,i,1])+sum(array[:,i,2]) != 0:
			ymax = i
			break;
	new_array = array[xmin:xmax,ymin:ymax,:]
	image = Image.fromarray(new_array)
	return image

if __name__ == '__main__':
	
	print('test black')
	image = black(220,400)
	image.save('d:/0.jpg')
	print('\n')
	
	print('test background')
	image = Image.open('d:/fudan.jpg')
	image = background(image)
	image.save('d:/1.jpg')
	print('\n')
		
	print('test xdeformation')
	image = Image.open('d:/fudan.jpg')
	w, h = image.size
	image = xdeformation(image,w/2)
	image.save('d:/2.jpg')
	print('\n')
	
	print('test ydeformation')
	image = Image.open('d:/fudan.jpg')
	w, h = image.size
	image = ydeformation(image,h/2)
	image.save('d:/3.jpg')
	print('\n')

	print('test deformation')
	image = Image.open('d:/fudan.jpg')
	image = deformation(image,-100,100,200,300,300,200)
	image.save('d:/4.jpg')
	print('\n')

	print('test paradeformation')
	image = Image.open('d:/fudan.jpg')
	image = paradeformation(image,-100,100,200,300,300,200)
	image.save('d:/5.jpg')