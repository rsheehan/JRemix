# Remix
![landscape1.png](images/landscapeImage.png)
## This is the current Remix - implemented in Java
Remix is a flexible programming language based around the idea of mix-fix (as opposed to pre-fix or post-fix) 
function names. With as many space-separated words and parameters as you want in a function name you can make these 
very readable. There is a very straightforward path from designing a program in natural language pseudocode and 
transitioning it to running Remix code.

Remix also allows spaces in variable names by wrapping variable names in single 'quotes'.

When you add these together you can name a function by its pseudocode making minor changes to use as the real executable function name and function call.
e.g. the pseudocode:

    make a list of people from a file of names

can be converted to the function:

    make a list of people from 'a file of names'

Here 'a file of names' is a single variable representing a file of names.

To keep function calls as readable as possible, there can be many names for the same function. Multiple names are defined with a simple syntax. e.g.

    pause turtles/turtle for 'time' seconds/second:
is the function signature for a function which can be called with any of the following statements:

    pause turtles for 5 seconds
    pause turtles for 1 second
    pause turtle for 6 seconds
    pause turtle for 1 second

Objects are created using the following syntax, and can have automatically generated getter and setter methods for fields (which are otherwise private). Methods which include a "ME" or "MY" parameter, indicating the receiver, are public. Methods without a "ME" or "MY" parameter can only be called from methods in the same object, hence they are private.
You can access object fields which have getters or setters using the possessive 
apostrophe e.g. 'object'’s 'fieldname'. (This looks better in the Remix 
editor which distinguishes between single quotes and the apostrophe.) Fields 
without getters or setters are private to the object.

    create
        'field1' : value
        'field2' : value
        'private field' : value

        getter
            'field1'

        setter
            'field2'

        MY method1 :
            body of method

        another method on ME with a 'parameter' :
            body of method
            
        a private method with a 'parameter' but no object parameter :
            body of method

More information about Remix can be found in this [presentation](https://github.com/rsheehan/JRemix/blob/main/RemixIntroduction.pdf).
The first half shows how Remix can be used to develop a program from pseudocode, the second half briefly describes the language.

Here is an animated random landscape program in Remix. N.B. This is how it appears in the Remix editor. In particular note the underlining of variable names.

![landscape1.png](images/landscape1.png)
![landscape2.png](images/landscape2.png)
![landscape3.png](images/landscape3.png)
![landscape4.png](images/landscape4.png)

Remix has a simple library system which provides a measure of 
encapsulation. Libraries can contain both Java and Remix functions as in the 
graphics library used in the landscape example.