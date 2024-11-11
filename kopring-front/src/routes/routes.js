import RedisChatComponent from "../component/main/RedisChatComponent";
import Home from "../component/main/Home";
const routes = [
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/chat",
    element: <RedisChatComponent />,
  },
];

export default routes;
