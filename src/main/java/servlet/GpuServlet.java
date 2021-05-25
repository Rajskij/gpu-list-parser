package servlet;

import dao.GpuDao;
import entity.Gpu;
import entity.UserRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GpuServlet", value = "/index")
public class GpuServlet extends HttpServlet {
    private GpuDao gpuDao;

    public void init() {
        gpuDao = new GpuDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String price = request.getParameter("price");
        String ethash = request.getParameter("ethash");

        System.out.println(price + " " + ethash);

        UserRequest userRequest = new UserRequest();
        userRequest.setPrice(Integer.parseInt(price));
        userRequest.setEthash(Integer.parseInt(ethash));
        //gpuDao.findByValue(userRequest);
        List<Gpu> userRequestList = gpuDao.findByRequest(userRequest.getEthash());
        for (Gpu gpu : userRequestList) {
            System.out.println(gpu.getTitle());
            System.out.println(gpu.getEthash());
        }

        List<Gpu> gpuPriceList = gpuDao.findByPrice(userRequestList, userRequest.getPrice());
        for (Gpu gpu : gpuPriceList) {
            System.out.println(gpu.getTitle());
            System.out.println(gpu.getPrice());
            System.out.println(gpu.getUrl());
        }

        Gpu gpu = new Gpu();
        gpu.setPrice(1);
        gpu.setTitle("Hussein");
        gpu.setUrl("25");

        request.setAttribute("student", gpu);

        request.setAttribute("gpuList", gpuPriceList);
        request.setAttribute("name", "My name is!");
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/gpu.jsp");
        dispatcher.forward(request, response);
    }
}
