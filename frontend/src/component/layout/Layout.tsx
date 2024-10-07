import { Body } from "@/component/Body";
import { Footer } from "./Footer";
import { Header } from "./Header";

type LayoutProps = {
  children: React.ReactNode;
};
export const DefaultLayout = ({ children }: LayoutProps) => {
  return (
    <main className="flex flex-col w-full">
      <Header />
      <Body>{children}</Body>
      <Footer />
    </main>
  );
};
