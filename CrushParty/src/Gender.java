
public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    NONBINARY("Non-Binary"),
    OTHER("Other");

    private final String textRepresentation;

    private Gender(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    @Override
    public String toString() {
        return textRepresentation;
    }

}
