package az.company.regions.exception;

public class RegionNotFoundException extends RuntimeException {
    public RegionNotFoundException(String msg) {
        super(msg);
    }
}
