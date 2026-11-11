# Ayansh Hosting API (Cloudflare Worker)

## Deploy using Cloudflare dashboard
1. Create or sign in to a Cloudflare account.
2. Open Workers & Pages and create a Worker named `ayansh-minecraft-hosting-api`.
3. Paste the contents of `src/index.js` into the Worker editor and deploy.
4. In Worker Settings → Variables and Secrets, add secret `PANEL_API_TOKEN` with a long random value. Never commit this token to GitHub.
5. Visit `https://YOUR-WORKER.workers.dev/health`; it should return OK JSON.
6. Call `/api/status` with header `Authorization: Bearer YOUR_TOKEN`.

## Deploy with Wrangler
```sh
npm install -g wrangler
wrangler login
cd cloudflare-worker
wrangler secret put PANEL_API_TOKEN
wrangler deploy
```

## Current endpoints
- `GET /health` — public health check.
- `GET /api/status` — authenticated; currently returns `NOT_CONNECTED`.
- `GET /api/servers` — authenticated; currently returns an empty list.
- `POST /api/server/*` — intentionally refuses actions with HTTP 409 until a trusted host agent exists.

This Worker is a lightweight control-plane API, not a Minecraft game server. GitHub Pages and Workers do not provide a persistent Minecraft Java process. A trusted compute host and authenticated agent are required for real Start/Stop/console/file actions. Never expose RCON, Docker socket, or an agent port publicly.
