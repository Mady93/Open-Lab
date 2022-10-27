package it.jdk.ship.dto;


public class ReportDTO {
    private String name;
    private long count;

    public ReportDTO(String name, long count) {
        this.count = count;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
