-- Switch to host_agent
\c host_agent;



-- Create host_info table if does not exist
CREATE TABLE IF NOT EXISTS PUBLIC.host_info
    (
        id SERIAL NOT NULL PRIMARY KEY,
        hostname VARCHAR NOT NULL UNIQUE,
        cpu_number INT NOT NULL,
        cpu_architecture VARCHAR NOT NULL,
        cpu_model VARCHAR NOT NULL,
        cpu_mhz FLOAT NOT NULL,
        l2_cache INT NOT NULL,
        total_mem INT NOT NULL,
        "timestamp" TIMESTAMP NOT NULL
    );


-- Create host_usage table if does not exist
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage
    (
        "timestamp" TIMESTAMP NOT NULL,
        host_id SERIAL NOT NULL,
        mem_free INT NOT NULL,
        cpu_idle DECIMAL NOT NULL,
        cpu_kernel DECIMAL NOT NULL,
        disk_io INT NOT NULL,
        disk_available INT NOT NULL,
        FOREIGN KEY (host_id) REFERENCES host_info(id)
    );


