package CPDA;

/**
 * 自定义异常
 *
 * @author cheng
 * @create 2018-12-06 16:48
 */
// 自定义异常
public class IndexException extends Exception {
    public IndexException(String message) {
        super(message);
    }

    public IndexException(String message, Exception cause) {
        super(message, cause);
    }
}