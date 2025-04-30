'use client'

import React, { createContext } from "react";
import { deleteCookie, getCookie, setCookie } from "cookies-next";

type AuthContextType = {
  isAuthenticated: boolean;
  signIn: (token: string) => void;
  signOut: () => void;
  recoveryToken: () => string | undefined;
}

export const AuthContext = createContext({} as AuthContextType);

export function AuthContextProvider({ children }: { children: React.ReactNode }) {
  const isAuthenticated = !!recoveryToken();

  function signIn(token: string) {
    setCookie("money-manager.token", token, {
      maxAge: 60 * 60 * 3
    });
  }

  function signOut() {
    deleteCookie("money-manager.token");
  }

  function recoveryToken() {
    const cookie = getCookie("money-manager.token");
    const token = cookie?.toString();

    return token;
  }

  return (
    <AuthContext.Provider value={{ isAuthenticated, signIn, signOut, recoveryToken }}>
      {children}
    </AuthContext.Provider>
  )
}