var a = if (101 > 100) { 11 }
else { 111 } //11

var l1 = List(2, 4, 6, 8, 10, 12, 14)

for (i <- 6 to 0 by -2) {
  println(s"Reverse List: $i" + l1(i))
}
