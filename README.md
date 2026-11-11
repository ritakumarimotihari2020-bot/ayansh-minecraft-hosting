# Ayansh Minecraft Hosting Panel

A responsive, dark-themed frontend dashboard preview for managing a Minecraft server.

## Deploy with GitHub Pages

1. Open **Settings → Pages** in this repository.
2. Under **Build and deployment**, set **Source** to **GitHub Actions**.
3. Push the workflow in `.github/workflows/pages.yml`.
4. Open **Actions** and wait for the Pages workflow to finish successfully.
5. Visit: https://ritakumarimotihari2020-bot.github.io/ayansh-minecraft-hosting/

## Important

This repository provides a **frontend demo only**. Start, stop, and restart buttons change the preview UI; they do not control a real Minecraft server. GitHub Pages cannot run a Minecraft server process. Real server management requires a separate backend/agent on a host machine, authenticated API endpoints, and secure access controls. Never expose server credentials or API tokens in frontend JavaScript.
