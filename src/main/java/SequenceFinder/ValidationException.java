package SequenceFinder;

/**
 * Checked exception class,
 * used for validation process in SequenceFinder.App
 */
class ValidationException extends Exception {
    /**
     * Current exception type
     */
    private ValidationExceptionsType type;

    ValidationException(ValidationExceptionsType type){
        this.type = type;
    }

    ValidationExceptionsType getType() {
        return type;
    }
}
