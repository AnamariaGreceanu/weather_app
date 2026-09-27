export type HistoryHours = 3 | 48;

export type Weather = {
  id: number;
  temperature: number;
  humidity: number;
  windSpeed: number;
  timestamp: string;
};

export type WeatherHistory = Weather;
