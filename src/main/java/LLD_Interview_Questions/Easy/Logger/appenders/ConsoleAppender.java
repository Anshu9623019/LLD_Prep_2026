package LLD_Interview_Questions.Easy.Logger.appenders;



import LLD_Interview_Questions.Easy.Logger.core.LogAppender;
import LLD_Interview_Questions.Easy.Logger.core.LogFormatter;
import LLD_Interview_Questions.Easy.Logger.core.LogLevel;
import LLD_Interview_Questions.Easy.Logger.core.LogMessage;
import LLD_Interview_Questions.Easy.Logger.formatters.SimpleFormatter;

import java.io.PrintStream;

/**
 * Appender that writes log messages to the console (System.out/System.err).
 */
public class ConsoleAppender implements LogAppender {
    private LogLevel level;
    private LogFormatter formatter;
    private PrintStream outputStream;

    public ConsoleAppender() {
        this(LogLevel.DEBUG);
    }

    public ConsoleAppender(LogLevel level) {
        this.level = level;
        this.formatter = new SimpleFormatter();
        this.outputStream = System.out;
    }

    @Override
    public void append(LogMessage message) {
        if (!isEnabled(message.getLevel())) {
            return;
        }

        String formattedMessage = formatter.format(message);

        // Use System.err for ERROR and FATAL levels
        if (message.getLevel() == LogLevel.ERROR || message.getLevel() == LogLevel.FATAL) {
            System.err.println(formattedMessage);
        } else {
            outputStream.println(formattedMessage);
        }
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public LogLevel getLevel() {
        return level;
    }

    @Override
    public boolean isEnabled(LogLevel level) {
        return level.isGreaterOrEqual(this.level);
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    public void setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
}