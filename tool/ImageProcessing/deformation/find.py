#coding=utf-8
from PIL import Image
import numpy
from image_set import deformation, paradeformation

def combine(im,im2,node_site,f=deformation):
	a1,b1,a2,b2,a3,b3,a4,b4=node_site   
	ymax = max([b1,b2,b3,b4])
	index = [b1,b2,b3,b4].index(ymax)
	xmax = [a1,a2,a3,a4][index]
	li = [a1 - xmax, ymax - b1, a2 - xmax, ymax - b2, a3 - xmax, ymax - b3, a4 - xmax, ymax - b4]
	del li[2*index]
	del li[2*index]
	tu = tuple(li)
	x1, y1, x2, y2, x3, y3 = tu
	
	xmin = min([a1,a2,a3,a4])
	ymin = min([b1,b2,b3,b4])
	
	photo_frame = numpy.array(im)
	width, height = im.size
	im2 = f(im2,x1, y1, x2, y2, x3, y3)
	im_add = numpy.array(im2)
	w, h = im2.size
	
	for i in range(h):
		for j in range(w):
			if(photo_frame[ymin+i,xmin+j,3] == 0):
				photo_frame[ymin+i,xmin+j,range(3)] = im_add[i,j,:]
	return Image.fromarray(photo_frame[:,:,range(3)])

im=Image.open('d:/u4.png')
im2=Image.open('d:/back.jpg')
node_site = [128,154,525,181,525,380,128,406]
image = combine(im,im2,node_site,f=paradeformation)
image.save('d:/1.jpg')