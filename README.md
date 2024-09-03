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

More information about Remix can be found in this presentation, RemixIntroduction.pdf.
The first half shows how Remix can be used to develop a program from pseudocode, the second half describes the language. (That link is for an earlier version of Remix. A revised document will eventually be provided.)

Here is an animated random landscape program in Remix.

	using graphics lib
	
		the-Window : the graphics panel
		the-Window's Background-Colour : SKY-BLUE
	
		draw the landscape in the-Window
		add Clouds to the-Window
	
		animate the-Window 1000 times at 30 ticks per second
			for each Cloud in Clouds
				move Cloud
	
	-============================================================================-
	
	draw the landscape in the-Window :
		the-Back-Layer  : the-Window base layer
		THE-CLOUD-LAYER : the-Window next layer
		- a constant can be accessed anywhere
		the-Front-Layer : the-Window next layer
		add the back mountains on the-Back-Layer
		add the sea on the-Back-Layer
		add the front islands on the-Front-Layer
		update the-Window
	
	add the back mountains on the-Back-Layer :
		"back mountains" ↲
		repeat 20 times
			Mountain : a DARK-GREY mountain with BLACK outline of 300
			… based at 600
			place Mountain in the-Back-Layer
	
	add the sea on the-Back-Layer :
		"sea" ↲
		Box : a DARK-BLUE box of STD-WIDTH x 200 at {
			STD-WIDTH ÷ 2
			STD-HEIGHT - 100
		}
		place Box in the-Back-Layer
	
	add the front islands on the-Front-Layer :
		"front islands" ↲
		for each Location from 650 to 680 in steps of 3 do
			Island : a GREY mountain with WHITE outline of 250
			… based at Location
			place Island in the-Front-Layer
	
	add #Clouds to the-Window :
		#Clouds : apply
			Cloud : a cloud
			place (Cloud parts) in THE-CLOUD-LAYER
			Cloud
		… 10 times
	
	=
	A mountain is an isosceles triangle.
	=
	a Colour mountain with outline-Colour outline of max-Height based at Level :
		Centre : random STD-WIDTH
		Height : random max-Height + 20
		Width : random 150 + 300
		Mountain : a Colour shape from {
			{ 0, -Height }
			{ Width ÷ 2, 0 }
			{ -Width ÷ 2, 0 }
		} scaled by 1 at { Centre, Level }
		Mountain's outline-Colour : outline-Colour
		return Mountain
	
	=
	A cloud is a composite object comprising of two circles, a rectangle and a line.
	
	Each part is added separately to the same window layer using the list returned from "MY parts".
	
	When animated the "move ME" method changes the positions of all the parts.
	The "move ME" also wraps the cloud around when it passes off the window.
	=
	a cloud :
		Base : random (STD-HEIGHT - 200)
		Across : random STD-WIDTH
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
			radius-A : R1
			radius-B : R2
			circle-A : null
			circle-B : null
			Box : null
			Line : null
	
			- these are methods
	
			MY parts :
				{ circle-A, circle-B, Box, Line }
	
			move ME :
				increment X1
				increment X2
				if ((X1 - radius-A) > STD-WIDTH)
					Back : 0 - radius-B
					X1 : Back - (X2 - X1)
					X2 : Back
				circle-A's Position : { X1, Y1 }
				circle-B's Position : { X2, Y2 }
				Box's Position : {
					(X1 + X2) ÷ 2
					(Y1 + radius-A + Y2 + 1) ÷ 2
				}
				Line's Start : { X1, Y1 + radius-A }
				Line's Finish : { X2, Y1 + radius-A }
	
			setup MY parts :
				circle-A : a WHITE circle of radius radius-A at { X1, Y1 }
				circle-A's outline-Colour : BLACK
				circle-B : a WHITE circle of radius radius-B at { X2, Y2 }
				circle-B's outline-Colour : BLACK
				Position : { (X1 + X2) ÷ 2, (Y1 + radius-A + Y2 + 1) ÷ 2 }
				Width : X2 - X1
				Height : Y1 - Y2 + radius-A
				Box : a WHITE box of Width x Height at Position
				Line : a BLACK line from {
					X1, Y1 + radius-A
				} to {
					X2, Y1 + radius-A
				}
				Line's Width : 2
	
		setup Cloud parts
		return Cloud

Remix has a simple library and include file system. Libraries can contain both Java and Remix functions as in the graphics library used in the landscape example.