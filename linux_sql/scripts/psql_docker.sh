#!/bin/bash

cmd=$1
db_username=$2
db_password=$3

#arguments validation
if [ $# -lt 1 ]; then
  echo "Error: No arguments"
  exit 1
fi

# Checking running status of docker and run when it's ready
systemctl status docker || systemctl start docker

#check container status
docker container inspect jrvs-psql
container_status=$?


# "create" cmd to create a PSQL container with db_username and db_password
case $cmd in
  create)
  if [ $container_status -eq 0 ]; then
    echo "Container already exists"
     exit 1
  fi

  #check number of CLI arguments
  if [ $# -ne 3 ]; then
     echo "Create requires username and password"
    exit 1
  fi

  #Create container
  docker docker volume create pgdata
  docker run --name jrvs-psql -e POSTGRES_PASSWORD=${db_password} -e POSTGRES_USER=${db_username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
  exit $?
  ;;
  start|stop)
  #check instance status; exit 1 if container has not beenccreated
  if [ $container_status -eq 1 ]; then
    echo  "container has not been created"
    exit 1
  fi

  #Start or stop container
  docker container $cmd jrvs-psql
  exit $?
  ;;
  *)
  echo 'illegal command'
  echo 'Commands: start|stop|create'
  exit 1
  ;;
esac
