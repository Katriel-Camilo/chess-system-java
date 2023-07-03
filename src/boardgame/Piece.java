package boardgame;

public abstract class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		this.position = null;
	}
	
	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	/**
	 * Checks if the piece movement to the specified target {@code Position} is possible.
	 * @param position target position
	 * @return {@code boolean}
	 */
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()]; //Hook method implementation
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] moves = possibleMoves();
		for (int i = 0; i < moves.length; i++)
			for(int j = 0; j < moves[0].length; j++)
				if (moves[i][j])
					return true;
		return false;
	}
}
