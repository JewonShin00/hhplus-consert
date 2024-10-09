## <항해 플러 백엔드 6기 과정_콘서트 예약 서비스>
-------------------------------------------------
## 선정한 시나리오 : 콘서트 예약 서비스
- 마일스톤 : [콘서트 예약 마일스톤 보기](https://github.com/users/JewonShin00/projects/2)

## [콘서트 예약 서비스 요구사항 분석]
## 서비스 개요
- 사용자는 콘서트 목록을 조회하고, 원하는 좌석을 선택하여 예약을 완료할 수 있는 서비스.
- 시스템은 좌석의 중복 예약을 방지하고, 결제 과정에서 안정성을 보장해야 함.

## 사용자 요구사항
1. 사용자는 콘서트 목록을 조회할 수 있어야 한다.
   - 시스템은 예약 가능한 콘서트 목록과 세부 정보를 제공해야 한다.
2. 사용자는 원하는 콘서트의 좌석을 선택할 수 있어야 한다.
   - 시스템은 선택한 좌석이 예약 가능한 상태인지 확인해야 한다.
3. 사용자는 선택한 좌석을 결제할 수 있어야 한다.
   - 시스템은 결제 요청을 처리하고, 결제 결과에 따라 예약을 확정하거나 취소해야 한다.
4. 사용자는 자신의 예약 내역을 조회할 수 있어야 한다.
   - 시스템은 사용자의 예약 내역을 데이터베이스에서 조회해 반환해야 한다.
5. 사용자는 필요 시 예약을 취소할 수 있어야 한다.
   - 시스템은 취소 요청을 처리하고, 예약 상태를 업데이트해야 한다.

## 비즈니스 규칙
1. 좌석 중복 예약 방지: 사용자가 좌석을 선택할 때, 다른 사용자가 이미 예약한 좌석은 선택할 수 없어야 함.
2. 결제 실패 시 예약 취소: 결제 중 오류가 발생할 경우, 해당 예약을 자동으로 취소해야 함.
3. 결제 완료 시 좌석 예약 확정: 결제가 완료되면, 좌석의 예약 상태를 확정 상태로 변경해야 함.
4. 예약 가능한 좌석 수 제한: 각 콘서트는 예약 가능한 좌석 수가 정해져 있으며, 이 수를 초과할 수 없음.
5. 대기열 시스템 적용: 특정 콘서트에 다수의 예약 요청이 들어올 경우, 대기열을 통해 순차적으로 처리.

## 시퀀스 다이어그램
- 시퀀스 다이어그램 : [콘서트 예약 시퀀스 다이어그램 보기](https://www.mermaidchart.com/raw/060b5af4-3762-427d-9113-69b91df6a996?theme=light&version=v0.1&format=svg)

## 플로우차트
- 플로우차트 : [콘서트 예약 플로우차트 보기](https://www.mermaidchart.com/raw/9baf529a-5848-4efe-97f5-6dfb5c29c068?theme=light&version=v0.1&format=svg)
