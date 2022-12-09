buildscript {

	repositories {
		mavenCentral()
	}

	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:2.4.13")
		classpath("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
	}
}

plugins {
	java
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
	group = "com.example"
	version = "0.0.1-SNAPSHOT"

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

subprojects {

	apply {
		plugin("java")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
	}

    repositories {
        mavenCentral()
    }

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		compileOnly("org.projectlombok:lombok")
		runtimeOnly("com.h2database:h2")
		annotationProcessor("org.projectlombok:lombok")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("io.projectreactor:reactor-test")
	}
}

//sub module 별로 기초 디렉토리가 존재하지 않으면 자동 생성
tasks {
	subprojects.stream()
	.flatMap { it.sourceSets.stream() }
	.flatMap { it.java.srcDirs.stream() }
	.filter { !it.exists() }
	.forEach { it.mkdirs() }
	
	subprojects.stream()
	.flatMap { it.sourceSets.stream() }
	.flatMap { it.resources.srcDirs.stream() }
	.filter { !it.exists() }
	.forEach { it.mkdirs() }
}
