# Education_In_Tjouen

## IO

### FileWrite, FileReader
write(), Scanner()
```
FileWriter fw = new FileWriter("C:/labs/sample.txt");
fw.write(new String(" ~~ "));
FileReader fr = new FileReader("C:/labs/sample.txt");
Scanner sc = new Scanner(fr);
String line = sc.nextLine();
```

### DataInputStream, DataOutputStream
writeInt(), writeUTF(String), readInt, readUTF(String)
```
String fpath = "address";
DataOutputStream dout = new DataOutputStream(new FileOutputStream(fpath));
DataInputStream din = new DataInputStream(new FileInputStream(fpath));
```

### ObjectInputStream, ObjectOutputStream
- 객체의 직렬화(Serialization): 객체를 저장할 때, 비트를 시리얼화하여 저장.(<->비직렬화)
- writeObejct(), readObject()(단, readObject의 경우, 해당 객체로 Typecasting 필요)
```
String fpath = "address";
ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(fpath));
ObjectInputStream objin = new ObjectInputStream(new FileInputStream(fpath));
```

### ImageCopy
1. txt파일
FileReader, FileWriter: 한 글자 = 2byte(16bit)

2. 그 외 
FileInputStream(Source Stream), FileOutputStream(Sink Stream): 1byte => Byte Stream
ByteArrayInputStream, ByteArrayOutputStream
* Node Stream(종류: Source Stream, Sink Stream): 노드와 직접 연결된 스트림(키보드, 모니터), 말단 노드
* Source Stream: 소스(원 데이터)를 읽어오는 노드 스트림
* Sink Stream: 최종적으로 저장하는 노드 스트림
* Filter Stream(종류: BufferedInputStream, BufferedOutputStream, Scanner, PrintWriter)

### close()
스트림 클래스들은 내부에서 버퍼(memory)를 사용하여 데이터를
메모리에 먼저 기록하고 버퍼가 채워지면 그 때 목적지에 보내는 방법을 사용한다.

이유: 디스크에 매번 접속하면 성능이 떨어지므로 메모리에 기록하여
데이터를 모아 한꺼번에 디스크를 보내는 방법을 사용하여 성능을 올리려고 한다. 

만약, 버퍼가 채워지지 않으면 디스크로 보내지 않기 때문에 적은 데이터는 파일에
기록되지 않을 수 있다. 그러나 마지막에 close()를 호출하면 먼저 버퍼에 있는
데이터가 목적지에 보내지고 모든 리소스를 해제하게 된다.

### End of File
-1, null, Exception

## Example of Using Stream
### Input
1. FileInputStream: 2Byte(16bit)씩 읽어오는 바이트 스트림. 이 때, 읽어온 형태는 숫자의 나열이다. (txt파일일 때, FileReader면 문자로 읽지만 아닐 경우에는 깨지기 때문에 txt파일이 아닐 경우 사용함)
2. InputStreamReader: 2Byte로 받아서 문자화(숫자 -> Java) 시켜주는 변환스트림(ConversionStream). 전체를 읽어오기 때문에 한 행씩 읽어주는 추가 스트림을 사용해야 한다.
3. BufferedReader: 버퍼에 한 문자씩 엔터를 치기 이전까지 모음(== 한 행을 모음)

### Output(Input과 순서 동일)
1. FileOutputStream
2. OutputStreamWriter
3. PrintWriter

## ObjectStream
### Serialization
* 객체를 구성하는 비트의 열의 순서를 정하는 것
* 메모리에 있는 객체 -> 파일에 저장, 네트워크 전송
* 파일에 저장된 객체 -> 메모리에 복원, 네트워크에서 수신하여 복원

### Sequence
1. 클래스 선언(Implements Serializable)
2. 인스턴스 생성
3. 객체를 직렬화
4. 직렬화된 객체를 복원

### Think
* 요구사항에 따라 어느 stream을 사용해야할지 결정해야 함(txt파일이면 FileReader, 그 외의 경우엔 FileInputStream) 
* 시간과 메모리를 둘 다 선택할 수 없기 때문에 시간의 경우 byte 배열을 통해 한꺼번에 읽어오는 방식(ByteArrayInputStream)을 사용하면 절약할 수 있음. 대신 그 만큼의 메모리 할당이 필요함.
* 기본적으로 file 객체를 읽어오는 stream을 사용한 후, 케이스에 따라 사용할 stream 결정.
