package com.neilren.neilren4j.test;

/**
 * Created by neil on 19/06/2017.
 */
public class test {
    /**
     * 1024 happy! By NeilRen
     *
     * @param args
     */
    public static void main(String[] args) {
        int base = 100;
        int iBase[] = new int[]{-51, -52, -50, -48, -68, 4, -3, 12, 12, 21, -67};
        char[] say = new char[iBase.length];
        for (int i = 0; i < say.length; i++) {
            say[i] = intToChar(base + iBase[i]);
        }
        System.out.println(String.valueOf(say));
    }

    private static char intToChar(int i) {
        return (char) i;
    }
}
