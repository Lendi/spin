COMMA = ','
SQ = "'"
for line in open("filename"):
	rollnumber , name , phonenum , bid = line.strip().split(COMMA)
	print "insert into table_name values(rollnumber = " + SQ + rollnumber + SQ + COMMA +"name = " + SQ + name +SQ + COMMA  + 
	"phonenum = "+ phonenum + COMMA +"bid = "+ bid +")"