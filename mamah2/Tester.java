class A
{
    public double myFunc(double x, int y)
    {
        return 0.1d;
    }
    public int myFunc()
    {
        return 2;
    }
    public void myFunc(int x)
    {
        System.out.println(1);
    }
    public int myFunc(double x, char y)
    {
        return 3;
    }/*
    public double myFunc(double a, int b)
    {
        return 0.2d;
    }*/
}

class B extends A
{

}

class Mmh02 
{
    private int _num;
    private static int _count = 0;
    
    public Mmh02() 
    {
        _count++;
        _num = _count;
    }
    public void printNow() 
    {
        System.out.println(_num + "" + _count);
    }
}

/*  Question 7
class Person
{
    private String _name;
}
class Student extends Person
{
    private int[] _grades;
    private int _id;
    public static int _studentsNum;

    public Student (Student student)
    {
        super(student._name);
    }
}
*/

class AA
{
    private int _val=0;
    public AA(){
        _val=5;
    }
    public AA(int val){
        _val=val;
    }
    public int getVal(){
        return _val;
    }
    public void setVal(int val){
        _val=val;
    }
    public String toString(){
        return "val=" + _val ;
    }

}

class BB extends AA
{
    private String _st;
    public BB (){
        _st="bb";
    }
    public BB(String st, int val){
    super(val); 
        _st=st;
    }
    public String getSt(){
        return _st;
    }
    public boolean equals (Object ob) // 1 שיטה
    {
        System.out.print("method 1 ");
        if ((ob != null) && (ob instanceof BB))
        {
            if (_st.equals(((BB)ob)._st) && 
                (getVal() == ((BB)ob).getVal()))
                    return true;
        }
        return false;
    }
    public boolean equals (AA ob) // 2 שיטה
    {
        System.out.print("method 2 ");
        if ((ob != null) && (ob instanceof BB))
        {
            if (_st.equals(((BB)ob)._st) && 
                (getVal() == ((BB)ob).getVal()))
                    return true;
        }
        return false;
    }
    public boolean equals (BB ob) // 3 שיטה
    {
        System.out.print("method 3 ");
        if (ob != null) 
        {
            if (_st.equals(((BB)ob)._st) && 
                (getVal() == ((BB)ob).getVal()))
                    return true;
        }
        return false;
    }
}

class AAA
{
    AAA()
    {
        System.out.println("AAA");
    }

    AAA(int a)
    {
        System.out.println("AAA " + a);
    }
}

class BBB extends AAA
{
    BBB()
    {
        System.out.println("BBB");
    }
    BBB(int a)
    {
        super(a);
        System.out.println("BBB " + a);
    }
}

abstract class AAAA
{
    private int _x;

    AAAA()
    {
        _x = 5;
        System.out.println(_x);
    }
    public abstract boolean f(int x);
}

abstract class BBBB extends AAAA
{
    public abstract boolean g();
}

class CCCC extends BBBB
{
    public boolean f(int x)
    {
        return true;
    }

    public boolean g()
    {
        return false;
    }
}
   
public class Tester
{
    public static void main(String[] args)
    {
        /* Question 1
        A testOveride = new A();
        System.out.println(testOveride.myFunc(0.1d, 1));
        System.out.println(testOveride.myFunc());
        testOveride.myFunc(1);
        System.out.println(testOveride.myFunc(0.1d, 'c'));
        System.out.println(testOveride.myFunc(0.1d, 1));
        */
        /* Question 4
        Mmh02 a = new Mmh02();
        Mmh02 b = new Mmh02();
        Mmh02 c = new Mmh02();
        Mmh02 d = new Mmh02();
        Mmh02 e = new Mmh02();
        c.printNow();
        */
        /* Question 5
        A a1 = new B();
        B b1 = a1;
        */
        /* Questions 8 - 14 

        AA a1 = new AA();
        AA a2 = new BB();
        AA a3 = new AA();
        AA a4 = new BB();
        BB b1 = new BB();
        BB b2 = new BB();

        System.out.println(a3.equals(a1)); // object equals - false
        System.out.println(a4.equals(a2)); // BB equals - method 2 - true // after run - method 1
        System.out.println(a1.equals(a2)); // object equals - false
        System.out.println(a2.equals(b1)); // BB equals - method 3 - true // after run - method 1
        System.out.println(b1.equals(a1)); // BB equals - method 2 - false
        System.out.println(b2.equals(b1)); // BB equals - method 3 - true
        System.out.println(b1.equals(a4)); // BB equals - method 2 - true
        */
        /*
        BBB b = new BBB();
        BBB b2 = new BBB(6);
        */
        CCCC c = new CCCC();
    }
}