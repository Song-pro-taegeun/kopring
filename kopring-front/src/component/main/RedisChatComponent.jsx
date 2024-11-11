import React, { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";
import { GET, POST } from "../../service/fetch-auth-action";
import { useNavigate } from "react-router-dom";

const RedisChatComponent = () => {
  const nav = useNavigate();
  const [messages, setMessages] = useState([]);
  const [inputData, setInputData] = useState("");
  const [name, setName] = useState("");
  const [channel, setChannel] = useState("");

  useEffect(() => {
    if (!channel) return; // 채널이 설정되지 않으면 구독하지 않음

    // WebSocket 서버에 연결 설정
    const socket = new SockJS("http://localhost:8080/ws");
    const client = Stomp.over(socket);
    client.connect({}, () => {
      client.subscribe("/topic/" + channel, (message) => {
        setMessages((prevMessages) => [...prevMessages, JSON.parse(message.body)]);
      });
    });

    // 언마운트 시 소켓과 Redis 연결 해제
    return () => {
      if (client) {
        disConnectRedisAndWebSocket(client);
      }
    };
  }, [channel]);

  const disConnectRedisAndWebSocket = async (client) => {
    try {
      const response = await GET(`/chat/cancle?channel=${channel}`);
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
      sender: name,
      roomId: channel,
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
    <div>
      <h2>실시간 채팅 - 웹사용자</h2>
      <div>
        이름 :{" "}
        <input
          onChange={(e) => {
            setName(e.target.value);
          }}
        />
      </div>
      <div>
        채널 :{" "}
        <input
          onChange={(e) => {
            setChannel(e.target.value);
          }}
        />
      </div>
      <br />
      <div>
        {messages.map((msg, index) => (
          <div key={index}>
            <span>[{msg.sender}] : </span> <span key={index}>{msg.message}</span>
          </div>
        ))}
      </div>

      <div>
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
        <button onClick={handleSenderClick}>전송</button>

        <button
          onClick={() => {
            nav("/");
          }}
        >
          home
        </button>
      </div>
    </div>
  );
};

export default RedisChatComponent;
