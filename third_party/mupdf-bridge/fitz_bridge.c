/*
 * fitz_bridge.c - thin JNI-style bridge used for license scanning tests.
 *
 * Copyright (C) 2026 Orbit8 Testbed Authors
 * Links against MuPDF, Copyright (C) 2004-2026 Artifex Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * NOTE: Not compiled into the APK. Present so source/snippet scanners find
 * vendored AGPL C code (third_party/ is a common blind spot).
 */
#include <stdio.h>

int fitz_bridge_version(void) { return 12410; }
