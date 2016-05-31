import scrapy
import re
from scrapy.selector import Selector
from racoon.items import RacoonItem

class RacoonSpider(scrapy.Spider):
    name = 'Racoon'
    allowed_domains = ["imdb.com"]
    start_urls = []

    def __init__(self):
        for year in range(1990,2017):
            self.start_urls.append('http://www.imdb.com/search/title?year='+str(year)+','+str(year)+'&title_type=feature&sort=moviemeter,asc')

    # MARK: crawl and extract movie data and handling pagination
    def parse_movie(self, response):
        #extract movie data:
        for movie in response.xpath("//table[@class='results']/tr"):
            item = RacoonItem()
            item['imgURL'] = movie.xpath("td[@class='image']/a@href").extract()
            item['title'] = movie.xpath("td[@class='title']/a/text()").extract()
            item['year'] = movie.xpath("td[@class='title']/span[@class='year_type']/text()").extract()
            item['userRating'] = movie.xpath("td[@class='title']/div[@class='usr_rating']/span[@class='value'/text()").extract()
            item['outline'] = movie.xpath("td[@class='title']/span[@class='outline']/text()").extract()
            credit = movie.xpath("td[@class='title']/span[@class='credit']/text()").extract()
            creditList = credit.split("Dir: ")
            dirList = creditList[1].split(',')
            castList = creditList[2].split('With: ')[1].split(',')

            for dirA in dirList:
                if(char)





        #follwing the link to the next page until it doesn't find one
        next_page = response.css(".pagination > a[text~="Next"]::attr('href')" ):
        # use urljoin method to yield new requests to be sent later
        if next_page:
            url = response.urljoin(href.extract())
            #register as callback method parse_dir_contents()
            yield scrapy.Request(url, callback=self.parse_movie)
