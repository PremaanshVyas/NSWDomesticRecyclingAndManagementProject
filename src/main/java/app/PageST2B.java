package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2B.html";

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html>";

        html += "<head>";
        html += "<meta charset='UTF-8'>";
        html += "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html += "<title>Focused view of waste and recycling by Regional Group</title>";
        html += "<link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap' rel='stylesheet'>";
        html += "<link rel='stylesheet' href='common.css'>";
        html += "<style>";
        html += "body { font-family: 'Playfair Display', serif; margin: 0; padding: 0; color: #333; }";
        html += ".topnav { background-color: #6d9197; padding: 10px; text-align: center; }";
        html += ".topnav a { color: #f2f2f2; margin: 0 15px; text-decoration: none; }";
        html += ".banner { background-color: #ddd; padding: 60px; text-align: center; }";
        html += ".banner h2 { margin: 0; }";
        html += "h1, h2, h3, h4, h5, h6 { font-family: 'Playfair Display', serif; }";
        html += ".form-section { background-color: #f9f9f9; padding: 20px; border-radius: 8px; max-width: 600px; margin: 0 auto; }";
        html += ".form-section h2 { color: #6d9197; text-align: center; }";
        html += ".form-group { margin-bottom: 15px; }";
        html += ".form-group label { display: block; margin-bottom: 5px; font-weight: bold; }";
        html += ".form-group select, .form-group input { width: 100%; padding: 8px; border-radius: 4px; border: 1px solid #ccc; }";
        html += ".btn { background-color: #6d9197; color: #f2f2f2; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; display: block; width: 100%; text-align: center; }";
        html += ".results { margin-top: 20px; }";
        html += ".results table { width: 100%; border-collapse: collapse; }";
        html += ".results th, .results td { padding: 10px; border: 1px solid #ccc; text-align: left; }";
        html += ".results th { background-color: #6d9197; color: #f2f2f2; }";
        html += ".footer { background-color: #6d9197; color: #f2f2f2; padding: 20px; text-align: center; }";
        html += "</style>";
        html += "</head>";

        html += "<body>";

        html += "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
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

        // Add header content block
        html = html + """
                    <div class='header' style='background-color: #6d9197;'>
                        <h1>
                            <a href='/'><img src='logo.png' class='top-image' alt='RMIT logo' height='75'></a>
                            <h1 style='color:#f2f2f2;'>
                            Focused view of waste and recycling by Regional Group
                            </h1>
                        </h1>
                    </div>
                """;

        // Content section
        html += "<div class='content'>";
        html += "<div class='form-section'>";
        html += "<h2>Filter Criteria</h2>";

        // Regional Group Dropdown
        html += "<div class='form-group'>";
        html += "<form action='/page2B.html' method='post'>";
        html += "<label for='regional-group'>Select Regional Group:</label>";
        html += "<select id='regional-group' name='regional-group'>";
        html += "<option value='SMA'>Sydney Metropolitan Area (SMA)</option>";
        html += "<option value='ERA'>Extended Regulated Area (ERA)</option>";
        html += "<option value='RRA'>Regional Regulated Area (RRA)</option>";
        html += "<option value='Rest of NSW'>Rest of NSW</option>";
        html += "</select>";
        html += "</div>";

        // Waste Resource Type Dropdown
        html += "<div class='form-group'>";
        html += "<label for='waste-type'>Select Waste Resource Type:</label>";
        html += "<select id='waste-type' name='waste-type'>";
        html += "<option value='recyclable'>Recyclable</option>";
        html += "<option value='organics'>Organics</option>";
        html += "<option value='waste'>Waste</option>";
        html += "</select>";
        html += "</div>";

        // Threshold Input
        html += "<div class='form-group'>";
        html += "<label for='threshold'>Enter Threshold (tonnes):</label>";
        html += "<input type='number' id='threshold' name='threshold' min='0'>";
        html += "</div>";

        // Waste Statistic for Threshold
        html += "<div class='form-group'>";
        html += "<label for='statistic'>Apply Threshold to Statistic:</label>";
        html += "<select id='statistic' name='statistic'>";
        html += "<option value='Collected'>Collected</option>";
        html += "<option value='Recycled'>Recycled</option>";
        html += "<option value='Disposed'>Disposed</option>";
        html += "</select>";
        html += "</div>";

        // Sorting Options
        html += "<div class='form-group'>";
        html += "<label for='sort-by'>Sort By:</label>";
        html += "<select id='sort-by' name='sort-by'>";
        html += "<option value='Annual_Period'>Annual Period</option>";
        html += "<option value='Total_Waste_Collected'>Total Collected</option>";
        html += "<option value='Total_Waste_Recycled'>Total Recycled</option>";
        html += "<option value='Total_Waste_Disposed'>Total Disposed</option>";
        html += "<option value='percentageDisposed'>Percentage Disposed</option>";
        html += "</select>";

        html += "<label for='sort-order'>Order:</label>";
        html += "<select id='sort-order' name='sort-order'>";
        html += "<option value='ASC'>Ascending</option>";
        html += "<option value='DESC'>Descending</option>";
        html += "</select>";
        html += "</div>";

        // Submit Button
        html += "<button type='submit' class='btn'>Show Data</button>";
        html += "</form>";
        html += "</div>";

        // Display results
        if (context.method().equals("POST")) {
            String selectedRegion = context.formParam("regional-group");
            String wasteType = context.formParam("waste-type");
            String threshold = context.formParam("threshold");
            String statistic = context.formParam("statistic");
            String sortBy = context.formParam("sort-by");
            String sortOrder = context.formParam("sort-order");

            html += getWasteRecycleByRegion(selectedRegion, wasteType, threshold, statistic, sortBy, sortOrder);

        }

        // Footer
        html = html + """
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
                        <center><img src='logo.png' alt='Rmit logo' width='300' height='100'></center>
                    </div>
                </div>
                        """;

        // Add the footer
        html = html + "<footer class=\"footer\">";
        html = html + "<p>&copy; COSC2803 - Studio Project Starter Code (Sep24)</p>";
        html = html + "</footer>";

        html += "</body></html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    private String getWasteRecycleByRegion(String selectedRegion, String wasteType, String threshold, String statistic,
            String sortBy, String sortOrder) {

        StringBuilder html = new StringBuilder("<div class='results'><h2>Results</h2><table class='results-table'>");
        html.append(
                "<tr><th>Annual Period</th><th>Regional Group</th><th>Total Waste Collected</th><th>Total Waste Recycled</th><th>Total Waste Disposed</th><th>Disposal(%)</th></tr>");

        String query = buildQuery(selectedRegion, wasteType, threshold, statistic, sortBy, sortOrder);

        try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(query)) {

            while (results.next()) {
                html.append("<tr>")
                        .append("<td>").append(results.getString("Annual_Period")).append("</td>")
                        .append("<td>").append(results.getString("Regional_Group")).append("</td>")
                        .append("<td>").append(results.getDouble("Total_Waste_Collected")).append("</td>")
                        .append("<td>").append(results.getDouble("Total_Waste_Recycled")).append("</td>")
                        .append("<td>").append(results.getDouble("Total_Waste_Disposed")).append("</td>")
                        .append("<td>").append(results.getDouble("Disposal_Percentage_Relative_to_State"))
                        .append("</tr>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        html.append("</table></div>");
        return html.toString();
    }

    private String buildQuery(String selectedRegion, String wasteType, String threshold, String statistic,
            String sortBy, String sortOrder) {

        return """

                               SELECT
                    RC.Period AS Annual_Period,
                    RC.[Region Type] AS Regional_Group,

                    -- Calculate totals for each waste resource type (waste)
                    SUM(RC.[Residual Waste Collected]) AS Total_Waste_Collected,
                    SUM(RR.[Residual Waste Recycled]) AS Total_Waste_Recycled,
                    SUM(RD.[Residual Waste Disposed]) AS Total_Waste_Disposed,

                    -- Calculate the disposal percentage relative to the entire state
                    (SUM(RD.[Residual Waste Disposed]) /
                        (SELECT SUM(RD2.[Residual Waste Disposed])
                         FROM RegionalDisposedStatistics RD2
                         WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

                FROM
                    RegionalCollectedStatistics RC
                JOIN
                    RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND RC.Period = RR.Period
                JOIN
                    RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND RC.Period = RD.Period

                WHERE
                    RC.[Region Type] =
                    """ +
                " '" + selectedRegion + "'" + """

                        GROUP BY
                            RC.Period

                        HAVING
                            SUM(RC.[Residual Waste Collected]) > """ + threshold + """

                        ORDER BY """ + " " + sortBy + " " + sortOrder;

    }

    // private String buildQuery(String selectedRegion, String wasteType, String
    // threshold, String statistic,
    // String sortBy, String sortOrder) {

    // if (wasteType == "recyclable") {

    // if (statistic == "Collected") {

    // String theQuery;

    // theQuery = """

    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (recyclable)
    // SUM(RC.[Recyclables Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Recyclables Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Recyclables Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Recyclables Disposed]) /
    // (SELECT SUM(RD2.[Recyclables Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // // RC.[Region Type] =
    // """
    // + selectedRegion + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RC.[Recyclables Collected]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;

    // }

    // else if (statistic == "Recycled") {

    // String theQuery;

    // theQuery = """

    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (recyclable)
    // SUM(RC.[Recyclables Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Recyclables Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Recyclables Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Recyclables Disposed]) /
    // (SELECT SUM(RD2.[Recyclables Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // RC.[Region Type] = '"""
    // + selectedRegion + "'" + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RR.[Recyclables Recycled]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;

    // }

    // else if (statistic == "Disposed") {

    // String theQuery;

    // theQuery = """

    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (recyclable)
    // SUM(RC.[Recyclables Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Recyclables Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Recyclables Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Recyclables Disposed]) /
    // (SELECT SUM(RD2.[Recyclables Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // RC.[Region Type] = '"""
    // + selectedRegion + "'" + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RD.[Recyclables Disposed]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;

    // }

    // }

    // else if (wasteType == "organics") {

    // if (statistic == "Collected") {

    // String theQuery;

    // theQuery = """
    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (organics)
    // SUM(RC.[Organics Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Organics Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Organics Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Organics Disposed]) /
    // (SELECT SUM(RD2.[Organics Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // RC.[Region Type] = '"""
    // + selectedRegion + "'" + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RC.[Organics Collected]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;

    // }

    // else if (statistic == "Recycled") {

    // String theQuery;

    // theQuery = """

    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (organics)
    // SUM(RC.[Organics Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Organics Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Organics Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Organics Disposed]) /
    // (SELECT SUM(RD2.[Organics Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // RC.[Region Type] = '"""
    // + selectedRegion + "'" + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RR.[Organics Recycled]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;

    // }

    // else if (statistic == "Disposed") {

    // String theQuery;

    // theQuery = """

    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (organics)
    // SUM(RC.[Organics Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Organics Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Organics Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Organics Disposed]) /
    // (SELECT SUM(RD2.[Organics Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // RC.[Region Type] = '"""
    // + selectedRegion + "'" + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RD.[Organics Disposed]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;

    // }

    // }

    // else if (wasteType == "waste") {

    // if (statistic == "Collected") {

    // String theQuery;

    // theQuery = """

    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (waste)
    // SUM(RC.[Residual Waste Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Residual Waste Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Residual Waste Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Residual Waste Disposed]) /
    // (SELECT SUM(RD2.[Residual Waste Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // RC.[Region Type] = '"""
    // + selectedRegion + "'" + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RC.[Residual Waste Collected]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;

    // }

    // else if (statistic == "Recycled") {

    // String theQuery;

    // theQuery = """

    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (waste)
    // SUM(RC.[Residual Waste Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Residual Waste Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Residual Waste Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Residual Waste Disposed]) /
    // (SELECT SUM(RD2.[Residual Waste Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // RC.[Region Type] = '"""
    // + selectedRegion + "'" + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RR.[Residual Waste Recycled]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;

    // }

    // else if (statistic == "Disposed") {

    // String theQuery;

    // theQuery = """

    // SELECT
    // RC.Period AS Annual_Period,
    // RC.[Region Type] AS Regional_Group,

    // -- Calculate totals for each waste resource type (waste)
    // SUM(RC.[Residual Waste Collected]) AS Total_Waste_Collected,
    // SUM(RR.[Residual Waste Recycled]) AS Total_Waste_Recycled,
    // SUM(RD.[Residual Waste Disposed]) AS Total_Waste_Disposed,

    // -- Calculate the disposal percentage relative to the entire state
    // (SUM(RD.[Residual Waste Disposed]) /
    // (SELECT SUM(RD2.[Residual Waste Disposed])
    // FROM RegionalDisposedStatistics RD2
    // WHERE RD2.Period = RC.Period)) * 100 AS Disposal_Percentage_Relative_to_State

    // FROM
    // RegionalCollectedStatistics RC
    // JOIN
    // RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN
    // RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period

    // WHERE
    // RC.[Region Type] = '"""
    // + selectedRegion + "'" + """

    // GROUP BY
    // RC.Period

    // HAVING
    // SUM(RD.[Residual Waste Disposed]) > """ + threshold + """

    // ORDER BY """ + sortBy + " " + sortOrder;

    // return theQuery;
    // }

    // }

    // return "";

    // }

    // private String getWasteRecycleByRegion(String selectedRegion, String
    // wasteType, String threshold, String statistic,
    // String sortBy, String sortOrder) {

    // StringBuilder html = new StringBuilder("<div
    // class='results'><h2>Results</h2><table class='results-table'>");
    // html.append(
    // "<tr><th>Region</th><th>Annual Period</th><th>Total Collected</th><th>Total
    // Recycled</th><th>Total Disposed</th><th>Percentage Disposed</th></tr>");

    // String query = buildQuery(selectedRegion, wasteType, threshold, statistic,
    // sortBy, sortOrder);

    // try (Connection connection =
    // DriverManager.getConnection(JDBCConnection.DATABASE);
    // Statement statement = connection.createStatement();
    // ResultSet results = statement.executeQuery(query)) {

    // while (results.next()) {
    // html.append("<tr>")
    // .append("<td>").append(results.getString("Region")).append("</td>")
    // .append("<td>").append(results.getString("AnnualPeriod")).append("</td>")
    // .append("<td>").append(results.getDouble("TotalCollected")).append("</td>")
    // .append("<td>").append(results.getDouble("TotalRecycled")).append("</td>")
    // .append("<td>").append(results.getDouble("TotalDisposed")).append("</td>")
    // .append("<td>").append(results.getDouble("PercentageDisposed")).append("</td>")
    // .append("</tr>");
    // }

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // html.append("</table></div>");
    // return html.toString();
    // }

    // private String buildQuery(String selectedRegion, String wasteType, String
    // threshold, String statistic,
    // String sortBy, String sortOrder) {
    // // Adapting the query based on the parameters provided
    // String query = """
    // WITH StateTotalDisposal AS (
    // SELECT
    // Period,
    // SUM(%s) AS State_Disposal_Total
    // FROM RegionalDisposedStatistics
    // GROUP BY Period
    // ),
    // FilteredRegionalData AS (
    // SELECT
    // RC.[Region Type] AS Region,
    // RC.Period,
    // SUM(RC.%s) AS TotalCollected,
    // SUM(RR.%s) AS TotalRecycled,
    // SUM(RD.%s) AS TotalDisposed
    // FROM RegionalCollectedStatistics RC
    // JOIN RegionalRecycledStatistics RR ON RC.[Region Type] = RR.[Region Type] AND
    // RC.Period = RR.Period
    // JOIN RegionalDisposedStatistics RD ON RC.[Region Type] = RD.[Region Type] AND
    // RC.Period = RD.Period
    // WHERE RC.[Region Type] = '%s'
    // GROUP BY RC.[Region Type], RC.Period
    // HAVING TotalDisposed > %s
    // )
    // SELECT
    // F.Region,
    // F.Period AS AnnualPeriod,
    // ROUND(F.TotalCollected, 3) AS TotalCollected,
    // ROUND(F.TotalRecycled, 3) AS TotalRecycled,
    // ROUND(F.TotalDisposed, 3) AS TotalDisposed,
    // ROUND((F.TotalDisposed / STD.State_Disposal_Total) * 100, 3) AS
    // PercentageDisposed
    // FROM
    // FilteredRegionalData F
    // JOIN
    // StateTotalDisposal STD ON F.Period = STD.Period
    // ORDER BY
    // %s %s;
    // """;

    // // Selecting appropriate columns for wasteType
    // String collectedColumn = "";
    // String recycledColumn = "";
    // String disposedColumn = "";

    // if (wasteType.equalsIgnoreCase("recyclable")) {
    // collectedColumn = "[Recyclables Collected]";
    // recycledColumn = "[Recyclables Recycled]";
    // disposedColumn = "[Recyclables Disposed]";
    // } else if (wasteType.equalsIgnoreCase("organics")) {
    // collectedColumn = "[Organics Collected]";
    // recycledColumn = "[Organics Recycled]";
    // disposedColumn = "[Organics Disposed]";
    // } else if (wasteType.equalsIgnoreCase("waste")) {
    // collectedColumn = "[Residual Waste Collected]";
    // recycledColumn = "[Residual Waste Recycled]";
    // disposedColumn = "[Residual Waste Disposed]";
    // }

    // // Formatting the query with user input
    // return String.format(query, disposedColumn, collectedColumn, recycledColumn,
    // disposedColumn,
    // selectedRegion, threshold, sortBy, sortOrder);
    // }
}