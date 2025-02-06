package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class PageST3B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3B.html";

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html>";

        html += "<head>";
        html += "<meta charset='UTF-8'>";
        html += "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html += "<title>Identify changes in recycling and waste levels</title>";
        html += "<link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap' rel='stylesheet'>";
        html += "<link rel='stylesheet' href='common.css'>";
        html += "<style>";
        html += "body { font-family: 'Playfair Display', serif; margin: 0; padding: 0; color: #333; }";
        html += ".topnav { background-color: #6d9197; padding: 10px; text-align: center; }";
        html += ".topnav a { color: #f2f2f2; margin: 0 15px; text-decoration: none; }";
        html += ".banner { background-color: #ddd; padding: 60px; text-align: center; }";
        html += ".banner h2 { margin: 0; }";
        html += "h1, h2, h3, h4, h5, h6 { font-family: 'Playfair Display', serif; }";
        html += ".header { background-color: #6d9197; padding: 20px; text-align: center; color: #f2f2f2; }";
        html += ".form-section { background-color: #f9f9f9; padding: 20px; border-radius: 8px; max-width: 600px; margin: 20px auto; }";
        html += ".form-section h2 { color: #6d9197; text-align: center; }";
        html += ".form-group { margin-bottom: 15px; }";
        html += ".form-group label { display: block; margin-bottom: 5px; font-weight: bold; }";
        html += ".form-group select, .form-group input { width: 100%; padding: 8px; border-radius: 4px; border: 1px solid #ccc; }";
        html += ".btn { background-color: #6d9197; color: #f2f2f2; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; display: block; width: 100%; text-align: center; }";
        html += ".results { margin-top: 20px; text-align: center; }";
        html += ".results table { width: 100%; max-width: 800px; margin: 0 auto; border-collapse: collapse; margin-bottom: 20px; }";
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
                           Identify changes in recycling and waste levels
                           </h1>
                       </h1>
                   </div>
                """;

        // Filter Form
        html += "<div class='content'>";
        html += "<div class='form-section'>";
        html += "<h2>Filter Criteria</h2>";
        html += "<form action='/page3B.html' method='post'>";

        // Start Period Selection
        html += "<div class='form-group'>";
        html += "<label for='start-period'>Select Start Period:</label>";
        html += "<select id='start-period' name='start-period'>";
        html += "<option value='2018-19'>2013-14</option>";
        html += "<option value='2018-19'>2014-15</option>";
        html += "<option value='2018-19'>2015-16</option>";
        html += "<option value='2018-19'>2016-17</option>";
        html += "<option value='2019-20'>2017-18</option>";
        html += "<option value='2020-21'>2018-19</option>";
        html += "<option value='2020-21'>2019-20</option>";
        html += "<option value='2020-21'>2020-21</option>";
        html += "<option value='2020-21'>2021-22</option>";
        html += "</select>";
        html += "</div>";

        // End Period Selection
        html += "<div class='form-group'>";
        html += "<label for='end-period'>Select End Period:</label>";
        html += "<select id='end-period' name='end-period'>";
        html += "<option value='2018-19'>2013-14</option>";
        html += "<option value='2018-19'>2014-15</option>";
        html += "<option value='2018-19'>2015-16</option>";
        html += "<option value='2018-19'>2016-17</option>";
        html += "<option value='2019-20'>2017-18</option>";
        html += "<option value='2020-21'>2018-19</option>";
        html += "<option value='2020-21'>2019-20</option>";
        html += "<option value='2020-21'>2020-21</option>";
        html += "<option value='2020-21'>2021-22</option>";
        html += "</select>";
        html += "</div>";

        // Regional Group Selection
        html += "<div class='form-group'>";
        html += "<label for='regional-group'>Select Regional Group:</label>";
        html += "<select id='regional-group' name='regional-group'>";
        html += "<option value='SMA'>Sydney Metropolitan Area (SMA)</option>";
        html += "<option value='ERA'>Extended Regulated Area (ERA)</option>";
        html += "<option value='RRA'>Regional Regulated Area (RRA)</option>";
        html += "<option value='Rest of NSW'>Rest of NSW</option>";
        html += "</select>";
        html += "</div>";

        // Waste Resource Type Selection
        html += "<div class='form-group'>";
        html += "<label for='waste-type'>Select Waste Resource Type:</label>";
        html += "<select id='waste-type' name='waste-type'>";
        html += "<option value='recyclable'>Recyclable</option>";
        html += "<option value='organics'>Organics</option>";
        html += "<option value='waste'>Waste</option>";
        html += "</select>";
        html += "</div>";

        // Change Display Option
        html += "<div class='form-group'>";
        html += "<label for='change-type'>Display Change As:</label>";
        html += "<select id='change-type' name='change-type'>";
        html += "<option value='percentage'>Percentage Change</option>";
        html += "<option value='absolute'>Absolute Difference</option>";
        html += "</select>";
        html += "</div>";

        // Submit Button
        html += "<button type='submit' class='btn'>Show Data</button>";
        html += "</form>";
        html += "</div>";

        // Display results
        // if (context.method().equals("POST")) {
        String selectedRegion = context.formParam("regional-group");
        String startPeriod = context.formParam("start-period");
        String endPeriod = context.formParam("end-period");
        String wasteType = context.formParam("waste-type");
        String changeType = context.formParam("change-type");

        // }

        html += changeRecycleWaste(startPeriod, endPeriod, selectedRegion, wasteType, changeType);

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

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    private String changeRecycleWaste(String startPeriod, String endPeriod, String regionGroup, String wasteType,
            String changeType) {
        StringBuilder html = new StringBuilder("<div class='results'><h2>Results</h2><table class='results-table'>");

        String query = buildQuery(startPeriod, endPeriod, regionGroup, wasteType);

        try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(query)) {

            if ("percentage".equals(changeType)) {

                html.append(
                        "<tr><th>Regional Group</th><th>Start Period</th><th>End Period</th><th>Total Waste Collected in Start</th><th>Total Waste Collected in End</th><th>Change in Collected(%)</th><th>Total Waste Recycled in Start</th><th>Total Waste Recycled in End</th><th>Change in Recycled(%)</th></tr>");

                while (results.next()) {
                    html.append("<tr>")
                            .append("<td>").append(results.getString("Regional_Group")).append("</td>")
                            .append("<td>").append(results.getString("Start_Period")).append("</td>")
                            .append("<td>").append(results.getString("End_Period")).append("</td>")
                            .append("<td>").append(results.getDouble("Total_Waste_Collected_Start")).append("</td>")
                            .append("<td>").append(results.getDouble("Total_Waste_Collected_End")).append("</td>")
                            // .append("<td>").append(results.getDouble("Absolute_Change_Collected")).append("</td>")
                            .append("<td>").append(results.getDouble("Percentage_Change_Collected")).append("</td>")
                            .append("<td>").append(results.getDouble("Total_Waste_Recycled_Start")).append("</td>")
                            .append("<td>").append(results.getDouble("Total_Waste_Recycled_End")).append("</td>")
                            // .append("<td>").append(results.getDouble("Absolute_Change_Recycled")).append("</td>")
                            .append("<td>").append(results.getDouble("Percentage_Change_Recycled")).append("</td>");
                }
            }

            else if ("absolute".equals(changeType)) {

                html.append(
                        "<tr><th>Regional Group</th><th>Start Period</th><th>End Period</th><th>Total Waste Collected in Start</th><th>Total Waste Collected in End</th><th>Change in Collected</th><th>Total Waste Recycled in Start</th><th>Total Waste Recycled in End</th><th>Change in Recycled</th></tr>");

                while (results.next()) {
                    html.append("<tr>")
                            .append("<td>").append(results.getString("Regional_Group")).append("</td>")
                            .append("<td>").append(results.getString("Start_Period")).append("</td>")
                            .append("<td>").append(results.getString("End_Period")).append("</td>")
                            .append("<td>").append(results.getDouble("Total_Waste_Collected_Start")).append("</td>")
                            .append("<td>").append(results.getDouble("Total_Waste_Collected_End")).append("</td>")
                            .append("<td>").append(results.getDouble("Absolute_Change_Collected")).append("</td>")
                            // .append("<td>").append(results.getDouble("Percentage_Change_Collected")).append("</td>")
                            .append("<td>").append(results.getDouble("Total_Waste_Recycled_Start")).append("</td>")
                            .append("<td>").append(results.getDouble("Total_Waste_Recycled_End")).append("</td>")
                            .append("<td>").append(results.getDouble("Absolute_Change_Recycled")).append("</td>");
                    // .append("<td>").append(results.getDouble("Percentage_Change_Recycled")).append("</td>");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        html.append("</table></div>");
        return html.toString();
    }

    private String buildQuery(String startPeriod, String endPeriod, String regionGroup, String wasteType) {

        if ("recyclable".equals(wasteType)) {

            String theQuery;

            theQuery = """

                                WITH StartPeriodData AS (
                    SELECT
                        RC1.[Region Type] AS Regional_Group,
                        RC1.Period AS Start_Period,

                        -- Total collected and recycled for 'recyclable' waste type in start period
                        SUM(RC1.[Recyclables Collected]) AS Total_Waste_Collected_Start,
                        SUM(RR1.[Recyclables Recycled]) AS Total_Waste_Recycled_Start
                    FROM
                        RegionalCollectedStatistics RC1
                    JOIN
                        RegionalRecycledStatistics RR1 ON RC1.[Region Type] = RR1.[Region Type] AND RC1.Period = RR1.Period
                    WHERE
                        RC1.[Region Type] = '"""
                    + regionGroup + "'" + """

                            AND RC1.Period = '""" + startPeriod + "'"
                    + """
                                GROUP BY
                                    RC1.[Region Type], RC1.Period
                            ),

                            EndPeriodData AS (
                                SELECT
                                    RC2.[Region Type] AS Regional_Group,
                                    RC2.Period AS End_Period,

                                    -- Total collected and recycled for 'recyclable' waste type in end period
                                    SUM(RC2.[Recyclables Collected]) AS Total_Waste_Collected_End,
                                    SUM(RR2.[Recyclables Recycled]) AS Total_Waste_Recycled_End
                                FROM
                                    RegionalCollectedStatistics RC2
                                JOIN
                                    RegionalRecycledStatistics RR2 ON RC2.[Region Type] = RR2.[Region Type] AND RC2.Period = RR2.Period
                                WHERE
                                    RC2.[Region Type] = '"""
                    + regionGroup + "'" + """
                            AND RC2.Period = '""" + endPeriod + "'"
                    + """
                                GROUP BY
                                    RC2.[Region Type], RC2.Period
                            )

                            SELECT
                                StartPeriodData.Regional_Group,
                                StartPeriodData.Start_Period,
                                EndPeriodData.End_Period,
                                StartPeriodData.Total_Waste_Collected_Start,
                                EndPeriodData.Total_Waste_Collected_End,

                                -- Absolute and percentage change in waste collected
                                (EndPeriodData.Total_Waste_Collected_End - StartPeriodData.Total_Waste_Collected_Start) AS Absolute_Change_Collected,
                                CASE
                                    WHEN StartPeriodData.Total_Waste_Collected_Start > 0
                                    THEN ((EndPeriodData.Total_Waste_Collected_End - StartPeriodData.Total_Waste_Collected_Start) / StartPeriodData.Total_Waste_Collected_Start) * 100
                                    ELSE NULL
                                END AS Percentage_Change_Collected,

                                StartPeriodData.Total_Waste_Recycled_Start,
                                EndPeriodData.Total_Waste_Recycled_End,

                                -- Absolute and percentage change in waste recycled
                                (EndPeriodData.Total_Waste_Recycled_End - StartPeriodData.Total_Waste_Recycled_Start) AS Absolute_Change_Recycled,
                                CASE
                                    WHEN StartPeriodData.Total_Waste_Recycled_Start > 0
                                    THEN ((EndPeriodData.Total_Waste_Recycled_End - StartPeriodData.Total_Waste_Recycled_Start) / StartPeriodData.Total_Waste_Recycled_Start) * 100
                                    ELSE NULL
                                END AS Percentage_Change_Recycled

                            FROM
                                StartPeriodData
                            JOIN
                                EndPeriodData ON StartPeriodData.Regional_Group = EndPeriodData.Regional_Group;


                                                """;

            return theQuery;
        }

        else if ("organics".equals(wasteType)) {

            String theQuery;

            theQuery = """

                                WITH StartPeriodData AS (
                    SELECT
                        RC1.[Region Type] AS Regional_Group,
                        RC1.Period AS Start_Period,

                        -- Hardcoded to 'organics' waste type
                        SUM(RC1.[Organics Collected]) AS Total_Waste_Collected_Start,
                        SUM(RR1.[Organics Recycled]) AS Total_Waste_Recycled_Start
                    FROM
                        RegionalCollectedStatistics RC1
                    JOIN
                        RegionalRecycledStatistics RR1 ON RC1.[Region Type] = RR1.[Region Type] AND RC1.Period = RR1.Period
                    WHERE
                        RC1.[Region Type] = '"""
                    + regionGroup + "'" + """
                            AND RC1.Period = '""" + startPeriod + "'"
                    + """
                                GROUP BY
                                    RC1.[Region Type], RC1.Period
                            ),

                            EndPeriodData AS (
                                SELECT
                                    RC2.[Region Type] AS Regional_Group,
                                    RC2.Period AS End_Period,

                                    -- Hardcoded to 'organics' waste type
                                    SUM(RC2.[Organics Collected]) AS Total_Waste_Collected_End,
                                    SUM(RR2.[Organics Recycled]) AS Total_Waste_Recycled_End
                                FROM
                                    RegionalCollectedStatistics RC2
                                JOIN
                                    RegionalRecycledStatistics RR2 ON RC2.[Region Type] = RR2.[Region Type] AND RC2.Period = RR2.Period
                                WHERE
                                    RC2.[Region Type] = '"""
                    + regionGroup + "'" + """
                            AND RC2.Period = '""" + endPeriod + "'"
                    + """
                                GROUP BY
                                    RC2.[Region Type], RC2.Period
                            )

                            SELECT
                                StartPeriodData.Regional_Group,
                                StartPeriodData.Start_Period,
                                EndPeriodData.End_Period,
                                StartPeriodData.Total_Waste_Collected_Start,
                                EndPeriodData.Total_Waste_Collected_End,

                                -- Absolute and percentage change in waste collected
                                (EndPeriodData.Total_Waste_Collected_End - StartPeriodData.Total_Waste_Collected_Start) AS Absolute_Change_Collected,
                                CASE
                                    WHEN StartPeriodData.Total_Waste_Collected_Start > 0
                                    THEN ((EndPeriodData.Total_Waste_Collected_End - StartPeriodData.Total_Waste_Collected_Start) / StartPeriodData.Total_Waste_Collected_Start) * 100
                                    ELSE NULL
                                END AS Percentage_Change_Collected,

                                StartPeriodData.Total_Waste_Recycled_Start,
                                EndPeriodData.Total_Waste_Recycled_End,

                                -- Absolute and percentage change in waste recycled
                                (EndPeriodData.Total_Waste_Recycled_End - StartPeriodData.Total_Waste_Recycled_Start) AS Absolute_Change_Recycled,
                                CASE
                                    WHEN StartPeriodData.Total_Waste_Recycled_Start > 0
                                    THEN ((EndPeriodData.Total_Waste_Recycled_End - StartPeriodData.Total_Waste_Recycled_Start) / StartPeriodData.Total_Waste_Recycled_Start) * 100
                                    ELSE NULL
                                END AS Percentage_Change_Recycled

                            FROM
                                StartPeriodData
                            JOIN
                                EndPeriodData ON StartPeriodData.Regional_Group = EndPeriodData.Regional_Group;


                                                """;

            return theQuery;
        }

        else {

            String theQuery;

            theQuery = """

                                WITH StartPeriodData AS (
                    SELECT
                        RC1.[Region Type] AS Regional_Group,
                        RC1.Period AS Start_Period,

                        -- Hardcoded to 'waste' waste type
                        SUM(RC1.[Residual Waste Collected]) AS Total_Waste_Collected_Start,
                        SUM(RR1.[Residual Waste Recycled]) AS Total_Waste_Recycled_Start
                    FROM
                        RegionalCollectedStatistics RC1
                    JOIN
                        RegionalRecycledStatistics RR1 ON RC1.[Region Type] = RR1.[Region Type] AND RC1.Period = RR1.Period
                    WHERE
                        RC1.[Region Type] = '"""
                    + regionGroup + "'" + """
                            AND RC1.Period = '""" + startPeriod + "'"
                    + """
                                GROUP BY
                                    RC1.[Region Type], RC1.Period
                            ),

                            EndPeriodData AS (
                                SELECT
                                    RC2.[Region Type] AS Regional_Group,
                                    RC2.Period AS End_Period,

                                    -- Hardcoded to 'waste' waste type
                                    SUM(RC2.[Residual Waste Collected]) AS Total_Waste_Collected_End,
                                    SUM(RR2.[Residual Waste Recycled]) AS Total_Waste_Recycled_End
                                FROM
                                    RegionalCollectedStatistics RC2
                                JOIN
                                    RegionalRecycledStatistics RR2 ON RC2.[Region Type] = RR2.[Region Type] AND RC2.Period = RR2.Period
                                WHERE
                                    RC2.[Region Type] = '"""
                    + regionGroup + "'" + """
                            AND RC2.Period = '""" + endPeriod + "'"
                    + """
                                GROUP BY
                                    RC2.[Region Type], RC2.Period
                            )

                            SELECT
                                StartPeriodData.Regional_Group,
                                StartPeriodData.Start_Period,
                                EndPeriodData.End_Period,
                                StartPeriodData.Total_Waste_Collected_Start,
                                EndPeriodData.Total_Waste_Collected_End,

                                -- Absolute and percentage change in waste collected
                                (EndPeriodData.Total_Waste_Collected_End - StartPeriodData.Total_Waste_Collected_Start) AS Absolute_Change_Collected,
                                CASE
                                    WHEN StartPeriodData.Total_Waste_Collected_Start > 0
                                    THEN ((EndPeriodData.Total_Waste_Collected_End - StartPeriodData.Total_Waste_Collected_Start) / StartPeriodData.Total_Waste_Collected_Start) * 100
                                    ELSE NULL
                                END AS Percentage_Change_Collected,

                                StartPeriodData.Total_Waste_Recycled_Start,
                                EndPeriodData.Total_Waste_Recycled_End,

                                -- Absolute and percentage change in waste recycled
                                (EndPeriodData.Total_Waste_Recycled_End - StartPeriodData.Total_Waste_Recycled_Start) AS Absolute_Change_Recycled,
                                CASE
                                    WHEN StartPeriodData.Total_Waste_Recycled_Start > 0
                                    THEN ((EndPeriodData.Total_Waste_Recycled_End - StartPeriodData.Total_Waste_Recycled_Start) / StartPeriodData.Total_Waste_Recycled_Start) * 100
                                    ELSE NULL
                                END AS Percentage_Change_Recycled

                            FROM
                                StartPeriodData
                            JOIN
                                EndPeriodData ON StartPeriodData.Regional_Group = EndPeriodData.Regional_Group;


                                                """;

            return theQuery;

        }

    }
}
