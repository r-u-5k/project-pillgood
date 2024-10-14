import * as React from "react"
import Autoplay from "embla-carousel-autoplay"
import img1 from "../../images/마그네슘디테일1.png";
import img2 from "../../images/마그네슘디테일2.jpg";
 
import { Card, CardContent } from "@/components/ui/card"
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel"
 
export function OrderListCarouselPlugin({orderItems}) {
  const plugin = React.useRef(
    Autoplay({ delay: 2000, stopOnInteraction: true})
  )
 
  return (
    <Carousel
      plugins={[plugin.current]}
      className="w-[200px] h-[200px] inline-flex rounded-3xl"
      //onMouseEnter={plugin.current.stop}
      onMouseLeave={plugin.current.start}
    >
      <CarouselContent className="w-full h-full">
        {orderItems.map((item,index)=>(
          <CarouselItem key={index}>
          <Card>
            <CardContent className="flex aspect-square items-center justify-center" style={{ padding: 0 }}>
              <img src={item.item.itemImageDto[0].url} className="w-[200px] h-[200px] bg-gray-100" />
            </CardContent>
          </Card>
        </CarouselItem>
        ))}
      </CarouselContent>
    </Carousel>
  )
}