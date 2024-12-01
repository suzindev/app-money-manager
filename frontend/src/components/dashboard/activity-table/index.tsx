import { Activity, columns } from "./columns";
import { DataTable } from "./data-table";

const data: Activity[] = [
  {
    id: "1",
    date: new Date("11-30-2024"),
    description: "Pagamento Luz",
    value: 250.95,
    type: "expense",
  },
  {
    id: "2",
    date: new Date("11-20-2024"),
    description: "Pagamento Água",
    value: 150,
    type: "expense",
  },
  {
    id: "3",
    date: new Date("11-10-2024"),
    description: "Pagamento Internet",
    value: 1200.95,
    type: "expense",
  },
];

function getData(): Activity[] {
  // Fetch data from your API here.
  return data;
}

export async function ActivityTable() {
  const data = getData();

  return (
    <div className="container mx-auto p-8">
      <DataTable columns={columns} data={data} />
    </div>
  );
}
