*****************************THIS README FILE CREATED BY Nuni98***********************************
||===========================================================================Monom-Class=======================================================================||
------------------------Monom(String s):------------------------------
1) coefficient part:	
if the coefficient is negative we skip the ' - ' sign and start collect coefficient digits into t`.
once we finished collect coefficient digits we convert it to double with ParseDouble and save the value in coef variable.

2) x part:
this part check if our monom contains an x` if so x' flag equals true.

3) Power part:
if the monom contains ' ^ ' i.e that a number should be follow and start to collect power digits.
else i.e power should be 1 or 0 depend whether an x` exist or not.
at the end we put coef and pow values in the attributes variabls.

---------------------boolean equals(Monom m):--------------------------
compare this.toString with m.toString with String class, equals function
 

-----------------------void add(Monom m)------------------------
if m.power does not equal to this.power we cannot do the add function therefore throw an exception
add m`s coefficient with our Monom coefficient
if this.coefficient is 0 then the power should be 0 also.

-------------------------Multiply(Monom m)--------------------------
multiply both coefficients and add the powers by powers rules

-------------------------String toString()-----------------------------
if power equals to 0 return only the coefficient
if power equal to 1 and coefficient equal to 1 return x with coefficient sign ( - / + )
if power equal to 1 and coefficient not equal to 1 return coefficient*x
if power is greater than 1 and coefficient equal to 1 or -1  return ( - / + )coefficient*x^power
if power is greater than 1 and coefficient not equal to 1 or -1 return coefficient*x^power

--------------------------isPositive(String s)------------------
Auxiliary function that checks whether the coefficient is Positive or negative 
return true if positive

||======================================================================Polynom-Class=============================================================================||

--------------------------------------Polynom(String s)--------------------------------
The main working method is to split s String into Monoms and build them one after one into the ArrayList that uses us as the data structure that contains the Polynom.

-----------------------------------Polynom_able copy()-----------------------------
1- define an itertor 
2- build an empty Polynom
3- build new Monom  : a copy of iterator.next()
4- add the new Monom to the existing Polynom with add(Monom m) function

----------------------------------------f(double x)-------------------------------------
1- define an itertor 
2- add to sum the function value of f(x) of every Monom in the Polynom
3- return sum

----------------------------------add(Polynom_able p1)--------------------------------
1- define an itertor 
2- split p1 Polynom into Monoms with iterator help, and then send every Monom to add(Monom m) function

-----------------------------------add(Monom m)------------------------------------
1- flag=false (description about flag below)
2- define an itertor 
3- search in our Polynom a Monom that his power equals to m`s power
4- if we found a monom such as 3 describes, add this monom with m monom with Monom class function add(Monom m)   
5- turn flag to true i.e we found a Monom that his power equals to m`s power.
6- if flag equals false i.e we didn`t found a Monom that his power equals to m`s power, and then we add m Monom to our ArrayList with ArrayList add operation.

------------------------------subtract(Polynom_able p1)------------------------------
1- create new Monom 'm' equals to "-1"
2- multiply p1 Polynom with m Monom
3- add p1 to our Polynom with add(Polynom_able p1) function

--------------------------------------Multiply(Polynom_able p1)---------------------------
1- define an itertor on p1 Polynom
2- create new Polynom "temp" equals to our Polynom
3- create a final Polynom "original" that keeps our original Polynom with no change
4- reboot our Polynom
5- with iterator help we take every Monom from p1 and Multiply with temp Polynom
6- we add the result to our new rebooted Polynom
7- before every multiplication we make sure that "temp" polynom is equal to "original" polynom, becuase we want to be sure that we multiply every Monom with our original polynom.

-----------------------------------Multiply(Monom m)----------------------------------
1- define an itertor 
2- with iterator help we multiply every monom in our Polynom with m monom, we use Monom class function Multiply(Monom m)

--------------------------------------equals(Polynom_able p1)---------------------------
compare this.toString with p1.toString with String class, equals function

-----------------------------------boolean-isZero()
1- define an itertor 
2- with iterator help check whether every Monom in our polynom is zero, with Monom class function isZero(Monom m)
3- return flase if one Monom returns flase

-------------------------------------double root(x0 ,x1 ,eps)---------------------------
1- throw runtime exception if epsilon not positive or f(x0) and f(x1) are both positive or both negative
2- if f(x0) smaller than epsilon return x0, (same check for f(x1)).
3- define a middle point m. 
4- if f(middle) smaller than epsilon return middle.
5- if f(middle) *f(x0) is negative number call root(x0 ,middle ,epsilon), (same check for f(middle) *f(x1) )
6- if we`ve got till here i.e  f(middle) *f(x0) not negative and f(middle) *f(x1) also not negative, and for sure we know that one of f(x1) or f(x0) is negative, we can be sure that f(middle) equals to 0.
therfore return m.

----------------------------------area(x0 , x1, eps)------------------------------------------
1-throw runtime exception if x0 greater than x1 or eps smaller or equal to 0.
2-we increment x0 value by epsilon value every step, until x0 greater than x1, and add the rectangle area (height=f(x0), width= epsilon) to sum variable.in case f(x0) negative we ignore rectangle area.
3- return sum.

--------------------------------iterator<Monom> iterator()--------------------------------
we create an ArrayList iterator<Monom> and return it.

---------------------------------String toString()-----------------------------------------
1- we use ArrayList sort according to the order induced by Comperator<Monom>(the greater power first)
2- create empty string "ans"
3- with iterator help we add to "ans" every monom.toString() ignoring zeros and adding - /+ according to coefficient sign
4-return ans

---------------------------------------void reboot()---------------------------------------- 
Auxiliary function that replaces our Polynom with a new empty Polynom
