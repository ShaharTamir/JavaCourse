
import java.util.Random;

class Space
{
    private int[] _ufoPos;
    private int _size;

    Space(int n)
    {
        Random r = new Random();
        _ufoPos = new int[2];
        _ufoPos[0] = r.nextInt(n);
        _ufoPos[1] = r.nextInt(n);

        _size = n;
    }

    public int getSize()
    {
        return _size;
    }

    public int[] ask(int x, int y)
    {
        int[] res = new int[2];

        res[0] = (x == _ufoPos[0]) ? 0 : (x > _ufoPos[0] ? 1 : -1);
        res[1] = (y == _ufoPos[1]) ? 0 : (y > _ufoPos[1] ? 1 : -1);

        return res;
    }

    public String toString()
    {
        String str = new String();

        str += "Space size is: " + _size + "\n";
        str += "location is: (" + _ufoPos[0] + "," + _ufoPos[1] + ")";

        return str;
    }
}

public class UFO
{
    public static void main(String[] args)
    {
        Space test1 = new Space(4);
        int[] res;

        res = findUFO(test1);
        System.out.println("res is: (" + res[0] + "," + res[1] + ")");
        System.out.println(test1);

    }

    public static int[] findUFO(Space tmp)
    {
        return new int[2];
    }
}