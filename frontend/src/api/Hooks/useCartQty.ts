import { useEffect, useState } from "react";
import * as userApi from "@/api/userApi";
import { useQuery } from "@tanstack/react-query";

export function useCartQty(loginUser) {
  return useQuery({
    queryKey: ["cart"],
    staleTime: 3000,
    queryFn: () => userApi.userCartList(loginUser.id),
    enabled: !!loginUser,
  });
}
