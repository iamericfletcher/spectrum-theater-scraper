# Spectrum Theater Scraper

## Background

Our local movie theater was recently bought by Landmark Cinema. I am not a fan of their website or app, so I wanted to create an application that would send me all the neccesary information about new movies via email. The goal is to use their site only for ticket purchasing.

## General Information

A web scraping application built with `Java` that sends me an email with all the films that are now playing at my local movie theater. The film title, brief synopsis, extended synopsis, trailer, and link to buy tickets are presented in an `HTML` table within the email body. 

Target website: https://www.landmarktheatres.com/albany-ny/spectrum-8-theatres

## TODO
- Create an EC2 AWS instance to run the application twice per week (Thursday and Friday). 
- Add the data to a database. 
- Imporve the style of the `HTML` table. 
- Allow for other people to sign up for the weekly email notification.
