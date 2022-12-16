PROJECT TITLE: Traveling Salesperson Problem
PURPOSE OF PROJECT: Implement two greedy heuristics in the attempt to find
an optimal solution to the Traveling Salesperson Problem
DATE: 12/5/22
AUTHOR: Caden Christesen and Anirudh Narumanchi
ALGORITHM DESCRIPTIONS: 
    insertNearest()
    O(N^2)
    to do this, we once again make a node to traverse the the list before checking whether or not there is a point. If there isnt we simply
    add the given point. we then take the taversing node and set it equal to the first node in the list. We then move on to the list iteration
    where we set the distance variable to the distance between two points the method is dealing with in its current state (the current and 
    the possible next point), before checking whether or not the distance is greater or less than the current minimum distance. Should it be 
    be less than the minimum distance, that distance becomes the new minimum. the temporary node called at the begining is then called to save 
    the current node with the shortest distance. whether or not the if statement is executed doesnt affect the program from moving to the next
    node, as only the shortest node is saved and therefore if it isnt the shortest there is no point in saving it. once the iteration is 
    finished, the traversal node (node) is set to the node after the shortest distance node (temp). That temp node is then set as a new 
    ListNode from the private class, and then the node after that is set to the traversal node (node).
    
    insertSmallest()

    first step here is to make sure we have at least 3 points added, and wherever there isnt one we add a new one.We then take a node and look
    at the distance between it and the point (One), the next node and the point (Two), and finally the current and next nodes (Three). We then
    Start our iterations and use the tempMin variable to look at the difference between this point and a new before using comparing temopMin 
    to the minimum distance (either the current minimum or -1 in the first iteration). we then check another 3 distances: node to the head of
    the list (One) , the node to the point (Two), and the poiunt to the head of the list(Three). We use the same three variables not because i
    was lazy and didnt want to write 3 new variables at the time, but because we dont want to waste space. we make the same calculation as
    earlier, reusing tempMin and minDist but this time if the new value of temp min is lower we'll set the node to the tail, while if it isnt, 
    we move to the next node and check if that is the best point (aka if it has the shortest distance).
    
    Edit: thanks to advice from other students, i have changed the process so that the One, Two, and Three variables arent stored and instead 
          make the calculation based soley on the values of the variables without assigning them. This saves storage so woo-hoo
    
