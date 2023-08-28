package home.payload;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePayload implements ResponsePayloadBuilder{
	private Map<String, Object> response = new HashMap<>();
	
	@Override
	public ResponsePayloadBuilder setKeyValue(String key, Object value) {
		this.response.putIfAbsent(key, value);
		return this;
	}

	@Override
	public ResponsePayload build() {
		return new ResponsePayload(this.response);
	}	
}
