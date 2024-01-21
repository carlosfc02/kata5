package software.ulpgc.kata5;

public interface Command {
    Output execute(Input input);

    interface Output{
        int responseCode();
        String result();
    }

    interface Input{
        String get(String parameter);
    }
}
