package shapov.cointrack.controllers;


public enum DisplayType {
    PANEL("Панель"),
    TABLE("Таблица");
    private final String value;

    DisplayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
