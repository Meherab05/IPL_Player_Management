package com.example.huhuhahahihi;

public class Player implements java.io.Serializable {
   private String name,country,club,position,jerseyNumber;
   private double height;private int age;private long salary;
   public boolean isIntransferlist=false;
public Player(String name, String country,int age,double height, String club, String position,String jerseyNumber,   long salary
        ) {
    this.name = name;
    this.country = country;
    this.club = club;
    this.position = position;
    this.age = age;
    this.height = height;
    this.salary = salary;
    this.jerseyNumber = jerseyNumber;
}

public String getName() {
    return name;
}
public String getCountry() {
    return country;
}
public String getClub() {
    return club;
}
public String getPosition() {
    return position;
}
public int getAge() {
    return age;
}
public double getHeight() {
    return height;
}
public long getSalary() {
    return salary;
}
@Override
public String toString() {
    return name+","+country+","+age+","+height+","+club+","+position+","+jerseyNumber+","+salary;
}
public String toString2() {
    return "Name: " + name + "\nCountry: " + country + "\nAge: " + age + "\nHeight: " + height + "\nClub: "+club+"\nPosition: "+position+"\nJersey Number: "+jerseyNumber+"\nWeekly Salary: "+salary;
}
public void setClub(String club) {
    this.club = club;
}
public String toString3() {
    return "Name: " + name + " Country: " + country + " Age: " + age + " Height: " + height + " Club: "+club+" Position: "+position+" Jersey Number: "+jerseyNumber+" Weekly Salary: "+salary;
}

   
}
