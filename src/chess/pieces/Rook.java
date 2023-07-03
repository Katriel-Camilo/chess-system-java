package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Board board = this.getBoard();
		Position p = new Position(0, 0);

		// above
		p.setValues(this.position.getRow() - 1, this.position.getColumn());
		while (board.positionExists(p) && !board.thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (board.positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(this.position.getRow(), this.position.getColumn() - 1);
		while (board.positionExists(p) && !board.thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (board.positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(this.position.getRow(), this.position.getColumn() + 1);
		while (board.positionExists(p) && !board.thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (board.positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(this.position.getRow() + 1, this.position.getColumn());
		while (board.positionExists(p) && !board.thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (board.positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
}
