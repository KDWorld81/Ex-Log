# Ex-Log


### **Ex-Log**는 현대인이 꾸준히 운동을 이어나갈 수 있도록  **간편한 운동 기록**과 **달성 보상 시스템**으로 동기부여에 집중한 운동 기록 서비스입니다.
![Ex-Log 마크](https://github.com/KDWorld81/Ex-Log_Docs/blob/main/chap1Images/Ex-Log%20%E1%84%86%E1%85%A1%E1%84%8F%E1%85%B3.jpg?raw=true)
---

## 🛠 기술 스택

- Java 21, Spring Boot 3.5.3
- Spring Data JPA, Spring Security, JWT (jjwt 0.12.6)
- Spring Mail (Gmail SMTP), Thymeleaf, Redis
- MySQL 8.0, Docker / Docker Compose
- AWS EC2

---

## 🌐 배포 주소

**http://43.201.9.189:8080/exlog**

> ※ 회원 인증이 완료된 경우에만 홈 화면으로 진입할 수 있습니다.  

> 간혹 서버 상태에 따라 접속이 원활하지 않을 수 있어, 로컬 실행 방법도 함께 제공합니다.
> > 로컬에서는 docker , DB ,  redis setting에 대한 조건이 갖춰져야 정상작동합니다.

---

## 💻 로컬 실행 방법

### 0. 사전 환경 확인

- Java 21 이상 설치 필요
- Docker 및 Docker Compose 설치 필요
- Gradle Wrapper(`./gradlew`) 포함되어 있음

---

### 1. GitHub에서 프로젝트 클론

```bash
git clone https://github.com/KDWorld81/Ex-Log.git
cd Ex-Log
```

---

### 2. .env 파일 생성

프로젝트 루트에 `.env` 파일을 생성하고 아래 항목을 설정하세요.

```env
DB_HOST=localhost
DB_NAME=ExLog
DB_USER=localmaster
DB_PASSWORD=[비밀번호]
REDIS_HOST=localhost
SMTP_USERNAME=[Gmail 계정]
SMTP_PASSWORD=[Gmail 앱 비밀번호]
JWT_SECRET=[JWT 시크릿 키]
JWT_ISSUER=[발급자]
TZ=Asia/Seoul
```

---

### 3. DB, Redis 실행

```bash
docker-compose -f docker-compose.local.yml up -d
```

---

### 4. 앱 실행

```
<bash>
./gradlew bootRun

<DOS>
gradlew bootRun
```

---

### 5. 접속 확인

```
http://localhost:8080/exlog
```

> ※ 로컬 실행 시 Docker DB 기준으로 동작하므로 Docker DB에 새로 회원가입 후 테스트 부탁드립니다.



## Repository address
- Project repository : [Ex-Log](https://github.com/KDWorld81/Ex-Log)
- Docs repository : [Ex-Log_Docs](https://github.com/KDWorld81/Ex-Log_Docs)

## ⚙️ Infra Architecture
![infraarchtecture](https://github.com/KDWorld81/Ex-Log_Docs/blob/main/chap3Images/infra-architecture.png)


## 📖 ERD
![signup](https://github.com/KDWorld81/Ex-Log_Docs/blob/main/chap3Images/erd.png)
