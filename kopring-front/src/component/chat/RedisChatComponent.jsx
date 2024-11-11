import React, { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";
import { GET, POST } from "../../service/fetch-auth-action";

const RedisChatComponent = ({ setEnterRoom, enterRoomInfo, setEnterRoomInfo }) => {
  const [messages, setMessages] = useState([]);
  const [inputData, setInputData] = useState("");

  useEffect(() => {
    if (!enterRoomInfo.roomId) return; // 채널이 설정되지 않으면 구독하지 않음

    // WebSocket 서버에 연결 설정
    const socket = new SockJS("http://localhost:8080/ws");
    const client = Stomp.over(socket);
    client.connect({}, () => {
      client.subscribe("/topic/" + enterRoomInfo.roomId, (message) => {
        setMessages((prevMessages) => [...prevMessages, JSON.parse(message.body)]);
      });
    });

    // 언마운트 시 소켓과 Redis 연결 해제
    return () => {
      if (client) {
        disConnectRedisAndWebSocket(client);
      }
    };
  }, [enterRoomInfo.roomId]);

  const disConnectRedisAndWebSocket = async (client) => {
    try {
      const response = await GET(`/chat/cancle?channel=${enterRoomInfo.roomId}`);
      if (response.status === 200) {
        client.disconnect(() => {
          console.log("웹 소켓/레디스 구독 종료 ");
        });
      }
    } catch (error) {
      console.error("구독 종료 실패:", error);
    }
  };

  const handleSenderClick = async () => {
    const param = {
      message: inputData,
      sender: enterRoomInfo.userId,
      roomId: enterRoomInfo.roomId,
    };

    const url = `/chat/send`;
    const response = await POST(url, param, null);

    if (response.status !== 200) {
      alert("메시지 전송에 실패하셨습니다.");
    } else {
      setInputData("");
    }
  };

  return (
    <div className="chat-container">
      <h2>실시간 채팅 - {enterRoomInfo.roomName}</h2>
      <div className="message-list">
        {messages.map((msg, index) => {
          const isMyMessage = msg.sender === enterRoomInfo.userId;
          return (
            <div key={index} className={isMyMessage ? "my-message" : "other-message"}>
              <span>{isMyMessage ? msg.message : `[${msg.sender}] : ${msg.message}`}</span>
            </div>
          );
        })}
      </div>

      <div className="input-container">
        <input
          value={inputData}
          type="text"
          onChange={(e) => {
            setInputData(e.target.value);
          }}
          onKeyDown={(e) => {
            if (e.keyCode === 13) {
              handleSenderClick();
            }
          }}
        />
        <button className="send-button" onClick={handleSenderClick}>
          전송
        </button>
        <button
          className="back-button"
          onClick={() => {
            setEnterRoom(false);
            setEnterRoomInfo(null);
          }}
        >
          뒤로가기
        </button>
      </div>
    </div>
  );
};

export default RedisChatComponent;
