from django.shortcuts import render
from django.http import HttpResponse
import os
import json

# Create your views here.
def index(request):
	return render(request, 'upload/index.html')


def upload(request):
	image = request.FILES['image']
	print(image)
	base_path = os.path.join('static', 'upload', 'img')
	if not os.path.exists(base_path):
		os.makedirs(base_path)
	img_path = os.path.join(base_path, image.name)
	with open(img_path, 'wb+') as destination:
		for chunk in image.chunks():
		    destination.write(chunk)
	result = {'response': 'Image saved in ' + img_path}
	return HttpResponse(json.dumps(result), content_type="application/json")