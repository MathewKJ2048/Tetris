public class Block
{
    int x = 1;
    int y = 1;
    char rep;
    char v;
    char[][] block = new char[3][3];
    boolean ex = true;
    //
    //
    Block()
    {
    }
    Block(char rep,char v)
    {
        this.rep = rep;
        this.v = v;
    }
    //
    //
    public void move_to(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    //
    public void destroy()
    {
        this.ex = false;
    }
    //
    private void clear()
    {
        for(int i = 0 ; i <= 2 ; i++)
        {
            for(int j = 0 ; j <= 2 ; j++)
            {
                block[i][j] = this.v;
            }
        }
    } 
    public void create(char s)
    {
        clear();
        if(s == 'i')
        {
            for(int i = 0 ; i < 3 ; i++)
            {
                block[i][1] = this.rep;
            }          
        }
        else if(s == 'o')
        {
            block[0][0] = this.rep;
            block[0][1] = this.rep;
            block[1][0] = this.rep;
            block[1][1] = this.rep;
        }
        else if(s == 'l')
        {
            for(int i = 0 ; i < 3 ; i++)
            {
                block[i][1] = this.rep;
            }
            block[2][2] = this.rep;
        }
        else if(s == 't')
        {
            for(int i = 0 ; i < 3 ; i++)
            {
                block[i][1] = this.rep;
            }
            block[1][2] = this.rep;
        }
        else if(s == 'n')
        {
            block[2][2] = this.rep;
            block[0][1] = this.rep;
            block[1][2] = this.rep;
            block[1][1] = this.rep;
        }
        else
        {
            block[1][1] = this.rep;
        }
    }
    //
    public void turn_right()
    {
        char[][] arr = new char[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                arr[i][j] = this.block[2-j][i];
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.block[i][j] = arr[i][j];
            }
        }
    }
    public void turn_left()
    {
        char[][] arr = new char[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                arr[i][j] = this.block[j][2-i];
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.block[i][j] = arr[i][j];
            }
        }
    }
    //
    public void mirror()
    {
        char[][] arr = new char[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                arr[i][2-j] = this.block[i][j];
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.block[i][j] = arr[i][j];
            }
        }
    }
}