CREATE TABLE IF NOT EXISTS Employee(
  id INT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  department VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  manager_id INT NOT NULL,
  salary INT NOT NULL
 );
