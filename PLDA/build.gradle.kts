import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.plda"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            // icon 설정
            macOS {
                iconFile.set(project.file("src/main/resources/plda.icns"))
            }
            windows {
                iconFile.set(project.file("src/main/resources/plda.ico"))
            }
            linux {
                iconFile.set(project.file("src/main/resources/plda.png"))
            }

            // 프로그램 이름 설정
            packageName = "PLDA"
            packageVersion = "1.0.0"
        }
    }
}
