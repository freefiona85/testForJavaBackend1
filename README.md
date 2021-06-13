in this project using Spring boot with 
 - handler 404 or 500 
 - Spring boot 2.5.0
 - swagger-ui
 - FlickrAPI using ScribeJava core, API, ScribeJava 8.3.1

For Another API Exists : 
 - Search Photo by photo_id and page
 - search List Comments
 - Testing Login Authentication OAuth with ScribeJava to Flickr Web

for example try this link https://invspringboot.herokuapp.com/swagger-ui


# Simple Coding Test for AIA SG Java Backend - 1

### What will you do, in general?

1. Create a simple Java Spring app with Maven that can provide RESTFUL or XMLRPC API Endpoint
2. Create unit testing for your app so it will survive changes.
3. Deploy your application into a simple JAR or WAR
   An optional is, deploy the JAR/WAR to public repository like Heroku, or set it on your own server.

### What we are expecting?

1. Good code understanding and expertise on using efficient method to solve problems.
2. Good source control usage (using Git!)
3. Practical use of tools available to you.

### What stack should you use?

1. We expect you to use Java, Spring and Maven at its fullest to develop the test application.
   Use whatever method and framework you are comfortable with, as long as it's using Spring and Maven. It's all depend on your preferences!
2. Use any type of unit testing you are comfortable with.
3. As an optional one, you can create a simple app that consumes the API. You can use anything for this regard. Java, PHP, JS, Dotnet, whatever. Just to show that your Endpoint is consumable and usable. 

### Details, details.

1. Grab the everchanging public image list from [Flickr public feed](https://api.flickr.com/services/feeds/photos_public.gne). 
   You can check the documentation from this [API Doc](http://www.flickr.com/services/feeds/).
2. Use a public github repository to put your code. If you don't have one, just make one! Also, make use of good source versioning. 
   Commit often, and commit meaningfully.
3. Create the endpoint that will use the data you get from that Flickr public feed API. Like, searching, or paging. Make sure the API Endpoint is not directly using the Flickr      one(use your own API), and make sure it can be consumed from different URL (CORS-safe).
4. Bonus point if you can save the pulled data from Flickr periodically to your database(be it mysql, noSQL or any other storage) by an API Calling. So, there's one API to save    the data from Flickr, and another API to pull the data, with different parameters.  
5. Optional one (only after doing the bonus point) : Create API Endpoint that can pull the specific data (like, specific tags, date, etc), and other that can save the specific      data from Flickr to your database only. Also, it's good if you are able to create one more endpoint to clear all the data pulled.
6. Expected result is JAR or WAR that can be deployed on Tomcat/JBOSS. 
   Deployment is optional. If you can deploy to public/private distribution, the better.
   If you can't deploy your app, it's okay! Just make sure that you can demo it to us when we do the interview.

### So, what should you do?

## Use your creativity, and be unique. Do what you can do on this test! Don't worry if it's not perfect, we learn along the way!

-Fiorina Skyla Liberta-
