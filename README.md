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

![image](https://github.com/user-attachments/assets/38720d2a-5282-4efe-8a12-ebd6d7917e54)
![image](https://github.com/user-attachments/assets/614ed9bd-61ff-411e-bd32-b1671132cdfb)
![image](https://github.com/user-attachments/assets/cd69c1dd-52ea-4f60-8f22-3ff26b870edd)
![image](https://github.com/user-attachments/assets/8cb2463e-0019-4397-9bd5-e85a40db3b12)
