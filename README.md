# redis-cluster

## steps to having a ubuntu-redis-local vagrant box
- `sudo systemctl stop redis`
```
wget http://download.redis.io/releases/redis-5.0.7.tar.gz
tar xzf redis-5.0.7.tar.gz
cd redis-5.0.7/
make
```

- replace redis-server and redis-cli
```
cp /usr/bin/redis-server ~/redis-server.bk
cp /usr/bin/redis-cli ~/redis-cli.bk
sudo cp /etc/redis/redis.conf ~/redis.conf.bk
sudo cp ./src/redis-server /usr/local/bin/
sudo cp ./src/redis-cli /usr/bin/
```
- update redis.service

```
sudo vi /etc/systemd/system/redis.service
 to add ExecStart=/usr/local/bin/redis-server /etc/redis/redis.conf
```

- updaste redis.conf
```
sudo vi /etc/redis/redis.conf
comment #bind 127.0.0.1 ::1
uncomment cluster-enabled yes
```

- start redis
```
sudo systemctl daemon-reload
sudo systemctl restart redis
```


  

