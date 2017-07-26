/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import java.util.Random;

/**
 *
 * @author chenyufeng
 */
public class SeedUtil {

    public static int getIntRandom() {
        Random random = new Random();
        int x = random.nextInt();
        return x;
    }

    public static int getIntRandom(int min, int max) {
        Random random = new Random();
        int x = random.nextInt(max) % (max - min + 1) + min;
        return x;
    }
}
