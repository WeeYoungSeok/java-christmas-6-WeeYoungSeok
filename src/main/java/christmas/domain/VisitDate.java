package christmas.domain;

public class VisitDate {
    private final int visitDay;

    private VisitDate(int visitDay) {
        this.visitDay = visitDay;
    }

    public static VisitDate of(final int visitDay) {
        return new VisitDate(visitDay);
    }
}
