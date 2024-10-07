import { RouterProvider } from "react-router-dom";
import { router } from "./router";
import React from "react";

export const UserContext = React.createContext({});

import { QueryClientProvider, QueryClient } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
function App() {
  const client = new QueryClient();

  return (
    <>
      <QueryClientProvider client={client}>
        <RouterProvider router={router} />
        <ReactQueryDevtools initialIsOpen={false} />
      </QueryClientProvider>
    </>
  );
}

export default App;
