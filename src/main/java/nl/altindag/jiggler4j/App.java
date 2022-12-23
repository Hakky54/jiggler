package nl.altindag.jiggler4j;

import nl.altindag.jiggler4j.command.JiggleCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "jiggler4j",
        version = "jiggler4j v1.0.0",
        description = "CLI tool to jiggle your mouse"
)
public class App {

    public static void main(String[] applicationArguments) {
        new CommandLine(new JiggleCommand())
                .setCaseInsensitiveEnumValuesAllowed(true)
                .execute(applicationArguments);
    }

}
