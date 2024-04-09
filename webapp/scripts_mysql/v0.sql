CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    hashed_password BLOB NOT NULL,
    salt BLOB NOT NULL,
    name TEXT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    email_verified INTEGER NOT NULL CHECK (email_verified IN (0, 1))
);


CREATE TABLE IF NOT EXISTS federated_credentials (
  id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  provider VARCHAR(255) NOT NULL,
  subject VARCHAR(255) NOT NULL,
  UNIQUE (provider, subject)
);

CREATE TABLE IF NOT EXISTS todos (
  id INTEGER PRIMARY KEY,
  owner_id INTEGER NOT NULL,
  title VARCHAR(255) NOT NULL,
  completed INTEGER
);

