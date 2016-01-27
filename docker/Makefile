IMAGE_NAME="mentatlabs/revenj"

## Build image from Dockerfile
build:
	docker build --rm -t $(IMAGE_NAME) .

## Push image to hub
push:
	docker push $(IMAGE_NAME)

## Run container with default command
run:
	docker run -it \
		-p 127.0.0.1:5432:5432 \
		--log-driver=syslog \
		$(IMAGE_NAME)
