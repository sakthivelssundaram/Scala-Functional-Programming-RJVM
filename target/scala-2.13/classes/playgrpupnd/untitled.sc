

true | false
true | true
false | true
false | false

!(true & false)
!(true & true)
!(false & true)
!(false & false)

true ^ false
true ^ true
false ^ true
false ^ false


val m = collection.mutable.Set(1,2,3,4,5)
m.remove(2)
m.add(23)
m
m.to(Set)
m.to(collection.mutable.Set)


case class Point(x: Int, y: Int)
case class Point2(x: Int, y: Int)
val p = Point(123,123)
val p2 = Point2(123,123)


val Point(x,y) = p
x
y

val Point2(x2,y2) = p2
x
y

