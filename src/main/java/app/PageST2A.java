package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageST2A implements Handler {

    public static final String URL = "/page2A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Fetch the list of LGAs from the database
        ArrayList<String> lgaList = getAllLGAs();

        String html = "<html>";

        // Add head, CSS, and styling
        html += "<head>";
        html += "<meta charset='UTF-8'>";
        html += "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html += "<title>Focused view of waste and recycling by LGA in 2019-2020</title>";
        html += "<link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap' rel='stylesheet'>";
        html += "<link rel='stylesheet' href='common.css'>";
        html += "<style>";
        html += "body { font-family: 'Playfair Display', serif; margin: 0; padding: 0; color: #333; }";
        html += ".topnav { background-color: #6d9197; padding: 10px; text-align: center; }";
        html += ".topnav a { color: #f2f2f2; margin: 0 15px; text-decoration: none; }";
        html += ".header { background-color: #6d9197; padding: 20px; text-align: center; color: #f2f2f2; }";
        html += ".form-section { background-color: #f9f9f9; padding: 20px; border-radius: 8px; max-width: 600px; margin: 20px auto; }";
        html += ".form-section h2 { color: #6d9197; text-align: center; }";
        html += ".form-group { margin-bottom: 15px; }";
        html += ".form-group label { display: block; margin-bottom: 5px; font-weight: bold; }";
        html += ".form-group select, .form-group input { width: 100%; padding: 8px; border-radius: 4px; border: 1px solid #ccc; }";
        html += ".btn { background-color: #6d9197; color: #f2f2f2; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; display: block; width: 100%; text-align: center; }";
        html += ".footer { background-color: #6d9197; color: #f2f2f2; padding: 20px; text-align: center; }";
        html += ".results-table { margin-top: 20px; width: 100%; border-collapse: collapse; }";
        html += ".results-table th, .results-table td { padding: 10px; text-align: left; border: 1px solid #ddd; }";
        html += "</style>";
        html += "</head>";

        // Start body and navigation
        html += "<body>";
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

        // Add header
        html += """
                <div class='header'>
                    <h1>
                        <img src='logo.png' class='top-image' alt='RMIT logo' height='75'>
                        Focused view of waste and recycling by LGA in 2019-2020
                    </h1>
                </div>
                """;

        // Filter Form
        html += "<div class='content'>";
        html += "<div class='form-section'>";
        html += "<h2>Filter Criteria</h2>";
        html += "<form action='/page2A.html' method='post'>";

        // LGA Dropdown (Multiple Selection)
        html += "<div class='form-group'>";
        html += "<label for='lga-select'>Select LGAs:</label>";
        html += "<select id='lga-select' name='selectedLGAs' multiple size='5'>";
        for (String lga : lgaList) {
            html += "<option value='" + lga + "'>" + lga + "</option>";
        }
        html += "</select>";
        html += "</div>";

        // Waste Resource Type
        html += "<div class='form-group'>";
        html += "<label for='waste-type'>Select Waste Resource Type:</label>";
        html += "<select id='waste-type' name='wasteType'>";
        html += "<option value='recyclable'>Recyclable</option>";
        html += "<option value='organics'>Organics</option>";
        html += "<option value='waste'>Waste</option>";
        html += "</select>";
        html += "</div>";

        // Waste Resource Subtypes
        html += "<div class='form-group'>";
        html += "<label for='subtype-select'>Select Waste Resource Subtypes:</label>";
        html += "<select id='subtype-select' name='wasteSubTypes' multiple>";
        html += "<option value='Kerbside'>Kerbside</option>";
        html += "<option value='CDS'>CDS</option>";
        html += "<option value='Drop off'>Drop off</option>";
        html += "<option value='Cleanup'>Cleanup</option>";
        html += "</select>";
        html += "</div>";

        // Sort By Options
        html += "<div class='form-group'>";
        html += "<label for='sort-by'>Sort By:</label>";
        html += "<select id='sort-by' name='sortBy'>";
        html += "<option value='percentageRecycled'>Percentage Recycled</option>";
        html += "<option value='totalCollected'>Total Collected</option>";
        html += "<option value='totalRecycled'>Total Recycled</option>";
        html += "<option value='avgWastePerHousehold'>Average Waste Per Household</option>";
        html += "</select>";

        html += "<label for='sort-order' style='margin-left: 10px;'>Order:</label>";
        html += "<select id='sort-order' name='orderBy'>";
        html += "<option value='ASC'>Ascending</option>";
        html += "<option value='DESC'>Descending</option>";
        html += "</select>";
        html += "</div>";

        // Submit Button
        html += "<button type='submit' class='btn'>Show Data</button>";
        html += "</form>";
        html += "</div>";
        html += "</div>";

        // Display results if form is submitted
        if (context.method().equals("POST")) {
            // Capture form parameters
            ArrayList<String> selectedLGAs = new ArrayList<>(context.formParams("selectedLGAs"));
            String wasteType = context.formParam("wasteType");
            ArrayList<String> wasteSubTypes = new ArrayList<>(context.formParams("wasteSubTypes"));
            String sortBy = context.formParam("sortBy");
            String orderBy = context.formParam("orderBy");

            // Append table of results to the same page
            html += displayLgaStatistics(selectedLGAs, wasteType, wasteSubTypes, sortBy, orderBy);
        }

        // JavaScript to dynamically change subtypes based on waste type selection
        html += "<script>";
        html += "const wasteTypeSelect = document.getElementById('waste-type');";
        html += "const subtypeSelect = document.getElementById('subtype-select');";
        html += "const subtypes = {";
        html += "  'recyclable': ['Kerbside', 'CDS', 'Drop off', 'Cleanup'],";
        html += "  'organics': ['Kerbside', 'Drop off', 'Cleanup', 'Kerbside FOGO Organics', 'Other Council Garden Organics'],";
        html += "  'waste': ['Kerbside', 'Drop off', 'Cleanup']";
        html += "};";
        html += "wasteTypeSelect.addEventListener('change', function() {";
        html += "  const selectedType = this.value;";
        html += "  const options = subtypes[selectedType] || [];";
        html += "  subtypeSelect.innerHTML = '';";
        html += "  options.forEach(function(subtype) {";
        html += "    const option = document.createElement('option');";
        html += "    option.value = subtype;";
        html += "    option.text = subtype;";
        html += "    subtypeSelect.appendChild(option);";
        html += "  });";
        html += "});";
        html += "</script>";

        // Footer
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

        // Add footer content
        html += "<footer class='footer'>";
        html += "<p>&copy; COSC2803 - Studio Project Starter Code (Sep24)</p>";
        html += "</footer>";

        // Close HTML tags
        html += "</body></html>";

        // Render the webpage
        context.html(html);
    }

    // Method to retrieve all LGAs
    public ArrayList<String> getAllLGAs() {
        ArrayList<String> lgaList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery("SELECT DISTINCT name FROM LGA");
            while (results.next()) {
                lgaList.add(results.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return lgaList;
    }

    // Method to display statistics based on user-selected options
    public String displayLgaStatistics(ArrayList<String> selectedLGAs, String wasteType,
            ArrayList<String> wasteSubTypes, String sortBy, String orderBy) {
        String html = "<div class='results-section'><h2>Results</h2><table class='results-table'>";
        html += "<tr><th>LGA Name</th><th>Population</th><th>Households Surveyed</th><th>Total Collected</th><th>Total Recycled</th><th>Percentage Recycled</th><th>Waste per Household</th></tr>";

        try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                Statement statement = connection.createStatement()) {

            String collectedColumn = "";
            String recycledColumn = "";

            for (String subtype : wasteSubTypes) {
                if (wasteType.equals("recyclable")) {
                    switch (subtype) {
                        case "Kerbside":
                            collectedColumn += "COALESCE(CAST(REPLACE(RCS.KerbsideRecyclingBinCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(RRS.KerbsideRecyclingBinRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "CDS":
                            collectedColumn += "COALESCE(CAST(REPLACE(RCS.CDSRecyclingCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(RRS.CDSRecyclingRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "Drop off":
                            collectedColumn += "COALESCE(CAST(REPLACE(RCS.DropoffRecyclingCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(RRS.DropoffRecyclingRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "Cleanup":
                            collectedColumn += "COALESCE(CAST(REPLACE(RCS.CleanupRecyclingCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(RRS.CleanupRecyclingRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                    }
                } else if (wasteType.equals("organics")) {
                    switch (subtype) {
                        case "Kerbside":
                            collectedColumn += "COALESCE(CAST(REPLACE(OS.KerbsideOrganicsBinCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(ORS.KerbsideOrganicsBinRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "Drop off":
                            collectedColumn += "COALESCE(CAST(REPLACE(OS.DropoffOrganicsCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(ORS.DropoffOrganicsRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "Cleanup":
                            collectedColumn += "COALESCE(CAST(REPLACE(OS.CleanupOrganicsCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(ORS.CleanupOrganicsRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "Kerbside FOGO Organics":
                            collectedColumn += "COALESCE(CAST(REPLACE(OS.KerbsideFOGOOrganicsCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(ORS.KerbsideFOGOOrganicsRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "Other Council Garden Organics":
                            collectedColumn += "COALESCE(CAST(REPLACE(OS.OtherCouncilGardenOrganicsCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(ORS.OtherCouncilGardenOrganicsRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                    }
                } else if (wasteType.equals("waste")) {
                    switch (subtype) {
                        case "Kerbside":
                            collectedColumn += "COALESCE(CAST(REPLACE(WCS.KerbsideWasteCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(WRS.KerbsideWasteRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "Drop off":
                            collectedColumn += "COALESCE(CAST(REPLACE(WCS.DropoffWasteCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(WRS.DropoffWasteRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                        case "Cleanup":
                            collectedColumn += "COALESCE(CAST(REPLACE(WCS.CleanupWasteCollected, ',', '') AS INTEGER), 0) + ";
                            recycledColumn += "COALESCE(CAST(REPLACE(WRS.CleanupWasteRecycled, ',', '') AS INTEGER), 0) + ";
                            break;
                    }
                }
            }

            collectedColumn = collectedColumn.isEmpty() ? "0"
                    : collectedColumn.substring(0, collectedColumn.length() - 3);
            recycledColumn = recycledColumn.isEmpty() ? "0" : recycledColumn.substring(0, recycledColumn.length() - 3);

            String query = "SELECT L.name AS LGA_Name, "
                    + "P.[Population 2019_2020] AS Population, "
                    + "P.[Households Surveyed_2019_2020] AS Households_Surveyed, "
                    + "(" + collectedColumn + ") AS Total_Collected, "
                    + "(" + recycledColumn + ") AS Total_Recycled, "
                    + "CASE WHEN (" + collectedColumn + ") > 0 THEN ROUND((" + recycledColumn + ") * 100.0 / ("
                    + collectedColumn + "), 2) ELSE 0 END AS Percentage_Recycled, "
                    + "CASE WHEN P.[Households Surveyed_2019_2020] > 0 THEN ROUND(CAST((" + collectedColumn
                    + ") AS REAL) / P.[Households Surveyed_2019_2020], 3) ELSE 0 END AS Waste_Per_Household "
                    + "FROM Lga L "
                    + "LEFT JOIN LgaPopulationStatistics P ON L.code = P.LGA_Code "
                    + (wasteType.equals("recyclable")
                            ? "LEFT JOIN LGARecyclingCollectedStatistics RCS ON L.code = RCS.code AND RCS.time_period = '2019-2020' "
                                    + "LEFT JOIN LGARecyclingRecycledStatistics RRS ON L.code = RRS.code AND RRS.time_period = '2019-2020' "
                            : wasteType.equals("organics")
                                    ? "LEFT JOIN LGAOrganicStatistics OS ON L.code = OS.code AND OS.time_period = '2019-2020' "
                                            + "LEFT JOIN LGAOrganicRecycledStatistics ORS ON L.code = ORS.code AND ORS.time_period = '2019-2020' "
                                    : "LEFT JOIN LGAWasteCollectedStatistics WCS ON L.code = WCS.code AND WCS.time_period = '2019-2020' "
                                            + "LEFT JOIN LGAWasteRecycledStatistics WRS ON L.code = WRS.code AND WRS.time_period = '2019-2020' ")
                    + "WHERE L.name IN ("
                    + String.join(", ", selectedLGAs.stream().map(lga -> "'" + lga + "'").toArray(String[]::new)) + ") "
                    + "GROUP BY L.name "
                    + "ORDER BY " + (sortBy.equals("percentageRecycled") ? "Percentage_Recycled" : "Total_Collected")
                    + " " + orderBy;

            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                html += "<tr><td>" + results.getString("LGA_Name") + "</td>"
                        + "<td>" + results.getInt("Population") + "</td>"
                        + "<td>" + results.getInt("Households_Surveyed") + "</td>"
                        + "<td>" + results.getDouble("Total_Collected") + "</td>"
                        + "<td>" + results.getDouble("Total_Recycled") + "</td>"
                        + "<td>" + results.getDouble("Percentage_Recycled") + "</td>"
                        + "<td>" + results.getDouble("Waste_Per_Household") + "</td></tr>";
            }
            html += "</table></div>";

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return html;
    }
}
