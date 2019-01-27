/**
 * 自定义异常
 *
 * @author cheng
 * @create 2018-12-06 16:48
 */
// 自定义异常
public class TypeException extends Exception {
    public TypeException(String message) {
        super(message);
    }

    public TypeException(String message, Exception cause) {
        super(message, cause);
    }
}
