package Chap13;



import java.util.*;

public class ChessKnight {
    private int boardDimension;
    private int[] cells;
    private int startCell;
    private static final char[] letters = {'A' , 'B', 'C', 'D', 'E', 'F' , 'G' , 'H'};
    private static final String BOARDS_DELIMITER = "-----------";

    public ChessKnight(int boardDimension) {
        this.boardDimension = boardDimension;
        cells = new int[boardDimension * boardDimension];
    }

    public static void main(String[] args) {
        ChessKnight chessKnight = new ChessKnight(5);
        chessKnight.start(3, 1);  // letter A-1 , B-2, C-3 ...
    }

    public void start(int letter, int degit) {
        startCell = letter -1 + ((degit -1)) ;
        displayBoard();
        dfs(startCell);
    }

    public void dfs(int start) {
        cells[start] = 1;
        Deque<Integer> stack = new ArrayDeque();
        stack.push(start);

        while( !stack.isEmpty() )
        {
            // get an unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex(stack.peek());
            if(v == -1) {
                int poppedCell = stack.pop();
                System.out.println(getCellDescription(poppedCell) +  " doesn't have available movements");

                if (!stack.isEmpty()) {
                    System.out.println("Return to " + getCellDescription(stack.peek()));
                } else {
                    System.out.printf("There are not available movements");
                }
            } else  {
                cells[v] = 1;
                displayBoard();
                System.out.println("moved to " + getCellDescription(v));
                stack.push(v);
            }
        }
        cells = new int[boardDimension * boardDimension];
    }

    public int getAdjUnvisitedVertex(int startCell)  {


        int endCell = calcEndCell(startCell,1,2);

        if (endCell != -1) {
            return endCell;
        } else {
            endCell = calcEndCell (startCell,2, 1);
        }

        if (endCell != -1) {
            return endCell;
        } else {
            endCell = calcEndCell (startCell,2, -1);
        }

        if (endCell != -1) {
            return endCell;
        } else {
            endCell = calcEndCell (startCell,1, -2);
        }
        if (endCell != -1) {
            return endCell;
        } else {
            endCell = calcEndCell (startCell,-1, -2);
        }
        if (endCell != -1) {
            return endCell;
        } else {
            endCell = calcEndCell (startCell, -2, -1);

        }
        if (endCell != -1) {
            return endCell;
        } else {
            endCell = calcEndCell (startCell,-2, 1);
        }
        if (endCell != -1) {
            return endCell;
        } else {
            endCell = calcEndCell (startCell,-1, 2);
        }

        return (endCell != -1) ? endCell : -1;
        }


    private int calcEndCell( int cell, int letter, int digit) {
        int x = ((cell ) % boardDimension) + 1 + letter;
        int y = ((cell ) / boardDimension) + 1 + digit;

        int result = cell + letter +  digit * boardDimension;
        return isApplicableCell(x, y) ? result : -1;
    }

    private String getCellDescription(int indexCell) {
        int  indexLetter = (indexCell + 1) % boardDimension;
        if (indexLetter == 0) indexLetter = 5;
        int raw = (indexCell) / boardDimension + 1;
        return  String.valueOf(letters[indexLetter - 1 ])  +  raw;
    }

    private boolean isApplicableCell(int x, int y) {
        return isPossibleCoordinate(x) && isPossibleCoordinate(y) && isUnvisitedCell(x , y);
    }

    private boolean isPossibleCoordinate(int coordinate) {
        return (coordinate > 0 && coordinate <= boardDimension);
    }

    private boolean isUnvisitedCell(int letter, int digit) {
        return (cells[letter - 1 + (digit-1) * boardDimension ] == 0);
    }

    private void displayBoard( ) {
        System.out.println(BOARDS_DELIMITER);
        String letters =" |A|B|C|D|E|F|G|H|";
        System.out.println(letters.substring(0,2 + boardDimension*2 ));

        System.out.print("1|");

        for (int i = 1 ; i <= cells.length; i++) {
            System.out.print(cells[i-1] + "|");

            if ((i % boardDimension == 0) && (i !=0 && i < cells.length))  {
                System.out.println("");
                System.out.print((i/boardDimension) + 1 + "|") ;
            }
        }
        System.out.println("");
    }
}
