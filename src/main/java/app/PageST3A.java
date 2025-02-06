package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageST3A implements Handler {

    public static final String URL = "/page3A.html";

    @Override
    public void handle(Context context) throws Exception {
        ArrayList<String> lgaList = getAllLGAs();

        String html = "<html>";

        html += "<head>";
        html += "<meta charset='UTF-8'>";
        html += "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html += "<title>Find LGAs with Similar Recycling and Waste Levels</title>";
        html += "<link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap' rel='stylesheet'>";
        html += "<link rel='stylesheet' href='common.css'>";
        html += "<style>";
        html += "body { font-family: 'Playfair Display', serif; margin: 0; padding: 0; color: #333; }";
        html += ".topnav { background-color: #6d9197; padding: 10px; text-align: center; }";
        html += ".topnav a { color: #f2f2f2; margin: 0 15px; text-decoration: none; }";
        html += ".banner { background-color: #ddd; padding: 60px; text-align: center; }";
        html += ".banner h2 { margin: 0; }";
        html += "h1, h2, h3, h4, h5, h6 { font-family: 'Playfair Display', serif; }";
        html += ".form-section { background-color: #f9f9f9; padding: 20px; border-radius: 8px; max-width: 600px; margin: 20px auto; }";
        html += ".form-section h2 { color: #6d9197; text-align: center; }";
        html += ".form-group { margin-bottom: 15px; }";
        html += ".form-group label { display: block; margin-bottom: 5px; font-weight: bold; }";
        html += ".form-group select, .form-group input { width: 100%; padding: 8px; border-radius: 4px; border: 1px solid #ccc; }";
        html += ".btn { background-color: #6d9197; color: #f2f2f2; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; display: block; width: 100%; }";
        html += ".results { margin-top: 20px; }";
        html += ".results table { width: 100%; border-collapse: collapse; }";
        html += ".results th, .results td { padding: 10px; border: 1px solid #ccc; text-align: left; }";
        html += ".results th { background-color: #6d9197; color: #f2f2f2; }";
        html += ".footer { background-color: #6d9197; color: #f2f2f2; padding: 20px; text-align: center; }";
        html += "</style>";
        html += "</head>";

        html += "<body>";

        // Add the topnav
        html += """
                    <div class='topnav'>
                        <a href='/'>Home</a>
                        <a href='mission.html'>Our Mission</a>
                        <a href='page2A.html'>Sub Task 2.A</a>
                        <a href='page2B.html'>Sub Task 2.B</a>
                        <a href='page3A.html'>Sub Task 3.A</a>
                        <a href='page3B.html'>Sub Task 3.B</a>
                    </div>
                """;

        // Header section
        html += """
                   <div class='header' style='background-color: #6d9197;'>
                       <h1>
                           <a href='/'><img src='logo.png' class='top-image' alt='RMIT logo' height='75'></a>
                           <h1 style='color:#f2f2f2;'>
                           Find LGAs with Similar Recycling and Waste Levels
                           </h1>
                       </h1>
                   </div>
                """;

        // Content section
        html += "<div class='content'>";
        html += "<div class='form-section'>";
        html += "<h2>Filter Criteria</h2>";
        html += "<form action='/page3A.html' method='post'>";
        // LGA Dropdown
        html += "<div class='form-group'>";
        html += "<label for='lga-select'>Select LGA:</label>";

        html += "<select id='lga-select' name='selectedLGA'>";
        for (String lga : lgaList) {
            html += "<option value='" + lga + "'>" + lga + "</option>";
        }

        html += "</select>";
        html += "</div>";

        // Form inputs

        html += "<div class='form-group'>";
        // html += "<label for='lga-select'>Select LGA:</label>";

        html += "</select>";
        html += "</div>";

        html += "<div class='form-group'>";
        html += "<label for='waste-type'>Select Waste Resource Type:</label>";
        html += "<select id='waste-type' name='wasteType'>";
        html += "<option value='recyclable'>Recyclable</option>";
        html += "<option value='organics'>Organics</option>";
        html += "<option value='waste'>Waste</option>";
        html += "</select>";
        html += "</div>";

        html += "<div class='form-group'>";
        html += "<label for='time-period'>Select Time Period:</label>";
        html += "<select id='time-period' name='timePeriod'>";
        html += "<option value='2018-2019'>2018-2019</option>";
        html += "<option value='2019-2020'>2019-2020</option>";
        html += "</select>";
        html += "</div>";

        html += "<div class='form-group'>";
        html += "<label for='cutoff-value'>Cutoff Value:</label>";
        html += "<input type='number' id='cutoff-value' name='cutoffValue' min='1'>";
        html += "</div>";

        html += "<button type='submit' class='btn'>Find Similar LGAs</button>";
        html += "</form>";
        html += "</div>";

        html += "<div style='padding-bottom: 30px;'>";
        // Display results
        if (context.method().equals("POST")) {
            String selectedLGA = context.formParam("selectedLGA");
            String wasteType = context.formParam("wasteType");
            String timePeriod = context.formParam("timePeriod");
            Integer cutoffValue = Integer.parseInt(context.formParam("cutoffValue"));

            // Retrieve results
            html += getSimilarLGAs(selectedLGA, wasteType, timePeriod, cutoffValue);
        }

        // Footer section (unchanged from your specifications)
        html += """
                   <div style='background-color: #6d9197; height: 320px;'>
                       <div style='float: left; width: 50%; font-size:large;'>
                           <center><h2 style='color: #f2f2f2; font-weight: bold;'>Main Menu</h2>
                           <a href='/' style='color: #f2f2f2;'>Home</a><br><br>
                           <a href='mission.html' style='color: #f2f2f2;'>Our Mission</a><br><br>
                           <a href='page2A.html' style='color: #f2f2f2;'>Sub Task 2.A</a><br><br>
                           <a href='page2B.html' style='color: #f2f2f2;'>Sub Task 2.B</a><br><br>
                           <a href='page3A.html' style='color: #f2f2f2;'>Sub Task 3.A</a><br><br>
                           <a href='page3B.html' style='color: #f2f2f2;'>Sub Task 3.B</a><br><br>
                           </center>
                       </div>
                       <div style='width: 50%; margin-left: 50%; padding-top: 70px;'>
                           <center><img src='logo.png' alt='RMIT logo' width='300' height='100'></center>
                       </div>
                   </div>
                """;

        html += "<footer class=\"footer\">";
        html += "<p>&copy; COSC2803 - Studio Project Starter Code (Sep24)</p>";
        html += "</footer>";

        html += "</body></html>";

        context.html(html);
    }

    // Method to retrieve all LGAs for selection
    private ArrayList<String> getAllLGAs() {
        ArrayList<String> lgaList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                Statement statement = connection.createStatement()) {

            ResultSet results = statement.executeQuery("SELECT DISTINCT name FROM LGA");
            while (results.next()) {
                lgaList.add(results.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lgaList;
    }

    // Method to find similar LGAs
    private String getSimilarLGAs(String selectedLGA, String wasteType, String timePeriod, int cutoffValue) {
        StringBuilder html = new StringBuilder("<div class='results'><h2>Results</h2><table class='results-table'>");
        html.append(
                "<tr><th>LGA</th><th>Kerbside</th><th>CDS</th><th>Drop Off</th><th>Cleanup</th><th>Similarity</th></tr>");

        String query = buildQuery(selectedLGA, wasteType, timePeriod, cutoffValue);

        try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(query)) {

            while (results.next()) {
                html.append("<tr>")
                        .append("<td>").append(results.getString("lga_name")).append("</td>")
                        .append("<td>").append(results.getDouble("KerbsideRecyclingPercentage")).append("</td>")
                        .append("<td>").append(results.getDouble("CDSRecyclingPercentage")).append("</td>")
                        .append("<td>").append(results.getDouble("DropoffRecyclingPercentage")).append("</td>")
                        .append("<td>").append(results.getDouble("CleanupRecyclingPercentage")).append("</td>")
                        .append("<td>").append(results.getDouble("similarity_score")).append("</td>")
                        .append("</tr>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        html.append("</table></div>");
        return html.toString();
    }

    // Query builder based on user selection
    private String buildQuery(String selectedLGA, String wasteType, String timePeriod, int cutoffValue) {
        return "WITH SelectedLGA AS (" +
                "    SELECT lga.code AS lga_code, lga.name AS lga_name, rec_stats.time_period, " +
                "        ROUND(REPLACE(COALESCE(rec_stats.KerbsideRecyclingBinRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3) AS KerbsideRecyclingPercentage, "
                +
                "        ROUND(REPLACE(COALESCE(rec_stats.CDSRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3) AS CDSRecyclingPercentage, "
                +
                "        ROUND(REPLACE(COALESCE(rec_stats.DropoffRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3) AS DropoffRecyclingPercentage, "
                +
                "        ROUND(REPLACE(COALESCE(rec_stats.CleanupRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3) AS CleanupRecyclingPercentage "
                +
                "    FROM Lga AS lga " +
                "    JOIN LGARecyclingRecycledStatistics AS rec_stats ON lga.code = rec_stats.code " +
                "    JOIN LGARecyclingCollectedStatistics AS coll_stats ON lga.code = coll_stats.code AND rec_stats.time_period = coll_stats.time_period "
                +
                "    WHERE lga.name = '" + selectedLGA + "' AND rec_stats.time_period = '" + timePeriod + "' " +
                "), SimilarLGAs AS (" +
                "    SELECT other_lga.name AS lga_name, rec_stats.time_period, " +
                "        ROUND(REPLACE(COALESCE(rec_stats.KerbsideRecyclingBinRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3) AS KerbsideRecyclingPercentage, "
                +
                "        ROUND(REPLACE(COALESCE(rec_stats.CDSRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3) AS CDSRecyclingPercentage, "
                +
                "        ROUND(REPLACE(COALESCE(rec_stats.DropoffRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3) AS DropoffRecyclingPercentage, "
                +
                "        ROUND(REPLACE(COALESCE(rec_stats.CleanupRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3) AS CleanupRecyclingPercentage, "
                +
                "        ROUND(ABS(SelectedLGA.KerbsideRecyclingPercentage - ROUND(REPLACE(COALESCE(rec_stats.KerbsideRecyclingBinRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3)) + "
                +
                "            ABS(SelectedLGA.CDSRecyclingPercentage - ROUND(REPLACE(COALESCE(rec_stats.CDSRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3)) + "
                +
                "            ABS(SelectedLGA.DropoffRecyclingPercentage - ROUND(REPLACE(COALESCE(rec_stats.DropoffRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3)) + "
                +
                "            ABS(SelectedLGA.CleanupRecyclingPercentage - ROUND(REPLACE(COALESCE(rec_stats.CleanupRecyclingRecycled, '0'), ',', '') * 100.0 / NULLIF(REPLACE(COALESCE(coll_stats.TotalRecyclingCollected, '1'), ',', ''), 0), 3)), 3) AS similarity_score "
                +
                "    FROM Lga AS other_lga " +
                "    JOIN LGARecyclingRecycledStatistics AS rec_stats ON other_lga.code = rec_stats.code " +
                "    JOIN LGARecyclingCollectedStatistics AS coll_stats ON other_lga.code = coll_stats.code AND rec_stats.time_period = coll_stats.time_period "
                +
                "    JOIN SelectedLGA ON rec_stats.time_period = SelectedLGA.time_period " +
                "    WHERE other_lga.name != '" + selectedLGA + "' " +
                ") " +
                "SELECT lga_name, KerbsideRecyclingPercentage, CDSRecyclingPercentage, DropoffRecyclingPercentage, CleanupRecyclingPercentage, similarity_score "
                +
                "FROM SimilarLGAs " +
                "ORDER BY similarity_score ASC " +
                "LIMIT " + cutoffValue + ";";
    }
}
