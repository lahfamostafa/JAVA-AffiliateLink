CREATE TABLE profiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uk_profiles_email UNIQUE (email)
);

CREATE TABLE publishers (
    id BIGINT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    niche VARCHAR(255),
    bio VARCHAR(2000),
    country VARCHAR(255),

    PRIMARY KEY (id),
    CONSTRAINT fk_publishers_profile
        FOREIGN KEY (id) REFERENCES profiles(id)
);

CREATE TABLE advertisers (
    id BIGINT NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    sector VARCHAR(255),
    website VARCHAR(255),

    PRIMARY KEY (id),
    CONSTRAINT fk_advertisers_profile
        FOREIGN KEY (id) REFERENCES profiles(id)
);