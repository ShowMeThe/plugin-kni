import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

val jniLibDir = File(project.buildDir, arrayOf("generated", "jniLibs").joinToString(File.separator))

kotlin {

    androidNativeArm32 {
        binaries {
            sharedLib("KSharelib") {
                if(buildType == NativeBuildType.DEBUG){
                    linkTask.doLast {
                        copy {
                            from(outputFile)
                            into(File(jniLibDir, "armeabi-v7a"))
                        }
                    }

                    afterEvaluate {
                        val preReleaseBuild by tasks.getting{

                        }
                        preReleaseBuild.dependsOn(linkTask)
                    }
                }
            }
        }
    }
}

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)

        ndk {
            abiFilters("armeabi-v7a")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        val main by getting {
            jniLibs.srcDir(jniLibDir)
        }
    }
}
