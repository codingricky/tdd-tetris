package tddtetris;

import java.util.ArrayList;
import java.util.List;

public class Tetromino implements Blockable {

    private static final Piece T_PIECE = new Piece("" +
            ".T.\n" +
            "TTT\n" +
            "...\n");

    public static final Tetromino T_SHAPE = new Tetromino(1, T_PIECE, T_PIECE.rotateRight(), T_PIECE.rotateRight().rotateRight(), T_PIECE.rotateRight().rotateRight().rotateRight());
    public static final Tetromino I_SHAPE = new Tetromino(3,
            new Piece("" +
                    ".....\n" +
                    ".....\n" +
                    "IIII.\n" +
                    ".....\n" +
                    ".....\n"),
            new Piece("" +
                    ".....\n" +
                    "..I..\n" +
                    "..I..\n" +
                    "..I..\n" +
                    "..I..\n"));

    public static final Tetromino O_SHAPE = new Tetromino(2, new Piece("" +
            ".OO\n" +
            ".OO\n" +
            "...\n"));

    public static final Tetromino Z_SHAPE = new Tetromino(3, new Piece("" +
            "...\n" +
            ".ZZ\n" +
            "ZZ.\n"),
            new Piece("" +
                    ".Z.\n" +
                    ".ZZ\n" +
                    "..Z\n"));

    public static final Tetromino S_SHAPE = new Tetromino(3, new Piece("" +
            "...\n" +
            "SS.\n" +
            ".SS\n"),
            new Piece("" +
                    "..S\n" +
                    ".SS\n" +
                    ".S.\n"));

    public static final Tetromino L_SHAPE = new Tetromino(2, new Piece("" +
            ".L.\n" +
            ".L.\n" +
            ".LL\n"),
            new Piece("" +
            "...\n" +
            "LLL\n" +
            "L..\n"),
            new Piece("" +
            "LL.\n" +
            ".L.\n" +
            ".L.\n"),
            new Piece("" +
            "..L\n" +
            "LLL\n" +
            "...\n"));


    public static final Tetromino J_SHAPE = new Tetromino(2, new Piece("" +
            ".J.\n" +
            ".J.\n" +
            "JJ.\n"),
            new Piece("" +
                    "J..\n" +
                    "JJJ\n" +
                    "...\n"),
            new Piece("" +
                    ".JJ\n" +
                    ".J.\n" +
                    ".J.\n"),
            new Piece("" +
                    "...\n" +
                    "JJJ\n" +
                    "..J\n"));

    private final Piece[] pieces;
    private final int currentOrientation;
    private final int depth;


    public Tetromino(int depth, Piece... pieces) {
        this.depth = depth;
        this.pieces = pieces;
        currentOrientation = 0;
    }

    public Tetromino(int depth, int currentRotation, Piece[] pieces) {
        this.depth = depth;
        this.pieces = pieces;
        this.currentOrientation = currentRotation;
    }

    public Tetromino rotateRight() {
        if ((currentOrientation + 1) == pieces.length) {
            return new Tetromino(depth, 0, pieces);
        } else {
            return new Tetromino(depth, currentOrientation + 1, pieces);
        }
    }

    public Tetromino rotateLeft() {
        if ((currentOrientation - 1) < 0) {
            return new Tetromino(depth, pieces.length - 1, pieces);
        } else {
            return new Tetromino(depth, currentOrientation - 1, pieces);
        }

    }

    public Piece getCurrentOrientation() {
        return pieces[currentOrientation];
    }

    public Tetromino move(Direction direction) {
        Point newCentrePoint = getCurrentOrientation().getCentrePoint().move(direction);
        List<Piece> piecesRelativeToNewCentrePoint = new ArrayList<Piece>(pieces.length);
        for (Piece piece : pieces) {
            piecesRelativeToNewCentrePoint.add(piece.relativeToCentrePoint(newCentrePoint));
        }
        return new Tetromino(depth, currentOrientation, piecesRelativeToNewCentrePoint.toArray(new Piece[pieces.length]));
    }

    public Point[] getPoints() {
        return getCurrentOrientation().getPoints();
    }

    public int getDepth() {
        return depth;
    }

    public Tetromino newBlockableRelativeToNewCentrePoint(Point centrePoint) {
        List<Piece> piecesRelativeToNewCentrePoint = new ArrayList<Piece>(pieces.length);
        for (Piece piece : pieces) {
            piecesRelativeToNewCentrePoint.add(piece.relativeToCentrePoint(centrePoint));
        }

        return new Tetromino(depth, currentOrientation, piecesRelativeToNewCentrePoint.toArray(new Piece[pieces.length]));
    }

    public char getBlock() {
        return getCurrentOrientation().getBlock();
    }

    @Override
    public String toString() {
        return getCurrentOrientation().toString();
    }
}
