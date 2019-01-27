import java.util.HashMap;
import java.util.Map;

/**
 * 图要继承的稀疏矩阵类
 *
 * @author cheng
 * @create 2018-12-06 16:43
 */
public class SparseMatrix {

    protected Map<Integer, Map<Integer, Object>> rows = new HashMap<>();
    int rowNum, colNum;
    boolean sizeFixed;
    Object DEFAULT_VALUE = null; // 矩阵元素默认值, 在图的邻接矩阵中可设为无穷大

    // 如果没有初始化矩阵维数，稀疏矩阵大小可变
    public SparseMatrix() {
        sizeFixed = false;
    }

    public SparseMatrix(int r, int c) {
        sizeFixed = true;
        rowNum = r;
        colNum = c;
    }

    // getter & setter
    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setDefaultValue(Object def) {
        DEFAULT_VALUE = def;
    }

    // 添加矩阵元素
    public void put(int x, int y, Object v) throws IndexException, TypeException {
        checkIndexAndType(x, y, v);
        if (v != DEFAULT_VALUE) {
            Map<Integer, Object> row = rows.get(x);
            if (null == row) {
                row = new HashMap<Integer, Object>();
            }
            row.put(y, v);
            rows.put(x, row);
        }
    }

    // 获取矩阵元素
    public Object get(int x, int y) throws IndexException {
        checkIndex(x, y);
        Map<Integer, Object> row = rows.get(x);
        if (row == null) {
            return DEFAULT_VALUE;
        }
        Object v = row.get(y);
        if (v == null) {
            v = DEFAULT_VALUE;
        }
        return v;
    }

    // 转换为 一般矩阵
    public Object[][] toDensity() {
        Object[][] mat = new Object[rowNum][colNum];
        for (int i = 0; i < rowNum; ++i) {
            for (int j = 0; j < colNum; ++j) {
                try {
                    mat[i][j] = get(i, j);
                } catch (IndexException e) {
                    e.printStackTrace();
                }
            }
        }
        return mat;
    }

    // 从一般矩阵 转换为 稀疏矩阵
    public SparseMatrix fromDensity(Object[][] mat) {
        SparseMatrix sm = new SparseMatrix(mat.length, mat[0].length);
        sm.setDefaultValue(DEFAULT_VALUE);
        try {
            for (int i = 0; i < mat.length; ++i) {
                for (int j = 0; j < mat[0].length; ++j) {
                    if (mat[i][j] != DEFAULT_VALUE) {
                        sm.put(i, j, mat[i][j]);
                    }
                }
            }
        } catch (IndexException | TypeException e) {
            e.printStackTrace();
        }
        return sm;
    }

    // 检查是否越界，下标越界则抛出异常
    private void checkIndex(int x, int y) throws IndexException {
        if (sizeFixed) {
            if (x >= rowNum || y >= colNum) {
                throw new IndexException("Index out of bound: (" + String.valueOf(x) + "," + String.valueOf(y) + "), "
                        + "(rowBound,colBound)=(" + String.valueOf(rowNum) + "," + String.valueOf(colNum) + ").");
            }
        } else {
            rowNum = rowNum > x + 1 ? rowNum : x + 1;
            colNum = colNum > y + 1 ? colNum : y + 1;
        }
    }

    // 检查元素类型是否和默认值一致
    private void checkIndexAndType(int x, int y, Object o) throws TypeException, IndexException {
        checkIndex(x, y);
        if (o.getClass() != this.DEFAULT_VALUE.getClass()) {
            throw new TypeException("Invalid element type at location(" + x + "," + y + "). Expected: "
                    + this.DEFAULT_VALUE.getClass() + ", Got: " + o.getClass());
        }
    }
}



