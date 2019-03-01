import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Arena {

    private int width;
    private int heigth;
    private Screen screen;
    private Hero character;

    Arena(int width, int heigth) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        this.width = width;
        this.heigth = heigth;
        this.character = new Hero(10,10);
    }


    private boolean canMoveHero(Position pos){
        return pos.getX() >= 0 && pos.getX() <= width && pos.getY() >= 0 && pos.getY() <= heigth;
    }

    public void moveHero(Position pos){
        if(canMoveHero(pos)){
        this.character.setPosition(pos);
        }
    }

    private void draw() throws IOException{
        screen.clear();
        this.character.draw(screen);
        screen.refresh();
    }

    public void run() throws IOException {
        KeyStroke key = null;
        boolean open = true;

            while(open){
                this.draw();
                key = screen.readInput();

                this.processKey(key);
                if(key.getKeyType() == KeyType.EOF){
                    open = false;
                }
            }
    }

    private void processKey(KeyStroke key) throws IOException {

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


}

