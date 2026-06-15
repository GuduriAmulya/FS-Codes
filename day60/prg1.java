/*
A cybersecurity team is analyzing a large block of communication logs to extract
valid email addresses. The logs are provided as a single line string (user input) 
and may contain:
    Zero, one, or many email addresses
    Valid and invalid email formats
    Emails separated by spaces, commas, or punctuation
    Duplicate email addresses
    Emails with mixed case (uppercase + lowercase)

You must:
    Extract only valid email addresses
    Ignore all invalid email formats
    Consider only emails belonging to allowed domains
    Treat emails as case-insensitive (convert to lowercase)
    Remove duplicate emails
    Return the count of distinct valid emails

Allowed Domains (ONLY THESE)
    gmail.com  
    yahoo.com  
    outlook.com  
    hotmail.com  
    protonmail.com  
    icloud.com  
    zoho.com  
    rediffmail.com  
    yandex.com  
    mail.com  

Email Validation Rules (STRICT): A valid email must follow:
    1. Structure:
    username@domain

    2. Username Rules:
    Allowed characters:
    lowercase letters (a–z)
    digits (0–9)
    dot (.)
    underscore (_)
    Must start and end with letter or digit
    Dot (.) and underscore (_) can appear only between characters
    No consecutive dots → a..b ❌
    No consecutive underscores → a__b ❌
    Hyphen (-) is NOT allowed ❌

    3. Domain Rules:
    Must exactly match one of the allowed domains

    4. Invalid Cases (must be ignored):
    Multiple @ → abc@@gmail.com ❌
    Missing username → @gmail.com ❌
    Invalid domain → user@fake.com ❌
    Partial matches → g-h@gmail.com should NOT count as h@gmail.com ❌
    Uppercase emails should be treated as valid after converting to lowercase

Input Format
------------
A single line string (user input)

Output Format
-------------
Print a single integer → number of distinct valid email IDs

Sample Input 1
--------------
Contact us at JOHN.DOE@gmail.com, support@yahoo.com, invalid@@yahoo.com, a..b@outlook.com, helpdesk@zoho.com

Sample Output 1
---------------
3


Sample Input 2
--------------
Emails: test@mail.com, Test@mail.com, user@fake.com, ok@icloud.com

Sample Output 2
---------------
2

*/

