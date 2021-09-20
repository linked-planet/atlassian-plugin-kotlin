import org.gradle.util.GradleVersion
import org.jetbrains.kotlin.gradle.targets.js.nodejs.*

println("Gradle Version: " + GradleVersion.current().toString())
println("Java Version: " + JavaVersion.current().toString())
val ciProfile = if (ext.has("ci")) (ext.get("ci") as String).toBoolean() else false
val kotlinVersion = "1.5.31"

group = "${groupId}"
version = "0.1.0-SNAPSHOT"

plugins {
    kotlin("js") version "1.5.31"
}

kotlin {
    js {
        useCommonJs()
        browser {
            compilations.all {
                compileKotlinTask.kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.RequiresOptIn")
            }
            runTask {
                devServer = devServer?.copy(
                    // frontend is embedded, so no point in opening a separate browser window
                    open = false
                )
            }
            webpackTask {
                outputFileName = "${project.name}.js"
            }
            dceTask {
                // list exported, unused functions to protect from dead-code-elimination
                keep(
                    "frontend.start${frontendAppNameUpperCamelCase}", "frontend.stop${frontendAppNameUpperCamelCase}"
                )
            }
        }
        binaries.executable()
    }
}

val kotlinWrapperVersion = "pre.247-kotlin-$kotlinVersion"
val reduxVersion = "4.0.5"
val reactVersion = "17.0.2"
val reactReduxVersion = "7.2.3"
dependencies {
    implementation(kotlin("stdlib-js", kotlinVersion))

    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core-js", "1.1.1")
    implementation("org.jetbrains.kotlinx", "kotlinx-html-js", "0.7.3")
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-extensions", "1.0.1-$kotlinWrapperVersion")
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-react", "$reactVersion-$kotlinWrapperVersion")
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-react-dom", "$reactVersion-$kotlinWrapperVersion")
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-redux", "$reduxVersion-$kotlinWrapperVersion")
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-react-redux", "$reactReduxVersion-$kotlinWrapperVersion")

    // React
    implementation(npm("react", reactVersion))
    implementation(npm("react-dom", reactVersion))
    implementation(npm("core-js", "3"))

    // Atlaskit
    implementation(npm("@atlaskit/banner", "^11.2.2"))
    implementation(npm("@atlaskit/button", "^13.0.1"))
    implementation(npm("@atlaskit/checkbox", "^7.0.0"))
    implementation(npm("@atlaskit/dropdown-menu", "10.0.0"))
    implementation(npm("@atlaskit/flag", "^11.0.0"))
    implementation(npm("@atlaskit/icon", "^17.0.1"))
    implementation(npm("@atlaskit/textfield", "^2.0.0"))
    implementation(npm("@atlaskit/textarea", "^2.0.0"))
    implementation(npm("@atlaskit/select", "^9.0.1"))
    implementation(npm("@atlaskit/modal-dialog", "^9.0.0"))
    implementation(npm("@atlaskit/table-tree", "^8.0.5"))
    implementation(npm("@atlaskit/dynamic-table", "^13.7.7"))
    implementation(npm("@atlaskit/atlassian-navigation", "^0.10.13"))
    implementation(npm("@atlaskit/inline-edit", "^10.0.33"))
    implementation(npm("@atlaskit/logo", "^12.3.5"))
    implementation(npm("@atlaskit/page-layout", "0.8.0"))
    implementation(npm("@atlaskit/avatar", "19.0.0"))
    implementation(npm("@atlaskit/lozenge", "10.0.1"))
    implementation(npm("@atlaskit/popup", "0.6.0"))
    implementation(npm("@atlaskit/menu", "0.5.0"))
    implementation(npm("styled-components", "^3.5.0-0"))
    implementation(npm("@fortawesome/fontawesome-free", "^5.15.1"))

    // Redux
    implementation(npm("redux", reduxVersion))
    implementation(npm("react-redux", reactReduxVersion))

    // Misc
    implementation(npm("uuid", "^3.3.2"))

    implementation(devNpm("style-loader", "2.0.0"))
    implementation(devNpm("css-loader", "3.4.2"))
    implementation(devNpm("sass-loader", "10.1.0"))
    implementation(devNpm("node-sass", "4.14.1"))
    implementation(devNpm("file-loader", "6.2.0"))
    implementation(devNpm("@babel/core", "7.12.9"))
}

// without this, node will fail to execute in the Bitbucket Pipeline Build Container
if (ciProfile) {
    rootProject.plugins.withType(NodeJsRootPlugin::class.java) {
        rootProject.the<NodeJsRootExtension>().download = false
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}
