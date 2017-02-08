#coding=utf-8
import math
import numpy as np
from scipy import linalg

#构造四边形所在的框架	
def Frame(x1,y1,x2,y2,x3,y3):
	left = min([0,x1,x2,x3])
	right = max([0,x1,x2,x3])
	bottom = 0
	top = max([y1,y2,y3])
	
	return left,right,bottom,top

#判断三点是否共线
def isColinear(x1,y1,x2,y2,x3,y3):
	if(x1==x2):
		if(x2==x3 or y1==y2):
			return True
		return False
		
	if(x2==x3):
		if(y2==y3):
			return True
		return False
		
	if((float(y2)-y1)/(x2-x1)==(float(y3)-y2)/(x3-x2)):
		return True
	return False

#判断四点是否能构成凸四边形
def ConvexNumber(x1,y1,x2,y2,x3,y3):
	if(isColinear(x1,y1,x2,y2,x3,y3) or isColinear(0,0,x2,y2,x3,y3) or isColinear(x1,y1,0,0,x3,y3) or isColinear(x1,y1,x2,y2,0,0)):
		raise Exception('Colinear, algorithm fails.')
		
	A, B, C = np.array([x1,y1]), np.array([x2,y2]), np.array([x3,y3])
	
	v0, v1, v2 = B-A, C-A, 0-A
	M = np.array([[np.dot(v0,v0),np.dot(v1,v0)],[np.dot(v0,v1),np.dot(v1,v1)]])
	b = np.array([np.dot(v2,v0),np.dot(v2,v1)])
	[u,v] = linalg.solve(M,b)
	if(u>0 and v>0 and u+v>1):
		return 1

	v0, v1, v2 = A-B, C-B, 0-B
	M = np.array([[np.dot(v0,v0),np.dot(v1,v0)],[np.dot(v0,v1),np.dot(v1,v1)]])
	b = np.array([np.dot(v2,v0),np.dot(v2,v1)])
	[u,v] = linalg.solve(M,b)
	if(u>0 and v>0 and u+v>1):
		return 2

	v0, v1, v2 = A-C, B-C, 0-C
	M = np.array([[np.dot(v0,v0),np.dot(v1,v0)],[np.dot(v0,v1),np.dot(v1,v1)]])
	b = np.array([np.dot(v2,v0),np.dot(v2,v1)])
	[u,v] = linalg.solve(M,b)
	if(u>0 and v>0 and u+v>1):
		return 3
	
	raise Exception('Not convex, algorithm fails.')
	
#输出与需要旋转的角（弧度）使得平行边靠在x轴上
def oneline(x1, y1, x2, y2, x3, y3):
	e = line(x1,y1,x2,y2,x3,y3)
	
	def isParallel(e1, e2):
		x1, y1, x2, y2 = e1[0], e1[1], e1[2], e1[3]
		x3, y3, x4, y4 = e2[0], e2[1], e2[2], e2[3]
		if x1 == x2:
			if x3 == x4:
				return True
			else:
				return False
		elif x3 == x4:
			return False
		elif abs((float(y2) - y1) / (x2 - x1) - (float(y4) - y3) / (x4 - x3)) < math.tan(180.0*10/math.pi):
			return True
		return False
	
	def theta(e):
		x, y = e[0], e[1]
		return math.acos(x / (x**2 + y**2)**0.5)

	if isParallel(e[0], e[2]):
		if isParallel(e[1], e[3]):
			if abs(theta(e[0])-theta(e[2])) > abs(theta(e[1])-theta(e[3])):
				if theta(e[1]) > theta(e[0]):
					return theta(e[1]) - math.pi 
				else:
					return theta(e[1])
			else:
				if theta(e[0]) > theta(e[1]):
					return theta(e[0]) - math.pi
				else:
					return theta(e[0])
	else:
		raise Exception('No parallel edge pair, algorithm fails.')

#输出四边形的四条边
def line(x1,y1,x2,y2,x3,y3):
	e = []
	number = ConvexNumber(x1,y1,x2,y2,x3,y3)

	if(number==1):
		e.append([x2,y2,0,0])
		e.append([x3,y3,0,0])
		e.append([x3,y3,x1,y1])
		e.append([x1,y1,x2,y2])
	elif(number==2):
		e.append([x3,y3,0,0])
		e.append([x1,y1,0,0])
		e.append([x1,y1,x2,y2])
		e.append([x2,y2,x3,y3])
	else:
		e.append([x1,y1,0,0])
		e.append([x2,y2,0,0])
		e.append([x2,y2,x3,y3])
		e.append([x3,y3,x1,y1])
	
	return e

#给定一个y值c,返回y=c与(x1,y1),(x2,y2)线段的交点横坐标/横坐标范围
def xlineintersect(c,x1,y1,x2,y2):
    if(y1==c and y1==y2): 
        return min(x1,x2),max(x1,x2)
    elif((y1-c)*(y2-c)>0): 
        return None,None
    else: 
        x1 = float(x1)
        return x1+(c-y1)*(x2-x1)/(y2-y1),x1+(c-y1)*(x2-x1)/(y2-y1)
		
#给定一个x值c,返回x=c与(x1,y1),(x2,y2)线段的交点纵坐标/纵坐标范围
def ylineintersect(c,x1,y1,x2,y2):
    if(x1==c and x1==x2): 
        return min(y1,y2),max(y1,y2)
    elif((x1-c)*(x2-c)>0): 
        return None,None
    else: 
        y1 = float(y1)
        return y1+(c-x1)*(y2-y1)/(x2-x1),y1+(c-x1)*(y2-y1)/(x2-x1)

#给定2个y值c,d,返回y=c,y=d与O=(0,0),A=(x1,y1),B=(x2,y2),C=(x3,y3)组成的凸四边形所截x轴值的范围xmin,xmax
def xintersection(c,d,x1,y1,x2,y2,x3,y3):
	e = line(x1,y1,x2,y2,x3,y3)
	
	cmin, cmax, dmin, dmax = [], [], [], []
	for i in range(4):
		a, b = xlineintersect(c,e[i][0],e[i][1],e[i][2],e[i][3])
		if a != None:
			cmin.append(a)
			cmax.append(b)
		
		a, b = xlineintersect(d,e[i][0],e[i][1],e[i][2],e[i][3])
		if a != None:
			dmin.append(a)
			dmax.append(b)
		
	cmin, cmax, dmin, dmax = min(cmin), max(cmax), min(dmin), max(dmax)
	
	return min([cmin,dmin]), max([cmax,dmax])
		
#给定2个x值c,d,返回x=c,x=d与O=(0,0),A=(x1,y1),B=(x2,y2),C=(x3,y3)组成的凸四边形所截y轴值的范围ymin,ymax
def yintersection(c,d,x1,y1,x2,y2,x3,y3):
	e = line(x1,y1,x2,y2,x3,y3)
	
	cmin, cmax, dmin, dmax = [], [], [], []
	for i in range(4):
		a, b = ylineintersect(c,e[i][0],e[i][1],e[i][2],e[i][3])
		if a != None:
			cmin.append(a)
			cmax.append(b)
		
		a, b = ylineintersect(d,e[i][0],e[i][1],e[i][2],e[i][3])
		if a != None:
			dmin.append(a)
			dmax.append(b)
		
	cmin, cmax, dmin, dmax = min(cmin), max(cmax), min(dmin), max(dmax)
	
	return min([cmin,dmin]), max([cmax,dmax])

#将四边形旋转到延x正半轴
def transformation(x1,y1,x2,y2,x3,y3):
	angle = oneline(x1,y1,x2,y2,x3,y3)
	array = np.array([[math.cos(angle),math.sin(angle)],[-math.sin(angle),math.cos(angle)]])
	array1 = np.dot(array, np.array([[x1],[y1]]))
	array2 = np.dot(array, np.array([[x2],[y2]]))
	array3 = np.dot(array, np.array([[x3],[y3]]))
	
	return int(math.ceil(array1[0])),int(math.ceil(array1[1])),int(math.ceil(array2[0])),int(math.ceil(array2[1])),int(math.ceil(array3[0])),int(math.ceil(array3[1]))
	
if __name__ ==  '__main__':

	print('test Frame')
	print(Frame(-1,1,2,3,3,2))
	print('\n')
	
	print('test isColinear')
	print(isColinear(0,0,1,1,2,2))
	print(isColinear(0,0,1,1,2,3))
	print('\n')
	
	print('test ConvexNumber')
	print(ConvexNumber(1,1,1,0,0,1))
	print(ConvexNumber(0,1,1,1,1,0)) 
	print(ConvexNumber(1,0,0,1,1,1))
	print('\n')
	
	print('test oneline')
	print(oneline(-1,1,2,3,3,2))
	print('\n')
	
	print('test line')
	print(line(-1,1,2,3,3,2))
	print('\n')
	
	print('test xlineintersect')
	print(xlineintersect(0,1,0,0,1))
	print(xlineintersect(0,0,1,1,1))
	print(xlineintersect(0,1,0,2,0))
	print('\n')
	
	print('test ylineintersect')
	print(ylineintersect(0,1,0,0,1))
	print(ylineintersect(0,1,0,1,1))
	print(ylineintersect(0,0,1,0,2))
	print('\n')
	
	print('test xintersection')
	print(xintersection(1,2,-1,1,2,3,3,2))
	print('\n')
	
	print('test yintersection')
	print(yintersection(0,1,-1,1,2,3,3,2))
	print('\n')
	
	print('test transformation')
	print(transformation(-100,0,-100,100,0,100))