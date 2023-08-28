package home.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeConverter;

public class IntegerListConverter implements AttributeConverter<List<Object>, String> {
	private static final String SPLIT_CHAR = "|";
	
	@Override
	public String convertToDatabaseColumn(List<Object> attribute) {
//		if(attribute.isEmpty() || attribute.size() < 0) return "";
		System.out.println(attribute);
		return "";
//		return attribute.stream().map(String::valueOf).collect(Collectors.joining(SPLIT_CHAR));
	}

	@Override
	public List<Object> convertToEntityAttribute(String dbData) {
		return Arrays.stream(dbData.split(SPLIT_CHAR))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

}
