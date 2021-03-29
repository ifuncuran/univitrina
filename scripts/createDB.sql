DROP TABLE IF EXISTS profession, training_direction, specialty, specialty_profession, university, university_education;

CREATE TABLE IF NOT EXISTS profession
(
    profession_id   SERIAL PRIMARY KEY,
    hhapi_id        VARCHAR(15)  NOT NULL UNIQUE CHECK (hhapi_id != ''),
    profession_name VARCHAR(255) NOT NULL UNIQUE CHECK (profession_name != '')
);

CREATE TABLE IF NOT EXISTS training_direction
(
    training_direction_id   SERIAL PRIMARY KEY,
    training_direction_name VARCHAR(255) NOT NULL,
    training_direction_code VARCHAR(8)   NOT NULL UNIQUE CHECK (training_direction_code != '')
);

CREATE TABLE IF NOT EXISTS specialty
(
    specialty_id   SERIAL PRIMARY KEY,
    specialty_name VARCHAR(255) NOT NULL,
    specialty_code VARCHAR(8)   NOT NULL UNIQUE CHECK (specialty_code != ''),
    degree                 VARCHAR(255) NOT NULL,
    training_direction_id      INT          NOT NULL,
    FOREIGN KEY (training_direction_id) REFERENCES training_direction (training_direction_id)
);

CREATE TABLE IF NOT EXISTS specialty_profession
(
    specialty_profession_id SERIAL PRIMARY KEY,
    specialty_id    INT NOT NULL,
    profession_id           INT NOT NULL,
    FOREIGN KEY (specialty_id) REFERENCES specialty (specialty_id),
    FOREIGN KEY (profession_id) REFERENCES profession (profession_id)
);

CREATE TABLE IF NOT EXISTS university
(
    university_id   SERIAL PRIMARY KEY,
    university_name VARCHAR(255) NOT NULL UNIQUE CHECK (university_name != ''),
    area_id         INT          NOT NULL,
    address         VARCHAR(255) NOT NULL,
    phone           VARCHAR(255) NOT NULL,
    email           VARCHAR(255),
    site            VARCHAR(255)
--     wiki_url VARCHAR(255) NOT NULL,
--     is_state BOOLEAN,
--     has_hostel BOOLEAN
);

CREATE TABLE IF NOT EXISTS university_education
(
    university_education_id SERIAL PRIMARY KEY,
    specialty_id    INT NOT NULL,
    university_id           INT NOT NULL,
    recruitment_plan_budget INT,
    score_budget_basis      INT,
    recruitment_plan_paid   INT,
    score_paid_basis        INT,
    cost_per_year           INT,
    FOREIGN KEY (specialty_id) REFERENCES specialty (specialty_id),
    FOREIGN KEY (university_id) REFERENCES university (university_id)
);
