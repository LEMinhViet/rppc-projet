package rppc.modele;

public class Codage {

	private int permutationPlus[];
	private int permutationMoins[];

	public Codage(int taille) {
		this.permutationPlus = new int[taille];
		this.permutationMoins = new int[taille];
	}

	public Codage(int[] permutationPlus, int[] permutationMoins) {
		this.permutationPlus = permutationPlus;
		this.permutationMoins = permutationMoins;
	}

	public int[] getPermutationPlus() {
		return permutationPlus;
	}

	public void setPermutationPlus(int[] permutationPlus) {
		this.permutationPlus = permutationPlus;
	}

	public int[] getPermutationMoins() {
		return permutationMoins;
	}

	public void setPermutationMoins(int[] permutationMoins) {
		this.permutationMoins = permutationMoins;
	}

	public static Codage permutationEnonce() {
		int permutationPlus[] = new int[] { 0, 1, 2, 3, 4, 5 };
		int permutationMoins[] = new int[] { 3, 4, 0, 2, 5, 1 };

		Codage c = new Codage(permutationPlus, permutationMoins);
		return c;
	}
}
