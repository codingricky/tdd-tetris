// Copyright (c) 2008-2010  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tddtetris;

import net.orfjackal.nestedjunit.NestedJUnit4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(NestedJUnit4.class)
public class MovingAFallingPieceTest extends Assert {

    // TODO: a falling piece can be moved left
    private final Board board = new Board(6, 8);


    public class A_falling_piece {

        @Before
        public void dropPiece() {
            board.drop(Tetromino.T_SHAPE);
        }

        @Test
        public void can_be_moved_left() {
            board.move(Direction.LEFT);
            assertEquals("" +
                    "...T....\n" +
                    "..TTT...\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void can_be_moved_right() {
            board.move(Direction.RIGHT);
            assertEquals("" +
                    ".....T..\n" +
                    "....TTT.\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void can_not_move_right_over_the_board() {
            board.move(Direction.RIGHT);
            board.move(Direction.RIGHT);
            board.move(Direction.RIGHT);
            board.move(Direction.RIGHT);
            board.move(Direction.RIGHT);
            board.move(Direction.RIGHT);
            assertEquals("" +
                    "......T.\n" +
                    ".....TTT\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void can_not_move_left_over_the_board() {
            board.move(Direction.LEFT);
            board.move(Direction.LEFT);
            board.move(Direction.LEFT);
            board.move(Direction.LEFT);
            board.move(Direction.LEFT);
            board.move(Direction.LEFT);
            assertEquals("" +
                    ".T......\n" +
                    "TTT.....\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }


        @Test
        public void can_not_move_down_over_the_board() {
            board.move(Direction.DOWN);
            board.move(Direction.DOWN);
            board.move(Direction.DOWN);
            board.move(Direction.DOWN);
            board.move(Direction.DOWN);
            board.move(Direction.DOWN);
            assertEquals("" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "....T...\n" +
                    "...TTT..\n", board.toString());
        }
    }

    public class If_another_piece_is_in_the_way {

        @Before
        public void dropPiece() {
            board.drop(Tetromino.T_SHAPE);
        }

        @Test
        public void can_not_be_moved_right_if_another_piece_is_in_the_way() {
            for (int i = 0; i < 5; i++) {
                board.move(Direction.DOWN);
                board.move(Direction.RIGHT);
            }
            board.drop(Tetromino.T_SHAPE);

            board.move(Direction.LEFT);
            for (int i = 0; i < 4; i++) {
                board.move(Direction.DOWN);
            }

            board.move(Direction.RIGHT);

            assertEquals("" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "...T..T.\n" +
                    "..TTTTTT\n", board.toString());
        }

        @Test
        public void can_not_be_moved_left_if_another_piece_is_in_the_way() {
            for (int i = 0; i < 5; i++) {
                board.move(Direction.DOWN);
                board.move(Direction.LEFT);
            }
            board.drop(Tetromino.T_SHAPE);

            for (int i = 0; i < 4; i++) {
                board.move(Direction.DOWN);
            }

            board.move(Direction.LEFT);
            assertEquals("" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    ".T..T...\n" +
                    "TTTTTT..\n", board.toString());
        }

        @Test
        public void can_not_be_moved_down_if_another_piece_is_in_the_way() {
            for (int i = 0; i < 5; i++) {
                board.move(Direction.DOWN);
            }

            board.drop(Tetromino.T_SHAPE);
            for (int i = 0; i < 4; i++) {
                board.move(Direction.DOWN);
            }

            assertEquals("" +
                    "........\n" +
                    "........\n" +
                    "....T...\n" +
                    "...TTT..\n" +
                    "....T...\n" +
                    "...TTT..\n", board.toString());
        }
    }
}
