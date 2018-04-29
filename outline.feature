Feature: outline

Scenario Outline: login functionality for social networking site
     Given user navigates to QQ
     When I enter Username as "<username>" and Password as "<password>"
     Then login should be unsuccessful
     
     Examples:
     |username|password|
     |username1|password1|
     |username2|password2|
     