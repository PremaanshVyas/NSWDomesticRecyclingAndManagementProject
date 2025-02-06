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

public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
        html += "<head>";
        html += "<meta charset='UTF-8'>";
        html += "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html += "<title>Recycling in NSW</title>";
        html += "<link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap' rel='stylesheet'>";
        html += "<link rel='stylesheet' href='common.css'>";
        html += "<style>";
        html += "body { font-family: 'Playfair Display', serif; margin: 0; padding: 0; color: #333; }";
        html += ".topnav { background-color: #6d9197; padding: 10px; text-align: center; }";
        html += ".topnav a { color: #f2f2f2; margin: 0 15px; text-decoration: none; }";
        html += ".banner { background-color: #ddd; padding: 60px; text-align: center; }";
        html += ".banner h2 { margin: 0; }";
        html += "h1, h2, h3, h4, h5, h6 { font-family: 'Playfair Display', serif; }";
        html += ".section-title { font-size: 1.5em; margin: 40px 0; }";
        html += ".chart-section { display: flex; flex-direction: column; align-items: center; margin-bottom: 40px; }";
        html += ".chart { width: 60%; height: 480px; background-color: #ddd; margin-bottom: 20px; }";
        html += ".chart img { height: 80%; width: 100%; }";
        html += ".stats-container { display: flex; justify-content: center; gap: 20px; padding: 20px; }";
        html += ".stat-box { width: 200px; padding: 20px; border: 1px solid #ccc; border-radius: 8px; background-color: #f9f9f9; text-align: center; }";
        html += ".stat-box h3 { margin: 0; font-size: 1.2em; }";
        html += ".stat-box p { font-size: 2em; margin: 10px 0; }";
        html += ".stat-box small { font-size: 0.9em; color: #666; }";

        html += ".footer { background-color: #6d9197; color: #f2f2f2; padding: 20px; text-align: center; }";
        html += ".footer a { color: #f2f2f2; text-decoration: none; }";
        html += ".footer .menu { display: flex; justify-content: space-around; }";
        html += "</style>";
        html += "</head>";

        html += "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
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
                            Home
                            </h1>
                        </h1>
                    </div>
                """;

        // Banner
        html += "<header class='banner'>";
        html += "<h1>Welcome to Recycling in NSW</h1>";
        html += """
                <p style='text-align: justify; padding-left: 60px; padding-right: 60px; font-size:18px;'>
                The "Domestic Waste Management and Recycling" website educates users on the environmental impact of recycling and waste management, emphasizing benefits like reducing landfill use, greenhouse gas emissions, groundwater contamination, and air pollution. By raising awareness of these issues, the site aims to promote recycling as a meaningful contribution to sustainability.
                  </p>
                            """;

        html += """
                <p style='text-align: justify; padding-left: 60px; padding-right: 60px; font-size:18px;'>
                The website offers localized data for various Local Government Areas (LGAs) across NSW, giving users insights into specific waste management efforts within their own or neighboring regions. This feature allows residents and councils to gauge progress in waste reduction and identify areas needing improvement. Additionally, the site aggregates waste and recycling data for broader regions, such as the Sydney Metropolitan Area and the Rest of NSW, facilitating comparisons and trend analysis across these larger areas.
                  </p>
                            """;

        html += """
                <p style='text-align: justify; padding-left: 60px; padding-right: 60px; font-size:18px;'>
                Users can track changes in waste production, recycling, and disposal over time, offering a clear picture of trends in waste management practices. The site also categorizes waste into types—recyclables, organics, and general waste—along with specific subcategories, allowing for targeted data analysis by waste type.
                  </p>
                            """;

        html += """
                <p style='text-align: justify; padding-left: 60px; padding-right: 60px; font-size:18px;'>
                Designed to foster community engagement, the website equips residents and policymakers with accessible, unbiased data. It provides transparent information to encourage informed decision-making and supports community involvement in waste reduction efforts. Through these focused topics, the website offers a user-friendly, informative experience that promotes sustainable waste management and recycling.
                  </p>
                            """;

        html += "</header>";

        // Chart Section
        html += "<div class='chart-section' style='padding-top: 80px; object-fit: contain;'>";
        html += "<div class='chart' style='padding-left: 60px; padding-right: 60px; object-fit: contain; '>";
        html += "<h3 style='padding-left: 20px;'>Comparison of Houses Surveyed</h3>";
        html += "<img src='Surveys.jpg'>";
        html += "</div>";
        html += "</div>";
        // class='top-image' alt='chart' height='75'

        // Statistics Section
        html += "<section>";
        html += "<h2 class='section-title' style='text-align: center;'>2018-2019 Statistics</h2>";

        // Stats Summary
        html += "<div class='stats-container'>";
        html += "<div class='stat-box'>";

        ArrayList<String> lgasDetails = getLgasSurveysDetails();

        html += "<h3>Total LGAs</h3>";
        html += "<p>" + lgasDetails.get(0) + "</p>";
        html += "</div>";
        html += "<div class='stat-box'>";
        html += "<h3>Houses Surveyed</h3>";
        html += "<p>" + lgasDetails.get(1) + "</p>";
        html += "</div>";
        html += "</div>";

        html += "</section>";

        // Statistics Section
        html += "<section>";
        html += "<h2 class='section-title' style='text-align: center;'>2019-2020 Statistics</h2>";

        // Stats Summary
        html += "<div class='stats-container' style='padding-bottom: 70px;'>";
        html += "<div class='stat-box'>";
        html += "<h3>Total LGAs</h3>";
        html += "<p>" + lgasDetails.get(2) + "</p>";
        html += "</div>";
        html += "<div class='stat-box'>";
        html += "<h3>Houses Surveyed</h3>";
        html += "<p>" + lgasDetails.get(3) + "</p>";
        html += "<small>+2.5% compared to last year</small>";
        html += "</div>";
        html += "</div>";

        html += "</section>";

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

        html += "</body>";
        html += "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    /**
     * Get the names of the countries in the database.
     */
    public ArrayList<String> getAllLGAs() {
        // Create the ArrayList of String objects to return
        ArrayList<String> lgas = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM lga";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String lgaName = results.getString("name");

                // Add the country object to the array
                lgas.add(lgaName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just print the error
            System.err.println(e.getMessage());
            // e.printStackTrace();
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                // e.printStackTrace();
            }
        }

        // Finally we return all of the countries
        return lgas;
    }

    public ArrayList<String> getLgasSurveysDetails() {
        // Initialize an ArrayList to store student details
        ArrayList<String> lgaSurveysDetails = new ArrayList<>();

        // Declare the JDBC connection variable
        Connection connection = null;

        try {
            // Establish a connection to the JDBC database
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Create a new SQL statement and set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Define the SQL query
            String query = """
                                    SELECT

                    COUNT(DISTINCT L.LGA_Code) AS Total_LGAs_2018_2019,

                    SUM(L.'Households Surveyed_2018_2019') AS Total_Houses_Surveyed_2018_2019,

                    COUNT(DISTINCT L.LGA_Code) AS Total_LGAs_2019_2020,

                    SUM(L.'Households Surveyed_2019_2020') AS Total_Houses_Surveyed_2019_2020

                    FROM

                    LgaPopulationStatistics AS L

                    JOIN

                    Lga AS LG ON L.LGA_Code = LG.code

                    WHERE

                    (L.'Households Surveyed_2018_2019' IS NOT NULL OR

                    L.'Households Surveyed_2019_2020' IS NOT NULL);
                            """;

            // Execute the query and retrieve results
            ResultSet results = statement.executeQuery(query);

            // Iterate through the result set
            while (results.next()) {
                // Retrieve each column value
                String totalLgas18_19 = results.getString("Total_LGAs_2018_2019");
                String totalHousesSurveyed18_19 = results.getString("Total_Houses_Surveyed_2018_2019");
                String totalLgas19_20 = results.getString("Total_LGAs_2019_2020");
                String totalHousesSurveyed19_20 = results.getString("Total_Houses_Surveyed_2019_2020");

                // Add each value to the list
                lgaSurveysDetails.add(totalLgas18_19);
                lgaSurveysDetails.add(totalHousesSurveyed18_19);
                lgaSurveysDetails.add(totalLgas19_20);
                lgaSurveysDetails.add(totalHousesSurveyed19_20);
            }

            // Close the statement as it's no longer needed
            statement.close();
        } catch (SQLException e) {
            // Print any SQL errors
            System.err.println(e.getMessage());
        } finally {
            // Clean up by closing the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Handle any connection close errors
                System.err.println(e.getMessage());
            }
        }

        // Return the list of student details
        return lgaSurveysDetails;
    }
}