import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestJunit5 {
	@Test
	public void test1() {
		Assertions.assertEquals(3, 2 + 1);
	}
}
