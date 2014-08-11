#!/bin/bash
#

#bash color
PROMPT_COMMAND='PS1="[ 33[0;33m][!]`if [[ $? = "0" ]]; then echo "[�33[32m]"; else echo "[�33[31m]"; fi`[u.h: `if [[ `pwd|wc -c|tr -d " "` > 18 ]]; then echo "W"; else echo "w"; fi`]$[ 33[0m] "; echo -ne " 33]0;`hostname -s`:`pwd` 07"' >> ~/.bashrc

#hostname
#hostname fs2.fuge.it


##Install LAMP stack:
apt-get install apache2 php5 php5-mcrypt libapache2-mod-php5 libapache2-mod-scgi php5-gd php5-curl php5-xmlrpc mysql-server apache2-threaded-dev

##Apache Extras
a2enmod scgi
a2enmod ssl
a2enmod rewrite
apache2ctl restart

cd ~
wget http://h264.code-shop.com/download/apache_mod_h264_streaming-2.2.7.tar.gz
tar -zxvf apache_mod_h264_streaming-2.2.7.tar.gz

cd ~/mod_h264_streaming-2.2.7
./configure --with-apxs=`which apxs2`
make
sudo make install

##Install Python:
apt-get install python2.6 python2.6-twisted python-dev

#Install Memcache(d):
apt-get install memcached php5-memcached php5-memcache python-memcache

#Install psutil:
wget http://psutil.googlecode.com/files/psutil-0.6.1.tar.gz
tar -xvsf psutil-0.6.1.tar.gz
cd psutil-0.6.1 && python setup.py install


apt-get install git

cd /var/www && git clone https://github.com/fugeit/fugenode.git

echo "Include /var/www/fugenode/conf/apache.conf" >> /etc/apache2/apache2.conf



#edit /etc/php5/apache2/php.ini and change the following values:
#max_execution_time = 86400
#max_input_time = 86400
#upload_max_filesize = 5120M
#post_max_size = 5120M
#memory_limit = 1024M



apt-get install pkg-config && apt-get install libsigc++-2.0-dev



wget http://libtorrent.rakshasa.no/downloads/libtorrent-0.13.2.tar.gz
tar -xvsf libtorrent-0.13.2.tar.gz
cd libtorrent-0.13.2
./autogen.sh
./configure
make
make install

chmod -R 0777 /var/www/fugenode/log
cd /var/www/fugenode/conf &&  apxs -cia mod_xsendfile.c



pip install pyftpdlib

aptitude install tmux wget build-essential subversion git python-setuptools python-virtualenv python-dev libsigc++-2.0-dev libssl-dev libncurses-dev libncursesw5-dev locales libcppunit-dev autoconf automake libtool libxml2-dev libxslt1-dev

#https://bintray.com/pyroscope/rtorrent-ps/rtorrent-ps


mkdir /home/www-data
rsync -av --exclude '*.torrent' --exclude '*.libtorrent_resume' --exclude "*.rtorrent" root@fs1.fuge.it:/home/www-data /home/www-data
