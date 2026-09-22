# Orbit8 License Testbed (Android)

An intentionally "license-heavy" Android app for evaluating the **Orbit8** open source
compliance scanner. It contains multiple AGPL-3.0 dependencies, other copyleft and
non-OSI licenses, vendored third-party code, a network backend, and deliberate
compliance defects. The answer key is in `EXPECTED_FINDINGS.md`.

> Do not publish this app. It is deliberately non-compliant.

## Layout
```
app/                    Android app (Kotlin + Java), Gradle dependencies with mixed licenses
third_party/            Vendored AGPL C code, Commons-Clause code (not compiled)
server/                 Flask + PyMuPDF (AGPL) backend the app calls over the network
web/                    npm manifest that declares MIT but depends on AGPL mupdf
scripts/                Fetches full license texts (optional second scan pass)
EXPECTED_FINDINGS.md    Ground truth for scoring the scanner
```

## Build the APK
1. Open the folder in Android Studio (Koala or newer), JDK 17. Let it sync.
   Or from a terminal with Gradle installed: `gradle wrapper && ./gradlew assembleDebug`
2. APK: `app/build/outputs/apk/debug/app-debug.apk`

If a version fails to resolve (e.g., MuPDF from maven.ghostscript.com), bump it to the latest
available; license detection is unaffected.

## Optional: run the AGPL backend
```
cd server && pip install -r requirements.txt && python app.py
```
The emulator reaches it at `http://10.0.2.2:5000`.

## Suggested scan passes
1. **Source scan** of the whole repo (manifests + headers + vendored code).
2. **Binary scan** of the built APK (tests detection inside AAR/.so/dex without manifests).
3. **Second source scan** after `scripts/fetch-license-texts.sh` (full-text detection).

Compare each report against `EXPECTED_FINDINGS.md`.
