public class Bit
{
    int x;
    int y;
    char rep;
    boolean ex;
    Bit()
    {
        this.x = 0;
        this.y = 0;
        this.rep = 'O';
        this.ex = true;
    }
    Bit(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    public void move_to(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    public void create()
    {
        this.ex = true;
    }
    public void destroy()
    {
        this.ex = false;
    }
}