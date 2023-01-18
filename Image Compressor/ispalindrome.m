function palindrome = ispalindrome(phrase)
%string is inputed, logcial true or false is outputted, true if palendrome,
%false if not a palendrome.


palindrome = false;

phrase2 = erase(phrase,' ');
phrase2 = erase(phrase2,'!');
phrase2 = erase(phrase2,'.');
phrase2 = erase(phrase2,'?');
phrase2 = lower(phrase2);
phrase2 = char(phrase2);

%converts string into lower case character vector without spaces or
%punctuation

newPhrase = phrase2;

for i=1:length(phrase2)
    newPhrase((length(phrase2)+1-i)) = phrase2(i);
end

%creates a new char vector that is the reverse of the old char vector

if(newPhrase == phrase2)
palindrome = true;
end

%tests if the backwards new char vector is the same as the old char vector 


end