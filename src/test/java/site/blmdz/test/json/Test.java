package site.blmdz.test.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	static final ObjectMapper mapper = new ObjectMapper();
	public static void main(String[] args) throws JsonProcessingException {
		ClassTestA a = new ClassTestA();
		a.setType("1");
		a.setStatus("2");
		System.out.println(mapper.writeValueAsString(a));
	}
}
