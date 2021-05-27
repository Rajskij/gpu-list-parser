package ua.gpu.seeker.model.dao;

import ua.gpu.seeker.model.entity.Gpu;
import ua.gpu.seeker.model.service.Scraper;

import java.sql.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class GpuDao {
    private static final Logger logger = Logger.getLogger(GpuDao.class.getName());
    private final String FINED_ALL_GPU_BY_ETHASH = "SELECT * FROM gpu WHERE ethash >= ?;";
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/gpu?useSSL=false";
    private final String JDBC_USERNAME = "root";
    private final String JDBC_PASSWORD = "root";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            logger.severe(e.getMessage());
        }
        return connection;
    }

    public List<Gpu> findByRequest(int ethash) {
        List<Gpu> gpuList = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(FINED_ALL_GPU_BY_ETHASH)) {
            prepStmt.setInt(1, ethash);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                Gpu gpu = new Gpu();
                gpu.setTitle(rs.getString("title"));
                gpu.setEthash(rs.getInt("ethash"));
                gpuList.add(gpu);
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return gpuList;
    }

    public List<Gpu> findByPrice(List<Gpu> gpuList, int price) {
        List<Gpu> userGpuList = new LinkedList<>();
        for (Gpu gpu : gpuList) {
            List<Gpu> hotlineGpuList = Scraper.parseByTitle(gpu.getTitle());

            for (Gpu hotlineGpu : hotlineGpuList) {
                if (hotlineGpu.getPrice() <= price && hotlineGpu.getPrice() != 0) {
                    hotlineGpu.setEthash(gpu.getEthash());
                    userGpuList.add(hotlineGpu);
                }
            }
        }
        return userGpuList;
    }

    public List<Gpu> sortByPrice(List<Gpu> gpuList) {
        Collections.sort(gpuList, new Comparator<Gpu>() {
            @Override
            public int compare(Gpu o1, Gpu o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
        return gpuList;
    }
}