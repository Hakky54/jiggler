package nl.altindag.jiggler;

import nl.altindag.jiggler.command.JiggleCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "jiggler",
        version = "jiggler v1.0.0",
        description = "CLI tool to jiggle your mouse"
)
public class App {

    public static void main(String[] applicationArguments) {
        new CommandLine(new JiggleCommand())
                .setCaseInsensitiveEnumValuesAllowed(true)
                .execute(applicationArguments);
    }

}
