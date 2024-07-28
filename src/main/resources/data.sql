INSERT INTO task_snap_users (id, email, name) VALUES (0, 'thitiklinpon@gmail.com', 'Tony');


INSERT INTO tasks (id, title, status, start_date, start_time, due_date, due_time, all_day, label,
                   description, fk_user_id)
VALUES (0, 'Security Audit', 'ongoing', '2024-07-22', '08:00:00', '2024-08-02', '17:00:00', false,
        'Security', 'Audit of system security vulnerabilities', 0),
       (1, 'Code Refactoring', 'ongoing', '2024-07-20', NULL, '2024-07-29', NULL, true, 'Development',
        'Code optimization and cleanup', 0),
       (2, 'Client Meeting', 'ongoing', '2024-07-23', '13:00:00', '2024-08-03', '15:00:00', false,
        'Meeting', '', 0),
       (3, 'Server Migration', 'ongoing', '2024-07-01', NULL, '2023-07-29', NULL, true,
        'Infrastructure', '', 0),
       (4, 'UX/UI optimization', 'finished', '2024-07-01', '10:00:00', '2024-07-08', '12:00:00', false,
        'Optimization', '', 0),
       (5, 'UI Redesign', 'finished', '2024-07-11', '08:00:00', '2023-07-11', '17:00:00', false, 'Design',
        '', 0),
       (6, 'Feature testing', 'plan', '2024-06-24', NULL, NULL, NULL, true, 'Testing', '', 0),
       (7, 'Database Migration', 'plan', '2024-07-05', NULL, '2024-01-12', NULL, true, 'Migration',
        '', 0);