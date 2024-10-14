/**
 * v0 by Vercel.
 * @see https://v0.dev/t/L0Vr788P6Wu
 * Documentation: https://v0.dev/docs#integrating-generated-code-into-your-nextjs-app
 */
export default function LodingComponent() {
    return (
      <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 rounded-2xl">
        <div className="bg-white dark:bg-gray-900 rounded-xl p-8 shadow-lg">
          <div className="flex items-center justify-center space-x-2">
            <div className="animate-spin rounded-full h-8 w-8 border-4 border-gray-500 border-t-transparent" />
            <span className="text-gray-500 dark:text-gray-400 font-medium">Loading...</span>
          </div>
        </div>
      </div>
    )
  }