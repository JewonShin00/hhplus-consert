### **STEP 15_기본**

#### **1. 수행한 쿼리 수집 및 분석**

- **주요 쿼리**: 콘서트 예약 시스템에서 사용된 쿼리 중 가장 빈번히 호출되는 **특정 콘서트의 특정 좌석 정보를 조회**하는 쿼리입니다.
  - **쿼리 내용**: `SELECT * FROM seat WHERE concert_concert_id = :concertId AND seat_number = :seatNumber`
  - **쿼리의 빈도**: 이 쿼리는 좌석 예약, 예약 취소 시에 사용되며 매우 빈번하게 호출되는 쿼리 중 하나입니다.

#### **2. 인덱스 추가 전후 쿼리 성능 테스트 및 비교**
##### **2-0. 작업 전 테스트를 위한 데이터 생성**
![1 테스트를 위한 데이터 생성_1](https://github.com/user-attachments/assets/367ac633-4ed6-4ca8-93fd-cc2a292399ca)

##### **2-1. 인덱스 추가 전 성능 테스트 결과**
- **테스트 방식**:
  - JMeter를 이용하여 **1000개의 요청**을 동시에 보내어 쿼리 성능을 측정했습니다.
  - JMeter를 이용한 측정 시 좌석 예약 기능에서 조회만 담당하는 기능을 따로 생성해서 테스트했습니다.
  - 또한 MySQL에서 직접 쿼리를 실행하고, `performance_schema`를 통해 쿼리 실행 시간을 확인했습니다.(DBeaver)

- **JMeter 성능 테스트 결과**:
  
![인덱스적용전_1000건 테스트](https://github.com/user-attachments/assets/d74bdc60-3021-400e-af32-97fc9cd934b4)

  - **평균 응답 시간**: **190 ms**
  - **95% 응답 시간**: **500 ms**
  - **최대 응답 시간**: **822 ms**
  - **처리량(Throughput)**: **937.2/sec**

- **직접 쿼리 실행 결과**:
  
![인덱스적용전_직접쿼리테스트](https://github.com/user-attachments/assets/9c1598d4-61d9-4d09-a08e-577f582d2735)

  - **쿼리 실행 시간**: **0.9457 ms**

##### **2-2. 인덱스 추가 및 성능 테스트 결과**

- **추가한 인덱스**:
  - **concert_concert_id**에 대한 단일 인덱스
  - **seat_number**에 대한 단일 인덱스
  - **concert_concert_id와 seat_number**에 대한 복합 인덱스

- **JMeter 성능 테스트 결과 비교**:
  1. **concert_concert_id 인덱스 추가 후**:
![인덱스적용후_1000건 테스트_concert_id](https://github.com/user-attachments/assets/0768e415-7adc-451d-813d-26e15ae6e4c5)

    - **평균 응답 시간**: **20 ms**
    - **95% 응답 시간**: **74 ms**
    - **최대 응답 시간**: **191 ms**
    - **처리량(Throughput)**: **1356.9/sec**

  2. **seat_number 인덱스 추가 후**:
![인덱스적용후_1000건 테스트_seat_number](https://github.com/user-attachments/assets/c8da15f5-0013-4ef6-bf98-2d9f402e1dcf)

    - **평균 응답 시간**: **15 ms**
    - **95% 응답 시간**: **53 ms**
    - **최대 응답 시간**: **194 ms**
    - **처리량(Throughput)**: **1579.8/sec**

  3. **복합 인덱스 추가 후**:
![인덱스적용후_1000건 테스트_복합](https://github.com/user-attachments/assets/19ff03a9-4a67-4a9c-8c4d-0578edbb11e9)

    - **평균 응답 시간**: **35 ms**
    - **95% 응답 시간**: **119 ms**
    - **최대 응답 시간**: **317 ms**
    - **처리량(Throughput)**: **1557.6/sec**

- **직접 쿼리 실행 테스트 결과 비교**:
  1. **concert_concert_id 인덱스 추가 후**:
     
![인덱스적용후_직접쿼리테스트_concert_id](https://github.com/user-attachments/assets/324d6807-c848-466e-9bbe-7c39ff1e3530)

    - **쿼리 실행 시간**: **0.9457 ms**
  2. **seat_number 인덱스 추가 후**:
      
![인덱스적용후_직접쿼리테스트_seat_number](https://github.com/user-attachments/assets/06f51603-1cf6-4b3a-a45d-2277caaa926a)

    - **쿼리 실행 시간**: **0.9457 ms**
  3. **복합 인덱스 추가 후**:
      
![인덱스적용후_직접쿼리테스트_복합](https://github.com/user-attachments/assets/17e0baad-d7e3-4051-8f4d-6c9df77c1b3a)

    - **쿼리 실행 시간**: **0.9457 ms**

##### **2-3. 결론 및 개선 사항**
- **JMeter 성능 테스트**에서 **인덱스 추가 후 응답 시간이 크게 개선**되었습니다. 특히 `concert_concert_id`와 `seat_number`에 각각 인덱스를 추가했을 때, 평균 응답 시간과 최대 응답 시간이 크게 줄어들었음을 확인할 수 있었습니다.
- **복합 인덱스**의 경우에도 성능이 개선되었으나, 단일 인덱스보다 더 나은 결과를 보여주지는 않았습니다. 이는 데이터의 크기와 쿼리의 특성에 따라 복합 인덱스의 이점이 충분히 발휘되지 않은 것으로 보입니다.
- **직접 쿼리 테스트**의 경우, **인덱스 추가 전후의 쿼리 실행 시간이 동일**했습니다. 이는 테스트 데이터의 양 1건으로 적었기 때문으로 추정됩니다.
- Redis, cache 등 적용하지 못한게 많은 상황에서 이루어진 테스트라 성능 최적화가 이루어진 환경에서의 실제 성능을 온전히 반영하지 않을 수 있으며, 실제 시스템에 Redis와 같은 캐시를 적용할 경우 좀 더 나은 성능이 기대되기도 합니다.
