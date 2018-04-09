require 'watir'
require 'colorize'

MAIN_LINK = "http://localhost:8080/CSCI310Project2/landing.jsp"
browser = Watir::Browser.new

Given ("I am looking at the main page") do
  browser.goto MAIN_LINK
end

Then ("An input box for topic is shown") do
  topic_input_box = browser.text_field(:id, "topic-input")
  expect(topic_input_box.nil?).to eq(false)
end

Then ("The input box for topic has placeholder text") do
  topic_input_box = browser.text_field(:id, "topic-input")
  expect(topic_input_box.placeholder.empty?).to eq(false)
end

Then ("A button for building collages is shown") do
  build_collage_button = browser.button(:id, "topicSubmit")
  expect(build_collage_button.nil?).to eq(false)
end

Then ("A button for saving collages is shown") do
  save_collage_button = browser.button(:id, "saveButton")
  expect(save_collage_button.nil?).to eq(false)
end

Then ("A dropdown menu for collage shape is shown collapsed") do
  collage_shape_dropdown_menu = browser.button(:id, "collage-shape-dropdown-menu")
  expect(collage_shape_dropdown_menu.nil?).to eq(false)
end

Then ("A dropdown menu for collage options is shown collapsed") do
  collage_options_dropdown_menu = browser.button(:id, "collage-options-dropdown-menu")
  expect(collage_options_dropdown_menu.nil?).to eq(false)
end

Then ("The dropdown menu contains several options for collage generation") do
  collage_options_dropdown_options = browser.div(:id, "collage-options-dropdown-options")
  expect(collage_options_dropdown_options.exists?).to eq(true)
  expect(collage_options_dropdown_options.as.size).to eq(5)
end

Then ("The dropdown menu contains an option for photo rotation") do
  collage_options_dropdown_options = browser.div(:id, "collage-options-dropdown-options")
  option_tag = "Photo Rotation"
  option_found = false
  for option in collage_options_dropdown_options.as
    if option.inner_html.include? option_tag
      option_found = true
      break
    end
  end
  expect(option_found).to eq(true)
end

Then ("The dropdown menu contains an option for photo borders") do
  collage_options_dropdown_options = browser.div(:id, "collage-options-dropdown-options")
  option_tag = "Photo Borders"
  option_found = false
  for option in collage_options_dropdown_options.as
    if option.inner_html.include? option_tag
      option_found = true
      break
    end
  end
  expect(option_found).to eq(true)
end

Then ("The dropdown menu contains an option for sepia") do
  collage_options_dropdown_options = browser.div(:id, "collage-options-dropdown-options")
  option_tag = "Sepia"
  option_found = false
  for option in collage_options_dropdown_options.as
    if option.inner_html.include? option_tag
      option_found = true
      break
    end
  end
  expect(option_found).to eq(true)
end

Then ("The dropdown menu contains an option for grayscale") do
  collage_options_dropdown_options = browser.div(:id, "collage-options-dropdown-options")
  option_tag = "Grayscale"
  option_found = false
  for option in collage_options_dropdown_options.as
    if option.inner_html.include? option_tag
      option_found = true
      break
    end
  end
  expect(option_found).to eq(true)
end
