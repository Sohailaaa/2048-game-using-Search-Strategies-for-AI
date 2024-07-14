
public class Organism {
	int id;
	int size;
	int row;
	int column;

	public Organism(int id, int size, int row, int column) {
		this.id = id;
		this.size = size;
		this.row = row;
		this.column = column;
	}

	public int getId() {
		return id;
	}

	public void updateSize(int newSize) {
		this.size = newSize;
	}

	public void updateCoordinates(int[] newCoordinates) {
		this.row = newCoordinates[0];
		this.column = newCoordinates[1];
	}

	// Optional: Method to clone an organism
	public Organism clone() {
		return new Organism(this.id, this.size, this.row, this.column);
	}
}
