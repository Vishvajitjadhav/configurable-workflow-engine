INSERT INTO users (username, password, role)
VALUES ('requester', 'password', 'REQUESTER');

INSERT INTO users (username, password, role)
VALUES ('approver', 'password', 'APPROVER');

INSERT INTO users (username, password, role)
VALUES ('admin', 'password', 'ADMIN');


INSERT INTO approval_step (request_type, step_order, role)
VALUES ('LEAVE', 1, 'APPROVER');

INSERT INTO approval_step (request_type, step_order, role)
VALUES ('LEAVE', 2, 'ADMIN');


INSERT INTO approval_step (request_type, step_order, role)
VALUES ('EXPENSE', 1, 'APPROVER');

INSERT INTO approval_step (request_type, step_order, role)
VALUES ('EXPENSE', 2, 'ADMIN');