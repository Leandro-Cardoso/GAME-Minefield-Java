public class Main {
    public static void main(String[] args) {
        Field field = new Field(20, 10);
        
        field.activate(10, 5);
        field.activate(0, 0);
        field.activate(19, 9);

        System.out.println(field.str());
    }
}
