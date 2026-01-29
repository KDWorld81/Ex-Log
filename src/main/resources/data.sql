-- 카테고리 데이터
INSERT IGNORE INTO category (category_id, category_name) VALUES (1, '가슴');
INSERT IGNORE INTO category (category_id, category_name) VALUES (2, '등');
INSERT IGNORE INTO category (category_id, category_name) VALUES (3, '하체');
INSERT IGNORE INTO category (category_id, category_name) VALUES (4, '어깨');
INSERT IGNORE INTO category (category_id, category_name) VALUES (5, '팔');

-- 가슴
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (1, '벤치 프레스', 1);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (2, '인클라인 벤치 프레스', 1);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (3, '덤벨 플라이', 1);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (4, '체스트 프레스', 1);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (5, '푸쉬업', 1);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (6, '디클라인 벤치 프레스', 1);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (7, '펙 덱 플라이', 1);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (8, '케이블 크로스 오버', 1);

-- 등
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (9, '데드리프트', 2);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (10, '렛 풀 다운', 2);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (11, '시티드 로우', 2);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (12, '풀업', 2);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (13, '바벨 로우', 2);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (14, '원 암 덤벨 로우', 2);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (15, '티바 로우', 2);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (16, '풀오버', 2);

-- 하체
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (17, '스쿼트', 3);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (18, '레그 프레스', 3);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (19, '레그 익스텐션', 3);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (20, '레그 컬', 3);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (21, '런지', 3);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (22, '로마니안 데드리프트', 3);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (23, '스티프 레그 데드리프트', 3);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (24, '힙 쓰러스트', 3);

-- 어깨
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (25, '밀리터리 프레스', 4);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (26, '사이드 레터럴 레이즈', 4);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (27, '프론트 레이즈', 4);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (28, '숄더 프레스', 4);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (29, '벤트 오버 레터럴 레이즈', 4);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (30, '아놀드 프레스', 4);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (31, '업라이트 로우', 4);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (32, '슈러그', 4);

-- 팔
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (33, '바벨 컬', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (34, '덤벨 컬', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (35, '해머 컬', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (36, '라잉 트라이셉스 익스텐션', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (37, '케이블 푸쉬 다운', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (38, '덤벨 킥백', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (39, '컨센트레이션 컬', 5);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (40, '프리처 컬', 5);