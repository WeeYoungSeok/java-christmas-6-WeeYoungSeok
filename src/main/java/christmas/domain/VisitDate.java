package christmas.domain;

public class VisitDate {
    private final int visitDay;

    private VisitDate(int visitDay) {
        this.visitDay = visitDay;
    }

    public static VisitDate of(final int visitDay, final int firstDay, final int endDay) {
        validate(visitDay, firstDay, endDay);
        return new VisitDate(visitDay);
    }

    public static void validate(final int visitDay, final int firstDay, final int endDay) {
        validateDayInMonth(visitDay, firstDay, endDay);
    }

    public static void validateDayInMonth(final int visitDay, final int firstDay, final int endDay) {
        if (visitDay < firstDay || visitDay > endDay) {
            throw new IllegalArgumentException();
        }
    }
}
