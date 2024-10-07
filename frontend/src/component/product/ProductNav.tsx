import { Link } from "react-router-dom";
import "../../style/sticky.css"
export const ProductNav = ()=>{


    return(
        <nav  className="sticky md:flex items-center space-x-4"> 
        <Link to="#" className="text-sm font-medium hover:underline underline-offset-4" >
          Product Details
        </Link>
        <Link to="#" className="text-sm font-medium hover:underline underline-offset-4" >
          Reviews
        </Link>
      </nav>
    );
}