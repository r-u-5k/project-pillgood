import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: [{ find: "@", replacement: "/src" }],
  },
  server: {
    host:'0.0.0.0',
    proxy: {
      "/api": {
        target: "http://192.168.15.3:8080/",
        changeOrigin: true,
      },
    },
    
  },
});

