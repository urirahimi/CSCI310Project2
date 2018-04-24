require 'watir'
require 'colorize'

MAIN_LINK = "http://localhost:8080/CSCI310/landing.jsp"
COLLAGE_LINK = "http://localhost:8080/CSCI310/search"
browser = Watir::Browser.new

Given("I submitted a valid topic on the main page") do
  browser.goto MAIN_LINK
  topic_input_box = browser.text_field(:id, "topic-input")
  topic_input_box.set("dogs") # valid input with sufficient images
  browser.button(:id, "topicSubmit").click
end

Then("A loading symbol should appear until I see the collage page") do
  loading_symbol = browser.div(:id, "loading")
  expect(loading_symbol.style("display")).to eq("none")
  expect(browser.url).to eq(COLLAGE_LINK)
end

Then("An export collage button is shown") do
  export_collage_button = browser.button(:id, "export-button")
  expect(export_collage_button.exists?).to eq(true)
end

Then("A save collage button is shown") do
  save_collage_button = browser.button(:id, "save-button")
  expect(save_collage_button.exists?).to eq(true)
end

Then("An input box for re-entering a topic is shown") do
  input_box = browser.text_field(:id, "input")
  expect(input_box.exists?).to eq(true)
end

Then("The input box for topic has placeholder text") do
  input_box = browser.text_field(:id, "input")
  expect(input_box.placeholder.empty?).to eq(false)
end

Then("A rectangle that displays miniaturized images of previous collages generated is shown") do
  prev_collages_div = browser.div(:id, "previous-collages")
  expect(prev_collages_div.exists?).to eq(true)
end

Then("A collage is shown") do
  collage_image = browser.img(:id, "collage-image")
  expect(collage_image.exists?).to eq(true)
end

Given("I submitted an invalid topic on the main page") do
  browser.goto MAIN_LINK
  topic_input_box = browser.text_field(:id, "topic-input")
  topic_input_box.set("daflk;sfdsfjrianvknelirjflq3n") # invalid input with insufficient images
  browser.button(:id, "topicSubmit").click
end

Then("A loading symbol should still appear until I see the collage page") do
  loading_symbol = browser.div(:id, "loading")
  expect(loading_symbol.style("display")).to eq("none")
  expect(browser.url).to eq(COLLAGE_LINK)
end

Then("An error message is shown") do
  error_message = browser.img(:id, "collage-page")
  expect(error_message.alt).to eq("Insufficient images found.")
end

Then("All UI elements besides the collage is the same") do
  # export button
  export_collage_button = browser.button(:id, "export-button")
  expect(export_collage_button.exists?).to eq(true)
  # save button
  save_collage_button = browser.button(:id, "save-button")
  expect(save_collage_button.exists?).to eq(true)
  # input box
  input_box = browser.text_field(:id, "input")
  expect(input_box.exists?).to eq(true)
  expect(input_box.placeholder.empty?).to eq(false)
  # previous collages
  prev_collages_div = browser.div(:id, "previous-collages")
  expect(prev_collages_div.exists?).to eq(true)
end
