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

public class PageMission implements Handler {

        // URL of this page relative to http://localhost:7001/
        public static final String URL = "/mission.html";

        @Override
        public void handle(Context context) throws Exception {

                // Start the HTML document
                String html = "<html>";

                // Add the Head section
                // Add some Header information
                html = html + "<head>" +
                                "<title>Our Mission</title>";

                // Add some CSS (external file)
                html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

                html = html + "<style>";
                html = html + "body { font-family: 'Playfair Display', serif; margin: 0; padding: 0; }";
                // html = html + "header { display: flex; justify-content: space-between;
                // align-items: center; padding: 20px; background-color: #f5f5f5; }";
                // html = html + "header nav a { margin: 0 15px; text-decoration: none; color:
                // #333; }";
                html = html + ".banner { background-color: #ddd; padding: 60px; text-align: center; }";
                html = html + ".banner h2 { margin: 0; }";
                html = html + "h1, h2, h3, h4, h5, h6 {\n" + //
                                "            font-family: 'Playfair Display', serif;\n" + //
                                "        }";
                html += ".topnav { background-color: #6d9197; padding: 10px; text-align: center; }";
                html += ".topnav a { color: #f2f2f2; margin: 0 15px; text-decoration: none; }";
                html += ".section-title { text-align: center; margin: 40px 0; font-size: 1.5em; }";
                html = html + ".content { display: flex; justify-content: space-around; padding: 20px; }";
                html = html + ".content div { width: 45%; padding: 20px; border: 1px solid #ccc; border-radius: 8px; background-color: #f9f9f9; }";
                html = html + ".impact-analysis { text-align: center; padding: 20px; }";
                html = html + ".stats { display: flex; justify-content: space-around; padding: 20px; }";
                html = html + ".stats div { width: 30%; padding: 20px; border: 1px solid #ccc; border-radius: 8px; background-color: #f9f9f9; text-align: center; }";
                html = html + ".footer { text-align: center; padding: 20px; background-color: #f5f5f5; }";
                html = html + "</style>";
                html = html + "</head>";

                // Add the body
                html = html + "<body>";

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
                                            Our Mission
                                            </h1>
                                        </h1>
                                    </div>
                                """;
                // Add the banner section
                html = html + "<section class=\"banner\">";

                // Section about the team of students
                html = html + "<div style=' overflow: hidden;'>";
                html = html + "<center><h1> Our Team </h1></center>";
                html = html + "<p style='text-align: center;'>Meet the talented individual behind this website</p>";
                html = html + """
                                <!-- Centered student details -->
                                <div style='text-align: center; display: flex; flex-direction: column; align-items: center;'>
                                    <img src='Student.jpeg' alt='Premaansh' style='border-radius: 50%; height:200px; width:200px;'>
                                    """;

                ArrayList<String> studentDetails = getStudentDetails();

                html += "<h3>" + studentDetails.get(0) + "</h3>";
                html += "<h3>" + studentDetails.get(1) + "</h3>";
                html += "</div>";

                html = html + "</div>";
                html = html + "</section>";

                // Add the mission section
                html = html + "<section class=\"section-title\" style='padding-top: 45px; padding-bottom: 45px;'>";
                html = html + "<h2>Our Mission</h2>";
                html = html + """
                                <p style='text-align: justify; padding-left: 60px; padding-right: 60px; font-size:18px;'>

                                    Our mission is to empower residents, policymakers, and other stakeholders in New South Wales (NSW), Australia, with insightful, accessible information on domestic waste management and recycling practices. Through our web application, users can explore comprehensive, unbiased data on waste production and recycling efforts across various regions, providing a platform to understand and evaluate the environmental impact of domestic waste. By focusing on waste reduction and sustainable practices, our website fosters informed decision-making that supports ecological well-being, resource conservation, and climate resilience.
                                    </p>

                                    <p style='text-align: justify; padding-left: 60px; padding-right: 60px; font-size: 18px;'>
                                    This platform caters to a diverse audience with varying levels of knowledge and interest, offering both high-level summaries and detailed data analyses. Users can delve into specific waste and recycling statistics by region, compare trends over time, and assess the impact of local government initiatives on waste reduction. Through personalized personas and easy navigation, our website enhances user engagement, enabling a better understanding of how individual and community actions contribute to sustainable waste management in NSW.
                                    </p>""";
                html = html + "</section>";

                // Add the content section with website purpose and user engagement
                html = html + "<section class=\"content\">";
                html = html + "<div>";
                html = html + "<h3>Website Purpose</h3>";
                html = html + "<p>To create a platform that connects individuals with resources to address pressing social issues</p>";
                html = html + "<p><strong>Tags:</strong> Social Impact, Community Engagement</p>";
                html = html + "</div>";
                html = html + "<div>";
                html = html + "<h3>User Engagement</h3>";
                html = html + "<p>Empowering you with insights to make informed choices for a cleaner, sustainable future in NSW.</p>";
                html = html + "<p><strong>Tags:</strong> Empowerment, Inclusivity</p>";
                html = html + "</div>";
                html = html + "</section>";

                // Add the impact analysis section with statistics and graph placeholder
                html = html + "<section class=\"impact-analysis\" style='padding: 20px;'>";
                html = html + "<h2>Impact Analysis</h2>";
                html = html + "<div class=\"stats\">";
                html = html + "<div>";
                html = html + "<h3>Site Visitation</h3>";
                html = html + "<p>5,000 Daily Users</p>";
                html = html + "<p>+15%</p>";
                html = html + "</div>";
                html = html + "<div>";
                html = html + "<h3>Volunteers Engaged</h3>";
                html = html + "<p>500</p>";
                html = html + "<p>+30%</p>";
                html = html + "</div>";
                html = html + "<div>";
                html = html + "<h3>Campaigns Launched</h3>";
                html = html + "<p>10</p>";
                html = html + "<p>+50%</p>";
                html = html + "</div>";
                html = html + "</div>";
                html = html + "<div style=\"padding: 20px; text-align: center;\">";
                html = html + """
                                <div style=' background-color: #dddddd; padding-left: 50px; overflow: hidden;'>
                                <center><h1>User's Feedback</h1></center>
                                <div style='float: left; width: 50%; overflow: hidden;'>
                                    <center><img src='Persona1.png' style='border-radius: 50%; height:250px; width:250px;'></center>
                                </div>
                                <div style='width: 45%; margin-left: 50%;'>
                                """;

                String[][] getPersonaDetails = getPersonas();

                html = html + "<h3>" + getPersonaDetails[2][0] + "</h3>";
                html = html + "<p style='text-align: justify; font-size: 18px;'>" + getPersonaDetails[2][1]
                                + getPersonaDetails[2][2] + getPersonaDetails[2][3] + "</p>";

                html = html + """
                                </div>
                                <br>
                                <div style='float: left; width: 50%;'>
                                """;

                html = html + "<h3>" + getPersonaDetails[1][0] + "</h3>";
                html = html + "<p style='text-align: justify; font-size: 18px;'>" + getPersonaDetails[1][1]
                                + getPersonaDetails[1][2] + getPersonaDetails[1][3] + "</p>";
                html = html + "</div>";

                html = html + """
                                <div style='width: 50%; padding-bottom: 100px; overflow: hidden;'>
                                    <center><img src='Persona2.png' style='border-radius: 50%; height:250px; width:250px;'></center>
                                </div>
                                <br>
                                <div style='float: left; width: 30%; margin-left: 10%;'>
                                    <center><img src='Persona3.png' style='border-radius: 50%; height:250px; width:250px;'></center>
                                </div>
                                <div style='width: 45%; margin-left: 50%; '>
                                """;

                html = html + "<h3>" + getPersonaDetails[0][0] + "</h3>";
                html = html + "<p style='text-align: justify; font-size: 18px;'>" + getPersonaDetails[0][1]
                                + getPersonaDetails[0][2] + getPersonaDetails[0][3] + " " + "</p>" + "<br>";

                html = html + """
                                    </div>

                                </div>
                                        """;

                html = html + "</section>";

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

        public ArrayList<String> getStudentDetails() {
                // Initialize an ArrayList to store student details
                ArrayList<String> students = new ArrayList<>();

                // Declare the JDBC connection variable
                Connection connection = null;

                try {
                        // Establish a connection to the JDBC database
                        connection = DriverManager.getConnection(JDBCConnection.DATABASE);

                        // Create a new SQL statement and set a timeout
                        Statement statement = connection.createStatement();
                        statement.setQueryTimeout(30);

                        // Define the SQL query
                        String query = "SELECT name, StudentId FROM students";

                        // Execute the query and retrieve results
                        ResultSet results = statement.executeQuery(query);

                        // Iterate through the result set
                        while (results.next()) {
                                // Retrieve each column value
                                String studentId = results.getString("StudentId");
                                String name = results.getString("name");

                                // Add each value to the list
                                students.add(name);
                                students.add(studentId);
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
                return students;
        }

        public String[][] getPersonas() {

                // Define the number of rows and columns to extract from the database
                int rows = 3;
                int columns = 4;

                // Initialize a 2D array to store persona data
                String[][] personas = new String[rows][columns];

                // Declare the JDBC connection variable
                Connection connection = null;

                try {
                        // Establish a connection to the database
                        connection = DriverManager.getConnection(JDBCConnection.DATABASE);

                        // Create an SQL statement and set a timeout for the query
                        Statement statement = connection.createStatement();
                        statement.setQueryTimeout(30);

                        // Define the SQL query to retrieve all personas
                        String query = "SELECT * FROM personas";

                        // Execute the query and store the result set
                        ResultSet results = statement.executeQuery(query);

                        // Initialize an index to track the current row in the 2D array
                        int index = 0;

                        // Process the result set and store each row in the personas array
                        while (results.next() && index < rows) {
                                // Retrieve each column and assign it to the array
                                personas[index][0] = results.getString("name");
                                personas[index][1] = results.getString("attributes");
                                personas[index][2] = results.getString("Needs and Goals");
                                personas[index][3] = results.getString("Skills");

                                // Move to the next row in the array
                                index++;
                        }

                        // Close the statement after use
                        statement.close();
                } catch (SQLException e) {
                        // Print any SQL error messages
                        System.err.println(e.getMessage());
                } finally {
                        // Clean up by closing the connection
                        try {
                                if (connection != null) {
                                        connection.close();
                                }
                        } catch (SQLException e) {
                                // Handle any errors when closing the connection
                                System.err.println(e.getMessage());
                        }
                }

                // Return the 2D array containing persona data
                return personas;
        }

}