"use client";

import { ColumnDef } from "@tanstack/react-table";
import { RemoveButton } from "./remove-button";

// This type is used to define the shape of our data.
// You can use a Zod schema here if you want.
export type Activity = {
  id: string;
  date: Date;
  description: string;
  value: number;
  type: "expense" | "revenue";
};

export const columns: ColumnDef<Activity>[] = [
  {
    accessorKey: "date",
    header: "Data",
    cell: ({ row }) => {
      const aDate = new Date(row.getValue("date"));
      const formatedDate = `${aDate.getDate()}/${aDate.getMonth()}/${aDate.getFullYear()}`;

      return <p>{formatedDate}</p>;
    },
  },
  {
    accessorKey: "description",
    header: "Descrição",
  },
  {
    accessorKey: "value",
    header: "Valor",
    cell: ({ row }) => {
      const aValue = row.getValue("value") as number;
      const aType = row.getValue("type");

      const valueClass =
        aType === "revenue" ? "text-emerald-500" : "text-red-500";

      return (
        <p className={valueClass}>
          R${" "}
          {aValue.toLocaleString("pt-BR", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2,
          })}
        </p>
      );
    },
  },
  {
    accessorKey: "type",
    header: "Tipo",
  },
  {
    id: "actions",
    header: "Ações",
    cell: ({ row }) => {
      const id = row.original.id;

      return <RemoveButton id={id} />;
    },
  },
];
