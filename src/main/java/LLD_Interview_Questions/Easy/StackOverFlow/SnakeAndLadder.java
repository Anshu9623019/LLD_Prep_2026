//package LLD_Interview_Questions.Easy.StackOverFlow;
//
//import java.util.*;
//
//// ─────────────────────────────────────────
//// MAIN CLASS
//// ─────────────────────────────────────────
//public class SnakeAndLadder {
//    public static void main(String[] args) {
//        // Client interaction
//        Client.run();
//    }
//}
//
//        // ─────────────────────────────────────────
//// CLIENT CLASS
//// ─────────────────────────────────────────
//        class Client {
//            public static void run() {
//                System.out.println("===== Snake and Ladder Game =====\n");
//
//                // Create game using factory
//                Game game = GameFactory.createGame(Difficulty.MEDIUM);
//
//                // Add players
//                game.addPlayer(new Player(1, "Alice"));
//                game.addPlayer(new Player(2, "Bob"));
//                game.addPlayer(new Player(3, "Charlie"));
//
//                // Add observers
//                game.addObserver(new ConsoleNotifier());
//                game.addObserver(new FileNotifier());
//
//                // Display board
//                game.board.display();
//
//                // Start game
//                game.play();
//            }
//        }
//
//        // ─────────────────────────────────────────
//// GAME CLASS
//// ─────────────────────────────────────────
//        class Game {
//            Board board;
//            Dice dice;
//            Deque<Player> players;
//            List<Observer> observers;
//            Rules rules;
//            boolean isGameOver;
//
//            Game() {
//                players    = new ArrayDeque<>();
//                observers  = new ArrayList<>();
//                isGameOver = false;
//            }
//
//            void addPlayer(Player player) {
//                players.addLast(player);
//                notifyObservers("Player added: " + player.name);
//            }
//
//            void addObserver(Observer observer) {
//                observers.add(observer);
//            }
//
//            void notifyObservers(String msg) {
//                for (Observer obs : observers) {
//                    obs.update(msg);
//                }
//            }
//
//            void play() {
//                notifyObservers("Game Started!");
//                System.out.println("\n===== Game Play =====\n");
//
//                while (!isGameOver) {
//                    // get current player
//                    Player current = players.pollFirst();
//
//                    // roll dice
//                    int diceVal = dice.roll();
//                    System.out.println("\n" + current.name
//                            + " rolled: " + diceVal
//                            + " (current pos: " + current.pos + ")");
//
//                    // check if move is valid
//                    if (rules.isValidMove(current.pos, diceVal, board)) {
//                        // calculate new position
//                        int newPos = rules.calNewPos(current.pos, diceVal, board);
//                        current.pos = newPos;
//                        System.out.println(current.name
//                                + " moved to: " + current.pos);
//                        notifyObservers(current.name + " moved to position " + current.pos);
//
//                        // check win
//                        if (rules.checkWin(current.pos, board)) {
//                            current.score++;
//                            isGameOver = true;
//                            System.out.println("\n🎉 " + current.name + " WINS! 🎉");
//                            notifyObservers(current.name + " WON the game!");
//                            break;
//                        }
//                    } else {
//                        System.out.println(current.name
//                                + " cannot move (overshoots board). Turn skipped.");
//                        notifyObservers(current.name + " skipped turn.");
//                    }
//
//                    // put player back in queue
//                    players.addLast(current);
//                }
//            }
//        }
//
//        // ─────────────────────────────────────────
//// BOARD CLASS
//// ─────────────────────────────────────────
//        class Board {
//            int size;
//            List<BoardEntity> boardEntityList;
//            Map<Integer, BoardEntity> entityMap;
//
//            Board(int size) {
//                this.size          = size;
//                this.boardEntityList = new ArrayList<>();
//                this.entityMap     = new HashMap<>();
//            }
//
//            public boolean canAddEntity(BoardEntity entity) {
//                // can't place entity on cell 1 or last cell
//                if (entity.getStart() <= 1 || entity.getStart() >= size)
//                    return false;
//                if (entity.getEnd() <= 1 || entity.getEnd() >= size)
//                    return false;
//                // can't place two entities on same cell
//                if (entityMap.containsKey(entity.getStart()))
//                    return false;
//                return true;
//            }
//
//            public void setUpBoard(SetUpBoardStrategy strategy) {
//                strategy.setUpBoard(this);
//            }
//
//            public void display() {
//                System.out.println("\n===== Board (size=" + size + ") =====");
//                for (BoardEntity entity : boardEntityList) {
//                    entity.display();
//                }
//                System.out.println("==============================\n");
//            }
//        }
//
//        // ─────────────────────────────────────────
//// BOARD ENTITY INTERFACE
//// ─────────────────────────────────────────
//        interface BoardEntity {
//            int getStart();
//            int getEnd();
//            void display();
//        }
//
//        // ─────────────────────────────────────────
//// SNAKE CLASS
//// ─────────────────────────────────────────
//        class Snake implements BoardEntity {
//            private int start;   // head (higher number)
//            private int end;     // tail (lower number)
//
//            Snake(int start, int end) {
//                this.start = start;
//                this.end   = end;
//            }
//
//            @Override
//            public int getStart() { return start; }
//
//            @Override
//            public int getEnd() { return end; }
//
//            @Override
//            public void display() {
//                System.out.println("🐍 Snake: Head=" + start + " → Tail=" + end);
//            }
//        }
//
//        // ─────────────────────────────────────────
//// LADDER CLASS
//// ─────────────────────────────────────────
//        class Ladder implements BoardEntity {
//            private int start;   // bottom (lower number)
//            private int end;     // top (higher number)
//
//            Ladder(int start, int end) {
//                this.start = start;
//                this.end   = end;
//            }
//
//            @Override
//            public int getStart() { return start; }
//
//            @Override
//            public int getEnd() { return end; }
//
//            @Override
//            public void display() {
//                System.out.println("🪜 Ladder: Bottom=" + start + " → Top=" + end);
//            }
//        }
//
//        // ─────────────────────────────────────────
//// SETUP BOARD STRATEGY INTERFACE
//// ─────────────────────────────────────────
//        interface SetUpBoardStrategy {
//            void setUpBoard(Board board);
//        }
//
//        // ─────────────────────────────────────────
//// RANDOM STRATEGY
//// ─────────────────────────────────────────
//        class RandomStrategy implements SetUpBoardStrategy {
//            private int numSnakes;
//            private int numLadders;
//
//            RandomStrategy(int numSnakes, int numLadders) {
//                this.numSnakes  = numSnakes;
//                this.numLadders = numLadders;
//            }
//
//            @Override
//            public void setUpBoard(Board board) {
//                Random rand = new Random();
//                int size    = board.size;
//
//                // add snakes
//                int snakesAdded = 0;
//                while (snakesAdded < numSnakes) {
//                    int head = rand.nextInt(size - 2) + 2;   // 2 to size-1
//                    int tail = rand.nextInt(head - 1) + 1;   // 1 to head-1
//                    Snake snake = new Snake(head, tail);
//
//                    if (board.canAddEntity(snake)) {
//                        board.boardEntityList.add(snake);
//                        board.entityMap.put(head, snake);
//                        snakesAdded++;
//                    }
//                }
//
//                // add ladders
//                int laddersAdded = 0;
//                while (laddersAdded < numLadders) {
//                    int bottom = rand.nextInt(size - 2) + 1; // 1 to size-2
//                    int top    = rand.nextInt(size - bottom) + bottom + 1;
//
//                    if (top >= size) continue;
//
//                    Ladder ladder = new Ladder(bottom, top);
//
//                    if (board.canAddEntity(ladder)) {
//                        board.boardEntityList.add(ladder);
//                        board.entityMap.put(bottom, ladder);
//                        laddersAdded++;
//                    }
//                }
//
//                System.out.println("Board setup with "
//                        + numSnakes + " snakes and "
//                        + numLadders + " ladders (Random Strategy)");
//            }
//        }
//
//        // ─────────────────────────────────────────
//// PAIR CLASS
//// ─────────────────────────────────────────
//        class Pair {
//            int start;
//            int end;
//
//            Pair(int start, int end) {
//                this.start = start;
//                this.end   = end;
//            }
//        }
//
//        // ─────────────────────────────────────────
//// CUSTOM STRATEGY
//// ─────────────────────────────────────────
//        class CustomStrategy implements SetUpBoardStrategy {
//            private List<Pair> snakePos;
//            private List<Pair> ladderPos;
//
//            CustomStrategy(List<Pair> snakePos, List<Pair> ladderPos) {
//                this.snakePos  = snakePos;
//                this.ladderPos = ladderPos;
//            }
//
//            @Override
//            public void setUpBoard(Board board) {
//                addSnakePos(board);
//                addLadderPos(board);
//                System.out.println("Board setup with custom positions.");
//            }
//
//            public void addLadderPos(Board board) {
//                for (Pair p : ladderPos) {
//                    Ladder ladder = new Ladder(p.start, p.end);
//                    if (board.canAddEntity(ladder)) {
//                        board.boardEntityList.add(ladder);
//                        board.entityMap.put(p.start, ladder);
//                    } else {
//                        System.out.println("⚠️ Cannot add ladder at "
//                                + p.start + " → " + p.end);
//                    }
//                }
//            }
//
//            public void addSnakePos(Board board) {
//                for (Pair p : snakePos) {
//                    Snake snake = new Snake(p.start, p.end);
//                    if (board.canAddEntity(snake)) {
//                        board.boardEntityList.add(snake);
//                        board.entityMap.put(p.start, snake);
//                    } else {
//                        System.out.println("⚠️ Cannot add snake at "
//                                + p.start + " → " + p.end);
//                    }
//                }
//            }
//        }
//
//        // ─────────────────────────────────────────
//// DICE CLASS
//// ─────────────────────────────────────────
//        class Dice {
//            int faces;
//            int numDice;
//            Random random;
//
//            Dice(int faces, int numDice) {
//                this.faces   = faces;
//                this.numDice = numDice;
//                this.random  = new Random();
//            }
//
//            public int roll() {
//                int total = 0;
//                for (int i = 0; i < numDice; i++) {
//                    total += random.nextInt(faces) + 1;  // 1 to faces
//                }
//                return total;
//            }
//        }
//
//        // ─────────────────────────────────────────
//// RULES INTERFACE
//// ─────────────────────────────────────────
//        interface Rules {
//            boolean isValidMove(int pos, int diceVal, Board board);
//            int calNewPos(int pos, int diceVal, Board board);
//            boolean checkWin(int pos, Board board);
//        }
//
//        // ─────────────────────────────────────────
//// STANDARD RULE
//// ─────────────────────────────────────────
//        class StandardRule implements Rules {
//
//            @Override
//            public boolean isValidMove(int pos, int diceVal, Board board) {
//                // valid if new position doesn't exceed board size
//                return pos + diceVal <= board.size;
//            }
//
//            @Override
//            public int calNewPos(int pos, int diceVal, Board board) {
//                int newPos = pos + diceVal;
//
//                // check if snake or ladder at newPos
//                if (board.entityMap.containsKey(newPos)) {
//                    BoardEntity entity = board.entityMap.get(newPos);
//
//                    if (entity instanceof Snake) {
//                        System.out.println("🐍 Oops! Snake at " + newPos
//                                + " → sliding down to " + entity.getEnd());
//                        newPos = entity.getEnd();
//                    } else if (entity instanceof Ladder) {
//                        System.out.println("🪜 Lucky! Ladder at " + newPos
//                                + " → climbing up to " + entity.getEnd());
//                        newPos = entity.getEnd();
//                    }
//                }
//
//                return newPos;
//            }
//
//            @Override
//            public boolean checkWin(int pos, Board board) {
//                return pos == board.size;
//            }
//        }
//
//        // ─────────────────────────────────────────
//// PLAYER CLASS
//// ─────────────────────────────────────────
//        class Player {
//            int id;
//            String name;
//            int pos;
//            int score;
//
//            Player(int id, String name) {
//                this.id    = id;
//                this.name  = name;
//                this.pos   = 0;
//                this.score = 0;
//            }
//
//            @Override
//            public String toString() {
//                return "Player{id=" + id
//                        + ", name=" + name
//                        + ", pos=" + pos
//                        + ", score=" + score + "}";
//            }
//        }
//
//        // ─────────────────────────────────────────
//// GAME FACTORY
//// ─────────────────────────────────────────
//        class GameFactory {
//            static Game createGame(Difficulty difficulty) {
//                Game game  = new Game();
//                game.dice  = new Dice(6, 1);
//                game.rules = new StandardRule();
//
//                switch (difficulty) {
//                    case EASY:
//                        // smaller board, fewer snakes
//                        game.board = new Board(50);
//                        game.board.setUpBoard(new CustomStrategy(
//                                Arrays.asList(
//                                        new Pair(20, 5),
//                                        new Pair(35, 10)
//                                ),
//                                Arrays.asList(
//                                        new Pair(4, 25),
//                                        new Pair(15, 40)
//                                )
//                        ));
//                        break;
//
//                    case MEDIUM:
//                        // standard 100-cell board
//                        game.board = new Board(100);
//                        game.board.setUpBoard(new CustomStrategy(
//                                Arrays.asList(
//                                        new Pair(17,  7),
//                                        new Pair(54, 34),
//                                        new Pair(62, 19),
//                                        new Pair(64, 60),
//                                        new Pair(87, 24),
//                                        new Pair(93, 73),
//                                        new Pair(95, 75),
//                                        new Pair(99, 78)
//                                ),
//                                Arrays.asList(
//                                        new Pair(4,  14),
//                                        new Pair(9,  31),
//                                        new Pair(20, 38),
//                                        new Pair(28, 84),
//                                        new Pair(40, 59),
//                                        new Pair(51, 67),
//                                        new Pair(63, 81),
//                                        new Pair(71, 91)
//                                )
//                        ));
//                        break;
//
//                    case HARD:
//                        // larger board, more snakes via random
//                        game.board = new Board(100);
//                        game.board.setUpBoard(new RandomStrategy(12, 5));
//                        break;
//                }
//
//                return game;
//            }
//        }
//
//        // ─────────────────────────────────────────
//// OBSERVER INTERFACE
//// ─────────────────────────────────────────
//        interface Observer {
//            void update(String msg);
//        }
//
//        // ─────────────────────────────────────────
//// CONSOLE NOTIFIER
//// ─────────────────────────────────────────
//        class ConsoleNotifier implements Observer {
//            @Override
//            public void update(String msg) {
//                System.out.println("[NOTIFY] " + msg);
//            }
//        }
//
//        // ─────────────────────────────────────────
//// FILE NOTIFIER
//// ─────────────────────────────────────────
//        class FileNotifier implements Observer {
//            @Override
//            public void update(String msg) {
//                // in real implementation → write to file
//                System.out.println("[FILE LOG] " + msg);
//            }
//        }
//
//        // ─────────────────────────────────────────
//// DIFFICULTY ENUM
//// ─────────────────────────────────────────
//        enum Difficulty {
//            EASY, MEDIUM, HARD
//        }
//
//
////        ---
////
////        ### Sample Output
////```
////        ===== Snake and Ladder Game =====
////
////        [NOTIFY] Player added: Alice
////[NOTIFY] Player added: Bob
////[NOTIFY] Player added: Charlie
////
////===== Board (size=100) =====
////        🐍 Snake: Head=17  → Tail=7
////        🐍 Snake: Head=54  → Tail=34
////        🐍 Snake: Head=62  → Tail=19
////        🪜 Ladder: Bottom=4  → Top=14
////        🪜 Ladder: Bottom=9  → Top=31
////        🪜 Ladder: Bottom=28 → Top=84
////        ==============================
////
////        ===== Game Play =====
////        [NOTIFY] Game Started!
////
////        Alice rolled: 4 (current pos: 0)
////        🪜 Lucky! Ladder at 4 → climbing up to 14
////        Alice moved to: 14
////        [NOTIFY] Alice moved to position 14
////
////        Bob rolled: 3 (current pos: 0)
////        Bob moved to: 3
////        [NOTIFY] Bob moved to position 3
////        ...
////
////        🎉 Alice WINS! 🎉
////        [NOTIFY] Alice WON the game!
////        ```
////
////        ---
////
////        ### Design Patterns Used
////```
////        1. Strategy Pattern
////        SetUpBoardStrategy → RandomStrategy / CustomStrategy
////        Swap board setup without changing Board class
////
////2. Observer Pattern
////        Observer → ConsoleNotifier / FileNotifier
////        Notify all observers on game events
////
////3. Factory Pattern
////   GameFactory.createGame(Difficulty)
////        Creates configured game based on difficulty
////
////4. Template Method
////        Rules interface → StandardRule
////        Swap rules without changing Game class
////```
////
////        ---
////
////        ### Class Diagram
////```
////        SnakeAndLadder
////    └── Client
////          └── GameFactory ──creates──► Game
////                                        ├── Board
////                                        │     ├── Snake (BoardEntity)
////                                        │     └── Ladder (BoardEntity)
////                                        ├── Dice
////                                        ├── Rules (StandardRule)
////                                        ├── Deque<Player>
////                                        └── List<Observer>
////                                              ├── ConsoleNotifier
////                                              └── FileNotifier