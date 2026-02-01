# 사용할 기본 이미지
FROM eclipse-temurin:21-jre-alpine

# 작업 디렉토리 설정: 컨테이너 내부의 파일이 저장될 경로
WORKDIR /app

# JAR 파일 경로 변수 설정: Gradle 빌드 결과물의 위치를 지정
# build.gradle의 description 'Ex-Log'와 version '0.0.1-SNAPSHOT'에 근거
ARG JAR_FILE=build/libs/Ex-Log-0.0.1-SNAPSHOT.jar

# JAR 파일 복사: 호스트의 JAR 파일을 컨테이너 안으로 복사
COPY ${JAR_FILE} app.jar

# 환경 변수 기본값 설정
ENV SPRING_PROFILES_ACTIVE=prod

# 실행 명령어: 애플리케이션을 실행합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]