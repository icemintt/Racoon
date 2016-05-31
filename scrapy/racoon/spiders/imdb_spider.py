import scrapy
import re
from scrapy.selector import Selector
from racoon.items import RacoonItem
from logging import getLogger
log = getLogger(__name__)

class RacoonSpider(scrapy.Spider):
    name = 'Racoon'
    allowed_domains = ["imdb.com"]
    start_urls = []

    def __init__(self):
        for year in range(1990,1991):
            self.start_urls.append('http://www.imdb.com/search/title?year='+str(year)+','+str(year)+'&title_type=feature&sort=moviemeter,asc')

    # MARK: crawl and extract movie data and handling pagination
    def parse(self, response):
        #extract movie data:
        for movie in response.xpath("//table[@class='results']/tr"):
            item = RacoonItem()
            item['imgURL'] = movie.xpath("td[@class='image']/a/@href").extract()
            item['title'] = movie.xpath("td[@class='title']/a/text()").extract()
            item['year'] = movie.xpath("td[@class='title']/span[@class='year_type']/text()").extract()
            item['userRating'] = movie.xpath("td[@class='title']/div[@class='user_rating']/div/span[@class='rating-rating']/span[@class='value']/text()").extract()
            item['outline'] = movie.xpath("td[@class='title']/span[@class='outline']/text()").extract()

            '''
            #extract director and cast information
            cList = movie.xpath("td[@class='title']/span[@class='credit']/a/text()").extract()
            cText = movie.xpath("td[@class='title']/span[@class='credit']/text()").extract()
            print('XXXXXXXXX')
            print(cText)
            numDir = len(cText.split('With: ')[0].split('u')) - 1
            numWith = len(cText.split('With: ')[1].split('u')) - 1

            for i in range(0,numDir):
                item['director'].append(cList[i])
            for j in range(numDir,numWith):
                item['cast'].append(cList[j])
            '''
            yield item

        #follwing the link to the next page until it doesn't find one
        next_page = response.xpath("//span[@class='pagination']/a[contains(text(),'Next')]/@href")
        # use urljoin method to yield new requests to be sent later
        if next_page:
            url = response.urljoin(next_page[0].extract())
            #register as callback method parse_dir_contents()
            yield scrapy.Request(url, callback=self.parse)
