package tddtetris;

public class Board {

    private static final Blockable EMPTY_BLOCK = null;

    private final int rows;
    private final int columns;

    private Blockable currentBlock;
    private Blockable[][] blocks;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        blocks = new Blockable[columns][rows];
    }

    public boolean hasFalling() {
        return currentBlock != null;
    }

    public void drop(Blockable block) {
        if (currentBlock != null) {
            throw new IllegalStateException("already falling");
        }

        int targetRow = block.getDepth();
        int targetColumn = columns / 2;
        currentBlock = block.newBlockableRelativeToNewCentrePoint(new Point(targetColumn, targetRow));
        add(currentBlock);
    }

    private void add(Blockable block) {
        for (Point point : block.getPoints()) {
            blocks[point.getX()][point.getY()] = block;
        }
    }

    private void remove(Blockable block) {
        for (Point point : block.getPoints()) {
            blocks[point.getX()][point.getY()] = EMPTY_BLOCK;
        }
    }


    public void tick() {
        move(Direction.DOWN);
    }

    public void move(Direction direction) {
        if (currentBlock == null) {
            return;
        }

        if (hasBlockHitTheBottom(currentBlock)) {
            currentBlock = EMPTY_BLOCK;
            return;
        }

        Blockable oldBlock = currentBlock;
        currentBlock = currentBlock.move(direction);
        remove(oldBlock);

        if (!canBlockFit(currentBlock)) {
            add(oldBlock);

            if (direction == Direction.DOWN) {
                // can't move any further down
                currentBlock = EMPTY_BLOCK;
            } else {
                currentBlock = oldBlock;
            }

        } else {
            add(currentBlock);
        }
    }

    private boolean hasBlockHitTheBottom(Blockable block) {
        for (Point point : block.getPoints()) {
            if (point.getY() == (rows - 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean canBlockFit(Blockable block) {
        for (Point point : block.getPoints()) {
            if (point.getY() > (rows - 1)) {
                return false;
            }

            if (point.getX() > (columns - 1)) {
                return false;
            }

            if (point.getX() < 0) {
                return false;
            }

            if (blocks[point.getX()][point.getY()] != EMPTY_BLOCK) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                Blockable block = blocks[x][y];
                if (block == null) {
                    stringBuilder.append(".");
                } else {
                    stringBuilder.append(block.getBlock());
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
