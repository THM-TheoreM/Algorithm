from PIL import Image
import numpy
import math

def perspectivemap(im, im2, node_site):
	w, h = im2.size
	im = numpy.array(im)
	im2 = numpy.array(im2)
	a = numpy.ones([3, 3])
	u0, v0, u1, v1, u2, v2, u3, v3 = [0, 0, w - 1, 0, w - 1, h - 1, 0, h - 1]
	x0, y0, x1, y1, x2, y2, x3, y3 = node_site
	a[2, 0] = x0
	a[2, 1] = y0
	a[2, 2] = 1
	det = (u1 * x1 - u1 * x2) * (v2 * y3 - v2 * y2) - (v2 * x3 - v2 * x2) * (u1 * y1 - u1 * y2)
	det1 = (x2 - x3 - x1 + x0) * (v2 * y3 - v2 * y2) - (v2 * x3 - v2 * x2) * (y2 - y3 - y1 + y0)
	det2 = (u1 * x1 - u1 * x2) * (y2 - y3 - y1 + y0) - (u1 * y1 - u1 * y2) * (x2 - x3 - x1 + x0)
	a[0, 2] = float(det1) / float(det)
	a[1, 2] = float(det2) / float(det)
	a[0, 0] = float(a[0, 2] * u1 * x1 + x1 - x0) / float(u1)
	a[0, 1] = float(a[0, 2] * u1 * y1 + y1 - y0) / float(u1)
	a[1, 0] = float(a[1, 2] * v2 * x3 + x3 - x0) / float(v2)
	a[1, 1] = float(a[1, 2] * v2 * y3 + y3 - y0) / float(v2)
	a_inv = numpy.linalg.inv(a)
	xmin = min(x0, x1, x2, x3)
	xmax = max(x0, x1, x2, x3)
	ymin = min(y0, y1, y2, y3)
	ymax = max(y0, y1, y2, y3)
	for i in range(xmin, xmax):
		for j in range(ymin, ymax):
			if im[j, i, 3] == 0:
				origin = numpy.dot([i, j, 1], a_inv)
				x = float(origin[0]) / float(origin[2])
				y = float(origin[1]) / float(origin[2])
				x_min = int(math.floor(x))
				x_max = int(math.ceil(x))
				y_min = int(math.floor(y))
				y_max = int(math.ceil(y))
				if x_max <= w - 1 and y_max <= h - 1:
					color = im2[y_min, x_min, range(3)]
				elif x_max > w - 1:
					color = im2[y_min, w - 1, range(3)]
				else:
					color = im2[h - 1, x_min, range(3)]
				im[j, i, range(3)] = color
	image = Image.fromarray(im)
	return image