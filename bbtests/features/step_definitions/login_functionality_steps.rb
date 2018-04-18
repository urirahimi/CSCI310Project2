require 'watir'
require 'colorize'

LOGIN_LINK = "http://localhost:8080/CSCI310/indexDeprecated.html"
LANDING_LINK = "http://localhost:8080/CSCI310/landing.jsp"
browser = Watir::Browser.new

Given ("I am on the login page") do
    browser.goto LOGIN_LINK
end

Then ("The input field for email should be required") do
    email_input_field = browser.text_field(:id, "login-modal-email-input")
    expect(email_input_field.required?).to eq(true)
end

Then ("The input field for password should be required") do
    password_input_field = browser.text_field(:id, "login-modal-password-input")
    expect(password_input_field.required?).to eq(true)
end

When ("I enter an empty email and submit") do
    email_input_field = browser.text_field(:id, "login-modal-email-input")
    email_input_field.set("")
    browser.button(:id, "login-modal-submission").click
end

Then ("I stay in the login page due to empty email input") do
    expect(browser.url).to eq(LOGIN_LINK)
end

When ("I enter an empty password and submit") do
    password_input_field = browser.text_field(:id, "login-modal-password-input")
    password_input_field.set("")
    browser.button(:id, "login-modal-submission").click
end

Then ("I stay in the login page due to empty password input") do
    expect(browser.url).to eq(LOGIN_LINK)
end

When ("I enter an email that isn't registered") do
    email_input_field = browser.text_field(:id, "login-modal-email-input")
    email_input_field.set("president.nikias@usc.edu")
    password_input_field = browser.text_field(:id, "login-modal-password-input")
    password_input_field.set("incorrect")
    browser.button(:id, "login-modal-submission").click
    sleep(1) # enough time to load the message
end

Then ("An error message about non-existent email should appear") do
    submission_err_msg = browser.p(:id, "login-error-message")
    expect(submission_err_msg.inner_html.include? "no user record").to eq(true)
end

When ("I enter a registered email and the incorrect password") do
    email_input_field = browser.text_field(:id, "login-modal-email-input")
    email_input_field.set("hintoso@usc.edu")
    password_input_field = browser.text_field(:id, "login-modal-password-input")
    password_input_field.set("csci310finalexam")
    browser.button(:id, "login-modal-submission").click
    sleep(1) # enough time to load the message
end

Then ("An error message about incorrect password should appear") do
    submission_err_msg = browser.p(:id, "login-error-message")
    expect(submission_err_msg.inner_html.include? "password is invalid").to eq(true)
end

When ("I enter a registered email and the correct password") do
    email_input_field = browser.text_field(:id, "login-modal-email-input")
    email_input_field.set("hintoso@usc.edu")
    password_input_field = browser.text_field(:id, "login-modal-password-input")
    password_input_field.set("csci310proj2")
    browser.button(:id, "login-modal-submission").click
    sleep(2) # enough time to get redirected
end

Then ("I get redirected to the main page") do
    expect(browser.url).to eq(LANDING_LINK)
    browser.close
end
