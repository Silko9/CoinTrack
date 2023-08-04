package shapov.cointrack.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DisplayType {
    PANEL("Панель"),
    TABLE("Таблица");
    private final String value;
}
