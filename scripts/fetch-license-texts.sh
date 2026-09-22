#!/usr/bin/env bash
# Downloads full license texts so full-text license detection can be tested too.
set -euo pipefail
cd "$(dirname "$0")/.."
curl -fsSL https://www.gnu.org/licenses/agpl-3.0.txt -o COPYING.AGPL
curl -fsSL https://www.gnu.org/licenses/gpl-3.0.txt  -o third_party/COPYING.GPL3
curl -fsSL https://www.apache.org/licenses/LICENSE-2.0.txt -o LICENSE-APACHE-2.0.txt
echo "License texts fetched."
