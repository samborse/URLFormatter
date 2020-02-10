package neueda.urlformatter.app.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FormatRequest {

    private String url;

    @JsonCreator
    public FormatRequest() {
    	super();
    }

    @JsonCreator
    public FormatRequest(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public boolean validateURL(String url) {
    	String regEx = "^(http:\\/\\/www\\.|https:\\/\\/www\\.";

        Pattern pattern = Pattern.compile(regEx);

        Matcher m = pattern.matcher(url);
        return m.matches();
    }

}
