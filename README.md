<!-- README.md -->

<h1 align="center"><font size="7" color="#2E86C1">Building Management Application</font></h1>
<p align="center"><font size="4">A Spring Boot Web Application Combining RESTful API and MVC for Java Backend Development</font></p>

<br/>

<hr/>

<h2><font color="#117864">Overview</font></h2>
<p>
  This is my first personal project as I learn and grow as a Java backend developer, focusing on hands-on experience with Spring Boot and backend technologies.  The application facilitates the management of buildings, customers, and transactions for a property management company. It provides distinct functionalities for different user roles including managers, staff, and general users.
</p>

<hr/>

<h2><font color="#117864">Technologies & Tools</font></h2>
<ul>
  <li><strong>Backend Framework:</strong> Spring Boot 3.x</li>
  <li><strong>Server:</strong> Apache Tomcat 10.x, Jakarta Servlet API 6.x</li>
  <li><strong>Packaging:</strong> Deployed as a <code>.war</code> file</li>
  <li><strong>Frontend:</strong> JSP for views, AJAX calls via jQuery</li>
  <li><strong>Database:</strong> MySQL</li>
  <li><strong>Security:</strong> Spring Security integrated with JWT; tokens are stored in HTTP-only cookies with automatic expiry and refresh mechanisms</li>
  <li><strong>Email Service:</strong> Configured with <code>spring.mail.host=smtp.gmail.com</code> to send OTP codes for password recovery</li>
  <li><strong>OTP Storage:</strong> Redis (with OTP auto-deletion after 5 minutes)</li>
  <li><strong>File Storage:</strong> Cloudinary (for storing building image files)</li>
  <li><strong>Design Patterns:</strong> Utilizes the Builder pattern for object construction</li>
</ul>

<hr/>

<h2><font color="#117864">Project Architecture & Design</font></h2>
<p>
  The application follows a layered architecture, which is often referred to as a three-layer model. Although I have organized the code into <em>Controller</em>, <em>Service</em>, and <em>Repository</em> layers, this design effectively encapsulates the presentation, business, and persistence logic.
</p>
<ul>
  <li>
    <strong>Controller Layer:</strong> 
    <br/>Handles incoming HTTP requests using <code>@RestController</code> for RESTful endpoints, and manages view rendering through ModelAndView with JSP files.
  </li>
  <li>
    <strong>Service Layer:</strong> 
    <br/>Contains business logic and orchestrates communication between controllers and repositories.
  </li>
  <li>
    <strong>Repository Layer:</strong> 
    <br/>Manages data persistence and interactions with the MySQL database.
  </li>
</ul>
<p>
  In addition, the Builder design pattern is applied to facilitate the construction of complex objects within the application.
</p>

<hr/>

<h2><font color="#117864">Application Features</font></h2>
<ul>
  <li>
    <strong>Building Listings & Management:</strong>
    <br/>The homepage displays a list of buildings complete with sale and purchase information. Managers can perform full CRUD operations (create, read, update, delete) on building data, including text details and image files.
  </li>
  <li>
    <strong>User Authentication & Profile Management:</strong>
    <br/>Includes user registration, login, and password recovery via OTP sent to email. Each user’s profile supports complete CRUD operations.
  </li>
  <li>
    <strong>Role-Based Access Control:</strong>
    <br/>Implements three primary user roles:
    <ul>
      <li>
        <em>Regular User:</em> Can view building listings and submit contact information.
      </li>
      <li>
        <em>Staff:</em> Access to a dedicated admin interface with limited permissions, primarily to view building details and manage assigned buildings and customers.
      </li>
      <li>
        <em>Manager:</em> Full administrative control including managing buildings, assigning staff, and overseeing customer data and transactions.
      </li>
    </ul>
  </li>
  <li>
    <strong>Customer & Transaction Management:</strong>
    <br/>Managers can assign customers to staff members. Each customer’s details include a transaction history where staff can log various customer care activities, ensuring smooth and efficient management of the business process.
  </li>
</ul>

<hr/>

<h2><font color="#117864">Security Implementation</font></h2>
<p>
  Security is a critical aspect of this application and is addressed through multiple layers:
</p>
<ul>
  <li>
    <strong>JWT & Spring Security:</strong>
    <br/>Authentication is managed using JWT integrated with Spring Security. Tokens are stored securely in HTTP-only cookies and are automatically removed upon expiration.
  </li>
  <li>
    <strong>Token Refresh & Revocation:</strong>
    <br/>A refresh token mechanism is in place to generate new access tokens when needed, and tokens are revoked immediately upon user logout.
  </li>
  <li>
    <strong>OTP-Based Password Recovery:</strong>
    <br/>When a user forgets their password, an OTP is sent via email (using SMTP configuration for Gmail). The OTP is temporarily stored in Redis and is auto-deleted after 5 minutes to enhance security.
  </li>
</ul>

<hr/>

<h2><font color="#117864">Frontend & API Integration</font></h2>
<ul>
  <li>
    <strong>View Layer:</strong>
    <br/>The application uses JSP files to render views. Although the frontend design is kept simple, it effectively demonstrates the backend functionalities.
  </li>
  <li>
    <strong>AJAX Communication:</strong>
    <br/>RESTful endpoints are consumed using jQuery AJAX calls, enabling seamless interaction between the client-side and server-side components.
  </li>
</ul>

<hr/>

<h2><font color="#117864">System Diagram</font></h2>
<p>
  The diagram below illustrates the overall architecture of the application:
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/1f9b41c9-1b86-4312-835f-8f76434cc610" alt="Application System Architecture" style="max-width:100%; height:auto;">


</p>

<hr/>

<h2><font color="#117864">Getting Started</font></h2>
<p>
  To set up and run this project locally, follow these steps:
</p>
<ol>
  <li>Clone the repository to your local machine.</li>
  <li>Ensure that JDK, Maven, and MySQL are installed and properly configured.</li>
  <li>Configure your SMTP settings for email services (using <code>smtp.gmail.com</code>).</li>
  <li>Set up Redis for OTP storage with a 5-minute auto-expiry configuration.</li>
  <li>Build the project to generate the <code>.war</code> file.</li>
  <li>Deploy the <code>.war</code> file to Apache Tomcat 10.x.</li>
  <li>Access the application via your designated domain.</li>
</ol>

<hr/>

<h2><font color="#117864">Conclusion</font></h2>
<p>
  This project is a comprehensive demonstration of Java backend development using Spring Boot. By integrating a variety of modern technologies, it delivers a secure and efficient system for managing building-related data. and practical knowledge of web technologies essential for a Java Backend Developer role.
</p>

<hr/>


