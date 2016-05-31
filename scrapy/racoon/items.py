# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class RacoonItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    # movie information:
    title = scrapy.Field()
    imdbid = scrapy.Field()
    imgURL = scrapy.Field()
    year = scrapy.Field()
    userRating = scrapy.Field()
    outline = scrapy.Field()
    director = scrapy.Field()
    cast = scrapy.Field()

    pass
