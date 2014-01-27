package rppc.modele;

import java.util.LinkedList;
import java.util.List;

public class Decodage {

	private Codage codage;
	private Probleme probleme;
	private int permutationPlusInverse[];
	private int permutationMoinsInverse[];

	private List<Integer> J;
	private List<Integer> K;

	public Decodage(Codage codage, Probleme probleme) {
		super();
		this.codage = codage;
		this.probleme = probleme;
		this.permutationPlusInverse = inversePermutation(codage.getPermutationPlus());
		this.permutationMoinsInverse = inversePermutation(codage.getPermutationMoins());
	}

	public int[] getPermutationPlusInverse() {
		return permutationPlusInverse;
	}

	public int[] getPermutationMoinsInverse() {
		return permutationMoinsInverse;
	}

	public int[] inversePermutation(int[] permutation) {
		int permutationInverse[] = new int[permutation.length];
		int nb = 0;
		for (int i : permutation) {
			permutationInverse[i] = nb;
			nb++;
		}
		return permutationInverse;
	}

	public void calculeJK(int indice) {
		int ipp = permutationPlusInverse[indice];
		int ipm = permutationMoinsInverse[indice];
		J = new LinkedList<>();
		K = new LinkedList<>();
		for (int j = 0; j < permutationPlusInverse.length; j++) {
			if (permutationPlusInverse[j] < ipp && permutationMoinsInverse[j] < ipm)
				J.add(j);
			if (permutationPlusInverse[j] > ipp && permutationMoinsInverse[j] < ipm)
				K.add(j);
		}
	}

	public Solution decoder() {

		Solution solution = new Solution(permutationPlusInverse.length, probleme);

		for (int i = 0; i < permutationPlusInverse.length; i++) {
			calculeJK(i);
			if (J.isEmpty())
				solution.setX(i, 0);
			else
				solution.setX(i, solution.maxX(J));
			if (K.isEmpty())
				solution.setY(i, 0);
			else
				solution.setY(i, solution.maxY(K));
		}
		solution.calculeHauteur();
		return solution;
	}
}
