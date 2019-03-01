import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero{

    private Position pos;
    private TextCharacter character;

    Hero(int x, int y){
        this.pos = new Position(x,y);
    }

    public int getX(){
        return this.pos.getX();
    }

    public void setX(int x){
        this.pos.setX(x);
    }

    public int getY(){
        return this.pos.getY();
    }

    public void setY(int y){
        this.pos.setY(y);
    }

    public Position moveUp(){
        return this.pos.moveUp();
    }

    public Position moveDown(){
        return this.pos.moveDown();
    }

    public Position moveLeft(){
        return this.pos.moveLeft();
    }

    public Position moveRight(){
       return this.pos.moveRight();
    }

    public void setPosition(Position pos){
       this.pos = pos;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "X");
    }
}
