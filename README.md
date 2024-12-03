# 구현과정 상세 설명

# 1단계

## 1. 목표

- 1단계에서는 사용자가 1부터 45 사이의 숫자 6개를 입력하고, 입력값이 유효한지 검증하는 기능을 구현합니다.  
- 유효하지 않은 입력이 들어오면 **적절한 에러 메시지를 출력**하고, 재입력을 요청합니다.
- 입력한 번호와 랜덤으로 생성한 당첨 번호와 비교하여 **일치하는 숫자 개수**를 출력합니다.

---

## 2. 문제 분석

### 요구사항

1. 사용자로부터 **1부터 45 사이의 숫자 6개**를 입력받아야 합니다.
2. 입력값은 **쉼표(,)로 구분된 형식**이어야 합니다.
   - 예시: `1,2,3,4,5,6`
   - 숫자 사이에 공백이 있어도 무관.
3. 입력값에 대해 다음과 같은 검증을 수행해야 합니다:
   - 숫자의 개수가 정확히 6개인지 확인.
   - 숫자가 1부터 45 사이에 포함되는지 확인.
   - 중복된 숫자가 없는지 확인.
4. 잘못된 입력값이 들어올 경우:
   - 적절한 에러 메시지를 출력.
   - 에러 메시지 출력 후 재입력을 요청.
5. 당첨 번호를 **랜덤**으로 생성하고, 사용자 입력값과 비교하여 **일치하는 숫자 개수**를 출력합니다.
6. 출력시 모든 번호는 **오름차순으로 정렬**해서 출력

---

## 3. 해결 과정

### 설계
- Lotto와 WinningNumbers를 별도의 클래스로 분리하여 각자의 책임을 명확히 정의.
- Lotto는 사용자 입력 로또 번호를 관리하고, 입력 검증과 당첨 개수를 제공.
- WinningNumbers는 랜덤한 당첨 번호 생성과 비교 로직의 일부를 담당.
- LottoValidator는 검증 로직을 전담하여 재사용성과 응집도를 높임.
- InputView와 OutputView는 입력과 출력을 책임짐.
- 새로운 로또 방식(예: 당첨 번호 수동 입력)이나 추가 기능(예: 보너스 번호)을 쉽게 확장 가능하도록 설계.

### 1단계: 사용자 입력 처리

- **Problem**
   - 사용자가 1부터 45 사이의 숫자를 6개 입력해야 하지만, 잘못된 입력(범위 벗어남, 중복 숫자 등)이 들어올 가능성이 높음.

- **Solve**
   - **`InputView` 클래스**를 통해 사용자 입력을 처리.
   - **`LottoValidator` 클래스**를 분리하여 입력값에 대한 검증 책임을 위임.
   - **예외 처리**를 통해 잘못된 입력 시 에러 메시지를 출력하고 재입력을 요청.

### 2단계: 당첨 번호 랜덤 생성 및 비교

- **Problem**
  - 당첨 번호는 1~45 사이의 중복되지 않는 숫자 6개여야 하며, 사용자 입력과 비교해야 함.

- **Solve**
  - **`NumberGenerator` 클래스**를 구현하여 1~45 사이의 중복되지 않는 숫자 6개를 생성.
  - **`WinningNumbers` 클래스**에서 당첨 번호 생성 및 사용자 입력 번호와 비교 로직을 구현.
  - **`Lotto` 클래스**에서 사용자 입력 번호와 당첨 번호를 비교하는 로직을 구현.

### 3단계: 결과 출력

- **Problem**
  - 사용자 입력 번호, 당첨 번호, 일치 숫자 개수를 명확하고 보기 좋게 출력해야 함.

- **Solve**
  - **`OutputView` 클래스**를 구현하여 결과 출력 책임을 분리.
  - 숫자 리스트를 보기 좋게 포맷팅하기 위해 **`Parser` 클래스**에서 포맷 로직을 작성 후 해당 객체에서 `toString`을 이용하여 객체 호출만으로 보기 좋게 출력됨.

### 프로그램 흐름

1. 사용자 입력:
   - InputView 를 통해 사용자로부터 입력받은 문자열을 Lotto.from()에 전달.
   - Lotto 객체가 생성시 입력값의 유효성을 LottoValidator 가 검증.
2. 당첨 번호 생성:
   - WinningNumbers.create()을 호출
   - NumberGenerator.generateSortedUniqueNumbers()를 통해 1~45 사이의 중복되지 않는 숫자 6개를 생성.
3. 비교 및 결과 계산:
   - Lotto.countMatchingNumbers(WinningNumbers)를 호출하여 일치하는 숫자의 개수를 계산.
4. 결과 출력:
   - OutputView 를 통해 사용자 번호, 당첨 번호, 일치 개수를 출력.


---

## 4. 코드 설명

### 주요 클래스 및 메서드

#### 1. `LottoGame` 클래스
- 프로그램의 전체 흐름을 제어하는 역할을 수행.
- `getLottoFromInput` 메서드를 통해 사용자 입력을 받아 검증하며, 잘못된 입력에 대해 재입력을 요청.

```java
private Lotto getLottoFromInput() {
    while (true) {
        try {
            String input = inputView.inputNumbers();
            return Lotto.from(input);
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
        }
    }
}
```

#### 2. `Lotto` 클래스

- 사용자 입력 로또 번호를 관리하고, 당첨 번호와의 비교를 수행.
- `from` 메소드를 통해 문자열 입력을 파싱하여 Lotto 객체 생성.
- `countMatchingNumbers` 메서드를 통해 당첨 번호와 비교하여 일치하는 숫자의 개수를 반환.

```java
public int countMatchingNumbers(WinningNumbers winningNumbers) {
    return (int) numbers.stream()
            .filter(winningNumbers::contains)
            .count();
}
```

#### 3. `WinningNumbers` 클래스

- 랜덤한 당첨 번호를 생성하고, 사용자 입력 번호와의 비교 로직 일부를 담당.
- `create` 메소드를 내에서 NumberGenerator를 통해 1~45 사이의 중복되지 않는 숫자 6개를 생성.
- `contains` 메서드를 통해 특정 숫자가 당첨 번호에 포함되어 있는지 확인.

```java
public boolean contains(int number) {
    return numbers.contains(number);
}
```

#### 4. `LottoValidator` 클래스

- 사용자가 입력한 로또 번호의 유효성을 검증.
- `validate` 메서드를 통해 입력된 번호의 개수, 범위, 중복 여부를 확인.
  - `validateRange` 및 `validateDuplicate` 메소드에서 유효하지 않는 범위 및 중복되는 숫자가 있으면 해당 숫자를 에러문구에 포함.

```java
    private static void validateRange(List<Integer> numbers) {
    List<Integer> invalidRangeNumbers = numbers.stream()
            .filter(n -> n < LottoConstants.MIN_NUMBER.getValue() || n > LottoConstants.MAX_NUMBER.getValue())
            .toList();

    if (!invalidRangeNumbers.isEmpty()) {
        throw new IllegalArgumentException(
                String.format(INVALID_RANGE_MESSAGE, Parser.formatNumbers(invalidRangeNumbers)));
    }
}

private static void validateDuplicates(List<Integer> numbers) {
    Set<Integer> uniqueNumbers = new HashSet<>(numbers);

    if (uniqueNumbers.size() != numbers.size()) {
        List<Integer> duplicateNumbers = findDuplicates(numbers);
        throw new IllegalArgumentException(
                String.format(DUPLICATE_NUMBER_MESSAGE, Parser.formatNumbers(duplicateNumbers)));
    }
}
```

#### 5. `NumberGenerator` 클래스

- 1~45 사이의 중복되지 않는 숫자 6개를 생성.
- `generateSortedUniqueNumbers` 메서드를 통해 중복되지 않는 숫자 6개를 생성하고 오름차순으로 정렬하여 반환.

```java
    public static List<Integer> generateSortedUniqueNumbers() {
    Set<Integer> uniqueNumbers = getUniqueNumbers();

    List<Integer> sortedNumbers = new ArrayList<>(uniqueNumbers);
    Collections.sort(sortedNumbers);

    return sortedNumbers;
}
```

---

## 실행 방법

1. **필수 조건**: JDK 21 이상이 설치되어 있어야 합니다.
2. **모든 `.java` 파일을 동일한 디렉토리에 저장**합니다.
```bash
# 컴파일
javac *.java
   
#실행
java Application
```

---

## 프로그램 실행 예시

### 유효한 입력
```
1~45 중 로또 번호를 여섯개 입력하세요.
1,12,23,34,45,6
플레이어의 숫자: 1, 6, 12, 23, 34, 45
로또 당첨 숫자: 1, 5, 9, 12, 22, 33
일치한 숫자 개수: 2개
```

### 잘못된 입력
```bash
1~45 중 로또 번호를 여섯개 입력하세요.
# 중복된 숫자
33,1,2,3,33,34
같은 번호 33이 이미 선택되었습니다. 다른 번호를 선택하세요.
# 유효하지 않는 문자
1,2,3,4,5,abc
숫자가 아닌 값이 포함되어 있습니다. 다시 번호를 선택하세요.
# 범위를 벗어난 숫자
0,1,2,44,45,46
범위를 벗어난 번호 0, 46이 포함되어 있습니다. 다른 번호를 선택하세요.
```

---

# 2단계

## 1. 목표

- 1단계에서 구현한 사용자 입력 처리 및 당첨 번호 생성 기능에 추가로 보너스 숫자를 관리하고 당첨 등수 판별 로직을 구현합니다.
- WinningNumbers 클래스에서 보너스 번호를 추가 관리하며, 이를 포함한 당첨 결과를 Rank에 매핑합니다. 
- 사용자가 입력한 숫자와 당첨 번호를 비교하여 일치 여부를 판별하고, 등수에 따라 적절한 결과를 출력합니다.

---

## 2. 문제 분석

### 요구사항

1. 보너스 숫자 추가
   - 당첨 번호 6개와 중복되지 않는 보너스 숫자 1개를 추가로 생성.
   - 숫자는 당첨 번호와 별도로 구분하여 표시.
2. 당첨 등수 판별
   - 당첨 번호와 사용자가 입력한 번호를 비교하여 일치 숫자 개수와 보너스 번호 일치 여부를 기준으로 등수를 판별.
   - 당첨 등수 예시
     - 동일한 번호 6개: 1등
     - 동일한 번호 5개 + 보너스 번호: 2등
     - 동일한 번호 5개: 3등
     - 동일한 번호 4개: 4등
     - 동일한 번호 3개: 5등
     - 그 외: 낙첨
3. 결과 출력:
   - 등수에 따라 적절한 결과 메시지를 출력.
   - ex `결과: 4개 일치. 4등 축하드립니다!`

---

## 3. 해결 과정

### 설계

- Rank는 일치 개수와 보너스 번호 여부를 기준으로 등수를 관리하고, 이를 통해 유연하게 확장 가능하도록 설계.
- WinningNumbers에서 보너스 번호를 추가로 관리.
- NumberGenerator에서 당첨 번호와 겹치지 않는 보너스 번호를 생성.

### 1단계: 보너스 번호 추가

- **Problem**
   - 당첨 번호에 보너스 번호를 추가하여 당첨 결과를 판별해야 함.
- **Solve**
   - **`WinningNumbers` 클래스**에서 보너스 번호를 추가로 관리.
   - **`NumberGenerator` 클래스**에서 당첨 번호와 겹치지 않는 보너스 번호를 생성.

### 2단계: 당첨 등수 판별

- **Problem**
  - 당첨 번호와 사용자 입력 번호를 비교하여 일치 숫자 개수와 보너스 번호 일치 여부를 기준으로 등수를 판별해야 함.
- **Solve**
  - **`Rank` 클래스**에서 일치 숫자 개수와 보너스 번호 일치 여부를 기준으로 등수를 판별.
  - `Lotto` 클래스에 있던 당첨 번호 비교 로직을 **`WinningNumbers` 클래스**로 이동 후 당첨 개수를 바탕으로 Rank를 반환.

### 3단계: 결과 출력

- **Problem**
  - 등수에 따라 적절한 결과 메시지를 출력해야 함. 
- **Solve**
  - **`RankMessageMapper` 클래스**에서 Rank에 따른 결과 메시지를 관리.
  - **`OutputView` 클래스**에서 결과 메시지 출력 로직을 분리.

### 프로그램 흐름

1. 보너스 번호 생성
   - WinningNumbers.create() 시에 NumberGenerator.generateBonusNumber()를 통해 보너스 번호를 생성.
2. 비교 및 결과 계산
   - WinningNumbers.isBonusNumberMatched()를 통해 보너스 번호 일치 여부를 확인.
   - WinningNumbers.determineRank()를 통해 일치하는 숫자 개수와 보너스 번호 일치 여부를 바탕으로 Rank를 반환.
3. 결과 출력
   - RankMessageMapper.getMessage()를 통해 Rank에 따른 결과 메시지를 반환.
   - OutputView.printResult()를 통해 결과 메시지 출력.

---

## 4. 코드 설명

### 주요 클래스 및 메서드

#### 1. `NumberGenerator` 클래스
- 당첨번호와 중복되지 않는 보너스 번호 1개를 생성.
- `generateBonusNumber` 메서드를 통해 당첨 번호와 중복되지 않는 보너스 번호를 생성.
  - `Stream.generate()`를 통해 무한한 숫자를 생성하고, filter()를 통해 중복되지 않는 숫자를 반환.

```java
public static int generateBonusNumber(List<Integer> excludedNumbers) {
    return Stream.generate(NumberGenerator::pickRandomNumber)
            .filter(number -> !excludedNumbers.contains(number))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(UNEXPECTED_ERROR_MESSAGE));
}
```

#### 2. `WinningNumbers` 클래스
- 보너스 번호를 추가로 관리
- `isBonusNumberMatched` 메서드를 통해 보너스 번호 일치 여부를 확인.
- `determineRank` 메서드를 통해 일치하는 숫자 개수와 보너스 번호 일치 여부를 바탕으로 Rank를 반환.

```java
public Rank determineRank(Lotto lotto) {
    int matchingCount = lotto.countMatchingNumbers(this);
    boolean isBonusNumberMatched = isBonusNumberMatched(lotto);

    return Rank.valueOf(matchingCount, isBonusNumberMatched);
}
```

#### 3. `Rank` 클래스
- 일치 숫자 개수와 보너스 번호 일치 여부를 기준으로 등수를 관리.
- `valueOf` 메소드에서 `isMatch` 메서드를 통해 일치하는 숫자 개수와 보너스 번호 일치 여부를 확인하고, Rank를 반환.

```java
public static Rank valueOf(int matchCount, boolean bonusMatch) {
    return Arrays.stream(Rank.values())
            .filter(rank -> rank.isMatch(matchCount, bonusMatch))
            .findFirst()
            .orElse(NO_MATCH);
}
```

#### 4. `RankMessageMapper` 클래스
- Rank에 따른 결과 메시지를 관리.
- `getMessage` 메서드를 통해 Rank에 따른 결과 메시지를 반환.

---

## 실행 방법

1. **필수 조건**: JDK 21 이상이 설치되어 있어야 합니다.
2. **모든 `.java` 파일을 동일한 디렉토리에 저장**합니다.
```bash
# 컴파일
javac *.java
   
#실행
java Application
```

---

## 프로그램 실행 예시

### 유효한 입력
```
1~45 중 로또 번호를 여섯개 입력하세요.
2,6,4,12,8,10
로또 당첨 숫자: 1, 3, 5, 7, 9, 11 + 보너스 숫자 13
플레이어 숫자: 2, 4, 6, 8, 10, 12
결과: 0개 일치. 낙첨입니다.
```

```
1~45 중 로또 번호를 여섯개 입력하세요.
1,2,3,4,5,6
로또 당첨 숫자: 1, 2, 3, 4, 5, 45 + 보너스 숫자 6
플레이어 숫자: 1, 2, 3, 4, 5, 6
결과: 5개 일치, 보너스 볼 일치. 2등 축하드립니다!
```

```
1~45 중 로또 번호를 여섯개 입력하세요.
1,2,3,4,5,6
로또 당첨 숫자: 1, 2, 3, 4, 5, 6 + 보너스 숫자 7
플레이어 숫자: 1, 2, 3, 4, 5, 6
결과: 6개 일치 1등 축하드립니다!
```

---


# 3단계

## 1. 목표

- 2단계에서 구현한 로또 게임에 추가로 플레이어 재산을 관리하고, 재산이 소진될 때까지 반복해서 게임을 진행하는 기능을 구현합니다.
- 각 게임마다 플레이어 재산 변동과 회차 정보를 출력하며, 당첨 여부에 따라 재산이 증가하도록 합니다.
- 당첨 금액과 회차를 포함한 게임 결과를 사용자에게 직관적으로 제공합니다.

---

## 2. 문제 분석

### 요구사항

1. 사용자로부터 **1부터 45 사이의 숫자 6개**를 입력받아야 합니다.
2. 입력값은 **쉼표(,)로 구분된 형식**이어야 합니다.
    - 예시: `1,2,3,4,5,6`
    - 숫자 사이에 공백이 있어도 무관.
3. 입력값에 대해 다음과 같은 검증을 수행해야 합니다:
    - 숫자의 개수가 정확히 6개인지 확인.
    - 숫자가 1부터 45 사이에 포함되는지 확인.
    - 중복된 숫자가 없는지 확인.
4. 잘못된 입력값이 들어올 경우:
    - 적절한 에러 메시지를 출력.
    - 에러 메시지 출력 후 재입력을 요청.
5. 당첨 번호를 **랜덤**으로 생성하고, 사용자 입력값과 비교하여 **일치하는 숫자 개수**를 출력합니다.
6. 출력시 모든 번호는 **오름차순으로 정렬**해서 출력


1. 재산 관리
   - 초기 재산은 **`10,000`**원으로 설정.  
   - 한 게임당 `1,000`원이 소진되며, **당첨 시 당첨금은 재산에 추가**.
   - 재산이 0원이 되면 게임이 종료.
2. 게임 반복
   - 재산이 있는 한 무한히 게임 진행 가능.
   - 각 게임마다 재산 변동, 회차 정보, 당첨 여부를 출력.
3. 당첨 금액
   - 1등: 1,000,000원
   - 2등: 100,000원
   - 3등: 10,000원
   - 4등: 5,000원
   - 5등: 1,000원
4. 출력 요구사항
   - 각 게임 시작 전, 현재 재산과 게임 비용을 출력.
   - 플레이어가 선택한 번호와 당첨 번호를 비교 후 당첨 결과 출력.
   - 당첨 금액과 변경된 재산을 표시.

---

## 3. 해결 과정

### 설계
- GameMoney: 플레이어 재산 관리.
- RoundManager: 현재 게임 회차를 관리.
- LottoGame: 게임의 주 흐름을 관리하며, 플레이어 재산이 0이 될 때까지 반복 실행. 

### 1단계: 재산 관리 추가

- **Problem**
    - 게임마다 1,000원씩 재산이 차감되고, 당첨 금액이 재산에 추가되어야 함.

- **Solve**
    - **`GameMoney` 클래스**에서 재산 차감 및 상금 추가 메서드 구현.
  
```java
public void deductGameCost() {
    balance -= GAME_COST;
}

public void addPrize(int prizeMoney) {
    balance += prizeMoney;
}
```

### 2단계: 게임 반복 로직 구현

- **Problem**
    - 재산이 0이 될 때까지 반복적으로 게임을 실행해야 함.
    - 매 회차별로 게임 결과를 출력해야 함.
    - 재산이 1000원 이상이면 게임을 계속 진행해야 함.

- **Solve**
  - **`LottoGame` 클래스**에서 `while (gameMoney.canPlay())` 구조로 게임 반복 로직 구현.
  - **`RoundManager` 클래스**에서 라운드 정보를 관리하고, 게임 회차를 증가시킴.
  - **`GameMoney` 클래스**에서 재산이 1000원 미만이면 게임 종료.

```java
// LottoGame.java
public void run() {
    while (gameMoney.canPlay()) {
        playRound();
    }

    inputView.close();
    outputView.printGameEndMessage();
}

// GameMoney.java
public boolean canPlay() {
    return balance >= GAME_COST;
}
```

### 3단계: 결과 반영 및 출력

- **Problem**
    - 어떤 번호가 **몇 개** 당첨 되었는지 출력해야 함.
    - **당첨 등수 및 상금 정보**를 출력해야 함.
    - 당첨 결과에 따라 **재산이 변동**되어야 함.

- **Solve**
    - **`Rank` 클래스**와 기존의 **`WinningNumbers` 클래스**를 활용해 당첨 여부 판별.
    - 기존 **`WinningNumbers` 클래스**에서 당첨 번호 개수만 세는것을 당첨된 숫자들을 반환하게 수정.
    - 기존 **`Rank` 클래스** 에서 등수별 Prize를 추가 후 반환하는 메소드 작성.
    - **`OutputView` 클래스**에서 게임 결과 메시지를 출력.

### 프로그램 흐름

1. 재산 관리:
    - GameMoney 클래스 생성시 기본 자산 10.000원으로 초기화.
2. 게임 반복:
    - LottoGame 클래스에서 while (gameMoney.canPlay()) 구조로 게임 반복 로직 구현.
    - 각 게임에서 회차를 증가시키고 결과를 출력.
    - 재산이 1000원 미만이면 게임 종료.
3. 결과 출력:
    - OutputView 클래스에서 게임 결과 메시지 출력.
    - 게임 결과에 따라 재산 변동을 반영.

---

## 4. 코드 설명

### 주요 클래스 및 메서드

#### 1. `LottoGame` 클래스
- 프로그램의 전체 흐름을 제어하는 역할을 수행.
- 각 라운드별 게임을 실행하고, 플레이어 재산이 1000원 미만이 될 때까지 반복 실행.

```java
public void run() {
    while (gameMoney.canPlay()) {
        playRound();
    }

    inputView.close();
    outputView.printGameEndMessage();
}

private void playRound() {
    outputView.printRoundInfo(roundManager, gameMoney);
    gameMoney.deductGameCost();

    Lotto playerLotto = getPlayerLotto();
    outputView.printPlayerNumbers(playerLotto);

    LottoResult lottoResult = calculateResult(playerLotto);
    processResult(lottoResult);

    roundManager.increaseRound();
}
```

#### 2. `GameMoney` 클래스

- 플레이어 재산을 관리하고, 게임 비용 차감 및 상금 추가 메서드를 제공.
- `canPlay` 메서드를 통해 재산이 1000원 이상인지 확인.
- `deductGameCost` 메서드를 통해 게임 비용 차감.
- `addPrize` 메서드를 통해 상금 추가.

#### 3. `RoundManager` 클래스

- 현재 게임 회차를 관리하고, 회차 정보를 출력.
- `increaseRound` 메서드를 통해 회차를 증가시키고, `toString` 메서드를 통해 회차 정보를 출력.

#### 4. `WinningNumbers` 클래스

- 기존 당첨 번호 생성 및 비교 로직을 수정하여 당첨된 숫자들을 반환.

```java
public List<Integer> findMatchingNumbers(Lotto lotto) {
    return winningNumbers.stream()
            .filter(lotto::contains)
            .collect(Collectors.toList());
}
```

#### 5. `MessageFormatter` 클래스

- 게임 결과 메시지를 포맷팅하여 출력.
- 기존에 **`OutputView` 클래스**가 담당하던 출력 메시지 포맷팅을 **`MessageFormatter` 클래스**로 분리.

#### 6. `LottoResult` 클래스

- 게임 결과를 담당하는 클래스.
- 기존에 **`WinningNumbers` 클래스**에서 Rank 판별 로직을 **`LottoResult` 클래스**로 이동.
- `determineRank` 메서드를 통해 Rank를 반환.

```java
public Rank determineRank() {
        int countOfMatch = matchingNumbers.size();

        return Rank.valueOf(countOfMatch, bonusMatch);
    }
```

---

## 실행 방법

1. **필수 조건**: JDK 21 이상이 설치되어 있어야 합니다.
2. **모든 `.java` 파일을 동일한 디렉토리에 저장**합니다.
```bash
# 컴파일
javac *.java
   
#실행
java Application
```

---

## 프로그램 실행 예시

### 유효한 입력
```
플레이어 재산: 10000원
1회차 게임(-1000)원
플레이어는 1~45 사이 로또 번호 여섯개를 선택하세요 (쉼표로 구분)
1,2,3,4,5,6
플레이어의 숫자: 1, 2, 3, 4, 5, 6
로또 당첨 숫자: 1, 2, 3, 4, 5, 6 + 보너스 숫자 7
당첨 번호 6개 일치!(1, 2, 3, 4, 5, 6)
1등 축하드립니다!!!! (+1000000)
플레이어 재산: 1009000원

플레이어 재산: 1009000원
2회차 게임(-1000)원
플레이어는 1~45 사이 로또 번호 여섯개를 선택하세요 (쉼표로 구분)
13,45,7,12,8,24
플레이어의 숫자: 7, 8, 12, 13, 24, 45
로또 당첨 숫자: 8, 12, 13, 24, 42, 45 + 보너스 숫자 7
당첨 번호 5개 일치!(8, 12, 13, 24, 45) + 보너스 볼 일치!
2등 축하드립니다! (+100000)
플레이어 재산: 1108000원

플레이어 재산: 1108000원
3회차 게임(-1000)원
플레이어는 1~45 사이 로또 번호 여섯개를 선택하세요 (쉼표로 구분)
3,41,23,45,12,8
플레이어의 숫자: 3, 8, 12, 23, 41, 45
로또 당첨 숫자: 3, 13, 15, 23, 30, 31 + 보너스 숫자 45
당첨 번호 2개 일치!(3, 23) + 보너스 볼 일치!
낙첨되었습니다. (+0)
플레이어 재산: 1107000원
```
