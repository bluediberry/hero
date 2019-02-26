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
    private int x = 10;
    private int y = 10;

    public Game(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void draw(){
        screen.clear();
        screen.setCharacter(x, y, new TextCharacter('X'));
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void processKey(KeyStroke key) {
        System.out.println(key);

        switch (key.getKeyType()){
            case ArrowUp:
                y = y-1;
                break;
            case ArrowDown:
                y = y+1;
                break;
            case ArrowLeft:
                x = x-1;
                break;
            case ArrowRight:
                x = x+1;
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
