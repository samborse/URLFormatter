package neueda.urlformatter.app.services;

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

}
