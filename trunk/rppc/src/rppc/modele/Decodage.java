package rppc.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Decodage {

	private Probleme probleme;
	private int permutationPlusInverse[];
	private int permutationMoinsInverse[];

	private List<ListeIndexe> J;
	private List<ListeIndexe> K;

	public Decodage(Codage codage, Probleme probleme) {
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
		J.add(new ListeIndexe(indice));
		K.add(new ListeIndexe(indice));
		for (int j = 0; j < permutationPlusInverse.length; j++) {
			if (permutationPlusInverse[j] < ipp && permutationMoinsInverse[j] < ipm)
				J.get(indice).add(j);
			if (permutationPlusInverse[j] > ipp && permutationMoinsInverse[j] < ipm)
				K.get(indice).add(j);
		}
	}

	public Solution decoder() {

		K = new LinkedList<>();
		J = new LinkedList<>();
		Solution solution = new Solution(probleme);

		for (int i = 0; i < permutationPlusInverse.length; i++)
			calculeJK(i);

		Comparator<List<Integer>> c = new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return Integer.compare(o1.size(), o2.size());
			}
		};

		Collections.sort(K, c);
		Collections.sort(J, c);

		for (int i = 0; i < J.size(); i++) {
			if (J.get(i).isEmpty())
				solution.setX(J.get(i).getIndice(), 0);
			else
				solution.setX(J.get(i).getIndice(), solution.maxX(J.get(i)));
		}

		for (int i = 0; i < K.size(); i++) {
			if (K.get(i).isEmpty())
				solution.setY(K.get(i).getIndice(), 0);
			else
				solution.setY(K.get(i).getIndice(), solution.maxY(K.get(i)));
		}

		solution.calculeHauteur();
		return solution;
	}

}

class ListeIndexe extends ArrayList<Integer> {

	private final int indice;

	public ListeIndexe(int indice) {
		this.indice = indice;
	}

	public int getIndice() {
		return indice;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + indice + ")";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
