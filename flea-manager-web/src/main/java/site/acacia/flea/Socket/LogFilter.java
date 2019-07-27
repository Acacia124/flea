package site.acacia.flea.Socket;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import site.acacia.flea.pojo.LoggerMessage;

@Service
public class LogFilter extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {
		LoggerMessage loggerMessage = new LoggerMessage(event.getMessage(),
				DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())), event.getThreadName(),
				event.getLoggerName(), event.getLevel().levelStr);
		LoggerQueue.getInstance().push(loggerMessage);
		return FilterReply.ACCEPT;
	}
}
