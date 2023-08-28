package home.payload;

public interface ResponsePayloadBuilder  {
	
	public ResponsePayloadBuilder setKeyValue(String key, Object value);

	public ResponsePayload build();
}
