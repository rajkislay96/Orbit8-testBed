plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.orbit8.testbed"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.orbit8.testbed"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0-license-test"
    }

    buildTypes {
        release { isMinifyEnabled = false }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    packaging {
        resources {
            // libsignal ships desktop natives inside libsignal-client
            excludes += setOf("libsignal_jni.dylib", "signal_jni.dll",
                "META-INF/DEPENDENCIES", "META-INF/LICENSE*", "META-INF/NOTICE*",
                "META-INF/*.kotlin_module", "META-INF/versions/**")
        }
    }
}

dependencies {
    // ================= AGPL-3.0 (strong network copyleft) =================
    implementation("com.itextpdf:itext7-core:7.2.5")                 // AGPL-3.0 (or commercial)
    implementation("com.itextpdf:itextpdf:5.5.13.3")                 // AGPL-3.0 (legacy iText 5)
    implementation("org.signal:libsignal-android:0.63.0")            // AGPL-3.0, pulls libsignal-client (AGPL) transitively
    implementation("com.artifex.mupdf:fitz:1.24.10")                 // AGPL-3.0 (or commercial), Artifex repo

    // ================= GPL family =================
    implementation("org.whispersystems:signal-protocol-android:2.8.1") // GPL-3.0 (older Signal protocol lib)
    implementation("com.mysql:mysql-connector-j:8.0.33")             // GPL-2.0 WITH Universal-FOSS-exception-1.0

    // ================= Weak copyleft =================
    implementation("net.sourceforge.jtds:jtds:1.3.1")                // LGPL-2.1
    implementation("org.mozilla:rhino:1.7.14")                       // MPL-2.0
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5") // EPL-2.0 / EDL-1.0
    implementation("com.sun.mail:android-mail:1.6.7")                // EPL-2.0 OR GPL-2.0 WITH Classpath-exception

    // ================= Custom / restrictive =================
    implementation("com.github.junrar:junrar:7.5.5")                 // UnRAR license (no RAR compressor re-creation)

    // ================= Permissive (should NOT be flagged high) =================
    implementation("com.squareup.okhttp3:okhttp:4.12.0")             // Apache-2.0
    implementation("com.google.code.gson:gson:2.10.1")               // Apache-2.0
    implementation("org.jsoup:jsoup:1.17.2")                         // MIT
    implementation("androidx.appcompat:appcompat:1.7.0")             // Apache-2.0
}
