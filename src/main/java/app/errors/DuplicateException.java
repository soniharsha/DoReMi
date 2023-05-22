package app.errors;

public class DuplicateException extends GenericException {
    private String duplicateObjectName;
    public DuplicateException(String duplicateObjectName) {
        super();
        this.duplicateObjectName = duplicateObjectName;
    }

    @Override
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("DUPLICATE_");
        builder.append(duplicateObjectName);
        return builder.toString();
    }
}
