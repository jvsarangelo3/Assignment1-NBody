# Assignment1-NBody

The Lists.java file contains the interface that consists of the ArrayList and LinkedList classes
the class creates it's own type "T" that can be of any type (int, double, string, etc.// it's
just a general type). The class itself creates the lists, returns the size of the list,
gets the value at the given index, and adds values to the list.

The NBody.java file contains the main as well as the methods to create the animation. It extends
the JPanel and implements the ActionListener to create the animation and drawings. It calculates 
the force, velocity, etc. and updates the positions and shapes of the n-bodies. In the main,
it reads the file and uses the Lists interface to store and organizes the values from the file,
then proceeds to create the animation based on the information.

To run the program, use the command line and type "java NBody nbody_input.txt" or whatever the 
file name is.
