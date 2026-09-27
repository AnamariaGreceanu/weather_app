import { useEffect, useState } from "react";

import {
  fetchCurrentWeather,
  fetchWeatherHistory,
  isAbortError,
} from "@/api/weatherApi";
import { connectWeatherSocket } from "@/api/weatherSocket";
import type { HistoryHours, Weather, WeatherHistory } from "@/types/weather";

export function useWeather() {
  const [weather, setWeather] = useState<Weather | null>(null);
  const [history, setHistory] = useState<WeatherHistory[]>([]);
  const [historyHours, setHistoryHours] = useState<HistoryHours>(3);

  useEffect(() => {
    const controller = new AbortController();

    fetchCurrentWeather(controller.signal)
      .then(setWeather)
      .catch((error) => {
        if (isAbortError(error)) {
          return;
        }

        console.error("Error fetching weather:", error);
      });

    const disconnect = connectWeatherSocket(setWeather);

    return () => {
      controller.abort();
      disconnect();
    };
  }, []);

  useEffect(() => {
    const controller = new AbortController();

    fetchWeatherHistory(historyHours, controller.signal)
      .then(setHistory)
      .catch((error) => {
        if (isAbortError(error)) {
          return;
        }

        console.error("Error fetching history:", error);
      });

    return () => {
      controller.abort();
    };
  }, [historyHours]);

  return { weather, history, historyHours, changeHistory: setHistoryHours };
}
