package com.trsljs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameOfLife extends JFrame implements ActionListener {
    private JRadioButton[][] grid;
    private JButton next;
    private JButton play;
    private JButton clear;
    private JButton reset;
    private JButton stop;

    private CellArray initialState;
    private CellArray startState;
    private CellArrayService cellArrayService = new CellArrayService();

    private boolean isPlaying;

    private static final String NEXT = "Step Next Generation";
    private static final String PLAY = "Play";
    private static final String STOP = "Stop Play";
    private static final String CLEAR = "Clear Grid";
    private static final String RESET = "Reset Initial State";

    public GameOfLife() {
        isPlaying = false;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = dimension.width / 2 - this.getSize().width / 2;
        int height = dimension.height / 2 - this.getSize().height / 2;

        initialState = getInitialState();

        initComponents(initialState);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("Conway's Game Of Life");
        this.setVisible(true);
        this.setLocation(width, height);
        this.setLocationRelativeTo(null);

    }

    private void initComponents(CellArray state) {
        int rows = state.getRowCount();
        int columns = state.getColumnCount();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        grid = new JRadioButton[rows][columns];
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, columns));
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                grid[row][column] = new JRadioButton();
                grid[row][column].setSelected(state.getCellAt(row, column).isAlive());

                gridPanel.add(grid[row][column]);
            }
        }
        mainPanel.add(gridPanel);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1, 5, 10) );

        clear = new JButton(CLEAR);
        clear.addActionListener(this);
        buttonsPanel.add(clear);

        reset = new JButton(RESET);
        reset.addActionListener(this);
        buttonsPanel.add(reset);
        mainPanel.add(buttonsPanel);

        next = new JButton(NEXT);
        next.addActionListener(this);
        buttonsPanel.add(next);

        JPanel playStopPanel = new JPanel();
        playStopPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        play = new JButton(PLAY);
        play.addActionListener(this);
        play.setPreferredSize(new Dimension(125, 50));
        playStopPanel.add(play);

        stop = new JButton(STOP);
        stop.addActionListener(this);
        stop.setPreferredSize(new Dimension(125, 50));
        stop.setEnabled(false);
        playStopPanel.add(stop);

        buttonsPanel.add(playStopPanel);

        getContentPane().add(mainPanel);
        pack();
    }

    private CellArray getInitialState() {
        CellArray initialState = new CellArray(6, 8);
        initialState.setCellAt(0, 6, Cell.LIVE_CELL);
        initialState.setCellAt(1, 0, Cell.LIVE_CELL);
        initialState.setCellAt(1, 1, Cell.LIVE_CELL);
        initialState.setCellAt(1, 2, Cell.LIVE_CELL);
        initialState.setCellAt(1, 6, Cell.LIVE_CELL);
        initialState.setCellAt(2, 6, Cell.LIVE_CELL);
        initialState.setCellAt(4, 3, Cell.LIVE_CELL);
        initialState.setCellAt(4, 4, Cell.LIVE_CELL);
        initialState.setCellAt(5, 3, Cell.LIVE_CELL);
        initialState.setCellAt(5, 4, Cell.LIVE_CELL);
        return initialState;
    }

    private void showNextGeneration() {
        CellArray currentGeneration = new CellArray(grid.length, grid[0].length);
        int rows = grid.length;
        int columns = grid[0].length;
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                Cell cell = grid[row][column].isSelected() ? Cell.LIVE_CELL : Cell.DEAD_CELL;
                currentGeneration.setCellAt(row, column, cell);
            }
        }
        CellArray nextGeneration = cellArrayService.getNextGeneration(currentGeneration);
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                grid[row][column].setSelected(nextGeneration.getCellAt(row, column).isAlive());
            }
        }
        startState = nextGeneration;

    }

    private void playNextGeneration() {
        int rows = grid.length;
        int columns = grid[0].length;
        play.setEnabled(false);
        next.setEnabled(false);
        clear.setEnabled(false);
        reset.setEnabled(false);
        stop.setEnabled(true);

        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                grid[row][column].setEnabled(false);
            }
        }

        isPlaying = true;
        SwingWorker playWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while (isPlaying){
                    showNextGeneration();
                    Thread.sleep(500);
                }
                return null;
            }
        };
        playWorker.execute();

    }

    private void stopNextGeneration() {
        int rows = grid.length;
        int columns = grid[0].length;
        isPlaying = false;
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                grid[row][column].setEnabled(true);
            }
        }
        next.setEnabled(true);
        play.setEnabled(true);
        clear.setEnabled(true);
        reset.setEnabled(true);
        stop.setEnabled(false);
    }

    private void clearGrid() {
        int rows = grid.length;
        int columns = grid[0].length;
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                grid[row][column].setSelected(false);
            }
        }
    }

    private void resetToInitialState() {
        int rows = grid.length;
        int columns = grid[0].length;
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                grid[row][column].setSelected(initialState.getCellAt(row, column).isAlive());
            }
        }
    }


    public static void main(String[] args )
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new GameOfLife().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case CLEAR:
                clearGrid();
                break;
            case RESET:
                resetToInitialState();
                break;
            case NEXT:
                showNextGeneration();
                break;
            case PLAY:
                playNextGeneration();
                break;
            case STOP:
                stopNextGeneration();
        }
    }
}
