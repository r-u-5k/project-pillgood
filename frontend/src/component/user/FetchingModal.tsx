import { Dimmer, Image, Loader, ModalContent, ModalDescription, ModalHeader, Segment } from "semantic-ui-react";
import LodingComponent from "../LodingComponent";

const FetchingModal = () => {
  return(
<>
    <ModalHeader> AI 영양제 추천 </ModalHeader>
        <LodingComponent></LodingComponent>

    </>);
};
export default FetchingModal;
