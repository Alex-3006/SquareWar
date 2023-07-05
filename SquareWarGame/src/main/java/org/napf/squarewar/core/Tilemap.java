package org.napf.squarewar.core;

public class Tilemap extends GameObject{

	private Tile[][] tiles;

	/**
	 * Instantiates a Tilemap
	 * @param xpos the xpos of the center of the Tilemap
	 * @param ypos the ypos of the center of the Tilemap
	 * @param name the name
	 * @param rows the amount of rows
	 * @param columns the amount of columns
	 * @param tiles the tiles formatted as a char array. Must contain as many elements as there are tiles in the tilemap. 'e' = empty / null. 's' = solid (standard tile).
	 */
	public Tilemap(double xpos, double ypos, String name, int rows, int columns, char... tiles) {
		super(xpos,ypos,name);
		this.tiles = new Tile[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				setTile(j, i, (tiles[i * columns + j] == 's' ? new Tile(0, 0, getName() + "-Tile" + j + "|" + i) : null));
			}
		}
	}

	/**
	 * Instantiates a Tilemap
	 * @param xpos the xpos of the center of the Tilemap
	 * @param ypos the ypos of the center of the Tilemap
	 * @param name the name
	 * @param rows the amount of rows
	 * @param columns the amount of columns
	 */
	public Tilemap(double xpos, double ypos, String name, int rows, int columns) {
		super(xpos,ypos,name);
		this.tiles = new Tile[rows][columns];
	}

	public Tile[][] getTiles() {
		return tiles.clone();
	}

	public int getColumns() {
		return tiles[0].length;
	}

	public int getRows() {
		return tiles.length;
	}

	public void setTile(int x, int y, Tile tile) {
		if (tile == null) {
			GameController.getInstance().killGameObject(tiles[x][y]);
		}
		tiles[x][y] = tile;
		if (tile != null) {
			tiles[x][y].setXpos(getXpos() - getColumns() / 2 + x + 0.5);
			tiles[x][y].setYpos(getYpos() - getRows() / 2 + y + 0.5);
		}
	}
}