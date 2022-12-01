plugins {
	java
	id("org.springframework.boot") version "2.7.6"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//sub module 별로 기초 디렉토리가 존재하지 않으면 자동 생성
tasks {
	subprojects.stream()
	.flatMap { it.sourceSets.stream() }
	.flatMap { it.java.srcDirs.stream() }
	.filter { !it.exists() }
	.forEash { it.mkdirs() }
	
	subprojects.stream()
	.flatMap { it.sourceSets.stream() }
	.flatMap { it.resources.srcDirs.stream() }
	.filter { !it.exists() }
	.forEach { it.mkdirs() }
}
