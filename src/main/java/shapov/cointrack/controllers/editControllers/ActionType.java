package shapov.cointrack.controllers.editControllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {
    CREATE("Добавить"),
    EDIT("Изменить");
    private final String value;
}
