## 흐름

1. 송출자 (OBS)
2. RTMP 스트림 전송 > nginx-rtmp 서버 (FFmpeg 프로세스 트리거)
3. FFmpeg 인코딩 서버에서 HLS 세그먼트 생성 및 저장
4. 스트리밍 서버 (Spring)에서 클라이언트 요청 처리 및 콘텐츠 제공 (HLS)

## Module Description

- api: 사용자에게 제공되는 RESTful API를 관리하는 모듈
- encoding: FFmpeg를 사용하여 비디오 인코딩 및 트랜스코딩을 처리하는 모듈
- stream: 실시간 스트리밍 서비스를 관리하고 제공하는 모듈
- core: 도메인 모델, 열거형(enum) 등 프로젝트 전반에 사용되는 공통 요소를 포함하는 모듈
- storage: 데이터베이스 및 Redis와 같은 저장소를 관리하는 모듈
