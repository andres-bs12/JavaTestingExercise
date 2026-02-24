package ex1;

public class Package {
    private String trackingId;
    private double weight;
    private String destination;

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Package(String trackingId, double weight, String destination) {
        this.trackingId = trackingId;
        this.weight = weight;
        this.destination = destination;
    }

    public Package() {

    }
}
