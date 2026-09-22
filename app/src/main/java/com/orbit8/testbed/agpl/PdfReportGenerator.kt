/*
 * SPDX-License-Identifier: AGPL-3.0-only
 * Copyright (C) 2026 Orbit8 Testbed Authors
 *
 * Portions adapted from the iText 7 "Hello World" usage pattern.
 * iText is licensed under the GNU Affero General Public License v3.
 * MuPDF is Copyright (C) Artifex Software, Inc., licensed under AGPL-3.0.
 */
package com.orbit8.testbed.agpl

import com.artifex.mupdf.fitz.Document as MuDocument
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import java.io.File

object PdfReportGenerator {

    fun create(target: File, title: String) {
        val pdf = PdfDocument(PdfWriter(target))
        val doc = Document(pdf)
        doc.add(Paragraph(title))
        doc.add(Paragraph("Generated with iText 7 (AGPL-3.0)."))
        doc.close() // also closes pdf + writer
    }

    fun countPagesWithMuPdf(file: File): Int {
        require(file.exists()) { "Generate the PDF first" }
        val doc = MuDocument.openDocument(file.absolutePath)
        try { return doc.countPages() } finally { doc.destroy() }
    }
}
