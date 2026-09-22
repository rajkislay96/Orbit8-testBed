// SPDX-License-Identifier: AGPL-3.0-or-later
// SPDX-FileCopyrightText: 2026 Orbit8 Testbed Authors
// SPDX-FileContributor: Uses libsignal (Copyright Signal Messenger, LLC, AGPL-3.0-only)
package com.orbit8.testbed.agpl

import org.signal.libsignal.protocol.IdentityKeyPair

object SecureChannel {
    fun newIdentityFingerprint(): String {
        val pair = IdentityKeyPair.generate()
        return pair.publicKey.serialize().joinToString("") { "%02x".format(it) }.take(32)
    }
}
