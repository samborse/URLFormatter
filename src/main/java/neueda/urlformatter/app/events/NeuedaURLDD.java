package neueda.urlformatter.app.events;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import neueda.urlformatter.exceptions.NeuedaNonBlockingException;

/**
 * @author Samadhan
 *
 */
@Repository
public class NeuedaURLDD {

	private final Map<String, String> dictionaryMap;
	private final String requestId;
	private final String longURL;
	private static final Logger LOGGER = LoggerFactory.getLogger(NeuedaURLDD.class);

	public NeuedaURLDD() {
		this.dictionaryMap = new HashMap<>();
		this.requestId = "id";
		this.longURL = "url:";
	}

	public NeuedaURLDD(Map<String, String> dictionaryMap, String requestId, String longURL) {
		this.dictionaryMap = dictionaryMap;
		this.requestId = requestId;
		this.longURL = longURL;
	}

	public Long uniqueRequestId() {
		System.out.println("requestId :"+requestId);
		String tempKey = (null!=dictionaryMap.get(requestId)) ? (dictionaryMap.get(requestId).substring(requestId.lastIndexOf(":"), requestId.length())) : null;
		Long id = (null != tempKey) ? Long.parseLong(tempKey) : 125;
		LOGGER.info("Unquie Request ID: {}", id );
		return id;
	}

	public void saveUrltoDD(String key, String longUrl) {
		LOGGER.info("Saving: {} at {}", longUrl, key);
		dictionaryMap.put(longURL + key, longUrl);
	}

	public String getOriginalLongUrl(Long id) throws NeuedaNonBlockingException {
		String url = dictionaryMap.get("url:" + id);
		if (url == null) {
			throw new NeuedaNonBlockingException("URL at key" + id + " not present");
		}
		return url;
	}

}
