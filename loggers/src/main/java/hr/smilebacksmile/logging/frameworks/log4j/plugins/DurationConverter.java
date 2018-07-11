package hr.smilebacksmile.logging.frameworks.log4j.plugins;


import org.apache.logging.log4j.ThreadContext;
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

    protected String getDuration() {
        final String duration;

        duration = ThreadContext.get("duration");

        ThreadContext.remove("duration");
        return duration;

    }

    public static DurationConverter newInstance(String[] options) {
        return new DurationConverter("duration","duration");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        toAppendTo.append(getDuration());
    }




}
