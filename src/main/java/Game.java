import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game{
    private Screen screen;
    private Hero character;
    private int x = 10;
    private int y = 10;

    public Game(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
            this.character = new Hero(10,10);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void draw() throws IOException{
        screen.clear();
        this.character.draw(screen);
        screen.refresh();
    }

    public void run() {
        KeyStroke key = null;
        boolean open = true;

        try {
            while(open){
                this.draw();
                key = screen.readInput();

                this.processKey(key);
                if(key.getKeyType() == KeyType.EOF){
                    open = false;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveHero(Position pos){
        this.character.setPosition(pos);
    }

    private void processKey(KeyStroke key) {

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
            try {
                screen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
