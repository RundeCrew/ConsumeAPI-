package co.grandcircus.consumeApiDemo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlanetController {

	@RequestMapping("/")
	public ModelAndView showStuff() {
		ModelAndView mav =  new ModelAndView("list-planets");
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.USER_AGENT, "Let me in!");
		
		String url = "https://swapi.co/api/planets/?format=json";
		
		ResponseEntity<PlanetResult> response = restTemplate.exchange
				(url, HttpMethod.GET, new HttpEntity<>(headers),
				PlanetResult.class);
		
		PlanetResult result = response.getBody();
		
		mav.addObject("planets", result.getResults());
		return mav;
		
	
	}
	
	
}
