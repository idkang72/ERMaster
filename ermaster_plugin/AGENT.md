# ERMaster Eclipse Plugin - AGENT.md

## 1. 프로젝트 개요 (Project Overview)
**ERMaster**는 Eclipse IDE 환경에서 동작하는 직관적이고 강력한 ERD(Entity-Relationship Diagram) 모델링 플러그인입니다.
테이블, 뷰, 시퀀스, 인덱스 등 데이터베이스 모델링 기능과 함께 DDL 생성, DDL 유효성 검증, Excel/HTML/이미지/Java Entity 내보내기, JDBC 기반 역공학(Reverse Engineering), 테스트 데이터 자동 생성 등의 다양한 기능을 제공합니다.

본 프로젝트는 원작자의 소스 및 `takahiro40264`의 개량 버전을 Fork하여, **최신 Eclipse 환경(Eclipse 2025-12R 이상) 및 Java 21 런타임**에서 안정적으로 빌드되고 동작하도록 오류 수정 및 의존성 개량이 적용된 버전입니다.

---

## 2. 기술 스택 및 빌드 환경 (Tech Stack & Environment)

| 구분 | 기술 / 도구 | 비고 |
| :--- | :--- | :--- |
| **Java Version** | Java SE 21 | `Bundle-RequiredExecutionEnvironment: JavaSE-21` |
| **빌드 도구** | Apache Maven 3.x, Eclipse Tycho 4.0.5 | OSGi Bundle / Eclipse Plugin 빌드 |
| **Eclipse Platform** | Eclipse 2024-06 (Target Platform) / 2025-12R 호환 | PDE (Plugin Development Environment) |
| **UI / 그래픽 프레임워크** | Eclipse GEF (Graphical Editing Framework), Draw2D, SWT, JFace | MVC 기반 다이어그램 에디터 |
| **외부 라이브러리** | Apache POI 4.1.2, xmlbeans 3.1.0, commons-collections4, orai18n 등 | Excel 내보내기/가져오기, Oracle i18n 등 |
| **파일 포맷** | `.erm` (XML 기반 ER 다이어그램 영속화 포맷) | |

---

## 3. 전체 프로젝트 구조 (Project Modules)

전체 저장소는 Eclipse 플러그인 표준 멀티 프로젝트 형태로 구성되어 있습니다.

```text
ERMaster/
├── pom.xml                     # 루트 Maven/Tycho 빌드 설정 (Parent POM)
├── README.md                   # 프로젝트 소개 및 최신 환경 수정 안내
├── ermaster_plugin/            # [핵심] ERMaster 플러그인 본체 소스코드
│   ├── META-INF/MANIFEST.MF    # OSGi 번들 메타데이터 및 클래스패스/패키지 Export 정의
│   ├── plugin.xml              # Eclipse 확장 포인트(Extensions) 및 에디터/마법사/환경설정 등록
│   ├── build.properties        # 플러그인 빌드 리소스 포함 규칙
│   ├── pom.xml                 # Tycho 플러그인 빌드 설정
│   ├── update_lib_and_manifest.sh # lib/*.jar 종속성 갱신 및 MANIFEST.MF 동기화 스크립트
│   ├── icons/                  # 다이어그램, 툴바, 팔레트용 아이콘 리소스
│   ├── images/                 # 문서/보고서용 이미지 리소스
│   ├── lib/                    # 번들에 임베드된 외부 JAR 라이브러리
│   ├── schema/                 # 플러그인 확장 포인트 스키마 (PopupMenu.exsd)
│   └── src/
│       ├── main/java/          # Java 소스 코드 (GEF MVC, DB Dialect, 다이얼로그 등)
│       └── main/resources/     # HTML 템플릿, SQL 타입 정의(SqlType.xlsx) 등
├── ermaster_feature/           # Eclipse Feature 정의 프로젝트 (feature.xml)
└── ermaster_site/              # Eclipse P2 Update Site (업데이트 사이트 배포용 정적 파일)
```

---

## 4. 소스코드 패키지 아키텍처 (`ermaster_plugin/src/main/java`)

플러그인 코드는 역할에 따라 명확히 분리된 패키지 구조와 Eclipse GEF의 **MVC(Model-View-Controller)** 아키텍처를 따릅니다.

```text
ermaster/
├── ERDiagramActivator.java     # 플러그인 생명주기 관리, 싱글톤 인스턴스, 색상/폰트/리소스 캐시 관리
├── Resources.java / ImageKey   # 플러그인 표준 색상(PK/FK, 그리드, 변경 추적 등), 폰트, 이미지 키 상수 정의
├── ResourceString.java         # 다국어(i18n) 번들 로더 (기본/일본어/한국어 지원)
│
├── common/                     # 공통 UI 컴포넌트 및 유틸
│   ├── dialog/                 # 공통 대화상자 베이스 (AbstractDialog, ValidatableTabWrapper)
│   ├── widgets/                # SWT 커스텀 위젯 (MultiLineText, Spinner, FileText, RowHeaderTable 등)
│   └── exception/              # 비즈니스/입출력 예외 클래스
│
├── db/                         # 다중 DBMS 방언(Dialect) 지원 모듈
│   ├── DBManager.java          # DBMS별 DDL 생성, 데이터타입 매핑, 문법 추상 인터페이스
│   ├── DBManagerFactory.java   # 지원 DB 식별자 기반 매니저 팩토리
│   ├── sqltype/                # 논리 타입과 물리 SQL 타입 매핑 (SqlType, SqlTypeFactory)
│   └── impl/                   # 각 DBMS별 구현체
│       ├── mysql / oracle / postgres / sqlserver / sqlserver2008 / db2
│       ├── h2 / hsqldb / sqlite / access / standard_sql
│       └── */tablespace        # 각 DBMS별 테이블스페이스 DDL 문법 지원
│
├── editor/                     # GEF 기반 ER 다이어그램 에디터 메인 모듈
│   ├── ERDiagramMultiPageEditor.java # 탭 기반 다중 페이지 에디터 (ERD 캔버스 + DDL 뷰어)
│   ├── ERDiagramEditor.java    # GEF GraphicalEditor 본체 (팔레트, 줌, 마우스 인터랙션)
│   │
│   ├── model/                  # [Model] 도메인 모델
│   │   ├── diagram_contents/   # 다이어그램 내용 요소
│   │   │   ├── element/node/   # Table, View, Note, Category, ModelProperties, ImageElement
│   │   │   ├── element/connection/ # Relation(1:1, 1:N, N:M), ConnectionElement, Bendpoint
│   │   │   └── not_element/    # Dictionary(단어 사전), Group(컬럼 그룹), Sequence, Index, Trigger, Tablespace
│   │   ├── dbexport/           # 내보내기 엔진 (DDL 생성/검증, Excel, HTML, Image, Java Entity, TestData)
│   │   ├── dbimport/           # JDBC 메타데이터 기반 역공학(Reverse Engineering) 임포트 엔진
│   │   ├── settings/           # 다이어그램 설정 모델 (페이지, 표기법, DB 종류 등)
│   │   ├── tracking/           # 변경 이력 추적(Diff/Change History) 모델
│   │   └── search/             # 다이어그램 내 테이블/컬럼/노트 검색 모델
│   │
│   ├── controller/             # [Controller] GEF EditPart, Command, EditPolicy
│   │   ├── editpart/           # 모델-뷰 매핑 컨트롤러 (ERDiagramEditPart, TableEditPart, RelationEditPart 등)
│   │   ├── command/            # 실행 취소/다시 실행(Undo/Redo) 지원 GEF Command 구현체들
│   │   └── editpolicy/         # 사용자 인터랙션(드래그, 리사이즈, 삭제, 관계 연결) 정책
│   │
│   ├── view/                   # [View] Draw2D Figure 및 SWT 대화상자/액션
│   │   ├── figure/             # Draw2D 렌더링 컴포넌트 (TableFigure, RelationFigure, IE/IDEF1X 표기법 데코레이션)
│   │   ├── dialog/             # 상세 설정 SWT 대화상자 (테이블/컬럼/뷰/인덱스/관계/내보내기 설정)
│   │   ├── action/             # 에디터 메뉴 및 툴바 액션 (DDL/Excel 내보내기, 정렬, 줌, 표기법 전환)
│   │   ├── outline/            # 에디터 개요(Outline) 트리 뷰
│   │   └── tool/               # 팔레트 도구 생성기
│   │
│   └── persistent/             # [.erm 영속화] XML 기반 다이어그램 저장 및 로드
│       └── impl/PersistentXmlImpl.java # XML 파싱 및 직렬화 구현체
│
├── extention/                  # 플러그인 확장 포인트 정의 (PopupMenu 등)
├── preference/                 # Eclipse 환경설정 페이지 (JDBC 드라이버, 번역/사전, 템플릿 등)
├── util/                       # 입출력, 그래픽, 포맷팅 공통 유틸리티
└── wizard/                     # 새 ERD 생성 마법사 (NewDiagramWizard)
```

---

## 5. 주요 기능 및 컴포넌트 흐름 (Core Features & Flow)

1. **다이어그램 렌더링 및 편집 (GEF MVC)**
   - 사용자가 팔레트나 툴바에서 테이블/관계선을 생성하거나 편집하면, `EditPolicy`를 거쳐 `Command`가 생성되고 `CommandStack`을 통해 실행되어 Undo/Redo를 완벽히 지원합니다.
   - `TableFigure`, `RelationFigure`는 IE 표기법(Crow's Foot) 및 IDEF1X 표기법을 실시간으로 렌더링합니다.

2. **다중 DBMS 지원 및 DDL 생성/검증**
   - 논리명/물리명 분리 관리 및 표준 단어 사전(`Dictionary`) 기능 제공.
   - 대상 DB(`DBManager`) 변경 시 데이터 타입 자동 변환 및 DBMS 특화 문법(시퀀스, Auto Increment, 인덱스 옵션 등) 자동 반영.
   - DDL 내보내기 전 유효성 검사기(`DDLValidator`)를 통해 PK 누락, 타입 불일치, 중복 명칭 등을 사전 검증.

3. **데이터 명세서 및 소스 내보내기 (Export Engine)**
   - **Excel**: Apache POI를 활용하여 시트별 테이블 정의서, 인덱스 목록, 변경 이력 등을 포함한 정밀한 엑셀 문서 생성.
   - **HTML**: 내장 HTML 템플릿 기반의 웹 브라우저용 ERD 명세 보고서 생성.
   - **Java**: 엔티티 클래스 소스코드 자동 생성.
   - **Image**: PNG, JPEG, SVG 등 고해상도 다이어그램 이미지 출력.

4. **DB 역공학 (Reverse Engineering Import)**
   - JDBC 드라이버 연결을 통해 기존 데이터베이스의 테이블, 컬럼, 외래키, 인덱스를 조회하여 `.erm` 다이어그램으로 자동 복원.

---

## 6. 최신 개선 및 호환성 작업 내역

1. **Java 21 및 최신 Eclipse Target Platform 적용**:
   - `MANIFEST.MF`의 `Bundle-RequiredExecutionEnvironment: JavaSE-21`로 상향.
   - Tycho `4.0.5` 및 Eclipse `2024-06` 릴리스 저장소 P2 연동.
2. **에디터 테이블 복사(Copy) 오류 수정**:
   - `CopyAction.java`: `calculateEnabled()`에서 선택된 `EditPart` 목록 누락으로 인해 복사가 비활성화되던 버그 수정.
3. **MariaDB 정식 DBMS 지원 추가**:
   - `MariaDBDBManager`, `MariaDBEclipseDBManager` 추가 (`DBManagerFactory`, `EclipseDBManagerFactory` 등록).
   - MariaDB 전용 시퀀스(`CREATE SEQUENCE`) DDL 생성 지원 (`SUPPORT_SEQUENCE` 활성화) 및 전용 드라이버(`org.mariadb.jdbc.Driver`) 연동.
4. **DBMS별 최신 자료형 대폭 보강 (`SqlType.xlsx`)**:
   - **JSON / JSONB**: MySQL, MariaDB, PostgreSQL, Oracle 21c/23c+ 지원.
   - **UUID**: MariaDB 10.7+, PostgreSQL, H2, SQL Server(`uniqueidentifier`) 지원.
   - **공간(Spatial/GIS) 데이터 타입**: `GEOMETRY`, `POINT`, `LINESTRING`, `POLYGON`, `MULTIPOINT`, `GEOMETRYCOLLECTION`, `GEOGRAPHY` 지원 (MySQL, MariaDB, PostgreSQL, SQL Server).
   - **네트워크 주소 타입**: `INET4`, `INET6` (MariaDB 10.10+), `INET` (PostgreSQL).
   - **Boolean**: Oracle 23c+ `BOOLEAN`, MySQL/MariaDB `BOOLEAN` 지원.
5. **SWT Image Resource 안전한 Dispose 처리**:
   - `ChangeBackgroundColorAction.java`: 에디터 닫기/색상 변경 시 이미 dispose된 이미지 객체에 재접근하여 발생하는 SWT 예외 방지 (`!image.isDisposed()` 검증 추가).
6. **구버전 `.erm` 파일 로드 호환성 강화**:
   - `ModelPropertiesFigure.java`: 이전 버전에서 생성일시/갱신일시 속성이 누락된 구형 `.erm` 파일을 열 때 발생하는 Null Pointer 오류 방어 로직 추가.
7. **UI 편의성 개선**:
   - `SqlTabWrapper.java`: View 편집 대화상자의 SQL 편집창에 Eclipse 기본 고정폭(Monospace) 글꼴을 적용하여 가독성 개선.
8. **Oracle i18n 라이브러리 추가**:
   - Oracle DB 접속 및 다국어 인코딩 지원을 위한 `orai18n.jar` 종속성 추가.

---

## 7. 빌드 및 배포 방법 (Build & Packaging)

### Maven 빌드
프로젝트 루트 또는 `ermaster_plugin` 디렉터리에서 Tycho 기반으로 빌드합니다.

```bash
# 전체 프로젝트 빌드
mvn clean package

# 플러그인 모듈 단독 빌드
mvn clean package -f ermaster_plugin/pom.xml
```

빌드 결과물:
- 플러그인 JAR: `ermaster_plugin/target/ermaster_plugin-1.0.0.jar`
- P2 Update Site 저장소: `ermaster_site/` (GitHub Pages 등을 통해 Update Site로 호스팅 가능)

### 외부 라이브러리 갱신 시 주의사항
Maven 의존성 변경 시 OSGi 번들 클래스패스와 동기화해야 합니다.
`ermaster_plugin/update_lib_and_manifest.sh` 스크립트를 사용하여 `lib/` 폴더로 JAR를 복사하고 `META-INF/MANIFEST.MF`를 갱신할 수 있습니다.

---

## 8. 개발 규칙 및 컨벤션 (Development Guidelines)

1. **원인 중심의 핀포인트 수정**:
   - 오류 수정 시 근본 원인이 되는 지점만을 간결하게 수정하며, 불필요한 레이어 우회나 자의적인 다중 방어책을 추가하지 않습니다.
2. **Java 코드 포매팅 규칙 준수**:
   - 들여쓰기: **Tab 문자** 사용 (Space 금지).
   - 중괄호: K&R 스타일 (선언문과 같은 줄에 `{`, `else`/`catch`는 `}` 다음 줄).
   - 공백: 메서드 간 2줄 공백, `!` 뒤 1칸 공백 (`if (! condition)`).
3. **리소스 메모리 관리**:
   - SWT `Color`, `Font`, `Image` 등의 OS 리소스 생성/소멸 시 `Resources` 캐시 또는 `isDisposed()` 상태를 반드시 확인하여 메모리 누수 및 Crash 방지.
