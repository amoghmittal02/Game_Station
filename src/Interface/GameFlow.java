package Interface;

/**
 * interface for standard game flow
 */
public interface GameFlow {
    int init();

    void start(int beginRole);

    Integer winCheck();
}
