from PIL import Image
import time
import numpy
from perspectives import perspectivemap

node_site_u2 = [165, 145, 557, 83, 564, 333, 137, 359]
node_site_u3 = [124, 150, 524, 113, 540, 246, 106, 272]
node_site_u4 = [128, 154, 523, 181, 523, 380, 128, 406]
node_site_u5 = [118, 38, 351, 46, 355, 183, 118, 187]
node_site_u6 = [171, 72, 423, 78, 423, 208, 171, 205]
node_site_12 = [634, 617, 1900, 431, 1896, 1161, 491, 1269]

a = time.time()
im = Image.open('d:/12.png')
im2 = Image.open('d:/boss.jpg')
image=perspectivemap(im,im2,node_site_12)
image.save('d:/1.jpg')
print time.time() - a