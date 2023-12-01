package christmas.util;

public interface Converter<F, T> {
    T convert(F target);
}
