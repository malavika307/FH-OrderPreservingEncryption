package org.example;//package org.example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FHOPETest {

    private int[] plaintexts;
    private int numberOfPlaintexts;
    private int securityParameter;

    // Parameters for the test
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Different plaintext arrays and corresponding FHOPE parameters
                { new int[]{1, 2, 1, 3, 4, 5, 4, 6, 8, 8, 9, 9, 9, 1, 1, 3, 3, 4, 5, 6}, 20, 3 },
                { new int[]{1, 2, 1, 3, 4, 5, 4, 6, 8, 8, 9, 9, 10, 10}, 14, 2 },
                { new int[]{1, 2, 1, 3, 1}, 5, 1 },
                { new int[]{
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
                }, 50, 7 },
                { new int[]{
                        24, 11, 32, 57, 23, 45, 9, 18, 53, 38,
                        19, 44, 14, 1, 21, 16, 29, 39, 47, 4,
                        60, 33, 13, 8, 36, 51, 48, 26, 40, 35,
                        12, 31, 56, 7, 50, 25, 3, 20, 34, 22,
                        28, 17, 30, 10, 55, 5, 52, 27, 37, 49,
                        6, 2, 41, 54, 15, 46, 43, 42, 62, 63,
                        64, 61, 32, 24, 13, 9, 57, 19, 18, 1,
                        44, 33, 45, 11, 38, 47, 29, 53, 39, 36,
                        21, 48, 7, 50, 16, 14, 26, 31, 56, 20,
                        40, 25, 3, 60, 35, 5, 8, 22, 28, 12,
                        17, 4, 55, 10, 34, 2, 41, 30, 6, 37,
                        52, 27, 43, 49, 42, 54, 15, 46, 62, 63,
                        64, 61, 1, 2, 3, 4, 5, 6
                }, 128, 1 },
                { new int[]{
                        256, 255, 254, 253, 252, 251, 250, 249, 248, 247,
                        246, 245, 244, 243, 242, 241, 240, 239, 238, 237,
                        236, 235, 234, 233, 232, 231, 230, 229, 228, 227,
                        226, 225, 224, 223, 222, 221, 220, 219, 218, 217,
                        216, 215, 214, 213, 212, 211, 210, 209, 208, 207,
                        206, 205, 204, 203, 202, 201, 200, 199, 198, 197,
                        196, 195, 194, 193, 192, 191, 190, 189, 188, 187,
                        186, 185, 184, 183, 182, 181, 180, 179, 178, 177,
                        176, 175, 174, 173, 172, 171, 170, 169, 168, 167,
                        166, 165, 164, 163, 162, 161, 160, 159, 158, 157,
                        156, 155, 154, 153, 152, 151, 150, 149, 148, 147,
                        146, 145, 144, 143, 142, 141, 140, 139, 138, 137,
                        136, 135, 134, 133, 132, 131, 130, 129, 128, 127,
                        126, 125, 124, 123, 122, 121, 120, 119, 118, 117,
                        116, 115, 114, 113, 112, 111, 110, 109, 108, 107,
                        106, 105, 104, 103, 102, 101, 100, 99, 98, 97,
                        96, 95, 94, 93, 92, 91, 90, 89, 88, 87,
                        86, 85, 84, 83, 82, 81, 80, 79, 78, 77,
                        76, 75, 74, 73, 72, 71, 70, 69, 68, 67,
                        66, 65, 64, 63, 62, 61, 60, 59, 58, 57,
                        56, 55, 54, 53, 52, 51, 50, 49, 48, 47,
                        46, 45, 44, 43, 42, 41, 40, 39, 38, 37,
                        36, 35, 34, 33, 32, 31, 30, 29, 28, 27,
                        26, 25, 24, 23, 22, 21, 20, 19, 18, 17,
                        16, 15, 14, 13, 12, 11, 10, 9, 8, 7,
                        6, 5, 4, 3, 2, 1
                }, 256, 3 },
                { new int[]{
                        2, 4, 5, 3, 3, 1, 5, 2, 5, 4,
                        4, 1, 3, 3, 2, 1, 1, 3, 4, 5
                }, 20, 3 },
                { new int[]{
                        1, 3, 1, 3, 1, 2, 1, 2, 3, 2
                }, 10, 1 },
                { new int[]{
                        3, 3, 8, 7, 2, 6, 9, 5, 4, 10,
                        7, 2, 9, 4, 1, 10, 5, 10, 6, 5,
                        9, 10, 8, 6, 7, 3, 8, 3, 1, 2
                }, 30, 3 },
        });
    }

    // Constructor with parameters
    public FHOPETest(int[] plaintexts, int numberOfPlaintexts, int securityParameter) {
        this.plaintexts = plaintexts;
        this.numberOfPlaintexts = numberOfPlaintexts;
        this.securityParameter = securityParameter;
    }

    @Test
    public void testMainFunction() {
        System.out.println("Running FHOPE with plaintexts: " + Arrays.toString(plaintexts) +
                ", numberOfPlaintexts: " + numberOfPlaintexts + ", securityParameter: " + securityParameter);

        // Arguments to pass to main method
        String[] args = new String[plaintexts.length + 2];
        args[0] = String.valueOf(numberOfPlaintexts);
        args[1] = String.valueOf(securityParameter);
        for (int i = 0; i < plaintexts.length; i++) {
            args[i + 2] = String.valueOf(plaintexts[i]);
        }
        long start = System.currentTimeMillis();
        FHOPE.main(args);
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start) + " MilliSeconds");
    }
}
