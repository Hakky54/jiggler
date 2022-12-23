package nl.altindag.jiggler4j.command;

import picocli.CommandLine.Command;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

@Command(name = "jiggle")
public class JiggleCommand implements Runnable {

    @Override
    public void run() {
        try {
            Robot robot = new Robot();

            int[][] positions = {
                    {3, 0, -3, 0},
                    {0, 3, 0, -3}
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
        } catch (InterruptedException | AWTException e) {
            throw new RuntimeException(e);
        }
    }

}
