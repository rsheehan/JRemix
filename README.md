# Remix
<img width="410" alt="Landscape" src="https://user-images.githubusercontent.com/3459269/118350983-80372780-b5ad-11eb-80ba-7072962a29ef.png">

Remix is a flexible programming language based around the idea of mix-fix (as opposed to pre-fix or post-fix) function names, with as many space-separated words and parameters as you want. This means that there is a very straightforward path from designing a program in natural language pseudocode and transitioning it to running Remix code.

To keep function calls as readable as possible, there can be many names for the same function. Multiple names are defined with a simple syntax. e.g.

    pause turtles/turtle for Time secs/sec:
is the function signature for a function which can be called with any of the following statements:

    pause turtles for 5 secs
    pause turtles for 1 sec
    pause turtle for 6 secs
    pause turtle for 1 sec

Objects are created using the following syntax, and can have optional automatically generated getter and setter methods for fields (which are otherwise private). Methods which include a "ME" or "MY" parameter, indicating the receiver, are public. Methods without a "ME" or "MY" parameter can only be called from methods in the same object, hence they are private.
You can access object fields using the possessive apostrophe.

    create
        Field1 : value
        Field2 : value

        getter
            Field1

        setter
            Field2

        MY method1 :
            body of method

        another method on ME with a Parameter :
            body of method
            
        a private method with a Parameter but no object parameter :
            body of method

More information about Remix can be found in this presentation, [IntroToRemix.pdf](https://github.com/rsheehan/Remix/files/7551327/IntroToRemix.pdf)
, the first half shows how Remix can be used to develop a program from pseudocode, the second half describes the language. (That link is for an earlier version of Remix. A revised document will eventually be provided.)

Here is an animated random landscape program in Remix.

    using graphics lib

        the-Window : make a (sky blue) window
        draw the landscape in the-Window
        add Clouds to the-Window

        Cloud-Movement : every 1 tick do
            for each Cloud in Clouds
                move Cloud

        on the-Window
            animate Cloud-Movement
        … 1000 times at 30 ticks per second
    
    ----------------------------------------
    
    draw the landscape in the-Window :
        add the back mountains on (the-Window next layer)
        add the sea on (the-Window next layer)
        add the front mountains on (the-Window next layer)

    add the back mountains on the-Back-Layer :
        repeat 20 times
            Mountain : a (dark grey) mountain with (black) outline of 200 based at 600
            add Mountain to the-Back-Layer

    add the sea on the-Middle-Layer :
        Box : a (dark blue) box of (the std width) x 200
        …     at { the std width ÷ 2, the std height - 100 }
        add Box to the-Middle-Layer
    
    add the front mountains on the-Front-Layer :
        for each (Location) from 650 to 680 in steps of 3 do
            Mountain : a (grey) mountain with (white) outline of 150 based at Location
            add Mountain to the-Front-Layer
    
    add #Clouds to the-Window :
        #Clouds : apply
            Cloud : a cloud
            add (Cloud parts) to (the-Window base layer)
            Cloud
        … 10 times
    
    =
    A mountain is a polygon shape object of 3 points.
    =
    
    a Colour mountain with outline-Colour outline of max-Height based at Level :
        Centre : random (the std width)
        Height : random max-Height + 20
        Width : random 100 + 250
        Mountain : a Colour shape from {
            { 0, -Height }
            { Width ÷ 2, 0 }
            { -Width ÷ 2, 0 }
        } scaled by 1 at { Centre, Level }
        Mountain's outline-Colour : outline-Colour
        return Mountain
    
    =
    A cloud is a composite object comprising of two circles, a rectangle and a line.
    
    Each part is added separately to the same window layer using the list returned from
    "MY parts".
    
    When animated the "move ME" method changes the positions of all the parts.
    The "move ME" also wraps the cloud around when it passes off the window.
    =
    
    a cloud :
        Base : random (the std height - 200)
        Across : random (the std width)
        R1 : random 10 + 30
        R2 : random 10 + 20
        Distance : 10
        if (R1 > R2)
            R1 swap R2
    
        Cloud : create
    
            - these are fields
    
            X1 : (Across - R1) + Distance
            X2 : (Across + R2) - Distance
            Y1 : Base - R1
            Y2 : Base - R2
            A-radius : R1
            B-radius : R2
            A-circle : null
            B-circle : null
            Box : null
            Line : null
    
            - these are methods
    
            MY parts :
                { A-circle, B-circle, Box, Line }
    
            move ME :
                inc X1
                inc X2
                if ((X1 - A-radius) > the std width)
                    Back : 0 - B-radius
                    X1 : Back - (X2 - X1)
                    X2 : Back
                A-circle's Position : { X1, Y1 }
                B-circle's Position : { X2, Y2 }
                Box's Position : { (X1 + X2) ÷ 2, (Y1 + A-radius + Y2 + 1) ÷ 2 }
                Line's Start : { X1, Y1 + A-radius }
                Line's Finish : { X2, Y1 + A-radius }
    
            setup MY parts :
                A-circle : a (white) circle of radius A-radius at { X1, Y1 }
                A-circle's outline-Colour : black
                B-circle : a (white) circle of radius B-radius at { X2, Y2 }
                B-circle's outline-Colour : black
                Position : { (X1 + X2) ÷ 2, (Y1 + A-radius + Y2 + 1) ÷ 2 }
                Width : X2 - X1
                Height : Y1 - Y2 + A-radius
                Box : a (white) box of Width x Height at Position
                Line : a (black) line from {
                    X1, Y1 + A-radius
                } to {
                    X2, Y1 + A-radius
                }
                Line's Width : 2
    
        setup Cloud parts
        return Cloud

Remix has a simple library and include file system. Libraries can contain both Java and Remix functions as in the graphics library used in the landscape example.