import React, { useState } from "react";
import { POST } from "../../service/fetch-auth-action";

const RedisCreateChatComponent = ({ setPopupView, userId, setEnterRoom, setEnterRoomInfo }) => {
  const [roomTitle, setRoomTitle] = useState("");

  const handleRoomTitleChange = (e) => {
    setRoomTitle(e.target.value);
  };

  const handleCreateRoom = async () => {
    if (roomTitle) {
      const url = "/chat/room";
      const param = {
        roomName: roomTitle,
        host: userId,
        participants: [userId],
      };

      const response = await POST(url, param, null);
      if (response.status === 200) {
        setPopupView(false);
        setEnterRoom(true);
        setEnterRoomInfo({
          userId: userId,
          roomId: response.data.roomId,
          roomName: response.data.roomName,
        });

        // setReload((prev) => prev + 1);
      } else {
        alert("오류!");
      }
    } else {
      alert("방 제목을 입력해 주세요.");
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>방 제목 생성</h2>
        <input type="text" placeholder="방 제목을 입력하세요" value={roomTitle} onChange={handleRoomTitleChange} className="room-title-input" />

        <button onClick={handleCreateRoom} className="create-room-button">
          방 생성
        </button>
        <button
          onClick={() => {
            setPopupView(false);
          }}
          className="close-button"
        >
          닫기
        </button>
      </div>
    </div>
  );
};

export default RedisCreateChatComponent;
