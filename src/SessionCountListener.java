import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCountListener implements HttpSessionListener {

	private static int activeSessionsCount;

	public static int getActiveSessionsCount() {
		return activeSessionsCount;
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		activeSessionsCount++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		activeSessionsCount--;
	}

}