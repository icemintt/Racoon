# Racoon
A movie collection web application used Spring MVC, MongoDB,Tomcat, Hibernate and AngularJS

Introduction
----
We want to build a movie collection web application that allows users to make their personal movie wishlists.
The system consists of four components:

1. Scrapy web crawler

   Our goal is to crawl 100,000 movies titles from IMDb in the past 30 years, from 1986 to 2016.

2. Movie Recommender

3. Restful API

4. Front-end implementation

Plan
----

We take _June, 2016_ as the __1st stage__ with the __primary__ goal of __prototyping__ our application following the development guild lines mentioned below. Here's the tentative timeline.

* __[2016/05/04 - 2016/05/30]__ Project Selection, Plan Discussion, Proposal Draft Writing, Resource Discovery
* __[2016/06/01 - 2016/06/04]__ Web Crawler With Scrapy
* __[2016/06/05 - 2016/06/11]__ Building Java Web Application Using Hibernate With Spring
* __[2016/06/12 - 2016/06/18]__ Implementation Restful APIs:
    * getTopMovies: get top N apps from MongoDB
    * getRecomMovies: get M recommended apps from MongoDB
    * getMovieByID: get a single app from MongoDB, searching by movie name
    * APIs for create, update, get and delete a movie
* __[2016/06/19 - 2016/06/25]__ Use Angular JS for the front end
* __[2016/06/26 - 2016/07/02]__ Document Writing, User Manual Writing and Video Presentation Making, Deploy on Heroku.


Owner
-----

@team: Racoon
