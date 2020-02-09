package neueda.urlformatter.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neueda.urlformatter.app.events.NeuedaURLDD;
import neueda.urlformatter.app.events.NeuedaUniqueIDCreator;
import neueda.urlformatter.exceptions.NeuedaNonBlockingException;


/**
 * @author Samadhan
 *
 */
@Service
public class NeuedaURLFormatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NeuedaURLFormatService.class);
    private final NeuedaURLDD urlRepository;

    @Autowired
    public NeuedaURLFormatService(NeuedaURLDD urlDD) {
        this.urlRepository = urlDD;
    }

    public String getShortenedURL(String localURL, String orgLongURL) {
        LOGGER.info("Shortened orignal URL {}", orgLongURL);
        Long tempReqId = urlRepository.uniqueRequestId();
        String uniqueID = NeuedaUniqueIDCreator.getUniqueId(tempReqId);
        urlRepository.saveUrltoDD("url:"+tempReqId, orgLongURL);
        String baseString = formatLocalURL(localURL);
        String formattedSHortURL = baseString + uniqueID;
        
        return formattedSHortURL;
    }

    public String getLongURLFromID(String uniqueID) throws NeuedaNonBlockingException {
        Long dictionaryKey = NeuedaUniqueIDCreator.getURLDDKey(uniqueID);
        String orgLongURL = urlRepository.getOriginalLongUrl(dictionaryKey);
        LOGGER.info("Converting shortened URL back to {}", orgLongURL);
        return orgLongURL;
    }

    private String formatLocalURL(String localURL) {
        String[] addressComponents = localURL.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }

}
