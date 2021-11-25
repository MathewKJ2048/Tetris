import java.util.Scanner;
import java.util.Calendar;
public class Tetris
{
    char mal;
    char mov;
    char[] rig;
    //
    char v;
    //
    Block bk;
    Bit[] bts;
    private void create_bits()
    {
        for(int i = 0 ; i < 4 ; i++)
        {
            this.bts[i] = new Bit();
        }
    }
    //
    int h;
    int b;
    char[][] screen;
    //
    private int score;
    public int get_score()
    {
        return this.score;
    }
    //
    Tetris()
    {    
        this.mal = 'o';
        this.mov = 'O';
        this.rig = new char[]{'X','M','N','U','C','V','H','Q'};
        this.v = '.';
        //
        this.bk = new Block(this.mal,this.v);
        this.bts = new Bit[4];
        //
        this.h = 27;
        this.b = 10;
        this.screen = new char[h][b];
        //
        this.score = 0;
        create_bits();
        set_screen();
        set_block();
        update_block();
    }
    //
    public void set_screen()
    {
        for(int i = 0 ; i < this.h ; i++)
        {
            for(int j = 0 ; j < this.b ; j++)
            {
                this.screen[i][j] = this.v;
            }
        }
    }
    private char random_shape()
    { 
        char[] shapes = new char[]{'o','l','n','i','t'};
        return shapes[((int)(Math.random()*1000))%5];
    }
    public void set_block()
    {
        this.bk.ex = true;
        this.bk.create(random_shape());
        this.bk.move_to((int)(this.b/2 - 1),1);
    }
    //
    private boolean check_existance_x(int x)
    {
        boolean r = false;
        if((x < this.b)&&(x >= 0))
        {
            r = true;
         }
        return r;
    }
    private boolean check_existance_y(int y)
    {
        boolean r = false;
        if((y < this.h)&&(y >= 0))
        {
            r = true;
         }
        return r;
    }
    //
    private void clear_block()
    {
        for(int i = 0 ; i < this.h ; i++)
        {
            for(int j = 0 ; j < this.b ; j++)
            {
                if(this.screen[i][j] == this.bk.rep)
                {
                    this.screen[i][j] = this.v;
                }
            }
        }
    }
    public void update_block()
    {       
        clear_block();
        //
        boolean up,down,left,right;
        up = check_existance_y(this.bk.y-1);
        down = check_existance_y(this.bk.y+1);
        left = check_existance_x(this.bk.x-1);
        right = check_existance_x(this.bk.x+1);
        //
         if(this.bk.ex)
        {
            this.screen[bk.y][bk.x] = this.bk.block[1][1];
            if(up)
            {
                this.screen[bk.y-1][bk.x] = this.bk.block[0][1];
            }
            if(down)
            {
                this.screen[bk.y+1][bk.x] = this.bk.block[2][1];
            }
            if(left)
            {
                this.screen[bk.y][bk.x-1] = this.bk.block[1][0];
            }
            if(right)
            {
                this.screen[bk.y][bk.x+1] = this.bk.block[1][2];
            }
            if((up)&&(left))
            {
                this.screen[bk.y-1][bk.x-1] = this.bk.block[0][0];
            }
            if((up)&&(right))
            {
                this.screen[bk.y-1][bk.x+1] = this.bk.block[0][2];
            }
            if((down)&&(left))
            {
                this.screen[bk.y+1][bk.x-1] = this.bk.block[2][0];
            }
            if((down)&&(right))
            {
                this.screen[bk.y+1][bk.x+1] = this.bk.block[2][2];
            }  
        }
    }
    //
    public char input()
    {
        String s = (new Scanner(System.in)).next();
        if(!s.equals(""))
        {
            return s.charAt(0);
        }
        else
        {
             return ' ';
        }     
    }
    public void process_input(char in)
    {
        if(this.bk.ex)
        {
            if(in == '6')
            {
                this.bk.turn_right();
            }
            if(in == '4')
            {
                 this.bk.turn_left();
            }
            if(in == '5')
            {
                 this.bk.mirror();
            }
            update_block();
            if(in == '+')
            {
                convert_to_bits();
            }
        }       
        else if(!this.bk.ex)
        {
            if(in == '5')
            {
                move_all(2);
            }
            if(in == '4')
            {
                move_all(3);
            }
            if(in == '6')
            {
                move_all(1);
            }
            update_bits();
            if(in == '+')
            {
                if(is_next())
                {
                    convert_to_block();
                }
            }
        }
    }
    public boolean is_next()
    {
        boolean clear = true;
        for(int i = 0 ; i < 4 ; i++)
        {
            for(int j = 0 ; j < this.b ; j++)
            {
                if(this.screen[i][j] != this.v)
                {
                    clear = false;
                }
            }
        }
        clear = clear || (!is_l_clear());
        return clear;
    }
    public boolean is_l_clear()
    {
        boolean clear = true;
        for(int j = 0 ; j < this.b ; j++)
        {
            if(this.screen[4][j] != this.v)
            {
                    clear = false;
            }
        }
        return clear;
    }
    //
    private void remove_block()
    {
        this.bk.ex = false;
    }
    private void to_bits()
    {
        for(int i = 0 ; i < this.h ; i++)
        {
            for(int j = 0 ; j < this.b ; j++)
            {
                if(this.screen[i][j] == this.bk.rep)
                {
                    this.screen[i][j] = this.bts[0].rep;
                }
            }
        }
    }
    private void set_bits()
    {
        int c = 0;
        for(int i = 0 ; i < this.h ; i++)
        {
            for(int j = 0 ; j < this.b ; j++)
            {
                if(this.screen[i][j] == this.bts[0].rep)
                {
                    this.bts[c].create();
                    this.bts[c].move_to(j,i);
                    c++;
                }
            }
        }
    }
    private void convert_to_bits()
    {
        remove_block();
        to_bits();
        set_bits();
        update_bits();
    }
    //
    private int[] generate_position(int d,Bit b)
    {
        int[] r = new int[2];
        r[0] = b.y;
        r[1] = b.x;
        if(d == 0)
        {
            r[0]--;
        }
        else if(d == 1)
        {
            r[1]++;
        }
        else if(d == 2)
        {
            r[0]++;
        }
        else if(d == 3)
        {
            r[1]--;
        }
        return r;
    }
    private boolean d_check(int d,Bit b)
    {
        boolean r = false;
        int[] npos = generate_position(d,b);
        if((check_existance_y(npos[0]))&&(check_existance_x(npos[1])))
        {
            if((this.screen[npos[0]][npos[1]] == b.rep)||(this.screen[npos[0]][npos[1]] == this.v))
            {
                r = true;
            }
        }
        return r;
    }
    //
    private void move_bit(int d,Bit b)
    {
        int[] npos = generate_position(d,b); 
        b.x = npos[1];
        b.y = npos[0];
    }
    private void move_all(int d)
    {
        clear_bits();
        boolean ch = true;
        for(int i = 0 ; i < 4 ; i++)
        {
            if(this.bts[i].ex)
            {
                if(!d_check(d,this.bts[i]))
                {
                    ch = false;
                }
            }
        }
        if(ch)
        {
            for(int i = 0 ; i < 4 ; i++)
            {
                if(this.bts[i].ex)
                {
                    move_bit(d,this.bts[i]);
                }
            }
        }   
    }
    //
    private void clear_bits()
    {
        for(int i = 0 ; i < this.h ; i++)
        {
            for(int j = 0 ; j < this.b ; j++)
            {
                if(this.screen[i][j] == this.bts[0].rep)
                {
                    this.screen[i][j] = this.v;
                }
            }
        }
    }
    private void update_bits()
    {
        for(int i = 0 ; i < 4 ; i++)
        {
            if(this.bts[i].ex)
            {
                this.screen[this.bts[i].y][this.bts[i].x] = this.bts[i].rep;
            }        
        }
    }
    //
    private void destroy_bits()
    {
        for(int i = 0 ; i < 4 ; i++)
        {
            this.bts[i].destroy();
        }
    }
    private char random_char()
    {
        return this.rig[(int)((Math.random()*10)%8)];
    }
    private void set_rig()
    {
        char rep = random_char();
        for(int i = 0 ; i < this.h ; i++)
        {
            for(int j = 0 ; j < this.b ; j++)
            {
                if(this.screen[i][j] == this.bts[0].rep)
                {
                    this.screen[i][j] = rep;
                }
            }
        }
    }
    private void clear()
    {
        for(int i = 0 ; i < 4 ; i++)
        {
            for(int j = 0 ; j < this.b ; j++)
            {
                this.screen[i][j] = this.v;
            }
        }
    }
    private void convert_to_block()
    {
        set_rig();
        destroy_bits();
        clear();
        set_block();
        update_block();
    }
    //
    public boolean check_end()
    {
        boolean r = false;
        for(int j = 0 ; j < this.b ; j++)
        {
            if((this.screen[4][j] != this.bts[0].rep)&&(this.screen[4][j] != this.v))
            {
                r = true;
            }
        }
        return r;
    }
    //
    public int[] completed()
    {
        int[] comp = new int[3];
        boolean is_clear;
        int c = 0;
        for(int i = 0 ; i < this.h ; i++)
        {
            is_clear = true;
            for(int j = 0 ; j < this.b ; j++)
            {
                if((this.screen[i][j] == this.bts[0].rep)||(this.screen[i][j] == this.v))
                {
                    is_clear = false;
                }
            }
            if(is_clear)
            {
                comp[c] = i;
                c++;
            }
        }
        return comp;
    }
    private void clear(int[] comp)
    {
        for(int a = 0 ; a < 3 ; a++)
        {         
            for(int j = 0 ; j < this.b ; j++)
            {
                if(comp[a] != 0)
                {
                     this.screen[comp[a]][j] = this.v;
                }
            }  
        }     
    }
    private void add_score(int[] comp)
    {
        int c = 0;
        for(int i = 0 ; i < comp.length ; i++)
        {
            if(comp[i] != 0)
            {
                this.score += 2000;
                c++;
            }       
        }
        if(c == 3)
        {
            this.score *= 2;
        }
    }
    private void move_down(int comp)
    {
       for(int i = comp ; i > 4 ; i--)
       {
           for(int j = 0 ; j < this.b ; j++)
           {
               this.screen[i][j] = this.screen[i-1][j];
           }
       }
    }
    public void clear_filled()
    { 
        int[] c = completed();
        clear(c);
        add_score(c);
        for(int i = 0 ; i < 3 ; i++)
        {
            if(c[i] != 0)
            {
                time_delay(500);
                move_down(c[i]);
            } 
        }
    }
    //
    public void print()
    {
        cls();
        //
        System.out.print("  ");
        for(int b = 0;b < this.b;b++)
        {
            System.out.print("---");
        }
        //
        for(int a = 0;a < this.h;a++)
        {
            System.out.println();
            if(a == 4)
            {
                System.out.print(">  ");
            }
            else
            {
                System.out.print("|  ");
            }
            for(int b = 0;b < this.b;b++)
            {
                System.out.print(screen[a][b]);
                System.out.print("  ");
             }
            if(a == 4)
            {
                System.out.print("<");
            }
            else
            {
                System.out.print("|");
            }
        }
        //
        System.out.println();
        System.out.print("  ");
        for(int b = 0;b < this.b;b++)
        {
            System.out.print("---");
        }
        //
        System.out.println();
        System.out.println("score: "+this.score);
        System.out.println();
        System.out.println();
    }
    public void cls()
    {
        System.out.print('\f');
    }
    public void end()
    {
        cls();
        System.out.println("GAME OVER");
        System.out.println();
        System.out.println("score: "+this.score);
    }
    private void time_delay(int mil)
    {
        int c_t = (Calendar.getInstance()).get(Calendar.MILLISECOND) + 1000*(Calendar.getInstance()).get(Calendar.SECOND) + 60*1000*(Calendar.getInstance()).get(Calendar.MINUTE);                   
        int d;
        do
        {
             d = (Calendar.getInstance()).get(Calendar.MILLISECOND) + 1000*(Calendar.getInstance()).get(Calendar.SECOND) + 60*1000*(Calendar.getInstance()).get(Calendar.MINUTE) - c_t;               
        }
        while(d <= mil);
    }
    //
    public static void main()
    {
        Tetris t = new Tetris();
        while(!t.check_end())
        {
            t.print();
            char in = t.input();
            if(in == 'q')
            {
                break;
            }
            t.process_input(in);
            t.clear_filled();
        }
        t.end();
    }
}