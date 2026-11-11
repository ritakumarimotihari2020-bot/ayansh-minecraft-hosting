const json=(data,status=200)=>new Response(JSON.stringify(data),{status,headers:{"content-type":"application/json","cache-control":"no-store"}});
export default {
 async fetch(request,env){
  const url=new URL(request.url);
  if(request.method==="OPTIONS") return new Response(null,{headers:{"access-control-allow-origin":"*","access-control-allow-methods":"GET,POST,OPTIONS","access-control-allow-headers":"authorization,content-type"}});
  if(url.pathname==="/health") return json({ok:true,service:"Ayansh Hosting API",mode:"control-plane-only"});
  if(!env.PANEL_API_TOKEN) return json({error:"API token is not configured"},503);
  if(request.headers.get("authorization")!==`Bearer ${env.PANEL_API_TOKEN}`) return json({error:"Unauthorized"},401);
  if(url.pathname==="/api/status" && request.method==="GET") return json({ok:true,server:{state:"NOT_CONNECTED",message:"Connect a Minecraft host agent to control a real server."}});
  if(url.pathname==="/api/servers" && request.method==="GET") return json({servers:[]});
  if(url.pathname.startsWith("/api/server/") && ["POST"].includes(request.method)){
   return json({error:"No host agent is connected. Start/stop actions are disabled until a trusted host agent is installed."},409);
  }
  return json({error:"Not found"},404);
 }
};
