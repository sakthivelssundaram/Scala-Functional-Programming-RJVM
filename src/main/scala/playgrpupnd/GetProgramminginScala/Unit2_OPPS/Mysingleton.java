package playgrpupnd.GetProgramminginScala.Unit2_OPPS;

class ClassicSingleton{}

public class Mysingleton extends ClassicSingleton{
    private static ClassicSingleton instance = null;

    private Mysingleton(){}

    public static ClassicSingleton getInstance() {
        if(instance == null)
            instance = new ClassicSingleton();
        return instance;
    }

    public static void main(String arg[]) {
        Mysingleton my = new Mysingleton();
        Mysingleton my1 = new Mysingleton();
        ClassicSingleton apple1 = my.getInstance();
        ClassicSingleton apple2 = my.getInstance();

        System.out.println(apple1 == apple1);
        System.out.println(apple1.equals(apple2));

        System.out.println(my == my1);
        System.out.println(my.equals(my1));
    }
}
