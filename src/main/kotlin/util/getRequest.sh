#!/bin/bash
BODY="$1"

curl -X GET \
  -H "Content-type: application/json" \
  -H "Accept: application/json" \
  -d "$BODY" \
  "http://localhost:8080/compiler/c-riscv"
