# Ayansh Minecraft Hosting

A Minecraft hosting control-panel project with a static web dashboard, an Android app source project, and a Cloudflare Worker API scaffold.

## Project folders
- `android/` — native Android app source (early scaffold; APK not built yet).
- `cloudflare-worker/` — API control-plane scaffold for Cloudflare Workers.
- `index.html`, `styles.css`, `app.js` — GitHub Pages web dashboard.

## Free-tier architecture
GitHub Pages can host the web dashboard, and Cloudflare Workers can host lightweight API endpoints on its free tier, subject to current plan limits. Neither platform runs a persistent Minecraft Java server process. A separate compute host (and a secure host agent installed there) is required for real server Start/Stop, console, player and file operations. A permanently free always-on Minecraft compute host is not guaranteed.

## Next steps
1. Deploy `cloudflare-worker/` as a Cloudflare Worker.
2. Set the secret `PANEL_API_TOKEN` in Worker settings. Never commit the token.
3. Build and install the Android project using Android Studio or Gradle.
4. Provision a suitable Minecraft compute host and connect a secure host agent before enabling real controls.

Until a host agent is connected, the API deliberately reports `NOT_CONNECTED` and refuses control actions rather than pretending they worked.
