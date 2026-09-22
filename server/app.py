# SPDX-License-Identifier: AGPL-3.0-or-later
# Copyright (C) 2026 Orbit8 Testbed Authors
#
# Network-facing backend used by the Android app. Because this service is
# AGPL and users interact with it remotely, AGPL-3.0 section 13 requires
# offering them the Corresponding Source. See the /source route.
import io
from flask import Flask, jsonify
import pymupdf  # PyMuPDF: AGPL-3.0 (or Artifex commercial)

app = Flask(__name__)
SOURCE_URL = "https://example.com/orbit8-testbed/source"


@app.get("/render")
def render():
    doc = pymupdf.open()
    page = doc.new_page()
    page.insert_text((72, 72), "Rendered by PyMuPDF (AGPL)")
    buf = io.BytesIO(doc.tobytes())
    return jsonify(bytes=len(buf.getvalue()), source=SOURCE_URL)


@app.get("/source")
def source():
    return jsonify(source=SOURCE_URL, license="AGPL-3.0-or-later")


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
