#!/bin/bash

# Arguments validator
if [ $# -ne 5 ]; then
  echo "Error: number of arguments are not correct!"
  exit  1
fi

# Arguments assigning
psql_host=$1
port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Save host name to variable
hostname=$(hostname -f)

#Save number of a CPU to variable
df_out=`df -BM /`
mem_out=`cat /proc/meminfo`

#usage specs
mem_free=$(echo "$mem_out" | egrep "MemFree:" | awk '{print $2}' | xargs)
cpu_idle=$(vmstat -t | tail -4 | awk '{print $15}' | sed 's/id//' | xargs)
cpu_kernel=$(vmstat -t | tail -6 | awk '{print $13}' | sed 's/us//' | xargs)
disk_io=$(vmstat -d | tail -1 | awk '{print $10}' | xargs)
disk_available=$(echo "$df_out" | tail -3 | awk '{print $4}' | sed 's/Available//' | sed 's/M//' | xargs)
timestamp=$(vmstat -t | tail -1 | awk '{print $18}' | xargs)


#insert query to host_usage table
insert_query="INSERT INTO host_usage (host_id, mem_free, cpu_idle, cpu_kernel, disk_io, disk_available, timestamp)
VALUES ((SELECT id FROM host_info WHERE hostname = '$hostname'), '$mem_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available', '$timestamp')"

export PGPASSWORD=$psql_password

psql -h $psql_host -p $port -d $db_name -U $psql_user -c "$insert_query"

exit 0

