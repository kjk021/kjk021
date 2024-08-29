package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface memberCommand {

	void execute(HttpServletRequest request, HttpServletResponse response);

}
