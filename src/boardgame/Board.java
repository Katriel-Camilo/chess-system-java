package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1)
			throw new BoardException("ERROR CREATING BOARD: there must be at least 1 row and 1 column.");
		
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	/**
	 * Get the board piece at the (row, column) pair or a null object.
	 * 
	 * @param row board row
	 * @param column board column
	 * @return A {@code Piece} object or a {@code null} value object if no piece is found.
	 */
	public Piece piece(int row, int column) {
		if (!positionExists(row, column))
			throw new BoardException("Position out of bounds.");
		
		return pieces[row][column];
	}
	
	/**
	 * Get the board piece at the specified {@code Position} or a null object.
	 * 
	 * @param position {@code Position} object representing a board cell.
	 * @return {@code Piece} object or a {@code null} value object if no piece is found.
	 */
	public Piece piece(Position position) {
		if (!positionExists(position))
			throw new BoardException("Position out of bounds.");
		
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position))
			throw new BoardException("Position already occupied.");
		
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if(!thereIsAPiece(position))
			return null;
		
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {
		return (row >= 0 && row < this.rows && column >= 0 && column < this.columns);
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position){
		if (!positionExists(position))
			throw new BoardException("Position out of bounds.");
			
		return (pieces[position.getRow()][position.getColumn()] != null);
		
	}
}
