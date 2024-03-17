#!/usr/bin/env bash

# API key for my-playground-project-391323
API_KEY="AIzaSyBGRQhxXTFc-z_UB81xGziP6YUajLhqT3E"

SCRIPT_DIR=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" &> /dev/null && pwd)

TOKEN_FILE="${SCRIPT_DIR}/.token"
if [ ! -f "$TOKEN_FILE" ]; then
  >&2 echo 'Token file not found, run signup.sh first'
  exit 1
fi

REFRESH_TOKEN=$(cat "$TOKEN_FILE" | jq -r '.refreshToken')
if [ -z "$REFRESH_TOKEN" ] | [ "$REFRESH_TOKEN" = "null" ]; then
    REFRESH_TOKEN=$(cat "$TOKEN_FILE" | jq -r '.refresh_token')

    if [ -z "$REFRESH_TOKEN" ] | [ "$REFRESH_TOKEN" = "null" ]; then
      >&2 echo 'Failed to extract refresh token from token file'
      exit 1
    fi
fi

curl \
  -s \
  -X POST \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d "grant_type=refresh_token&refresh_token=${REFRESH_TOKEN}" \
  "https://securetoken.googleapis.com/v1/token?key=${API_KEY}" \
  | tee "$TOKEN_FILE" \
  | jq -r '.id_token'
