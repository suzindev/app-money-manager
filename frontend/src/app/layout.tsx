import type { Metadata } from "next";
import { Poppins } from "next/font/google";
import "./globals.css";
import { cn } from "@/lib/utils";

const poppins = Poppins({ weight: "300", subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Money Manager",
  description: "Gerenciador Financeiro",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR">
      <body className={cn(poppins.className, "bg-gray-100")}>{children}</body>
    </html>
  );
}
