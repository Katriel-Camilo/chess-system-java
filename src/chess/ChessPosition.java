package chess;

import boardgame.Position;

public class ChessPosition {

	private int row;
	private char column;

	public ChessPosition(char column, int row) {
		if (row < 1 || row > 8)
			throw new ChessException("Row out of bounds.");
		if (column < 'a' || column > 'h')
			throw new ChessException("Column out of bounds.");

		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public char getColumn() {
		return column;
	}
	
	protected Position toPosition() {
		int posRow = 8 - this.row;
		int posColumn = this.column - 'a';
		return new Position(posRow, posColumn);
	}
	
	protected static ChessPosition fromPosition(Position position) {
		int chessRow = 8 - position.getRow();
		char chessColumn = (char)(position.getColumn() + 'a');
		return new ChessPosition(chessColumn, chessRow);
	}

	@Override
	public String toString() {
		return String.format("%c%c", column, row);
	}
}
