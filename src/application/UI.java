package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// Foreground colors
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// Background colors
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	/**
	 * Clear the console screen.
	 */
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * Reads the chess position string from a {@code Scanner} and returns the equivalent {@code ChessPosition} object
	 * @param sc A {@code Scanner}
	 * @return The equivalent {@code ChessPosition} object
	 */
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.next();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}

	/**
	 * Print the board to the standard output.
	 * @param pieces A piece matrix representing a chess board piece distribution
	 */
	public static void printBoard(ChessPiece[][] pieces) {
		System.out.print(ANSI_BLACK_BACKGROUND);
		System.out.print(ANSI_CYAN);
		for (int i = 0; i < pieces.length; i++) {
			System.out.printf(ANSI_GREEN + "%d " + ANSI_CYAN, 8 - i);
			for (int j = 0; j < pieces[0].length; j++)
				printPiece(pieces[i][j], false);
			System.out.println();
		}
		System.out.println(ANSI_GREEN + "  a b c d e f g h " + ANSI_CYAN);
		System.out.print(ANSI_RESET);
	}
	
	/**
	 * Print the given {@code ChessMatch} full info to the standard output.
	 * @param chessMatch A {@code ChessMatch} object
	 */
	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("TURN: " + chessMatch.getTurn());
		System.out.println("WAITING PLAYER: " + chessMatch.getCurrentPlayer());
	}
	
	/**
	 * Print the board to the standard output, highlighting possible move tiles.
	 * @param pieces A piece matrix representing a chess board piece distribution
	 * @param possibleMoves A boolean matrix representing a chess board possible moves distribution
	 */
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		System.out.print(ANSI_BLACK_BACKGROUND);
		System.out.print(ANSI_CYAN);
		for (int i = 0; i < pieces.length; i++) {
			System.out.printf(ANSI_GREEN + "%d " + ANSI_CYAN, 8 - i);
			for (int j = 0; j < pieces[0].length; j++)
				printPiece(pieces[i][j], possibleMoves[i][j]);
			System.out.println();
		}
		System.out.println(ANSI_GREEN + "  a b c d e f g h " + ANSI_CYAN);
		System.out.print(ANSI_RESET);
	}

	/**
	 * Print the given {@code ChessPiece} to the standard output. If the chess piece is null, then it
	 * will print a dash '-'
	 * @param piece A {@code ChessPiece} object
	 * @param background If {@code true}, the piece background will be highlighted
	 */
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background)
			System.out.print(ANSI_RED_BACKGROUND);
		if (piece == null) {
			System.out.print("-");
		} else {
			if (piece.getColor() == Color.BLACK)
				System.out.print(ANSI_YELLOW + piece);
			else
				System.out.print(ANSI_WHITE + piece);
		}
		System.out.print(ANSI_RESET + ANSI_CYAN);
		System.out.print(" ");
	}
}
