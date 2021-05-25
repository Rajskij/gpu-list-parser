package dao;

import com.mysql.cj.sasl.ScramSha1SaslClient;
import entity.Gpu;
import entity.UserRequest;
import parser.Scraper;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class GpuDao {
    private static final Logger logger = Logger.getLogger(GpuDao.class.getName());
    private final String FINED_ALL_GPU_BY_ETHASH = "SELECT * FROM gpu WHERE ethash >= ?;";
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/gpu?useSSL=false";
    private final String JDBC_USERNAME = "root";
    private final String JDBC_PASSWORD = "huait8of";

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

    public static void main(String[] args) {
        GpuDao gpuDao = new GpuDao();
        UserRequest userRequest = new UserRequest();
        userRequest.setEthash(31);
        List<Gpu> gpuDbList = gpuDao.findByRequest(userRequest.getEthash());
        for (Gpu gpu : gpuDbList) {
            System.out.println(gpu.getTitle());
            System.out.println(gpu.getEthash());
        }

        for (Gpu gpu : gpuDao.findByPrice(gpuDbList, 15_000)) {
            System.out.println(gpu.getTitle());
            System.out.println(gpu.getPrice());
            System.out.println(gpu.getUrl());
        }
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return gpuList;
    }

    public List<Gpu> findByPrice(List<Gpu> gpuList, int price) {
        List<Gpu> userGpuList = new LinkedList<>();
        for (Gpu gpu : gpuList) {
            List<Gpu> hotlineGpuList = Scraper.parseByTitle(gpu.getTitle());

            for (Gpu hotlineGpu : hotlineGpuList) {
                if (hotlineGpu.getPrice() <= price) {
                    userGpuList.add(hotlineGpu);
                }
            }
        }
        return userGpuList;
    }
}
