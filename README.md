# 영단어 관리 프로그램

- 최초에 allDB.txt를 읽어와 ArrayList로 저장
- 입/출력 시, 문자 스트림 사용 (보조 스트림 사용)
- ArrayList의 데이터가 변화되는 시점과 동시에 파일 내용에도 동기화 필요

---

## 1 단어 검색

- 영단어 또는 뜻으로 검색

- 뒤로가기 메뉴 존재

- 단어 존재 시, 단어 및 뜻 정보 출력 (검색 시, 모두 일치해야 하는 것은 아님)

- 단어 존재하지 않을 시, `없는 단어` 출력

## 2 영단어 등록

- 단어 등록에 필요한 값 (영단어, 뜻1, 뜻2)은 사용자에게 입력받음

- 영단어 중복체크 및 단어 등록

- 뒤로가기 메뉴 존재

## 3 테스트

- 종류 : 영한테스트(영단어를 보여주고, 뜻을 맞추는 테스트), 한영테스트(뜻을 보여주고, 영단어를 맞추는 테스트)
  - 모든 테스트는 완벽히 일치하는 문자열 입력 시에만 통과. 단, 한영테스트는 대소문자 구분 없음.

- 테스트할 문제의 갯수는 사용자에게 입력 받고, 랜덤으로 문제 추출.
  - 동일 문제 출제 불가.

- 실패한 문제들은 테스트 종료 후, 오답노트 파일(failDB.txt)로 저장.
  - (단, 오답노트 파일내부의 단어도 중복 저장 불가하고, 테스트 실패 시 기존 오답노트 파일에 추가 저장)

- 테스트 종료 메뉴 존재

## 4 오답노트

1. 오답노트 보기 : 오답노트 전체 리스트를 넘버링하여 영단어만 출력 후
2. 사용자에게 단어를 선택받은 뒤
3. 뜻을 출력

- 재시험 : 오답노트에 존재하는 영단어를 보고, 뜻을 맞춤.

- 비우기 : 오답노트 내용을 모두 지움 (파일은 삭제하지 말것)

- 종료 : 종료 시, 성공한 문제들은 오답노트에서 제거

## 5 단어 수정 및 삭제

- 수정할 영단어를 입력받아, 뜻1과 뜻2을 수정

- 수정되는 단어의 뜻1과 뜻2는 중복될 수 없음

- 삭제 시에는 영단어 또는 뜻을 입력받아 삭제

- 뜻을 입력하여 삭제 시, 대상 영단어가 2개 이상일 경우 넘버링하여 출력하고 삭제할 영단어를 숫자로 입력 받음
  - 전체 삭제 메뉴도 출력

---

## TODO List

### Search

- [x] Search with word
- [ ] Search with definition
- [x] fuzzy search for word
- [x] print no word exist message
- [x] option to cancel search

### Add

- [x] get input for word, 2 definitions
- [ ] check duplication before adding
- [ ] option to cancel add

### Test

- [ ] guess word by definitions
- [ ] guess one of definitions by word
- [ ] number of tests are chosen by user with no duplicated test
- [ ] words failed to guess goes to failDB.txt with no duplications
- [ ] option to cancel test

### Failed Note

- [ ] show failDB.txt content with index word only
- [ ] show definitions when word is chosen with index by user
- [ ] test only with words from failDB.txt
- [ ] empty failDB.txt without removing the file
- [ ] remove correctly guessed words from failDB.txt

### Edit

- [ ] search word to edit by word and change definitions without duplication
- [ ] search word to delete by word
- [ ] if more then one word is searched number it and take input
- [ ] option to delete all

---

## Compile & Run

- `find src -name "*.java"`: find all java files
- `-d out`: compiled `.class` files into `out/` directory

```bash
javac -d out $(find src -name "*.java")
```

- `Start`: java file with `psvm`
- `-cp`: classpath points to out which runs the compiled files

```bash
java -cp out kr.or.iei.start.Start
```

This will run the project

In one line:

```bash
javac -d out $(find src -name "*.java") && java -cp out kr.or.iei.start.Start
```

---

## Create JAR

Create `.jar` file

```bash
jar --create --file english-test.jar \
    --main-class kr.or.iei.start.Start \
    -C out .
```

Run created `.jar` file

```bash
java -jar english-test.jar
```

---

### Happy Hacking 🎉
