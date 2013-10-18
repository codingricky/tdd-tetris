// Copyright (c) 2008-2010  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tddtetris;

import net.orfjackal.nestedjunit.NestedJUnit4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Esko Luontola
 */
@RunWith(NestedJUnit4.class)
public class RotatingTetrominoesTest extends Assert {


    private Tetromino shape;


    public class All_shape_instances {

        @Before
        public void createAnyShape() {
            shape = Tetromino.T_SHAPE;
        }

        @Test
        public void are_immutable() {
            String original = shape.toString();
            shape.rotateRight();
            assertEquals(original, shape.toString());
            shape.rotateLeft();
            assertEquals(original, shape.toString());
        }
    }


    public class The_T_shape {

        @Before
        public void createTShape() {
            shape = Tetromino.T_SHAPE;
        }

        @Test
        public void is_shaped_like_T() {
            assertEquals("" +
                    ".T.\n" +
                    "TTT\n" +
                    "...\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right_3_times() {
            shape = shape.rotateRight();
            assertEquals("" +
                    ".T.\n" +
                    ".TT\n" +
                    ".T.\n", shape.toString());
            shape = shape.rotateRight();
            assertEquals("" +
                    "...\n" +
                    "TTT\n" +
                    ".T.\n", shape.toString());
            shape = shape.rotateRight();
            assertEquals("" +
                    ".T.\n" +
                    "TT.\n" +
                    ".T.\n", shape.toString());
        }

        @Test
        public void can_be_rotated_left_3_times() {
            shape = shape.rotateLeft();
            assertEquals("" +
                    ".T.\n" +
                    "TT.\n" +
                    ".T.\n", shape.toString());
            shape = shape.rotateLeft();
            assertEquals("" +
                    "...\n" +
                    "TTT\n" +
                    ".T.\n", shape.toString());
            shape = shape.rotateLeft();
            assertEquals("" +
                    ".T.\n" +
                    ".TT\n" +
                    ".T.\n", shape.toString());
        }

        @Test
        public void rotating_it_4_times_will_go_back_to_the_original_shape() {
            String originalShape = shape.toString();
            shape = shape.rotateRight().rotateRight().rotateRight().rotateRight();
            assertEquals(originalShape, shape.toString());
            shape = shape.rotateLeft().rotateLeft().rotateLeft().rotateLeft();
            assertEquals(originalShape, shape.toString());
        }
    }



    public class The_I_shape {

        @Before
        public void createIShape() {
            shape = Tetromino.I_SHAPE;
        }

        @Test
        public void is_shaped_like_I() {
            assertEquals("" +
                    ".....\n" +
                    ".....\n" +
                    "IIII.\n" +
                    ".....\n" +
                    ".....\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right_once() {
            shape = shape.rotateRight();
            assertEquals("" +
                    ".....\n" +
                    "..I..\n" +
                    "..I..\n" +
                    "..I..\n" +
                    "..I..\n", shape.toString());
        }

        @Test
        public void can_be_rotated_left_once() {
            shape = shape.rotateLeft();
            assertEquals("" +
                    ".....\n" +
                    "..I..\n" +
                    "..I..\n" +
                    "..I..\n" +
                    "..I..\n", shape.toString());
        }

        @Test
        public void rotating_it_twice_will_get_back_to_the_original_shape() {
            String originalShape = shape.toString();
            shape = shape.rotateRight().rotateRight();
            assertEquals(originalShape, shape.toString());
            shape = shape.rotateLeft().rotateLeft();
            assertEquals(originalShape, shape.toString());
        }
    }



    public class The_O_shape {

        @Before
        public void createOShape() {
            shape = Tetromino.O_SHAPE;
        }

        @Test
        public void is_shaped_like_O() {
            assertEquals("" +
                    ".OO\n" +
                    ".OO\n" +
                    "...\n", shape.toString());
        }

        @Test
        public void can_not_be_rotated_right() {
            shape = shape.rotateRight();
            assertEquals("" +
                    ".OO\n" +
                    ".OO\n" +
                    "...\n", shape.toString());
        }

        @Test
        public void can_not_be_rotated_left() {
            shape = shape.rotateLeft();
            assertEquals("" +
                    ".OO\n" +
                    ".OO\n" +
                    "...\n", shape.toString());
        }
    }

    public class The_Z_shape {

        @Before
        public void createZShape() {
            shape = Tetromino.Z_SHAPE;
        }

        @Test
        public void is_shaped_like_Z() {
            assertEquals("" +
                    "...\n" +
                    ".ZZ\n" +
                    "ZZ.\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right() {
            shape = shape.rotateRight();
            assertEquals("" +
                    ".Z.\n" +
                    ".ZZ\n" +
                    "..Z\n", shape.toString());
        }

        @Test
        public void can_be_rotated_left() {
            shape = shape.rotateLeft();
            assertEquals("" +
                    ".Z.\n" +
                    ".ZZ\n" +
                    "..Z\n", shape.toString());
        }
    }

    public class The_S_shape {

        @Before
        public void createSShape() {
            shape = Tetromino.S_SHAPE;
        }

        @Test
        public void is_shaped_like_S() {
            assertEquals("" +
                    "...\n" +
                    "SS.\n" +
                    ".SS\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right() {
            shape = shape.rotateRight();
            assertEquals("" +
                    "..S\n" +
                    ".SS\n" +
                    ".S.\n", shape.toString());
        }

        @Test
        public void can_be_rotated_left() {
            shape = shape.rotateLeft();
            assertEquals("" +
                    "..S\n" +
                    ".SS\n" +
                    ".S.\n", shape.toString());
        }
    }

    public class The_L_shape {

        @Before
        public void createLShape() {
            shape = Tetromino.L_SHAPE;
        }

        @Test
        public void is_shaped_like_L() {
            assertEquals("" +
                    ".L.\n" +
                    ".L.\n" +
                    ".LL\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right() {
            shape = shape.rotateRight();
            assertEquals("" +
                    "...\n" +
                    "LLL\n" +
                    "L..\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right_twice() {
            shape = shape.rotateRight().rotateRight();
            assertEquals("" +
                    "LL.\n" +
                    ".L.\n" +
                    ".L.\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right_three_times() {
            shape = shape.rotateRight().rotateRight().rotateRight();
            assertEquals("" +
                    "..L\n" +
                    "LLL\n" +
                    "...\n", shape.toString());
        }

        @Test
        public void can_be_rotated_left() {
            shape = shape.rotateLeft();
            assertEquals("" +
                    "..L\n" +
                    "LLL\n" +
                    "...\n", shape.toString());
        }
    }

    public class The_J_shape {

        @Before
        public void createJShape() {
            shape = Tetromino.J_SHAPE;
        }

        @Test
        public void is_shaped_like_J() {
            assertEquals("" +
                    ".J.\n" +
                    ".J.\n" +
                    "JJ.\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right() {
            shape = shape.rotateRight();
            assertEquals("" +
                    "J..\n" +
                    "JJJ\n" +
                    "...\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right_twice() {
            shape = shape.rotateRight().rotateRight();
            assertEquals("" +
                    ".JJ\n" +
                    ".J.\n" +
                    ".J.\n", shape.toString());
        }

        @Test
        public void can_be_rotated_right_three_times() {
            shape = shape.rotateRight().rotateRight().rotateRight();
            assertEquals("" +
                    "...\n" +
                    "JJJ\n" +
                    "..J\n", shape.toString());
        }

        @Test
        public void can_be_rotated_left() {
            shape = shape.rotateLeft();
            assertEquals("" +
                    "...\n" +
                    "JJJ\n" +
                    "..J\n", shape.toString());
        }
    }
}
