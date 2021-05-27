package ua.gpu.seeker.controller.command;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CommandContainer {
    private static final Logger log = Logger.getLogger(String.valueOf(CommandContainer.class));

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("/", new MainPageCommand());
        commands.put("rating", new GpuRatingCommand());
        commands.put("oopsCommand", new OopsPageCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("oopsCommand");
        }
        return commands.get(commandName);
    }

}
