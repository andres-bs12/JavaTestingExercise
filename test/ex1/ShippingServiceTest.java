package ex1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShippingServiceTest {

    @Test
    void shouldCalculateCorrectCostForMarsAndHeavyWeight() {
        // 1. Arrange (Preparar)
        ex1.Package pack = new ex1.Package();
        pack.setDestination("Mars");
        pack.setWeight(20);
        pack.setTrackingId("A123");
        ShippingService service = new ShippingService();

        // 2. Act (Actuar)
        double result = service.calculatePackageCost(pack) ;

        // 3. Assert (Afirmar/Verificar)
        // Cálculo: (20 * 2.5) = 50. + 10% (5) = 55. + 50 (Mars) = 105.
        assertEquals(105.0, result, "El cálculo para Marte y peso pesado debería ser 105.0");
    }


    @Test
    void shouldCalculateCorrectCostForEarthAndLightWeight() {
        // Caso: Peso <=10 y no es Marte
        // 5Kg * 2.5 = 12.5 -> Round 13

        ex1.Package pack = new ex1.Package("A124", 5.0, "Colombia");
        ShippingService service = new ShippingService();
        assertEquals(13.0, service.calculatePackageCost(pack));
    }

    @Test
    void shouldReturnZeroWhenPackageIsNull() {
        // Caso: El paquete es null
        ShippingService service = new ShippingService();
        assertEquals(0.0, service.calculatePackageCost(null), "Si el paquete es null, el costo debe ser 0");
    }

    @Test
    void shouldCalculateHeavyWeightToEarth() {
        // Caso: Peso > 10 pero NO es Marte
        // 20kg * 2.5 = 50 + 10%(5) = 55 -> Round = 55
        ex1.Package pack = new Package("A125", 20.0, "Portugal");
        ShippingService service = new ShippingService();
        assertEquals(55.0, service.calculatePackageCost(pack));
    }
}
