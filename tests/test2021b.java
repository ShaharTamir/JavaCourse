class AA
{
    public boolean something(Object obj)
    {
        System.out.println("1");
        
        return super.equals(obj);
    }
}

class BB extends AA
{
    public boolean something(Object obj)
    {
        System.out.println("2");
        return super.something(obj);
    }

    public boolean something(AA obj)
    {
        System.out.println("3");
        return super.something(obj);
    }

    public boolean something(BB obj)
    {
        System.out.println("4");
        return super.something(obj);
    }
}

class CC extends AA
{
    public boolean something(BB obj)
    {
        System.out.println("5");
        return super.something(obj);
    }
}

class DD extends BB
{
    public boolean something(AA obj)
    {
        System.out.println("6");
        return super.something(obj);
    }

    public boolean something(BB obj)
    {
        System.out.println("7");
        return super.something(obj);
    }

    public boolean something(DD obj)
    {
        System.out.println("8");
        return super.something(obj);
    }
}

public class test2021b
{
    public static void main(String[] args)
    {
        AA a1 = new AA();
        BB b1 = new BB();
        CC c1 = new CC();
        DD d1 = new DD();

        Object a2 = new AA();
        AA b2 = new BB();
        AA c2 = new CC();
        BB d2 = new DD();

        //a2.somthing();
        //a1.something(d1);
        //b1.something(b2);
        //c2.something(d1);
        d2.something(c1);
        //d2.something(d1);
    }
}