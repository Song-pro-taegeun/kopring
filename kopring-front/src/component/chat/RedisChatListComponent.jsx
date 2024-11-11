import React, { useEffect, useState } from "react";
import "../../css/Chat.css";
import { GET } from "../../service/fetch-auth-action";
import RedisCreateChatComponent from "./RedisCreateChatComponent";
import RedisChatComponent from "./RedisChatComponent";

const RedisChatListComponent = () => {
  const [chatList, setChatList] = useState([]);
  const [userId, setUserId] = useState(null);

  const [popupView, setPopupView] = useState(false);
  const [enterRoom, setEnterRoom] = useState(false);
  const [enterRoomInfo, setEnterRoomInfo] = useState(null);

  useEffect(() => {
    getChatList();
  }, [enterRoom]);

  const getChatList = async () => {
    const url = "/chat/rooms";
    const response = await GET(url, null);
    if (response.status === 200) {
      // createdDate를 기준으로 내림차순 정렬
      const sortedData = response.data.sort((b, a) => a.createdDate.localeCompare(b.createdDate));
      setChatList(sortedData);
    }
  };

  const handleCreateRommClick = () => {
    setPopupView(true);
  };

  const handleEnterRoom = (data) => {
    if (window.confirm("참가하시겠습니까?")) {
      let param = data;
      param.userId = userId;
      setEnterRoomInfo(param);
      setEnterRoom(true);
    }
  };

  return (
    <div>
      {!enterRoom && (
        <div className="table-container">
          <div className="table-header">
            Redis Chat Test
            <button className="create-room-button" onClick={handleCreateRommClick}>
              방 생성
            </button>
          </div>
          <span>
            아이디 입력
            <input
              type="text"
              onChange={(e) => {
                setUserId(e.target.value);
              }}
            />
          </span>
          <table>
            <thead>
              <tr>
                <th>No</th>
                <th>Title</th>
                <th>Host</th>
                <th>Participants</th>
              </tr>
            </thead>
            <tbody>
              {chatList.map((data, index) => (
                <tr
                  key={index}
                  onClick={() => {
                    handleEnterRoom(data);
                  }}
                >
                  <td>{index + 1}</td>
                  <td>{data.roomName}</td>
                  <td>{data.host}</td>
                  <td>{data.participants.length}명</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {popupView && <RedisCreateChatComponent setPopupView={setPopupView} userId={userId} setEnterRoom={setEnterRoom} setEnterRoomInfo={setEnterRoomInfo} />}
      {enterRoom && <RedisChatComponent setEnterRoom={setEnterRoom} enterRoomInfo={enterRoomInfo} setEnterRoomInfo={setEnterRoomInfo} />}
    </div>
  );
};

export default RedisChatListComponent;
