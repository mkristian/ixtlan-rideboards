# Methods added to this helper will be available to all templates in the application.
module Public::ListingsHelper

  def display(tag, field)
    if @listing.errors[field.to_sym].any?
      "<div class='fieldWithErrors'>#{tag}</div>".html_safe
    else
      tag
    end
  end

  def display_contact(tag, field)
    if @contact.errors[field.to_sym].any?
      "<div class='fieldWithErrors'>#{tag}</div>".html_safe
    else
      tag
    end
  end

end
