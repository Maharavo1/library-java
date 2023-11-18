package Models;
public enum Topic {
    ROMANCE, COMEDY, OTHER;
    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
