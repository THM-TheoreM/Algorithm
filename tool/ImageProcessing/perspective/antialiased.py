import numpy
import math
def antialiased(im,a1,b1,a2,b2):
	gradient=float(b2-b1)/float(a2-a1)
	if gradient >=1:
		if b1>b2:
			tx,ty=a1,b1
			a1,b1=a2,b2
			a2,b2=tx,ty
		e=float(1)/float(gradient)
		x=a1
		for y in range(b1,b2):
			im[y,x,range(3)]=im[y,x,range(3)]*e
			im[y,x+1,range(3)]=im[y,x+1,range(3)]*(1-e)#+im[y,x+2,range(3)]*e
			e=e+float(1)/float(gradient)
			if e>=1:
				e=e-1
				x=x+1
	if gradient>=0 and gradient<=1:
		if a1>a2:
			tx,ty=a1,b1
			a1,b1=a2,b2
			a2,b2=tx,ty
		e=gradient
		y=b1
		for x in range(a1,a2):
			im[y,x,range(3)]=im[y,x,range(3)]*e
			im[y+1,x,range(3)]=im[y+1,x,range(3)]*(1-e)#+im[y+2,x,range(3)]*e
			e=e+gradient
			if e>=1:
				y=y+1
				e=e-1
	if gradient>-1 and gradient<=0:
		if a1>a2:
			tx,ty=a1,b1
			a1,b1=a2,b2
			a2,b2=tx,ty
		e=-gradient
		y=b1
		for x in range(a1,a2):
			im[y,x,range(3)]=im[y,x,range(3)]*e
			im[y-1,x,range(3)]=im[y-1,x,range(3)]*(1-e)#+im[y-2,x,range(3)]*e
			e=e-gradient
			if e>1:
				y=y-1
				e=e-1
	if gradient<-1:
		if b1<b2:
			tx,ty=a1,b1
			a1,b1=a2,b2
			a2,b2=tx,ty
		e=float(-1)/float(gradient)
		x=a1
		for y in range(b1,b2):
			im[y,x,range(3)]=im[y,x,range(3)]*e
			im[y,x+1,range(3)]=im[y,x+1,range(3)]*(1-e)#+im[y,x+1,range(3)]*e
			if e>1:
				x=x+1
				e=e-1
	return im