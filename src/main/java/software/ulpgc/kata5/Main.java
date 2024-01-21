package software.ulpgc.kata5;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;



public class Main {
    static Map<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        commands.put("factorial", new FactorialCommand());
        commands.put("fibonacci", new FibonacciCommand());
        Spark.port(8081);
        Spark.get("/factorial/:number", (request, response) -> new CommandExecuter(request,response).execute("factorial"));
        Spark.get("/fibonacci/:number", (request, response) -> new CommandExecuter(request,response).execute("fibonacci"));

    }

    private static class CommandExecuter {
        private final Request request;
        private final Response reponse;
        public CommandExecuter(Request request, Response response) {
            this.request = request;
            this.reponse = response;
        }

        public String execute(String name) {
            Command command = commands.get(name);
            Command.Output output = command.execute(input());
            return output.result();
        }

        private Command.Input input() {
            return parameter -> oneOf(request.params(parameter), request.queryParams(parameter));
        }

        private String oneOf(String a, String b) {
            return a !=null ? a : b;
        }
    }
}
