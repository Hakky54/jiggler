/*
 * Copyright 2022 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.jiggler;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws AWTException, InterruptedException {
        int[][] positions = {
                {2, 0, -2, 0},
                {0, 2, 0, -2}
        };

        Robot robot = new Robot();
        while (true) {
            for (int i = 0; i < 4; i++) {
                Point currentPoint = MouseInfo.getPointerInfo().getLocation();
                int pointX = currentPoint.x + positions[0][i];
                int pointy = currentPoint.y + positions[1][i];
                robot.mouseMove(pointX, pointy);

                TimeUnit.SECONDS.sleep(2);
            }
        }
    }

}
