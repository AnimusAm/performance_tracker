package logging.frameworks.log4j.plugins;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

@Plugin(name="DurationConverter", category = "Converter")
@ConverterKeys({"duration"})
public class DurationConverter extends LogEventPatternConverter {

    protected DurationConverter(String name, String style) {
        super(name, style);
    }

    protected String getDuration(Object data) {
        final String duration;
        if (data.getClass().isAssignableFrom(LogEvent.class)) {
            duration = ((LogEvent) data).getContextData().getValue("duration");
        } else {
            duration = null;
        }
        return duration;

    }

    public static DurationConverter newInstance(String[] options) {
        return new DurationConverter("duration","duration");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        toAppendTo.append(getDuration(event));
    }




}
