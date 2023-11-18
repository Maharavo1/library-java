package Models;

public class Subscribers extends User{
    private String reference ;

    public Subscribers(int id, String name, String reference) {
        super(id,name);
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Subscribers{" +
                super.toString()+
                "reference='" + reference + '\'' +
                '}';
    }
}
