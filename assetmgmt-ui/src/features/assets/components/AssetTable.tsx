import type { AssetResponse } from "../../../shared/types/asset";

type Props = {
  assets: AssetResponse[];
  onEdit: (id: string) => void;
  onView: (id: string) => void;
  onDelete: (id: string) => void;
};

export const AssetTable = ({ assets, onEdit, onView, onDelete }: Props) => {
  return (
    <table className="w-full border-collapse text-sm">
      <thead className="bg-gray-50">
        <tr>
          <th className="px-4 py-3 text-left font-medium text-gray-600">Code</th>
          <th className="px-4 py-3 text-left font-medium text-gray-600">Description</th>
          <th className="px-4 py-3 text-left font-medium text-gray-600">Class</th>
          <th className="px-4 py-3 text-left font-medium text-gray-600">Status</th>
          <th className="px-4 py-3 text-left font-medium text-gray-600">Branch</th>
          <th className="px-4 py-3 text-left font-medium text-gray-600">Amount</th>
          <th className="px-4 py-3 text-left font-medium text-gray-600">Actions</th>
        </tr>
      </thead>

      <tbody className="divide-y divide-gray-200">
        {assets.map(asset => (
          <tr
            key={asset.id}
            className="hover:bg-gray-50 transition"
          >
            <td className="px-4 py-3 font-mono text-gray-800">
              {asset.assetCode}
            </td>
            <td className="px-4 py-3 text-gray-700">
              {asset.description}
            </td>
            <td className="px-4 py-3 text-gray-700">
              {asset.assetClass}
            </td>
            <td className="px-4 py-3">
              <span
                className="
                  inline-flex rounded-full px-2 py-0.5 text-xs font-medium
                  bg-gray-100 text-gray-700
                "
              >
                {asset.status}
              </span>
            </td>
            <td className="px-4 py-3 text-gray-700">
              {asset.branchName ?? "-"}
            </td>
            <td className="px-4 py-3 text-gray-700">
              {asset.amount ?? "-"}
            </td>
            <td className="px-4 py-3">
              <div className="flex gap-2">
                <button
                  onClick={() => onView(asset.id)}
                  className="
                    rounded px-2 py-1 text-xs font-medium
                    text-blue-600 hover:bg-blue-50
                  "
                >
                  View
                </button>
                <button
                  onClick={() => onEdit(asset.id)}
                  className="
                    rounded px-2 py-1 text-xs font-medium
                    text-yellow-600 hover:bg-yellow-50
                  "
                >
                  Edit
                </button>
                <button
                  onClick={() => onDelete(asset.id)}
                  className="
                    rounded px-2 py-1 text-xs font-medium
                    text-red-600 hover:bg-red-50
                  "
                >
                  Delete
                </button>
              </div>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
