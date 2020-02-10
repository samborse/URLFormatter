package neueda.urlformatter.app.services;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import neueda.urlformatter.exceptions.NeuedaNonBlockingException;

/**
 * @author Samadhan
 *
 */
@RestController
public class NeuedaURLFormatController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NeuedaURLFormatController.class);
	private final NeuedaURLFormatService urlConverterService;
	

	public NeuedaURLFormatController(NeuedaURLFormatService urlConverterService) {
		this.urlConverterService = urlConverterService;
	}

    
	@RequestMapping(value = "/urlformatter", method = RequestMethod.POST, consumes = {"application/json"})
	public String formatURL(@RequestBody @Valid final FormatRequest formattedURLReq, HttpServletRequest request)
			throws NeuedaNonBlockingException {
		LOGGER.info("URL to format " + formattedURLReq.getUrl());
		LOGGER.info("Saved URL to format " + request.getRequestURL().toString());
		String longUrl = formattedURLReq.getUrl();
		if (formattedURLReq.validateURL(longUrl)) {
			String formattedURL = urlConverterService.getShortenedURL(request.getRequestURL().toString(),
					formattedURLReq.getUrl());
			LOGGER.info("Foramatted url is : " + formattedURL);
			return formattedURL;
		}
		throw new NeuedaNonBlockingException("Not valid URL");
			
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request,
			HttpServletResponse response) throws IOException, URISyntaxException, NeuedaNonBlockingException {
		LOGGER.info("Received shortened url to redirect: " + id);
		String redirectUrlString = urlConverterService.getLongURLFromID(id);
		LOGGER.info("Original URL: " + redirectUrlString);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("http://" + redirectUrlString);
		return redirectView;
	}
}
