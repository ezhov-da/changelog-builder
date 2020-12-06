plugins {
    id("java-gradle-plugin")
}
description = "Changelog Builder Gradle Plugin"
group = "ru.ezhov"
version = "0.0.2"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("ru.ezhov:changelog-builder-engine:0.0.2")

    testImplementation("junit:junit:4.13")
}

gradlePlugin {
    val builder by plugins.creating {
        id = "ru.ezhov.changelog.builder.gradle.plugin"
        displayName = "Changelog Builder Gradle Plugin"
        description = "Changelog Builder Gradle Plugin"
        implementationClass = "ru.ezhov.changelog.builder.gradle.plugin.ChangelogBuilderPlugin"
    }
}

// Add a source set for the functional test suite
val functionalTestSourceSet = sourceSets.create("functionalTest") {
}

gradlePlugin.testSourceSets(functionalTestSourceSet)
configurations.getByName("functionalTestImplementation").extendsFrom(configurations.getByName("testImplementation"))

tasks.getByName("jar", Jar::class){
    baseName = "changelog-builder-gradle-plugin"
}

// Add a task to run the functional tests
val functionalTest by tasks.registering(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
}

val check by tasks.getting(Task::class) {
    // Run the functional tests as part of `check`
    dependsOn(functionalTest)
}
