package ua.gpu.seeker.controller.command;

import ua.gpu.seeker.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return Path.MAIN_PAGE;
    }
}
