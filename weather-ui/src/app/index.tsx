import { ScrollView, Text, TouchableOpacity, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

import { useWeather } from "@/hooks/useWeather";
import { styles } from "@/styles/homeScreen";
import { formatTime, formatTimestamp } from "@/utils/formatTimestamp";

export default function HomeScreen() {
  const { weather, history, historyHours, changeHistory } = useWeather();

  return (
    <SafeAreaView style={styles.screen}>
      <ScrollView
        contentContainerStyle={styles.container}
        showsVerticalScrollIndicator={true}
      >
        <Text style={styles.location}>Bucharest</Text>

        <Text style={styles.temperature}>
          {weather ? `${weather.temperature}°C` : "..."}
        </Text>

        <View style={styles.card}>
          <Text style={styles.label}>Humidity</Text>

          <Text style={styles.value}>
            {weather ? `${weather.humidity}%` : "..."}
          </Text>
        </View>

        <View style={styles.card}>
          <Text style={styles.label}>Wind</Text>

          <Text style={styles.value}>
            {weather ? `${weather.windSpeed} km/h` : "..."}
          </Text>
        </View>

        <Text style={styles.updated}>
          {weather
            ? `Last update: ${formatTimestamp(weather.timestamp)}`
            : "Loading..."}
        </Text>
        <View style={styles.historyContainer}>
          <Text style={styles.historyTitle}>Istoric temperatură</Text>

          <View style={styles.buttons}>
            <TouchableOpacity
              style={[
                styles.historyButton,
                historyHours === 3 && styles.activeButton,
              ]}
              onPress={() => changeHistory(3)}
            >
              <Text
                style={[
                  styles.buttonText,
                  historyHours === 3 && styles.activeButtonText,
                ]}
              >
                3 ore
              </Text>
            </TouchableOpacity>

            <TouchableOpacity
              style={[
                styles.historyButton,
                historyHours === 48 && styles.activeButton,
              ]}
              onPress={() => changeHistory(48)}
            >
              <Text
                style={[
                  styles.buttonText,
                  historyHours === 48 && styles.activeButtonText,
                ]}
              >
                2 zile
              </Text>
            </TouchableOpacity>
          </View>

          {history.length > 0 && (
            <View style={styles.table}>
              <View style={styles.tableHeader}>
                <Text style={styles.tableHeaderText}>Ora</Text>
                <Text style={styles.tableHeaderText}>Temp.</Text>
                <Text style={styles.tableHeaderText}>Umid.</Text>
                <Text style={styles.tableHeaderText}>Vânt</Text>
              </View>

              {history.map((item) => (
                <View style={styles.tableRow} key={item.id}>
                  <Text style={styles.tableText}>
                    {formatTime(item.timestamp)}
                  </Text>

                  <Text style={styles.tableText}>{item.temperature}°C</Text>

                  <Text style={styles.tableText}>{item.humidity}%</Text>

                  <Text style={styles.tableText}>{item.windSpeed} km/h</Text>
                </View>
              ))}
            </View>
          )}
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}
