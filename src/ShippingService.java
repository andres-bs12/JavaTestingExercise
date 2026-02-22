public class ShippingService {


    public double calculatePackageCost(Package p) {
        if (p == null) {
            System.out.println("Wrong package info");
        }


        double totalCost = p.getWeight() * 2.5;

        if (p.getWeight() > 10) {
            totalCost += (totalCost * 0.10);
        }

        if (p.getDestination().equalsIgnoreCase("Mars")) {
            totalCost += 50;
        }
        return Math.round(totalCost);
    }

}
