import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int width;
    private int height;
    private Screen screen;
    private Hero character;
    private List<Wall> walls;


    Arena(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.character = new Hero(10,10);
    }


    private boolean canMoveHero(Position pos){
        if (pos.getX() < 0) return false;
        if (pos.getX() > width - 1) return false;
        if (pos.getY() < 0) return false;
        if (pos.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(pos)) return false;

        return true;
    }

    public void moveHero(Position pos){
        if(this.canMoveHero(pos)){
        this.character.setPosition(pos);
        }
    }

    protected void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        this.character.draw(graphics);
    }


    protected void processKey(KeyStroke key) throws IOException {

        switch (key.getKeyType()){
            case ArrowUp:
                moveHero(character.moveUp());
                break;
            case ArrowDown:
                moveHero(character.moveDown());
                break;
            case ArrowLeft:
                moveHero(character.moveLeft());
                break;
            case ArrowRight:
                moveHero(character.moveRight());
                break;
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){

            screen.close();


        }

    }

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();
        for(int c = 0; c < width; c++){
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, this.height - 1));
        }
        for(int w = 0; w < height - 1; w++){
            walls.add(new Wall(0, w));
            walls.add(new Wall(this.width - 1, w));
        }
        return walls;
    }


}

