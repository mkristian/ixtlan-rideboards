Rideboard::Application.routes.draw do
  resource :configuration

  resources :errors

  resources :audits

  resource :session do
    member do
      post :reset_password
    end
  end

  #
  # public students' UI from here on
  #
  scope ":lang" do
    scope ":venue" do
      scope ":board" do
        resources :listings, :module => "public" do
          collection do
            get :wanted
            get :offer
          end
          member do
            post :reminder
            get :new_contact
            post :contact
          end
        end
      end
    end
  end

  # named venue and named board with language choice
  match ":lang/:venue/:board" => "public/boards#show"
  match ":lang/:venue/:board/index" => "public/boards#show"

  # named venue with language choice
  match ":lang/:venue" => "public/boards#lang_venue"
  match ":lang/:venue/index" => "public/boards#lang_venue"

  # named venue with language negotiation
  match ":venue" => "public/boards#venue"
  match ":venue/index" => "public/boards#venue"

end
