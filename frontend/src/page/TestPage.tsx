import axios from "axios";
import { useEffect } from "react";

export const TestPage = () => {
  useEffect(() => {
    axios
      .get("https://jsonplaceholder.typicode.com/todos/1")
      .then((res) => console.log(res));
  }, []);
  return <div>ok</div>;
};

const res = await axios.get("https://jsonplaceholder.typicode.com/todos/1");
console.log(res);
console.log(res);
console.log(res);
console.log(res);
console.log(res);
