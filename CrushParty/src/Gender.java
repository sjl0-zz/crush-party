public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    NONBINARY("Non-Binary"),
    OTHER("Other"),
    NOTHING("");

    private final String textRepresentation;

    Gender(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }

}
