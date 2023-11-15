package christmas.util;

public class EventCalculate implements Calculate {
    @Override
    public int plus(int x, int y) {
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        return Math.max(x - y, 0);
    }

    public int toNegative(int x) {
        return -x;
    }
}
