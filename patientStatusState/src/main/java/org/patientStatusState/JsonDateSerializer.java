package org.patientStatusState;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 *
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
@Component
public class JsonDateSerializer extends JsonDeserializer<Date>{
private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
@Override
public Date deserialize(JsonParser paramJsonParser,
        DeserializationContext paramDeserializationContext)
        throws IOException, JsonProcessingException {
    String str = paramJsonParser.getText().trim();
    try {
        return dateFormat.parse(str);
    } catch (ParseException e) {
        // Handle exception here
    }
    return paramDeserializationContext.parseDate(str);
}
}
