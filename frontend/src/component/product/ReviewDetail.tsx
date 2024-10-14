import { useEffect, useState } from "react";
import { Modal } from "semantic-ui-react";
import logo from "../../images/logo-orange.png";

export const ReviewDetail = ({ review }) => {
  console.log(review);
  const [reviewDetail, setReviewDetail] = useState({
    birthday: "",
    content: "",
    date: "",
    gender: "",
    itemDto: {
      brand: "",
      categorySymptom: "",
      categoryType: "",
      description: "",
      itemImageDto: [
        {
          img: "",
          type: "",
          url: "",
        },
      ],
      name: "",
      no: "",
      price: "",
    },
    name: "",
    no: "",
    rating: "",
    title: "",
    userNo: "",
  });
  useEffect(() => {
    setReviewDetail(review);
  }, []);
  const [open, setOpen] = useState(false);
  return (
    <Modal
      trigger={<div className="mt-[10px] text-[12px] text-gray-400 underline font-bold">더보기</div>}
      style={{ position: "fixed", top: "50%", left: "50%", transform: "translate(-50%, -50%)", width: "700px", height: "700px" }}
      className="opacity-100"
      centered={true}
      open={open}
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
    >
      <section className="relative isolate overflow-hidden bg-white px-6 py-24 sm:py-32 lg:px-8">
        <div className="absolute inset-0 -z-10 bg-[radial-gradient(45rem_50rem_at_top,theme(colors.indigo.100),white)] opacity-20" />
        <div className="absolute inset-y-0 right-1/2 -z-10 mr-16 w-[200%] origin-bottom-left skew-x-[-30deg] bg-white shadow-xl shadow-indigo-600/10 ring-1 ring-indigo-50 sm:mr-28 lg:mr-0 xl:mr-16 xl:origin-center" />
        <div className="mx-auto max-w-2xl lg:max-w-4xl">
          <img className="mx-auto h-12" src={logo} alt="" />
          <figcaption className="flex flex-between">
            <img className="mx-auto rounded-full flex justify-start w-[30%] h-full" src={reviewDetail.itemDto.itemImageDto[0].url} alt="" />
            <div className="flex justify-middle w-[70%]">
              <figure className="mt-10">
                <blockquote className="text-center text-xl font-semibold leading-8 text-gray-900 sm:text-2xl sm:leading-9">
                  <p>{reviewDetail.content}</p>
                </blockquote>
              </figure>
            </div>
          </figcaption>
        </div>
      </section>
    </Modal>
  );
};
