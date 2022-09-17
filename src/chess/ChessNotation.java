package chess;

import java.util.ArrayList;
import java.util.List;

import board.Piece;
import chess.pieces.King;
import chess.pieces.Pawn;

public class ChessNotation {
	
	private List<String> chessMoveSequence = new ArrayList<>();
	
	public ChessNotation() {
		
	}
	
	public List<String> getChessMoveSequence() {
		return chessMoveSequence;
	}
	
	public void notateChessMove(String movement) {
		chessMoveSequence.add(movement);
	}
	
	public void removeLastChessMove() {
		chessMoveSequence.remove(chessMoveSequence.size() - 1);
	}
	
	public String parseNotation(ChessPosition source, ChessPosition target, ChessPiece movedPiece,
			Piece capturedPiece, boolean check, boolean checkmate, boolean ambiguous) {
		
		StringBuilder chessMove = new StringBuilder();
		
		if(movedPiece instanceof King && target.getColumn() == source.getColumn() + 2) {
			//CASTLING KINGSIDE
			chessMove.append("O-O");
		}
		else if(movedPiece instanceof King && target.getColumn() == source.getColumn() - 2) {
			//CASTLING QUEENSIDE
			chessMove.append("O-O-O");
		}
		else {
			
			if(!(movedPiece instanceof Pawn)) {
				chessMove.append(movedPiece.toString());
			}
			if(ambiguous) {
				chessMove.append(source.getColumn());
			}
			
			if(movedPiece instanceof Pawn && capturedPiece != null) {
				chessMove.append(source.getColumn());
				chessMove.append("x");
			}
			else if(capturedPiece != null) {
				chessMove.append("x");
			}
			
			chessMove.append(target.toString());
		}
		
		if(checkmate) {
			chessMove.append("#");
		} else if(check) {
			chessMove.append("+");
		}
		
		return chessMove.toString();
	}
	
	public String parseNotation(ChessPiece promoted, boolean check, boolean checkmate) {
		StringBuilder chessMove = new StringBuilder();
		chessMove.append(promoted.getChessPosition() + "=" + promoted.toString());
		if(checkmate) {
			chessMove.append("#");
		} else if(check) {
			chessMove.append("+");
		}
		return chessMove.toString();
	}
	

}
