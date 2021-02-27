package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

//TODO: Nicht unbedingt als klasse da einzige was benutzt wird nur HashMap und wenige Methoden -- im nachhinein machen
public class RequestMap {

    private HashMap<String, String> parameterMap;

    public RequestMap() {
        parameterMap = new HashMap<String, String>();
    }

    public String getString() {
        String RequestString;
        RequestString = stringify();

        return RequestString;
    }

    public void add(String key, String value) {
        parameterMap.put(key, value);
    }

    private String stringify() {
        String RequestString = "?";
        Set<String> keySet = parameterMap.keySet();

        Iterator iterator = keySet.iterator();
        String key;

        while (iterator.hasNext()) {
            key = (String) iterator.next();
            RequestString += key + "=" + parameterMap.get(key);

            if (iterator.hasNext()) {
                RequestString += "&";
            }
        }
        return RequestString;
    }
}
