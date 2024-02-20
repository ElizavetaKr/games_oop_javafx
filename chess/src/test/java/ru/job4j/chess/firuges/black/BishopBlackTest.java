package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopBlackTest {

    @Test
    public void position() {
        BishopBlack bishopBlack = new BishopBlack(Cell.findBy(1, 7));
        Cell cell = bishopBlack.position();
        assertThat(cell.getX()).isEqualTo(1);
        assertThat(cell.getY()).isEqualTo(7);
    }

    @Test
    public void whenCopyIsValid() {
        Cell cellSource = Cell.findBy(1, 7);
        Cell cellDest = Cell.findBy(0, 6);
        BishopBlack bishopBlack = new BishopBlack(cellSource);
        Figure figure = bishopBlack.copy(cellDest);
        Cell cell = figure.position();
        assertThat(cell.getX()).isEqualTo(0);
        assertThat(cell.getY()).isEqualTo(6);
    }

    @Test
    public void whenWayIsValid() {
        Cell cellSource = Cell.C1;
        BishopBlack bishopBlack = new BishopBlack(cellSource);
        Cell[] actual = bishopBlack.way(Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void whenWayNotValid() {
        Cell cellSource = Cell.C1;
        BishopBlack bishopBlack = new BishopBlack(cellSource);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishopBlack.way(Cell.G4);
                });
        assertThat(exception.getMessage()).isEqualTo(String.format(
                "Could not way by diagonal from %s to %s", Cell.C1, Cell.G4));
    }

    @Test
    public void whenIsDiagonalFalse() {
        Cell source = Cell.C1;
        Cell dest = Cell.C2;
        BishopBlack bishopBlack = new BishopBlack(source);
        assertFalse(bishopBlack.isDiagonal(source, dest));
    }
}