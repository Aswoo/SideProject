# SideProject
최신 기술 연습과 적용을 위해 만든 프로젝트

poke api를 사용해 포켓몬 정보를 얻어오고 북마크로 포켓몬을 지정

| ![image](https://github.com/Aswoo/SideProject/blob/main/image/setting.jpeg) | ![image](https://github.com/Aswoo/SideProject/blob/main/image/main.jpeg) | ![image](https://github.com/Aswoo/SideProject/blob/main/image/detail.jpeg) | ![image](https://github.com/Aswoo/SideProject/blob/main/image/bookmark.jpeg) |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |



## Development

### Required

- IDE : Android Studio Giraffe ([Download](https://developer.android.com/studio))
- JDK : Java 17을 실행할 수 있는 JDK
    - (권장) Android Studio 설치 시 Embeded 된 JDK (Open JDK)
    - Java 17을 사용하는 JDK (Open JDK, AdoptOpenJDK, GraalVM)
- Kotlin Language : 1.8.21

### Language
d
- Kotlin

### Libraries

- AndroidX
    - Activity & Activity Compose
    - AppCompat
    - Core
    - Lifecycle & ViewModel Compose
    - Navigation
- Kotlin Libraries (Coroutine, DateTime, Serialization)
- Compose
    - Material3
    - Navigation
- Coil
- Dagger & Hilt
- Square (Retrofit, OkHttp)

#### Test & Code analysis

- Compose Test(UI, Navigation)
- JUnit4
- Mockk

#### Gradle Dependency
- Gradle Version Catalog 를 활용하여 종속성과 플러그인을 관리

자세한 내용은 [libs.versions.toml](https://github.com/Aswoo/SideProject/blob/main/gradle/libs.versions.toml)

#### Dark Theme (TODO)

- Dark Mode 에 따른 UI 변화


### Architecture
- Layer
- 본 프로젝트는 Layered Architecture 형태 
- 현재 Presentation/Domain/Data 레이어 구조
- ![image](https://github.com/Aswoo/SideProject/blob/main/image/mvvm-diagram.webp)

### Module
- 본 프로젝트는 Multi-module 구조이며 각 Feature마다 모듈 형태로 구성
- ![image](https://github.com/Aswoo/SideProject/blob/main/project.dot.png)



