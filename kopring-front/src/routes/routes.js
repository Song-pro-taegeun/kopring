import RedisChatComponent from "../component/chat/RedisChatComponent";
import RedisChatListComponent from "../component/chat/RedisChatListComponent";
import Home from "../component/main/Home";
const routes = [
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/chatList",
    element: <RedisChatListComponent />,
  },
  {
    path: "/chat",
    element: <RedisChatComponent />,
  },
];

export default routes;
