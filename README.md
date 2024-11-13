# Kopring - Chat

## 소개
이 프로젝트는 **Kopring**과 **React**를 기반으로 한 웹 애플리케이션입니다. 
주요 기능으로는 **실시간 메시징**과 **이벤트 스트리밍**을 포함하며, 이를 위해 **Redis**, **WebSocket**, **Kafka**를 활용하였습니다.

## 주요 기술 스택
- **Backend**: Kopring (Kotlin + Spring Boot)
- **Frontend**: React
- **메시징 및 데이터 스트림**:
  - **Redis**: 캐싱 및 실시간 데이터 처리
  - **WebSocket**: 실시간 양방향 통신
  - **Kafka**: 이벤트 스트리밍 및 분산 메시징 시스템

## 기능
- **방생성**: Redis와 WebSocket을 활용하여 사용자 간 실시간 메시지 전송
- **채팅 방 리스트 조회**: Redis와 WebSocket을 활용하여 사용자 간 실시간 메시지 전송
- **실시간 채팅**: Redis와 WebSocket을 활용하여 사용자 간 실시간 메시지 전송
- **데이터 캐싱**: Redis를 통해 데이터 접근 속도 향상
- **이벤트 스트리밍**: Kafka를 통해 대량의 데이터를 실시간으로 처리하고 스트리밍

## 설치 및 실행 방법
1. **환경 설정**: Docker Compose 파일을 사용하여 Redis와 Kafka 인프라를 구성합니다.
2. **백엔드 서버 실행**:
   ./gradlew bootRun
3. **프런트엔드 서버 실행**:
   npm install
   npm start

## 단위테스트 도구
1. Mockito
2. JUnit

## 방 생성
![image](https://github.com/user-attachments/assets/d475748b-8e1a-4338-ae9b-5e40daf3dbb8)


## Redis Data
![image](https://github.com/user-attachments/assets/aa3addd0-bb5e-4c11-bfe8-463af5bdaa8f)


## 방 리스트
![image](https://github.com/user-attachments/assets/c53a4e07-5de5-4b04-9d49-947f48e7e293)


## 채팅
![image](https://github.com/user-attachments/assets/869269a5-f0e7-4f3b-9731-0d4cd972f9b9)
![image](https://github.com/user-attachments/assets/c82fa57b-2dcf-4bb1-af79-35aa7b43da39)

