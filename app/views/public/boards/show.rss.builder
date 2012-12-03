xml.instruct! :xml, :version => "1.0" 
xml.rss :version => "2.0" do
  xml.channel do
    xml.title @board.name.capitalize + " " + "Rideboards" + " - " + @board.venue.fullname
    xml.description "Ride Requests and Offers for" + " " + @board.name.capitalize + " - " + @board.venue.fullname
    # xml.link formatted_articles_url(:rss)

    offer_url = @path_prefix.sub(/\?.*/,'#offer')
    request_url = @path_prefix.sub(/\?.*/,'#request')
        
    @board.listings.each do |listing|
      prefix = listing.driver ? "offer" : "request"
      xml.item do
        xml.title prefix + ": " + listing.location + " - " + listing.ridedate.to_s
        xml.description prefix + " ride to " + listing.location + " on " + listing.ridedate.to_s + " from " + listing.name

        xml.link listing.driver? ? offer_url : request_url
        xml.guid listing.driver? ? offer_url : request_url
      end
    end
    #    xml.description article.content
    #    xml.pubDate article.created_at.to_s(:rfc822)
    #    xml.link formatted_article_url(article, :rss)
    #    xml.guid formatted_article_url(article, :rss)
    #  end
    #end
  end
end
