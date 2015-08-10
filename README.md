=Permission for access external host=

1 . Create a file /etc/rabbitmq/rabbitmq.config with following content :

[{rabbit, [
      {loopback_users, []}
]}].

Because user "guest" cann login just from localhost.

OR

2. Create a new admin user :

First of all connect to your rabbitmq server machine using ssh client so as to be able to run rabbitmqctl (like puTTY) & get into the sbin directory of rabbit installation

you need to create a user for any vhost on that system (here I use default vhost "/")
$ rabbitmqctl add_user yourName yourPass

Set the permissions for that user for default vhost
$ rabbitmqctl set_permissions -p / yourName ".*" ".*" ".*"

Set the administrator tag for this user (to enable him access the management pluggin)
$ rabbitmqctl set_user_tags yourName administrator

=Accessing Rabbimq Management Web=

http://<YOUR_IP>:15672/