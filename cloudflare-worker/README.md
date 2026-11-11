# Ayansh Minecraft Hosting API

This is the control-plane API scaffold for the Android app.

## Free deployment
1. Create a Cloudflare account and a Worker named `ayansh-minecraft-hosting-api`.
2. Deploy this folder with Wrangler (or paste `src/index.js` into the Worker editor).
3. Add a Worker secret named `PANEL_API_TOKEN`; never commit the token.
4. Test `GET /health`.

## Current limitation
The Worker can host the API on a free tier, but **cannot run a Minecraft Java server process**. A separate always-on compute host and a trusted host agent are required for real Start/Stop/Console/Players. No fake success responses are returned; control endpoints stay disabled until that agent exists.
