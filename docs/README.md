# 🎄크리스마스 프로모션

## ⚙️기능 목록
- [ ] 🔣**공통 입력**
    - [ ] `공백`을 없앤다.
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `빈 값이 들어오면` ERROR 발생 후, 다시 입력 받는다.
- [ ] 🗓️**날짜**
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `숫자가 아니라면` ERROR 발생 후, 다시 입력 받는다.
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `음수라면` ERROR 발생 후, 다시 입력 받는다.
    - [x] <span style="color: #FC8C8C">**[예외]**</span> `해당 월의 날짜를 벗어나면` ERROR 발생 후, 다시 입력 받는다.
    - [ ] `해당 날짜에 맞게` 이벤트를 반환한다.
- [ ] 🥘**메뉴**
    - [ ] `입력된 메뉴`를 생성한다.
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `메뉴의 예시와 맞지 않는다면` ERROR 발생 후, 다시 입력 받는다.
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `메뉴의 갯수가 1미만이거나 숫자가 아니라면` ERROR 발생 후, 다시 입력 받는다.
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `없는 메뉴를 입력한다면` ERROR 발생 후, 다시 입력 받는다.
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `음료만 주문한 경우` ERROR 발생 후, 다시 입력 받는다.
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `중복된 메뉴가 입력된다면` ERROR 발생 후, 다시 입력 받는다.
    - [ ] <span style="color: #FC8C8C">**[예외]**</span> `메뉴가 20개가 넘는다면` ERROR 발생 후, 다시 입력 받는다.
    - [ ] 주문한 메뉴의 `총 가격`을 계산한다.
    - [ ] 추문한 메뉴를 `출력`한다.
    - [ ] 총주문 금액을 `계산`한다.
        - [ ] 총주문 금액을 `출력`한다. 
- [ ] 🎈**이벤트**
    - [ ] 총주문 금액이 `10,000원 이상인지` 확인한다.
    - [ ] 입력 받은 날짜가 `이벤트 날짜에 포함되는지` 확인한다.
        - [ ] 총주문 금액이 `10,000원 이상이면서 이벤트 날짜에 포함된다면` 이벤트를 적용한다. 
            - [ ] **크리스마스 디데이 할인**
                - [ ] `날짜에 맞게 100원씩 증감된 할인 금액`을 계산한다.
            - [ ] **평일 할인**
                - [x] 입력 받은 날짜가 `평일`인지 확인한다.
                - [ ] 입력 받은 날짜가 `평일`이라면 `디저트 메뉴 1개당 2023원 할인`을 적용한다.
            - [ ] **주말 할인**
                - [x] 입력 받은 날짜가 `주말`인지 확인한다.
                - [ ] 입력 받은 날짜가 `주말`이라면 `메인 메뉴 1개당 2023원 할인`을 적용한다.
            - [ ] **특별 할인**
                - [x] 입력 받은 날짜에 `별`이 있는지 확인한다.
                - [ ] 입력 받은 날짜에 `별`이 있다면 `총 주문금액에서 1000원 할인`을 적용한다.
            - [ ] **증정 이벤트**
                - [ ] 할인 전 총주문 금액이 `120,000원 이상이라면` `샴페인 1개`를 증정한다.
                - [ ] 증정 메뉴 출력 
                    - [ ] 증정 이벤트에 해당하지 않을 경우 `'없음'`을 표시한다.
- [ ] 🎉**혜택**
    - [ ] **할인**
      - [ ] 할인된 내역을 `출력`한다. 
      - [ ] 할인 후 예상 결제 금액을 `계산`한다.
          - [ ] 증정 이벤트 할인 금액은 `계산에서만` 제외한다.
          - [ ] 할인 후 예상 결제 금액을 `출력`한다.
    - [ ] **배지** 
       - [ ] `총혜택 금액` = `할인 금액의 합계` + `증정 메뉴의 가격`으로 계산한다.
       - [ ] 총혜택 금액이 `5,000원 이상`이라면 `별 배지`를 부여한다.
       - [ ] 총혜택 금액이 `10,000원 이상`이라면 `트리 배지`를 부여한다.
       - [ ] 총혜택 금액이 `20,000원 이상`이라면 `산타 배지`를 부여한다.
       - [ ] 이벤트 배지를 `출력`한다.
           - [ ] 이벤트 배지가 `부여되지 않는 경우 `'없음'`을 표시한다.
- [ ] **달력**
    - [x] 별이 있는 날짜를 셋팅한다.