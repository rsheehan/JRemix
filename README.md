# Remix
<img width="410" alt="Landscape" src="https://user-images.githubusercontent.com/3459269/118350983-80372780-b5ad-11eb-80ba-7072962a29ef.png">

## This is the current Remix - implemented in Java
Remix is a flexible programming language based around the idea of mix-fix (as opposed to pre-fix or post-fix) function names, with as many space-separated words and parameters as you want. This means that there is a very straightforward path from designing a program in natural language pseudocode and transitioning it to running Remix code.

To keep function calls as readable as possible, there can be many names for the same function. Multiple names are defined with a simple syntax. e.g.

    pause turtles/turtle for 'time' seconds/second:
is the function signature for a function which can be called with any of the following statements:

    pause turtles for 5 seconds
    pause turtles for 1 second
    pause turtle for 6 seconds
    pause turtle for 1 second

Objects are created using the following syntax, and can have optional automatically generated getter and setter methods for fields (which are otherwise private). Methods which include a "ME" or "MY" parameter, indicating the receiver, are public. Methods without a "ME" or "MY" parameter can only be called from methods in the same object, hence they are private.
You can access object fields using the possessive apostrophe.

    create
        'field1' : value
        'field2' : value

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

Here is an animated random landscape program in Remix.

	using graphics lib
	
		'the window' : the graphics panel
		'the window's 'colour' : SKY-BLUE
		
		'the back layer'  : 'the window's 'base-layer'
		'the cloud layer' : 'the window' next layer
		'the front layer' : 'the window' next layer
		
		add the mountains on 'the back layer'
		add the sea on 'the back layer'
		add the islands on 'the front layer'
		update 'the window'	
	
		'the clouds' : 10 clouds in 'the cloud layer'
	
		animate 'the window' 1000 times at 30 ticks per second
			for each 'cloud' in 'the clouds'
				move 'cloud'
	
	-============================================================================-
	
	add the mountains on 'the back layer' :
		repeat 20 times
			'mountain' : a DARK-GREY mountain with BLACK edges 
			… of height 300 based at 600
			place 'mountain' in 'the back layer'
	
	add the sea on 'the back layer' :
		'sea' : a DARK-BLUE box of STD-WIDTH x 200 at {
			STD-WIDTH ÷ 2
			STD-HEIGHT - 100
		}
		place 'sea' in 'the back layer'
	
	add the islands on 'the front layer' :
		for each 'location' from 650 to 680 in steps of 3 do
			'island' : a GREY mountain with WHITE edges
			… of height 250 based at 'location'
			place 'island' in 'the front layer'
	
	'n' clouds in 'the cloud layer' :
		apply
			'cloud' : a cloud
			place ('cloud' parts) in 'the cloud layer'
			'cloud'
		… 'n' times
	
	=
	A mountain is an isosceles triangle.
	=
	a 'colour' mountain with 'outline colour' edges
	… of height 'max height' based at 'level' :
		'centre' : random STD-WIDTH
		'height' : random 'max height' + 20
		'width'  : random 150 + 300
		'mountain' : a 'colour' shape from {
			{ 0, -'height' }
			{ 'width' ÷ 2, 0 }
			{ -'width' ÷ 2, 0 }
		} scaled by 1 at { 'centre', 'level' }
		'mountain's 'outline colour' : 'outline colour'
		'mountain'
	
	=
	A cloud is a composite object comprising of two circles, a rectangle
	and a line.
	
	Each part is added separately to the same window layer using the list
	returned from "MY parts".
	
	When animated the "move ME" method changes the positions of all the parts.
	The "move ME" also wraps the cloud around when it passes off the window.
	=
	a cloud :
		
		- setting up the object's field values
	
		'base'   : random (STD-HEIGHT - 200)
		'across' : random STD-WIDTH
		'r1' : random 10 + 30
		'r2' : random 10 + 20
		'distance' : 10
		if ('r1' > 'r2') ; bigger circle to the right
			'r1' swap 'r2'
	
		'x1' : ('across' - 'r1') + 'distance'
		'x2' : ('across' + 'r2') - 'distance'
		'y1' : 'base' - 'r1'
		'y2' : 'base' - 'r2'
	
		'circle A' : a WHITE circle of radius 'r1' at { 'x1', 'y1' }
		'circle A's 'outline colour' : BLACK
		'circle B' : a WHITE circle of radius 'r2' at { 'x2', 'y2' }
		'circle B's 'outline colour' : BLACK
	
		'width' : 'x2' - 'x1'
		'height' : 'y1' - 'y2' + 'r1'
		'position' : {
			('x1' + 'x2') ÷ 2
			('y1' + 'r1' + 'y2' + 1) ÷ 2
		}
	
		'box' : a WHITE box of 'width' x 'height' at 'position'
		'line' : a BLACK line from {'x1', 'y1' + 'r1'} to {'x2', 'y1' + 'r1'}
		'line's 'width' : 2
	
	
		- creating the object
		
		create
	
			- these are fields
	
			'circle A' : 'circle A'
			'circle B' : 'circle B'
			'box'  : 'box'
			'line' : 'line'
			'size' : 'circle A's 'radius' + 'circle B's 'radius' + 
			… ('circle B's 'x-pos' - 'circle A's 'x-pos')
				
			- these are methods
	
			MY parts :
				{ 'circle A', 'circle B', 'box', 'line' }
	
			move ME :
				'left edge' : 'circle A's 'x-pos' - 'circle A's 'radius'
				if ('left edge' < STD-WIDTH) ; ordinary move right
					for each 'part' in (MY parts)
						change 'part' x-pos by 1
				… otherwise ; wrap the cloud around the window
					for each 'part' in (MY parts)
						change 'part' x-pos by -(STD-WIDTH + 'size')

Remix has a simple library and include file system. Libraries can contain both Java and Remix functions as in the graphics library used in the landscape example.