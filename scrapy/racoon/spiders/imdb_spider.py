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

            #extract director and cast info
            info = movie.xpath("td[@class='title']/span[@class='credit']/a")
            tmp = movie.xpath("td[@class='title']/span[@class='credit']/text()").extract()

            dir_num = 0
            for c in tmp:
                if c == "\n    With: ":
                    break
                else:
                    dir_num += 1

            item['director'] = []
            item['cast'] = []
            count = 0

            for c in info:
                if count < dir_num:
                    item['director'].append(c.xpath("text()").extract());
                else:
                    item['cast'].append(c.xpath("text()").extract());
                count += 1
            yield item

        #follwing the link to the next page until it doesn't find one
        next_page = response.xpath("//span[@class='pagination']/a[contains(text(),'Next')]/@href")
        # use urljoin method to yield new requests to be sent later
        if next_page:
            url = response.urljoin(next_page[0].extract())
            #register as callback method parse_dir_contents()
            yield scrapy.Request(url, callback=self.parse)
