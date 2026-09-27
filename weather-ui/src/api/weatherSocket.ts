import { Client } from "@stomp/stompjs";

import type { Weather } from "@/types/weather";

import { WS_URL } from "./config";

export function connectWeatherSocket(
  onWeather: (weather: Weather) => void,
): () => void {
  const client = new Client({
    brokerURL: WS_URL,
    reconnectDelay: 5000,

    debug: (str) => {
      console.log("STOMP:", str);
    },

    onConnect: () => {
      console.log("STOMP CONNECTED");

      client.subscribe("/topic/weather", (message) => {
        console.log("MESSAGE:", message.body);

        const data: Weather = JSON.parse(message.body);
        onWeather(data);
      });
    },

    onStompError: (frame) => {
      console.error("STOMP ERROR:", frame.headers);
    },

    onWebSocketError: (error) => {
      console.error("WS ERROR:", error);
    },

    onWebSocketClose: (event) => {
      console.log("WS CLOSED:", event.code, event.reason);
    },
  });

  console.log("Activating WebSocket...");
  client.activate();

  return () => {
    void client.deactivate();
  };
}
