CREATE TABLE viewings (
     id BIGINT NOT NULL AUTO_INCREMENT,
     user_id BIGINT NOT NULL,
     episode_id BIGINT NOT NULL,
     updated_at DATETIME,
     timecode INT,
     PRIMARY KEY (id),
     INDEX viewings_episode_ind (episode_id),
    FOREIGN KEY (episode_id)
        REFERENCES episodes(id)
        ON DELETE CASCADE,
     INDEX viewings_user_ind (user_id),
    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);