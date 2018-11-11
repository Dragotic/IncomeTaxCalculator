package incometaxcalculator.data.management;

public class Company {

    private final String name;
    private final Address address;

    public Company(final String name, final String country,
                   final String city, final String street,
                   final int number) {
        this.name = name;
        this.address = new Address(country, city, street, number);
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return address.getCountry();
    }

    public String getCity() {
        return address.getCity();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public int getNumber() {
        return address.getNumber();
    }
}
