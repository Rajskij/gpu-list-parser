package ua.gpu.seeker.controller.command;

import ua.gpu.seeker.Path;
import ua.gpu.seeker.model.dao.GpuDao;
import ua.gpu.seeker.model.entity.Gpu;
import ua.gpu.seeker.model.entity.UserRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GpuRatingCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        GpuDao gpuDao = new GpuDao();
        String price = request.getParameter("price");
        String ethash = request.getParameter("ethash");

        UserRequest userRequest = new UserRequest();
        userRequest.setPrice(Integer.parseInt(price));
        userRequest.setEthash(Integer.parseInt(ethash));

        List<Gpu> userRequestList = gpuDao.findByRequest(userRequest.getEthash());
        List<Gpu> gpuPriceList = gpuDao.findByPrice(userRequestList, userRequest.getPrice());

        request.setAttribute("gpuList", gpuDao.sortByPrice(gpuPriceList));
        request.setAttribute("price", price);
        request.setAttribute("ethash", ethash);

        return gpuPriceList.isEmpty() ? Path.OOPS_PAGE : Path.RATING_PAGE;
    }
}
