INSERT INTO tasks (id, title, status, start_date, start_time, due_date, due_time, all_day, label,
                   description, fk_user_id)
VALUES (0, 'Security Audit', 'ongoing', '2023-11-22', '08:00:00', '2023-11-24', '17:00:00', false,
        'Security', 'Audit of system security vulnerabilities', 0),
       (1, 'Code Refactoring', 'ongoing', '2023-11-20', NULL, '2023-11-24', NULL, true, 'Development',
        'Code optimization and cleanup', 0),
       (2, 'Client Meeting', 'ongoing', '2023-11-27', '13:00:00', '2023-11-27', '15:00:00', false,
        'Meeting', '', 0),
       (3, 'Server Migration', 'ongoing', '2023-12-01', NULL, '2023-12-07', NULL, true,
        'Infrastructure', '', 0),
       (4, 'UX/UI optimization', 'finished', '2023-11-07', '10:00:00', '2023-11-09', '12:00:00', false,
        'Optimization', '', 0),
       (5, 'UI Redesign', 'finished', '2023-11-08', '08:00:00', '2023-11-11', '17:00:00', false, 'Design',
        '', 0),
       (6, 'Feature testing', 'plan', '2023-12-24', NULL, NULL, NULL, true, 'Testing', '', 0),
       (7, 'Database Migration', 'plan', '2024-01-05', NULL, '2024-01-12', NULL, true, 'Migration',
        '', 0);

INSERT INTO task_snap_users (id, email, name) VALUES (0, "thitiklinpon@gmail.com", "Tony");