package tddtetris;

public interface Blockable {
    Blockable move(Direction direction);
    Point[] getPoints();
    int getDepth();
    Blockable newBlockableRelativeToNewCentrePoint(Point centrePoint);
    char getBlock();
}
