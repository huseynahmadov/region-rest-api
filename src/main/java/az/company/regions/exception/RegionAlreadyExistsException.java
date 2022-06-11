package az.company.regions.exception;

public class RegionAlreadyExistsException extends RuntimeException {
    public RegionAlreadyExistsException(String msg) {
        super(msg);
    }
}
