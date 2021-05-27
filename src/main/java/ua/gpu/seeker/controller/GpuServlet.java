package ua.gpu.seeker.controller;

import ua.gpu.seeker.controller.command.Command;
import ua.gpu.seeker.controller.command.CommandContainer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GpuServlet", value = "/")
public class GpuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException  {
        String commandName = request.getParameter("command");
        Command command = CommandContainer.get(commandName);
        String forward = command.execute(request, response);

        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }
}