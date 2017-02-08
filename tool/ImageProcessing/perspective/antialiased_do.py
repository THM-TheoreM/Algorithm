from PIL import Image
import numpy
from antialiased import antialiased

im=Image.open('C:/Users/linzz/Desktop/pic/image_processing/ImageProcessing/perspective/find/line.jpg')
im=numpy.array(im)

node_site_u2 = [165, 145, 557, 83, 564, 333, 137, 359]
node_site_u3 = [124, 150, 524, 113, 540, 246, 106, 272]
node_site_u4 = [128, 154, 523, 181, 523, 380, 128, 406]
node_site_u5 = [118, 38, 351, 46, 355, 183, 118, 187]
node_site_u6 = [171, 72, 423, 78, 423, 208, 171, 205]
node_site_12 = [634, 617, 1900, 431, 1896, 1161, 491, 1269]

x0,y0,x1,y1,x2,y2,x3,y3=node_site_u2

x0=122
y0=266
x1=429
y1=161

im=antialiased(im,x0,y0,x1,y1)
'''
im=antialiased(im,x0,y0,x3,y3)
im=antialiased(im,x3,y3,x0,y0)
im=antialiased(im,x1,y1,x2,y2)
im=antialiased(im,x2,y2,x1,y1)
im=antialiased(im,x2,y2,x3,y3)
im=antialiased(im,x3,y3,x2,y2)
'''
im=Image.fromarray(im)
im.save('C:/Users/linzz/Desktop/pic/image_processing/ImageProcessing/perspective/find/anti_1.jpg')


