package neueda.urlformatter.app.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import neueda.urlformatter.exceptions.NeuedaNonBlockingException;

/**
 * @author Samadhan
 *
 */
public class NeuedaUniqueIDCreator {

	  public static final NeuedaUniqueIDCreator INSTANCE = new NeuedaUniqueIDCreator();
	private static HashMap<Character, Integer> carIndexMap;
	private static List<Character> indexCharList;

    public NeuedaUniqueIDCreator() {
        initIndexMap();
        initIndexList();
    }

    private void initIndexMap() {
        carIndexMap = new HashMap<>();
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            carIndexMap.put(c, i);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            carIndexMap.put(c, i);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            carIndexMap.put(c, i);
        }
    }

    private void initIndexList() {
        indexCharList = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            indexCharList.add(c);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            indexCharList.add(c);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            indexCharList.add(c);
        }
    }

    public static String getUniqueId(Long id) {
        List<Integer> base62ID = convertBase10ToBase62ID(id);
        StringBuilder uniqueURLID = new StringBuilder();
        for (int digit: base62ID) {
            uniqueURLID.append(indexCharList.get(digit));
        }
        return uniqueURLID.toString();
    }

    public static List<Integer> convertBase10ToBase62ID(Long id) {
        List<Integer> digits = new LinkedList<>();
        while(id > 0) {
            int remainder = (int)(id % 62);
            ((LinkedList<Integer>) digits).addFirst(remainder);
            id /= 62;
        }
        return digits;
    }

    public static Long getURLDDKey(String reqId) throws NeuedaNonBlockingException {
        List<Character> base62IDs = new ArrayList<>();
        for (int i = 0; i < reqId.length(); ++i) {
            base62IDs.add(reqId.charAt(i));
        }
        Long dictionaryKey = convertBase62ToBase10ID(base62IDs);
        return dictionaryKey;
    }

    private static Long convertBase62ToBase10ID(List<Character> reqIds) throws NeuedaNonBlockingException {
        long id = 0L;
        for (int i = 0, exp = reqIds.size() - 1; i < reqIds.size(); ++i, --exp) {
            int base10 = carIndexMap.get(reqIds.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }
}
