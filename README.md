# redis-cluster

## steps to having a ubuntu-redis-local vagrant box
- install redis

```
wget http://download.redis.io/releases/redis-5.0.7.tar.gz
tar xzf redis-5.0.7.tar.gz
cd redis-5.0.7/
make
```

- update redis.conf

```
vi redis-5.0.7/redis.conf, change to following
protected-mode no
comment #bind 127.0.0.1
daemonize yes
cluster-enabled yes
```

- Note: add password by editing redis.conf and change protected-mode to no if needed
`requirepass redispass`

- export PATH=$PATH:~/redis-5.0.7/src

## create cluster

- create cluster

```
vagrant redis1
redis-cli --cluster create 192.168.10.20:6379 192.168.10.21:6379 192.168.10.22:6379 192.168.10.23:6379 192.168.10.24:6379 192.168.10.25:6379 --cluster-replicas 1
plus '-a password' if have
```



  

