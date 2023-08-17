public class Main {
    public static void main(String[] args) {
//        Animal cat = () -> System.out.println("meo meo");
//        cat.speak();

        People person1 = (firstName, lastName) -> firstName + " " + lastName;

        String fullName = person1.showInfo("Jack", "Pham");
        System.out.println(fullName);
    }
}
