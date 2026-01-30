import { useForm } from "react-hook-form";
import type { AssetCreateRequest } from "../../../shared/types/asset";

type Props = {
  onSubmit: (data: AssetCreateRequest) => void;
  submitting?: boolean;
  defaultValues?: Partial<AssetCreateRequest>;
};

export const AssetForm = ({ onSubmit, submitting, defaultValues }: Props) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<AssetCreateRequest>({defaultValues});

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="space-y-6 bg-white p-6 rounded-lg shadow-sm border"
    >
      <h3 className="text-lg font-semibold text-gray-800">
        Asset Details
      </h3>

      {/* Asset Code */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Asset Code
        </label>
        <input
          {...register("assetCode", { required: true })}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
        {errors.assetCode && (
          <p className="text-xs text-red-600 mt-1">
            Asset code is required
          </p>
        )}
      </div>

      {/* Description */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Description
        </label>
        <input
          {...register("description", { required: true })}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
      </div>

      {/* Category */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Category ID
        </label>
        <input
          {...register("categoryId", { required: true })}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
      </div>

      {/* Serial Number */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Serial Number
        </label>
        <input
          {...register("serialNumber")}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
      </div>

      {/* Subsidiary */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Subsidiary
        </label>
        <input
          {...register("subsidiary")}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
      </div>

      {/* Branch */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Branch ID
        </label>
        <input
          {...register("branchId")}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
      </div>

      {/* Date */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Date of Acquisition
        </label>
        <input
          type="date"
          {...register("dateOfAcquisition")}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
      </div>

      {/* Amount */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Amount
        </label>
        <input
          type="number"
          {...register("amount", { valueAsNumber: true })}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
      </div>

      {/* Remark */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Remark
        </label>
        <textarea
          {...register("remark")}
          className="mt-1 w-full rounded-md border px-3 py-2 text-sm"
        />
      </div>

      {/* Actions */}
      <div className="flex justify-end pt-4">
        <button
          type="submit"
          disabled={submitting}
          className="
            inline-flex items-center gap-2
            rounded-md bg-blue-600 px-4 py-2
            text-sm font-medium text-white
            hover:bg-blue-700
            focus:outline-none focus:ring-2 focus:ring-blue-500
            disabled:opacity-50
          "
        >
          Save Asset
        </button>
      </div>
    </form>
  );
};
