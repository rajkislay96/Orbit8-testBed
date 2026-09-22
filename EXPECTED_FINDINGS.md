# Expected Findings (answer key for Orbit8)

Use this to score the scan: detection rate, correct license, correct obligation, and false positives.

## A. Declared dependencies (app/build.gradle.kts)

| # | Component | Version | License | Key obligations / risk | Expected severity |
|---|-----------|---------|---------|------------------------|-------------------|
| 1 | com.itextpdf:itext7-core | 7.2.5 | AGPL-3.0 | Whole app must be AGPL; provide full source; keep "Producer" line; network-use clause | High |
| 2 | com.itextpdf:itextpdf | 5.5.13.3 | AGPL-3.0 | Same as above (legacy line, duplicate PDF engine) | High |
| 3 | org.signal:libsignal-android | 0.63.0 | AGPL-3.0 | Copyleft + native .so libraries inside AAR | High |
| 4 | org.signal:libsignal-client | 0.63.0 (transitive) | AGPL-3.0 | Transitive detection test | High |
| 5 | com.artifex.mupdf:fitz | 1.24.10 | AGPL-3.0 (dual commercial) | Non-Central repo (maven.ghostscript.com) detection test | High |
| 6 | org.whispersystems:signal-protocol-android | 2.8.1 | GPL-3.0 | Strong copyleft; incompatible with Apache-2.0 project license | High |
| 7 | com.mysql:mysql-connector-j | 8.0.33 | GPL-2.0-only WITH Universal-FOSS-exception | GPL-2.0-only is incompatible with Apache-2.0 and with GPL-3.0/AGPL-3.0 code | High |
| 8 | net.sourceforge.jtds:jtds | 1.3.1 | LGPL-2.1 | Allow relinking/replacement; provide library source | Medium |
| 9 | org.mozilla:rhino | 1.7.14 | MPL-2.0 | File-level copyleft; disclose modified MPL files | Medium |
| 10 | org.eclipse.paho.client.mqttv3 | 1.2.5 | EPL-2.0 / EDL-1.0 | Weak copyleft; dual-license choice detection | Medium |
| 11 | com.sun.mail:android-mail | 1.6.7 | EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0 | Dual/choice license parsing | Medium |
| 12 | com.github.junrar:junrar | 7.5.5 | UnRAR license (non-OSI) | Custom restriction: may not be used to re-create RAR compressor | Medium/Custom |
| 13 | okhttp 4.12.0, gson 2.10.1, appcompat 1.7.0 | – | Apache-2.0 | Attribution + NOTICE | Low |
| 14 | org.jsoup:jsoup | 1.17.2 | MIT | Attribution | Low |

## B. First-party / vendored source files

| File | Detection method tested | Expected license |
|------|-------------------------|------------------|
| app/.../MainActivity.kt | SPDX tag | AGPL-3.0-or-later |
| app/.../agpl/PdfReportGenerator.kt | SPDX tag + upstream copyright mention | AGPL-3.0-only |
| app/.../agpl/SecureChannel.kt | SPDX FileCopyrightText / FileContributor | AGPL-3.0-or-later |
| app/.../gpl/LegacyCipher.java | Full notice TEXT, no SPDX | GPL-3.0-or-later |
| app/.../misc/ProprietaryHelper.kt | "All rights reserved" / confidential | Proprietary (conflict with AGPL in same APK) |
| third_party/mupdf-bridge/fitz_bridge.c | AGPL notice text in C header | AGPL-3.0-or-later |
| third_party/ghostscript-shim/gs_shim.c + README.third_party | SPDX + metadata file | AGPL-3.0-or-later (modified) |
| third_party/commons-clause/Widget.kt | License exception expression | Apache-2.0 WITH Commons-Clause (non-OSI) |
| app/src/main/assets/sample-data.json | License inside data asset | CC-BY-SA-4.0 (ShareAlike + attribution) |

## C. Other ecosystems in the same repo

| File | Component | License | Note |
|------|-----------|---------|------|
| server/requirements.txt | PyMuPDF 1.24.10 | AGPL-3.0 | Network service -> AGPL §13 source offer |
| server/requirements.txt | psycopg2-binary 2.9.9 | LGPL-3.0-or-later | |
| server/requirements.txt | Flask 3.0.3 | BSD-3-Clause | Low |
| server/app.py | first-party | AGPL-3.0-or-later | |
| web/package.json | mupdf (npm) | AGPL-3.0 | Package declares "MIT" -> conflict |
| web/package.json | express | MIT | Low |

## D. Policy-level issues a good tool should raise

1. Declared project license (LICENSE = Apache-2.0) contradicts concluded license (AGPL-3.0 required).
2. GPL-2.0-only (mysql-connector-j) cannot be combined with GPL-3.0 / AGPL-3.0 code.
3. Proprietary code (ProprietaryHelper.kt) linked with AGPL code in one distributed APK.
4. Missing NOTICE / third-party attribution file and no in-app "Open source licenses" screen.
5. Missing full license texts (COPYING) until scripts/fetch-license-texts.sh is run.
6. Source offer URL for AGPL §13 is a placeholder (example.com), i.e. obligation not actually met.
7. Native binaries (.so) from libsignal / MuPDF inside the APK: binary-level detection test.
8. Non-OSI licenses (Commons-Clause, UnRAR) should be flagged as "not open source".
