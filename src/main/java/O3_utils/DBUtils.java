package O3_utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private Connection connection;
    // -- ✅ Hardcoded connection details as public fields
    private static final String SERVER = "10.142.3.101";
    private static final String INSTANCE = "UATGENERAL";
    private static final String PORT = "1437";
    private static final String USERNAME = "Ahmed.Maarouf";
    private static final String PASSWORD = "hDf804_mcan5";
    private static final String databaseName = "vas-temp"; // ✅ Add database constant

    // -- ✅ Open Connection
    public void connect() throws SQLException {
        String url = String.format(
                "jdbc:sqlserver://%s:%s;instanceName=%s;databaseName=%s;encrypt=true;trustServerCertificate=true;loginTimeout=30",
                SERVER, PORT, INSTANCE, databaseName
        );
        System.out.println("The Final URL looks Like : " + url);
        System.out.println("Attempting to connect to: " + SERVER + "\\" + INSTANCE + ":" + PORT);
        connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
        System.out.println("✅ Connected to SQL Server successfully.");
    }

    // -- ✅ GetData from the BE
    public List<Map<String, Object>> executeQuery(String query) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount(); // This to get the ColumnCount
        System.out.println("✅ columnCount :" + columnCount);

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), rs.getObject(i));
            }
            results.add(row);
        }

        rs.close();
        stmt.close();

        return results;
    }

    // -- ✅ Close Connection
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("SQL Server connection closed.");
        }
    }
}
