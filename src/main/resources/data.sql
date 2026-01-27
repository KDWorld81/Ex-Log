-- 카테고리 데이터
INSERT IGNORE INTO category (category_id, category_name) VALUES (1, '가슴');
INSERT IGNORE INTO category (category_id, category_name) VALUES (2, '등');
INSERT IGNORE INTO category (category_id, category_name) VALUES (3, '하체');
INSERT IGNORE INTO category (category_id, category_name) VALUES (4, '어깨');
INSERT IGNORE INTO category (category_id, category_name) VALUES (5, '팔');

-- 팔 운동 세부종목 데이터
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (1, '바벨 컬', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (2, '덤벨 컬', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (3, '해머 컬', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (4, '라잉 트라이셉스 익스텐션', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (5, '케이블 푸쉬 다운', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (6, '덤벨 킥백', 5);