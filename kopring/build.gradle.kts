plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.scripting") version "1.9.25" // 스크립팅 플러그인 추가
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.kopring-back"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive") // 선택사항: 리액티브 프로그래밍을 사용하는 경우
//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

	implementation ("org.springframework.boot:spring-boot-starter-websocket")
	implementation ("org.springframework:spring-messaging")

	implementation ("org.springframework.kafka:spring-kafka")
	implementation ("com.google.code.gson:gson:2.9.0")



	compileOnly("org.projectlombok:lombok:1.18.28")
	annotationProcessor("org.projectlombok:lombok:1.18.28")

	testCompileOnly("org.projectlombok:lombok:1.18.28")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.28")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

	testImplementation("org.mockito:mockito-core:5.5.0")
	testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
