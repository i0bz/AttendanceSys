val groupID = "i0bz"
val versionID = "1.1"

group = groupID
version = versionID

plugins {
    id("java")
    id("application")
}


//Plugins
application {
    mainClass.set("Main")
    applicationDefaultJvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}




repositories {
    mavenCentral()
}
dependencies {
    implementation("com.formdev:flatlaf:3.7")
    implementation("com.formdev:flatlaf-extras:3.7")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}



//Tasks
tasks.jar {
    manifest {
        attributes["Main-Class"] = "Main"
    }
}
tasks.register<JavaExec>("runRecentBuild") {
    val jarFile = tasks.jar.get().archiveFile.get().asFile

    if (!jarFile.exists()) {
        dependsOn(tasks.jar)
    }

    classpath = files(jarFile) + sourceSets.main.get().runtimeClasspath
    group = "Custom"

    mainClass.set("Main")
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}
tasks.test {
    useJUnitPlatform()
}