require 'watir'
require 'colorize'

MAIN_LINK = "http://localhost:8080/CSCI310Project2/landing.jsp"
COLLAGE_LINK = "http://localhost:8080/CSCI310Project2/search"
browser = Watir::Browser.new

Given("I am looking at the collage page") do
  browser.goto MAIN_LINK
  topic_input_box = browser.text_field(:id, "topic-input")
  topic_input_box.set("dogs") # valid input with sufficient images
  browser.button(:id, "topicSubmit").click
  expect(browser.url).to eq(COLLAGE_LINK)
end

Then("An export collage button is shown") do
  export_collage_button = browser.button(:id, "export-button")
  expect(export_collage_button.exists?).to eq(true)
end

Then("An input box for re-entering a topic is shown") do
  input_box = browser.text_field(:id, "input")
  expect(input_box.exists?).to eq(true)
end

Then("The input box for topic has placeholder text") do
  input_box = browser.text_field(:id, "input")
  expect(input_box.placeholder.empty?).to eq(false)
end

Then("A rectangle that displays miniaturized images of previous collages generated.") do
  prev_collages_div = browser.div(:id, "previous-collages")
  expect(prev_collages_div.exists?).to eq(true)
end

Then("A collage is shown if there are sufficient images.") do
  collage_image = browser.img(:id, "collage-image")
  expect(collage_image.exists?).to eq(true)
end

Then("An error message is shown if there are insufficient images.") do
  browser.close
end
