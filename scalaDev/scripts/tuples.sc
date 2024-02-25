val  (a,b,c,d) = (1,2,3,4)


a
b

//pack
val test = (1,2,4,5)

test._1
test._2

def sumAndDifference(x:Int, y:Int):(Int,Int) = {

  (x+y, x-y)
}


val (sum,diff) = sumAndDifference(5,10)

sum
diff

//unpack
val (p,q,r,s) = test

p

//diffrent type
val test1 = ("sk",2,5.6, 0XF999, 's')

test1._1
test1._2
test1._3
test1._4
test1._5
