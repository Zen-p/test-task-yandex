CREATE TABLE system_item_history_unit (
    id VARCHAR(255) NOT NULL,
    url VARCHAR(255),
    type VARCHAR(255) NOT NULL CHECK (type IN ('FILE', 'FOLDER')),
    date TIMESTAMP WITH TIME ZONE NOT NULL,
    parent_id VARCHAR(255),
    size INTEGER CHECK (size >= 1),
    PRIMARY KEY (id),
    FOREIGN KEY (parent_id) REFERENCES system_item_history_unit(id)
);