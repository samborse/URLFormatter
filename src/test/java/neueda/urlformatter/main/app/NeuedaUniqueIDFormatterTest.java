package neueda.urlformatter.main.app;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import neueda.urlformatter.app.events.NeuedaUniqueIDCreator;

/**
 * @author Samadhan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class NeuedaUniqueIDFormatterTest {

	
	@Mock
	Map<String, String> dictionaryMap;
	
	NeuedaUniqueIDCreator neuedaIdCreator = new NeuedaUniqueIDCreator();
	
	@Test
	public void test01() {
		String id = NeuedaUniqueIDCreator.getUniqueId(125l);
		
		Assert.assertEquals("cb", id);
	}
	
	@Test
	public void test02() {
		List<Integer> listIds = NeuedaUniqueIDCreator.convertBase10ToBase62ID(125l);
		
		Assert.assertEquals(2, listIds.size());
	}
	

}
