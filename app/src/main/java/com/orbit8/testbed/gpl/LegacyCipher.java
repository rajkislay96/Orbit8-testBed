/*
 * LegacyCipher - checksum helper for the Orbit8 testbed.
 * Copyright (C) 2026 Orbit8 Testbed Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
// (No SPDX tag on purpose: tests license-notice TEXT detection -> GPL-3.0-or-later)
package com.orbit8.testbed.gpl;

import java.util.zip.CRC32;

public final class LegacyCipher {
    private LegacyCipher() {}

    public static String checksum(String input) {
        CRC32 crc = new CRC32();
        crc.update(input.getBytes());
        return Long.toHexString(crc.getValue());
    }
}
