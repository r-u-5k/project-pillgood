import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import 'bootswatch/dist/cosmo/bootstrap.min.css';
import "./style/output.css";
import { RecoilRoot } from "recoil";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <RecoilRoot>
    <App />
  </RecoilRoot>
);
