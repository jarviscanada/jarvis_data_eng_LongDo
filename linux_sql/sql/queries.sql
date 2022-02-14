--Group hosts by CPU number and sort by their memory size in descending order

SELECT cpu_number, host_id, total_mem
FROM host_info
GROUP BY cpu_number
ORDER BY total_mem DESC

-- 5 minutes interval function
CREATE FUNCTION round5(ts timestamp) RETURN timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * i
END
;
$$
    LANGUAGE PLPGSQL;

SELECT host_id, round5(timestamp) AS timestamp, AVG((total_mem - mem_free)/total_mem)
FROM host_usage
GROUP BY host_id, timestamp

--Detect host failure
SELECT host_id, round5(timestamp) AS ts, count(*) AS num_data_points
FROM host_usage
GROUP BY host_id, ts
HAVING num_data_points<3