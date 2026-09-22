// SPDX-License-Identifier: AGPL-3.0-or-later
// Copyright (C) 2026 Orbit8 Testbed Authors
//
// NOTE FOR SCANNERS: This file is AGPL-licensed while the root LICENSE file
// declares Apache-2.0. That mismatch is intentional (declared vs. concluded license test).
package com.orbit8.testbed

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.orbit8.testbed.agpl.PdfReportGenerator
import com.orbit8.testbed.agpl.SecureChannel
import com.orbit8.testbed.gpl.LegacyCipher
import com.orbit8.testbed.misc.ProprietaryHelper
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    // AGPL-3.0 section 13: users interacting over a network must be offered the source.
    private val sourceOfferUrl = "https://example.com/orbit8-testbed/source"
    private val backendUrl = "http://10.0.2.2:5000/render" // server/app.py (AGPL) on host machine

    private lateinit var out: TextView
    private val pdfFile by lazy { File(filesDir, "report.pdf") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        out = findViewById(R.id.output)
        log("Source code offer (AGPL §13): $sourceOfferUrl")
        log(ProprietaryHelper.banner())

        findViewById<Button>(R.id.btnPdf).setOnClickListener {
            runSafe { PdfReportGenerator.create(pdfFile, "Orbit8 license scan test"); "PDF written: ${pdfFile.path}" }
        }
        findViewById<Button>(R.id.btnKeys).setOnClickListener {
            runSafe { "Identity key: " + SecureChannel.newIdentityFingerprint() + "\nLegacy: " + LegacyCipher.checksum("orbit8") }
        }
        findViewById<Button>(R.id.btnPages).setOnClickListener {
            runSafe { "MuPDF page count: " + PdfReportGenerator.countPagesWithMuPdf(pdfFile) }
        }
        findViewById<Button>(R.id.btnServer).setOnClickListener {
            runSafe {
                OkHttpClient().newCall(Request.Builder().url(backendUrl).build()).execute().use {
                    "Backend replied ${it.code}: ${it.body?.string()?.take(200)}"
                }
            }
        }
    }

    private fun runSafe(block: () -> String) = thread {
        val msg = try { block() } catch (t: Throwable) { "Error: ${t.javaClass.simpleName}: ${t.message}" }
        runOnUiThread { log(msg) }
    }

    private fun log(s: String) { out.append(s + "\n\n") }
}
