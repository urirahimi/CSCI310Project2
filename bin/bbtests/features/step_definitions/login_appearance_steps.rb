require 'watir'
require 'colorize'

browser = Watir::Browser.new

Given ("I am looking at the login page") do
    browser.goto "http://localhost:8080/CSCI310Project2/index.html"
end

Then ("An input field for email is shown") do
    email_input_field = browser.text_field(:id, "login-modal-email-input")
    email_input_field.click unless email_input_field.nil?
end

Then ("An input field for password is shown") do
    password_input_field = browser.text_field(:id, "login-modal-password-input")
    password_input_field.click unless password_input_field.nil?
end

Then ("A button for logging in is shown") do
    submit_button = browser.button(:id, "login-modal-submission")
    submit_button.click unless submit_button.nil?
    browser.close
end

