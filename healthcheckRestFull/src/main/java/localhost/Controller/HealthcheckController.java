package localhost.Controller;

import java.util.HashMap;
import java.io.StringWriter;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.boot.json.JSONObject;

@Controller
@ResponseBody

public class HealthcheckController {

	/*
	 * import org.springframework.boot.configurationprocessor.json.JSONException;
	 * import org.springframework.boot.configurationprocessor.json.JSONObject;
	 * 
	 * public ResponseEntity<?> ApiCall(@PathVariable(name = "id") long id) throws
	 * JSONException { JSONObject resp = new JSONObject(); resp.put("status", 0);
	 * resp.put("id", id);
	 * 
	 * return new ResponseEntity<String>(resp.toString(), HttpStatus.CREATED); }
	 */
	/*
	 * @GetMapping(value = "/healthcheck", produces = "application/json" ) public
	 * ResponseEntity<String> healthcheckController() { return new
	 * ResponseEntity(HttpStatus.BAD_REQUEST); }
	 */

	/*
	 * @GetMapping(value = "/healthcheck", produces =
	 * MediaType.APPLICATION_JSON_VALUE )
	 * 
	 * @ResponseBody public ResponseEntity
	 * healthcheckShort(@RequestParam(defaultValue = "short") String format ) {
	 * //should_return_correct_response_body_for_simple_healthcheck
	 * //should_return_correct_response_body_for_simple_healthcheck //Actual string
	 * is null return new ResponseEntity(HttpStatus.OK); }
	 */

	/*
	 * @GetMapping(value = "/healthcheck", produces = "application/json" )
	 * 
	 * @ResponseBody public ResponseEntity<String>
	 * healthcheckFull(@RequestParam(defaultValue = "full") String format ) {
	 * //should_return_correct_ContentType_header_for_detailed_healthcheck
	 * //should_return_correct_response_body_for_detailed_healthcheck //Content-Type
	 * <null> does not match <application/json> String currentTime =
	 * ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT); return new
	 * ResponseEntity<String>(currentTime,HttpStatus.OK); }
	 */
	@GetMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public @ResponseBody ResponseEntity<String> healthcheck(@RequestParam String format) {
		String result = "vacio";
		System.out.println("Short_format " + format);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> hsMap = new HashMap<>();
		ResponseEntity entity;
		if ("short".equals(format)) {
			hsMap.put("status", HttpStatus.OK.name());
//			entity = new ResponseEntity(hsMap.toString(), HttpStatus.OK);
//			return entity.getBody().toString();

			// configure Object mapper for pretty print
//			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			try {
				result = objectMapper.writeValueAsString(hsMap);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			entity = new ResponseEntity(result, HttpStatus.OK);
			return entity;
		} else if ("full".equals(format)) {
			String currentTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
			hsMap.put("currentTime", currentTime);
			hsMap.put("status", HttpStatus.OK.name());
//			entity = new ResponseEntity(hsMap.toString(), HttpStatus.OK);
//			return entity.getBody().toString();
//			return "{\"currentTime\":\"2023-11-12T04:26:23Z\", \"status\":\"OK\"}";
			try {

				result = objectMapper.writeValueAsString(hsMap);
//	            throw new JsonProcessingException("Forced error") {};
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			entity = new ResponseEntity(result, HttpStatus.PRECONDITION_FAILED);
			return entity;

		} else {
			System.out.println("Short_HttpStatus.BAD_REQUEST " + HttpStatus.BAD_REQUEST.name());
//			return new ResponseEntity(HttpStatus.BAD_REQUEST).getBody().toString();
			hsMap.put("status", HttpStatus.BAD_REQUEST.name());
			entity = new ResponseEntity(hsMap, HttpStatus.BAD_REQUEST);
//			return entity.getBody().toString();
			return entity;
		}
	}

	/*
	*/

	/*
	*/

	/*
	 * 
	 * //@GetMapping(value = "/healthcheck", produces = "application/json" )
	 * 
	 * @RequestMapping(value = "/healthcheck", method = RequestMethod.GET, produces
	 * = MediaType.APPLICATION_JSON_VALUE , consumes =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public ResponseEntity<String>
	 * healthcheckFullP(@RequestParam(defaultValue = "full") String format ) {
	 * //should_return_correct_ContentType_header_for_detailed_healthcheck
	 * //should_return_correct_response_body_for_detailed_healthcheck //Content-Type
	 * <null> does not match <application/json> ResponseEntity entity; if
	 * ("short".equals(format)) { entity = new ResponseEntity(HttpStatus.OK);
	 * entity.getHeaders().setContentType(MediaType.APPLICATION_JSON); return
	 * entity; } else if ("full".equals(format)) { String currentTime =
	 * ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT); entity = new
	 * ResponseEntity(currentTime, HttpStatus.OK);
	 * entity.getHeaders().setContentType(MediaType.APPLICATION_JSON); return
	 * entity; } else { return new ResponseEntity(HttpStatus.BAD_REQUEST); } }
	 * 
	 * 
	 * @RequestMapping(value = "/healthcheck", method = RequestMethod.POST, produces
	 * = MediaType.APPLICATION_JSON_VALUE , consumes =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public ResponseEntity<String>
	 * healthcheckPath(@RequestParam(defaultValue = "short") String format) {
	 * 
	 * ResponseEntity entity; if ("short".equals(format)) { entity = new
	 * ResponseEntity(HttpStatus.OK);
	 * entity.getHeaders().setContentType(MediaType.APPLICATION_JSON); return
	 * entity; } else if ("full".equals(format)) { String currentTime =
	 * ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT); entity = new
	 * ResponseEntity(currentTime, HttpStatus.OK);
	 * entity.getHeaders().setContentType(MediaType.APPLICATION_JSON); return
	 * entity; } else { return new ResponseEntity(HttpStatus.BAD_REQUEST); } }
	 */


	@GetMapping(value = "/healthcheck", produces = MediaType.TEXT_PLAIN_VALUE,
			consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public @ResponseBody ResponseEntity<String> healthcheck__invalid_format_TEXT_PLAIN_VALUE() {
		System.out.println("healthcheck__invalid_format TEXT_PLAIN_VALUE");
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> healthcheckPut() {
		System.out.println("healthcheckPut");
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@PostMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> healthcheckPost() {
		System.out.println("healthcheckPost");
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@DeleteMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> healthcheckDelete() {
		System.out.println("healthcheckDelete");
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}

}
