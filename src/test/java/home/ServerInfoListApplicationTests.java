package home;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = ServerInfoListApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
class ServerInfoListApplicationTests {

	@Test
	public void contextLoads() throws Exception {
		assertTrue(true);
	}
}
