# Methods added to this helper will be available to all templates in the application.
module Public::ListingsHelper

  def display(tag, field)
    if !@listing.errors.on(field.to_sym).nil?
      "<div class='fieldWithErrors'>#{tag}</div>".html_safe
    else
      tag
    end
  end

end
