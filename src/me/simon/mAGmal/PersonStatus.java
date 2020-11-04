package me.simon.mAGmal;

public enum PersonStatus {
    NONE,
    LEHRER("Lehrerin"),
    SCHUELER("Schüler", "Schülerin");

    // --> generation of aliases
    final String[] aliases;
    PersonStatus (final String ... aliases) {
        this.aliases = aliases;
    }

    // --> compare aliases with Status
    public static PersonStatus get(final String name) {
        for (PersonStatus value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
            for (String alias : value.aliases) {
                if (alias.equalsIgnoreCase(name)) {
                    return value;
                }
            }
        }
        return NONE;
    }
}
