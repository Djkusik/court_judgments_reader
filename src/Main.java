import shell.Shell;

public class Main {

    public static void main(String[] args) {
        Shell shell;
        if (args.length == 2)
            shell = new Shell(args[0], args[1]);
        else if (args.length == 1)
            shell = new Shell(args[0]);
        else {
            System.out.println("You have to give at least one argument with path to judgment files, and one optional for logging!");
            return;
        }
        shell.init();
        shell.run();
    }
}
