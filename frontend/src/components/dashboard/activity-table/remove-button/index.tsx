import { Button } from "@/components/ui/button";
import { ActivityTableContext } from "@/context/activity-table-context";
import { frontendApi } from "@/lib/api";
import { useContext } from "react";

type RemoveButtonType = {
  id: string;
}

async function removeActivity(id: string) {
  try {
    const url = `/activities/${id}`;
    await frontendApi.delete(url);
  } catch (e) {
    alert("Impossível remover!");
  }
}

export function RemoveButton({ id }: RemoveButtonType) {
  const activityTableContext = useContext(ActivityTableContext);

  return (
    <Button variant="ghost"
      key={id}
      onClick={async () => {
        await removeActivity(id);
        activityTableContext.refreshTable();
      }}>
      Remover
    </Button>
  )
}