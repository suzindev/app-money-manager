'use client'

import { LoginResponseType } from "@/app/api/auth/login/route";
import { CustomAlert, CustomAlertType } from "@/components/general/custom-alert";
import { Button } from "@/components/ui/button";
import { FormControl, FormField, FormItem, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { AuthContext } from "@/context/auth-context";
import { frontendApi } from "@/lib/api";
import { zodResolver } from "@hookform/resolvers/zod";
import { AxiosError } from "axios";
import { BadgeDollarSign } from "lucide-react";
import { useRouter } from "next/navigation";
import { useContext, useState } from "react";
import { FormProvider, useForm } from "react-hook-form";
import { z } from "zod";

const loginFormSchema = z.object({
  email: z.string().email({ message: "E-mail inválido" }),
  password: z.string().min(1, { message: "Senha inválida" })
});

type loginFormType = z.infer<typeof loginFormSchema>;

export function LoginForm() {

  const [message, setMessage] = useState(<></>);

  const authContext = useContext(AuthContext);

  const router = useRouter();

  const loginForm = useForm<loginFormType>({
    resolver: zodResolver(loginFormSchema),
    defaultValues: {
      email: "",
      password: ""
    }
  });

  async function handleLoginSubmit({ email, password }: loginFormType) {
    const data = JSON.stringify({
      email,
      password
    });

    try {
      const result = await frontendApi.post("/auth/login", data);
      const { token, error } = result.data as LoginResponseType;

      if (token) {
        authContext.signIn(token);
        router.push("/dashboard");
      } else {
        const message = <CustomAlert
          type={CustomAlertType.ERROR}
          title="Erro ao logar-se"
          message={error || "Erro desconhecido. Por favor tente mais tarde"}
        />

        setMessage(message);
      }
    } catch (e) {
      const axiosError = e as AxiosError;

      const message = <CustomAlert
        type={CustomAlertType.ERROR}
        title="Erro ao logar-se"
        message={axiosError.message}
      />

      setMessage(message);
    }
  }

  return (
    <>
      <div className="flex pb-60 items-center h-screen justify-center">
        <div className="container space-y-2 p-8 max-w-md rounded-xl bg-gray-50 shadow-md">
          <span className="flex items-center gap-2">
            <BadgeDollarSign className="text-slate-500" size={48} />
            <h1 className="uppercase text-slate-600 font-bold text-xl">
              Money Manager
            </h1>
          </span>

          <FormProvider {...loginForm}>
            <form onSubmit={loginForm.handleSubmit(handleLoginSubmit)} className="space-y-2">
              {message}
              <FormField
                control={loginForm.control}
                name="email"
                render={({ field }) => {
                  return (
                    <FormItem>
                      <FormControl>
                        <Input type="text" placeholder="Digite seu e-mail" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )
                }}
              />

              <FormField
                control={loginForm.control}
                name="password"
                render={({ field }) => {
                  return (
                    <FormItem>
                      <FormControl>
                        <Input type="password" placeholder="Digite seu senha" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )
                }}
              />

              <Button className="float-right" type="submit">Entrar</Button>
            </form>
          </FormProvider>
        </div>
      </div>
    </>
  )
}