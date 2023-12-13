package shapov.cointrack.controllers.editControllers;


public enum ActionType {
    CREATE("Добавить"),
    EDIT("Изменить");
    private final String value;

    ActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
