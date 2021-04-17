# Network
## Network Program
* 바이트 단위로 데이터의 송수신이 이루어짐
* 송신: 문자는 바이트스트림으로 변환하여 전송해야한다.
* 수신: 바이트스트림으로 수신되기 때문에, 바이트스트림을 문자로 변환하여 사용해야한다.

### Description Of File
##### ChatClient: 채팅 클라이언트(메시지)
##### ChatServer: 채팅 서버(메시지)
##### ChatMsg: 채팅 메시지로 교환할 객체(Sender, Receiver, Msg, Filedata(byte[])
##### ChatClientExample, ChatServerExample: 채팅 클라이언트(메시지), 서버(메시지) 또다른 형태
##### ChatClientObject, ChatServerObject: 채팅 클라이언트(모든 파일, 메시지), 서버(모든 파일, 메시지). ObjectStream 개념 활용
