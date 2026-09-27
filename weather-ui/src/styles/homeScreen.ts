import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "#ffffff",
  },

  container: {
    backgroundColor: "#ffffff",
    padding: 24,
    paddingBottom: 40,
  },

  title: {
    color: "#111111",
    fontSize: 30,
    fontWeight: "bold",
    textAlign: "center",
  },

  location: {
    color: "#555555",
    fontSize: 18,
    textAlign: "center",
    marginTop: 8,
  },

  temperature: {
    color: "#111111",
    fontSize: 64,
    fontWeight: "bold",
    textAlign: "center",
    marginVertical: 40,
  },

  card: {
    backgroundColor: "#eeeeee",
    padding: 20,
    borderRadius: 12,
    marginBottom: 12,
  },

  label: {
    color: "#555555",
    fontSize: 16,
  },

  value: {
    color: "#111111",
    fontSize: 24,
    fontWeight: "bold",
    marginTop: 4,
  },

  updated: {
    color: "#777777",
    textAlign: "center",
    marginTop: 20,
  },

  historyContainer: {
    marginTop: 40,
  },

  historyTitle: {
    color: "#111111",
    fontSize: 24,
    fontWeight: "bold",
    marginBottom: 16,
  },

  buttons: {
    flexDirection: "row",
    gap: 10,
    marginBottom: 20,
  },

  historyButton: {
    flex: 1,
    paddingVertical: 12,
    borderRadius: 10,
    backgroundColor: "#eeeeee",
    alignItems: "center",
  },

  activeButton: {
    backgroundColor: "#111111",
  },

  buttonText: {
    color: "#555555",
    fontSize: 16,
    fontWeight: "600",
  },

  activeButtonText: {
    color: "#ffffff",
  },

  table: {
    marginTop: 10,
    borderRadius: 12,
    overflow: "hidden",
    backgroundColor: "#eeeeee",
  },

  tableHeader: {
    flexDirection: "row",
    padding: 12,
    backgroundColor: "#dddddd",
  },

  tableHeaderText: {
    flex: 1,
    fontWeight: "bold",
    color: "#111111",
    textAlign: "center",
  },

  tableRow: {
    flexDirection: "row",
    paddingVertical: 12,
    paddingHorizontal: 8,
    borderTopWidth: 1,
    borderTopColor: "#dddddd",
  },

  tableText: {
    flex: 1,
    color: "#333333",
    textAlign: "center",
  },
});
