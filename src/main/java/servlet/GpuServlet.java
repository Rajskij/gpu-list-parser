package servlet;

import dao.GpuDao;
import entity.Gpu;
import entity.UserRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GpuServlet", value = "/")
public class GpuServlet extends HttpServlet {
    private GpuDao gpuDao;
    private String price;
    private String ethash;

    public void init() {
        gpuDao = new GpuDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        if ("/rating".contains(action)) {
            showGpuRatingPage(request, response);
        } else {
            showMainPage(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showGpuRatingPage(request, response);
    }

    private void showMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }


    private void showGpuRatingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("price") != null) {
            price = request.getParameter("price");
        }
        if (request.getParameter("ethash") != null) {
            ethash = request.getParameter("ethash");
        }
//        System.out.println(price + " " + ethash);
        UserRequest userRequest = new UserRequest();
        userRequest.setPrice(Integer.parseInt(price));
        userRequest.setEthash(Integer.parseInt(ethash));

        List<Gpu> userRequestList = gpuDao.findByRequest(userRequest.getEthash());
        List<Gpu> gpuPriceList = gpuDao.findByPrice(userRequestList, userRequest.getPrice());
        /*        for (Gpu gpu : userRequestList) {
            System.out.println(gpu.getTitle());
            System.out.println(gpu.getEthash());
        }*/
        /*        for (Gpu gpu : gpuPriceList) {
            System.out.println(gpu.getTitle());
            System.out.println(gpu.getPrice());
            System.out.println(gpu.getUrl());
            System.out.println(gpu.getEthash());
        }*/
        request.setAttribute("gpuList", gpuDao.sortByEthash(gpuPriceList));
        request.setAttribute("price", price);
        request.setAttribute("ethash", ethash);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/gpu_rating.jsp");
        dispatcher.forward(request, response);
    }
}
