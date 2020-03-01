# -*- mode: ruby -*-
# vi: set ft=ruby :
# See: https://docs.vagrantup.com/v2/vagrantfile/tips.html

BOX_NAME = "ubuntu-redis-local"

REDIS_VMS = {
  :redis1 => {
    :ip => '192.168.10.20',
  },
  :redis2 => {
    :ip => '192.168.10.21',
  },
  :redis3 => {
    :ip => '192.168.10.22',
  },
  :redis4 => {
    :ip => '192.168.10.23',
  },
  :redis5 => {
    :ip => '192.168.10.24',
  },
  :redis6 => {
    :ip => '192.168.10.25',
  }
}

Vagrant.configure(2) do |config|
  #config.hostmanager.enabled = true
  config.vm.box = BOX_NAME
  #config.ssh.insert_key = false

  REDIS_VMS.each do |name,cfg|

    config.vm.define name do |vm_config|
      vm_config.vm.hostname = name
      vm_config.vm.box_check_update = false
      vm_config.vm.network :private_network,ip: REDIS_VMS[name][:ip]

      config.vm.provider :virtualbox do |vb|
        vb.memory = 512
        vb.cpus = 1
        vb.linked_clone = true
      end
      vm_config.vm.provision "shell", privileged: false, inline: <<-SHELL
      echo start redis server ...
      cd ~
      echo "redis-server ~/redis-5.0.7/redis.conf &" >> ./redis-start
      chmod +x ./redis-start
      ./redis-5.0.7/src/redis-server ./redis-5.0.7/redis.conf
      echo redis server started
      SHELL
    end
  end
end