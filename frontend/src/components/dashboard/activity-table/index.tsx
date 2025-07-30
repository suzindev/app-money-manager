'use client'

import { useContext } from "react";
import { columns } from "./columns";
import { DataTable } from "./data-table";
import { ActivityTableContext } from "@/context/activity-table-context";

export function ActivityTable() {
  const activityTableContext = useContext(ActivityTableContext);
  const activities = activityTableContext.activities;

  return (
    <div className="container mx-auto my-8">
      <DataTable columns={columns} data={activities} />
    </div>
  );
}
