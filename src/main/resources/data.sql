-- 카테고리 데이터
INSERT IGNORE INTO category (category_id, category_name) VALUES (1, '가슴');
INSERT IGNORE INTO category (category_id, category_name) VALUES (2, '등');
INSERT IGNORE INTO category (category_id, category_name) VALUES (3, '하체');
INSERT IGNORE INTO category (category_id, category_name) VALUES (4, '어깨');
INSERT IGNORE INTO category (category_id, category_name) VALUES (5, '팔');
INSERT IGNORE INTO category (category_id, category_name) VALUES (6, '유산소');

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

-- 유산소
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (41, '러닝 (러닝머신)', 6);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (42, '사이클', 6);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (43, '천국의 계단', 6);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (44, '일립티컬', 6);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (45, '로잉 머신', 6);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (46, '버피 테스트', 6);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (47, '줄넘기', 6);
INSERT IGNORE INTO exercise (exercise_id, exercise_name, category_id) VALUES (48, '인클라인 워킹', 6);

-- 칭호
-- 가슴 (Category 1)
INSERT IGNORE INTO title (title_name, explanation, title_condition, category_id, threshold) VALUES
('푸시업 요정', '가슴 운동 10회! 시작이 반입니다. 앙증맞은 가슴 근육이 생겼어요.', 'CATEGORY_COUNT', 1, 10),
('벤치 위의 철학자', '가슴 운동 50회! 밀면서 인생을 배웁니다.', 'CATEGORY_COUNT', 1, 50),
('가슴 근육 팝핀', '가슴 운동 300회! 이제 가슴 근육으로 박자를 탈 수 있습니다.', 'CATEGORY_COUNT', 1, 300),
('인간 대흉근', '가슴 운동 500회! 티셔츠가 터지려고 하네요. 조심하세요.', 'CATEGORY_COUNT', 1, 500),
('갑옷 제조기', '가슴 운동 1000회! 이제 웬만한 충격에는 끄떡없는 강철 가슴입니다.', 'CATEGORY_COUNT', 1, 1000);

-- 등 (Category 2)
INSERT IGNORE INTO title (title_name, explanation, title_condition, category_id, threshold) VALUES
('철봉의 친구', '등 운동 10회! 철봉과 조금 친해졌습니다.', 'CATEGORY_COUNT', 2, 10),
('화난 등 근육', '등 운동 50회! 누군가 뒤에서 당신을 보고 화난 줄 알겠어요.', 'CATEGORY_COUNT', 2, 50),
('코브라의 후예', '등 운동 100회! 등이 옆으로 넓어지기 시작했습니다.', 'CATEGORY_COUNT', 2, 100),
('태평양 등판', '등 운동 500회! 등에서 축구를 해도 되겠는데요?', 'CATEGORY_COUNT', 2, 500),
('등으로 말해요', '등 운동 1000회! 말하지 않아도 뒷모습이 모든 걸 말해줍니다.', 'CATEGORY_COUNT', 2, 1000);

-- 하체 (Category 3)
INSERT IGNORE INTO title (title_name, explanation, title_condition, category_id, threshold) VALUES
('스쿼트 꿈나무', '하체 운동 30회! 내일 계단 내려갈 때 조심하세요.', 'CATEGORY_COUNT', 3, 30),
('꿀벅지 생성기', '하체 운동 100회! 바지 사이즈가 한 치수 커졌습니다.', 'CATEGORY_COUNT', 3, 100),
('말벅지 장착', '하체 운동 300회! 당신의 발차기는 이제 흉기입니다.', 'CATEGORY_COUNT', 3, 300),
('지구력의 신', '하체 운동 500회! 하루 종일 서 있어도 지치지 않습니다.', 'CATEGORY_COUNT', 3, 500),
('하체 마스터', '하체 운동 1000회! 이제 스쿼트 하면서 밥도 먹을 수 있습니다.', 'CATEGORY_COUNT', 3, 1000);

-- 어깨 (Category 4)
INSERT IGNORE INTO title (title_name, explanation, title_condition, category_id, threshold) VALUES
('어깨 으쓱', '어깨 운동 10회! 자신감이 조금 상승했습니다.', 'CATEGORY_COUNT', 4, 10),
('어깨 깡패 지망생', '어깨 운동 50회! 어깨가 귀에 닿으려고 하네요.', 'CATEGORY_COUNT', 4, 50),
('태평양 어깨', '어깨 운동 200회! 이제 문 통과할 때 옆으로 걸어야 할걸요?', 'CATEGORY_COUNT', 4, 200),
('삼각근 몬스터', '어깨 운동 500회! 어깨에 뽕 넣었냐는 소리를 듣기 시작합니다.', 'CATEGORY_COUNT', 4, 500),
('어깨 끝판왕', '어깨 운동 1000회! 이제 당신의 어깨는 산맥입니다.', 'CATEGORY_COUNT', 4, 1000);

-- 팔 (Category 5)
INSERT IGNORE INTO title (title_name, explanation, title_condition, category_id, threshold) VALUES
('알통 사냥꾼', '팔 운동 10회! 귀여운 알통이 생겼습니다.', 'CATEGORY_COUNT', 5, 10),
('뽀빠이의 제자', '팔 운동 50회! 시금치 안 먹어도 팔 힘이 넘칩니다.', 'CATEGORY_COUNT', 5, 50),
('팔씨름 왕', '팔 운동 200회! 동네 팔씨름은 이제 당신이 접수합니다.', 'CATEGORY_COUNT', 5, 200),
('이두/삼두 폭격기', '팔 운동 500회! 팔 근육이 터질 듯한 펌핑을 즐기세요.', 'CATEGORY_COUNT', 5, 500),
('강철 팔뚝', '팔 운동 1000회! 이제 팔로 못 드는 건 없습니다.', 'CATEGORY_COUNT', 5, 1000);

-- 유산소 (Category 6)
INSERT IGNORE INTO title (title_name, explanation, title_condition, category_id, threshold) VALUES
('동네 산책가', '유산소 10회! 심장이 건강해지는 소리가 들립니다.', 'CATEGORY_COUNT', 6, 10),
('인간 엔진', '유산소 50회! 이제 웬만큼 뛰어도 숨이 안 차요.', 'CATEGORY_COUNT', 6, 50),
('마라토너의 심장', '유산소 200회! 지치지 않는 무한 동력 에너자이저.', 'CATEGORY_COUNT', 6, 200),
('바람의 아들', '유산소 500회! 걷는 속도가 남들 뛰는 속도입니다.', 'CATEGORY_COUNT', 6, 500),
('철인 0종 경기', '유산소 1000회! 이제 당신의 심폐지구력은 탈인간급입니다.', 'CATEGORY_COUNT', 6, 1000);
