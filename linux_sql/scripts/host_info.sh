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
lscpu_out=`lscpu`
mem_out=`cat /proc/meminfo`


#Hardware specs
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo  "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk '{print $3}' |  sed 's/K//' | xargs)
total_mem=$(echo "$mem_out" | egrep "MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(vmstat -t | tail -1 | awk '{print $18}' | xargs)

#query to insert to data to host_info table
insert_query="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)
VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp')"


#insert all values to the database
export PGPASSWORD=$psql_password

psql -h $psql_host -p $port -d $db_name -U $psql_user -c "$insert_query"

exit $?
