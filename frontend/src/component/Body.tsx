export type BodyProps = {
  children: React.ReactNode;
};

export const Body = ({ children }: BodyProps) => {
  return <main className="w-full flex-1">{children}</main>;
};
