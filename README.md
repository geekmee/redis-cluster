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

- add nodes

```
redis-cli --cluster add-node ip:port 192.168.10.20:6379
redis-cli --cluster add-node ip:port 192.168.10.20:6379 --cluster-slave (--cluster-master-id xxx)
or cluster replicate master-id
```
- remove nodes

```
redis-cli --cluster del-node 192.168.10.20:6379 `<node-id>` #The first argument is just a random node in the cluster, the second argument is the ID of the node you want to remove.
```

## test cluster

```
redis-cli -h redis1 -p 6379 -c #-c enables to redirect to a different proper node if the key is not located in this node
```

## ref
- https://redis.io/topics/cluster-tutorial
- https://redis.io/topics/cluster-spec
- https://redis.io/clients
- https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-redis-on-ubuntu-16-04
- https://github.com/erlong15/vagrant-redis-cluster
- https://github.com/geekmee/docker-redis-cluster



  

