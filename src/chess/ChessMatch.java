package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private static final int BOARD_SIZE = 8;
	private int turn;
	private Color currentPlayer;

	private List<ChessPiece> piecesOnTheBoard;
	private List<ChessPiece> capturedPieces;

	public ChessMatch() {
		board = new Board(BOARD_SIZE, BOARD_SIZE);
		turn = 1;
		currentPlayer = Color.WHITE;
		piecesOnTheBoard = new ArrayList<ChessPiece>();
		capturedPieces = new ArrayList<ChessPiece>();
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++)
			for (int j = 0; j < board.getColumns(); j++)
				mat[i][j] = (ChessPiece) board.piece(i, j); // Downcasting: Piece -> ChessPiece
		return mat;
	}

	public boolean[][] possibleMoves(ChessPosition chessPos) {
		Position p = chessPos.toPosition();
		validateSourcePosition(p);
		return board.piece(p).possibleMoves();
	}

	/**
	 * Perform a chess move using the piece at {@code sourcePos} going to
	 * {@code targetPos} if possible
	 * 
	 * @param sourcePos piece start position
	 * @param targetPos move destiny position
	 * @return The captured {@code ChessPiece} at {@code targetPos} or a
	 *         {@code null} object if the position is empty
	 */
	public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos) {
		Position source = sourcePos.toPosition();
		Position target = targetPos.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position))
			throw new ChessException("There is no piece at source position.");
		if (getCurrentPlayer() != ((ChessPiece) board.piece(position)).getColor())
			throw new ChessException("This piece does not belong to you");
		if (!board.piece(position).isThereAnyPossibleMove())
			throw new ChessException("This piece can't move.");
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target))
			throw new ChessException("The chosen piece can't move to target position");
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		if(capturedPiece != null) {
			piecesOnTheBoard.remove((ChessPiece)capturedPiece);
			capturedPieces.add((ChessPiece)capturedPiece);
		}
		board.placePiece(p, target);
		return capturedPiece;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); // Upcasting: ChessPiece -> Piece
		piecesOnTheBoard.add(piece);
	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
