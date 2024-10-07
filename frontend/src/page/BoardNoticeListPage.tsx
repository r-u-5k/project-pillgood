import { DefaultLayout } from "../component/layout/Layout";
import { BoardNoticeList } from "@/component/board/BoardNoticeList";

export const BoardNoticeListPage = () => {

  return (
    <DefaultLayout>
      <BoardNoticeList></BoardNoticeList>
    </DefaultLayout>
  );
};
