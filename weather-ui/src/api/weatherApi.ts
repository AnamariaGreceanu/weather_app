import type { HistoryHours, Weather, WeatherHistory } from "@/types/weather";

import { API_BASE_URL } from "./config";

export function isAbortError(error: unknown): boolean {
  return error instanceof Error && error.name === "AbortError";
}

export async function fetchCurrentWeather(
  signal?: AbortSignal,
): Promise<Weather> {
  const response = await fetch(`${API_BASE_URL}/api/weather`, { signal });

  if (!response.ok) {
    throw new Error(`Weather request failed: ${response.status}`);
  }

  return response.json();
}

export async function fetchWeatherHistory(
  hours: HistoryHours,
  signal?: AbortSignal,
): Promise<WeatherHistory[]> {
  const response = await fetch(
    `${API_BASE_URL}/api/weather/history?hours=${hours}`,
    { signal },
  );

  if (!response.ok) {
    throw new Error(`Weather history request failed: ${response.status}`);
  }

  return response.json();
}
