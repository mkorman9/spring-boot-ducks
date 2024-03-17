#!/usr/bin/env bash

# API key for my-playground-project-391323
API_KEY="AIzaSyBGRQhxXTFc-z_UB81xGziP6YUajLhqT3E"

SCRIPT_DIR=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" &> /dev/null && pwd)

curl \
  -s \
  -X POST \
  -H 'Content-Type: application/json' \
  -d '{"returnSecureToken": true}' \
  "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=${API_KEY}" \
  | tee "${SCRIPT_DIR}/.token" \
  | jq -r '.idToken'
