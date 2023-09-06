#
1. https://github.com/ivam3/termux-packages/tree/main

mkdir -p $PREFIX/etc/apt/sources.list.d

wget https://raw.githubusercontent.com/ivam3/termux-packages/gh-pages/ivam3-termux-packages.list -O $PREFIX/etc/apt/sources.list.d/ivam3-termux-packages.list

apt update && apt upgrade

2. apt install termux-docker-qemu

3. Steps screen output
 setup-interfaces
 ifup eth0
 echo "nameserver 8.8.8.8" >> /etc/resolv.conf

 wget https://raw.githubusercontent.com/ivam3/termux-packages/gh-pages/packages/termux-docker-qemu/ashrc.sh -O /etc/profile.d/ashrc.sh

 wget https://raw.githubusercontent.com/ivam3/termux-packages/gh-pages/packages/termux-docker-qemu/answerfile.alpine

 sed -i -E 's/(local kernel_opts)=.*/\1="console=ttyS0"/' /sbin/setup-disk

 setup-alpine -f answerfile.alpine

 apk add docker-cli-compose

 poweroff
 
 #passwd: termuxuser

#Video ivan3 https://youtu.be/CXvTUEG1oPs

service docker status

apk add tmux
tmux new -s Alpine
tmux list-session

poweroff

docker-compose up file.yml

#start
termux-docker-qemu alpine
termux-docker-qemu alpine x11

#SSH Remote Login
ssh root@localhost -p 2222

#copy files
scp -P 2222 -r licensing-service-SMIA-CH02.tar root@localhost:/tmp

#maven options
export MAVEN_OPTS="-Xmx512m -Xms256m -Djava.awt.headless=true"

#Build the image (inside the server with Docker running).
mvn package dockerfile:build

#clone
https://github.com/fabiop35/licensing-service-SMIA-CH02.git

#run image, create container
docker run -it smia/licensing-service:latest -p8080:8080


h graphical enviroment
[!] To set a graphical enviroment you should run in alpine the command:
    wget https://raw.githubusercontent.com/ivam3/termux-packages/gh-pages/packages/termux-docker-qemu/alpineX11.sh && ash alpineX11.sh


#Enabled external port
added:
hostfwd=tcp::8200-:8200
./files/usr/bin/termux-docker-qemu



