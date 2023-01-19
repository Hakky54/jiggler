package nl.altindag.jiggler.command;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Command(name = "jiggle")
public class JiggleCommand implements Runnable {

    @Option(names = {"-p", "--pattern"}, description = "The pattern of the movement of the mouse")
    private Pattern pattern = Pattern.MINI_SQUARE;

    @Override
    public void run() {
        try {
            pattern.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    enum Pattern {

        MINI_SQUARE("mini-square") {
            @Override
            public void run() throws Exception {
                Robot robot = getRobot().call();
                int[][] positions = {
                        {2, 0, -2, 0},
                        {0, 2, 0, -2}
                };

                while (true) {
                    for (int i = 0; i < 4; i++) {
                        Point currentPoint = MouseInfo.getPointerInfo().getLocation();
                        int pointX = currentPoint.x + positions[0][i];
                        int pointy = currentPoint.y + positions[1][i];
                        robot.mouseMove(pointX, pointy);

                        TimeUnit.SECONDS.sleep(1);
                    }
                }
            }
        },

        SQUARE("square") {
            @Override
            public void run() throws Exception {
                Robot robot = getRobot().call();

                int[][] positions = {
                        {2, 0, -2, 0},
                        {0, 2, 0, -2}
                };

                BigInteger horizontalMovement = BigInteger.valueOf(2);
                BigInteger verticalMovement = BigInteger.valueOf(0);

                while (true) {
                    for (int i = 0; i < 4; i++) {
                        for (int y = 0; y < 100; y++) {
                            Point currentPoint = MouseInfo.getPointerInfo().getLocation();
                            int pointX = currentPoint.x + horizontalMovement.intValue();
                            int pointy = currentPoint.y + verticalMovement.intValue();
                            robot.mouseMove(pointX, pointy);

                            TimeUnit.MILLISECONDS.sleep(100);
                        }

                        horizontalMovement = BigInteger.valueOf(positions[0][i]);
                        verticalMovement = BigInteger.valueOf(positions[1][i]);
                    }
                }
            }
        };

        private final String name;

        Pattern(String name) {
            this.name = name;
        }

        public abstract void run() throws Exception;

        private static Callable<Robot> getRobot() {
            return Robot::new;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
